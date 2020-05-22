package com.wxhj.cloud.school.controller.dormitoryManage;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.AuthorityType;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.school.DormitoryClient;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitDormitoryRequestDTO;
import com.wxhj.cloud.feignClient.school.responseDTO.DormitoryInfoResponseDTO;
import com.wxhj.cloud.feignClient.school.vo.ListDormitoryByOrgIdVO;
import com.wxhj.cloud.feignClient.school.vo.ListDormitoryVO;
import com.wxhj.cloud.school.domain.DormitoryDO;
import com.wxhj.cloud.school.domain.view.ViewDormitoryTotalDO;
import com.wxhj.cloud.school.service.DormitoryService;
import com.wxhj.cloud.school.service.ViewDormitoryTotalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@RestController
@RequestMapping("/dormitory")
@Api(tags="楼栋设置")
public class DormitoryController implements DormitoryClient {
    @Resource
    DormitoryService dormitoryService;
    @Resource
    ViewDormitoryTotalService viewDormitoryTotalService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    AuthorityGroupClient authorityGroupClient;
    @Resource
    MapperClient mapperClient;

    @Resource
    Mapper mapper;

    @ApiOperation("分页查询楼栋设置")
    @PostMapping("/listDormitory")
    @Override
    public WebApiReturnResultModel listDormitory(@RequestBody @Validated CommonListPageRequestDTO commonListPage){
        PageInfo<ViewDormitoryTotalDO> pageList = viewDormitoryTotalService.list(commonListPage,commonListPage.getNameValue(),commonListPage.getOrganizeId());
        List<ListDormitoryVO> voList = pageList.getList().stream().map(q-> mapper.map(q, ListDormitoryVO.class)).collect(Collectors.toList());
        try {
            voList = (List<ListDormitoryVO>) accessedRemotelyService.accessedOrganizeList(voList);
            voList = (List<ListDormitoryVO>) accessedRemotelyService.accessdAuthoritySynchroList(voList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageList, voList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("根据id查询楼栋信息")
    @PostMapping("/dormitoryInfo")
    public WebApiReturnResultModel dormitoryInfo(@RequestBody @Validated CommonIdRequestDTO commonId){
        DormitoryInfoResponseDTO responseDTO = mapper.map(dormitoryService.select(commonId.getId()),DormitoryInfoResponseDTO.class);
        if(responseDTO == null){
            return WebApiReturnResultModel.ofSuccess(responseDTO);
        }
        try {
            WebApiReturnResultModel apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthAccount(commonId);
            apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthScene(commonId);
            List<String> listScene = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
            if(listScene !=null && listScene.size()>0){
                responseDTO.setSceneId(listScene.get(0));
            }
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess(responseDTO);
    }

    @ApiOperation("根据组织id查询楼栋列表")
    @PostMapping("/listDormitoryByOrgId")
    @Override
    public WebApiReturnResultModel listDormitoryByOrgId(@RequestBody @Validated CommonIdRequestDTO commonId){
        List<ListDormitoryByOrgIdVO> voList = dormitoryService.listDormitory(commonId.getId()).stream().map(q-> mapper.map(q,ListDormitoryByOrgIdVO.class)).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(voList);
    }

    @ApiOperation("新增/修改 楼栋")
    @PostMapping("/submitDormitory")
    @Override
    public WebApiReturnResultModel submitDormitory(@RequestBody @Validated SubmitDormitoryRequestDTO submitDormitory){
        String authorityId = null;
        try {
            authorityId = submitAuthorityGroupInfo(submitDormitory);
            insertEntranceGroup(authorityId, submitDormitory);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess();
    }

    private String submitAuthorityGroupInfo(SubmitDormitoryRequestDTO submitDormitory)
            throws WuXiHuaJieFeignError {
        SubmitAuthorityGroupInfoRequestDTO submit = mapper.map(submitDormitory,SubmitAuthorityGroupInfoRequestDTO.class);
        submit.setType(AuthorityType.SCHOOL.getCode());
        //楼栋只能对应一个场景
        submit.setSceneIdList(Arrays.asList(submitDormitory.getSceneId()));
        submit.setFullName(submitDormitory.getNumber()+"楼栋");
        WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient.submitAuthorityGroupInfo(submit);
        return FeignUtil.formatClass(webApiReturnResultModel, String.class);
    }

    private void insertEntranceGroup(String id, SubmitDormitoryRequestDTO submitDormitory) {
        DormitoryDO dormitory = mapper.map(submitDormitory, DormitoryDO.class);
        dormitory.setId(id);
        if (Strings.isNullOrEmpty(submitDormitory.getId())) {
            dormitoryService.insert(dormitory);
        } else {
            dormitoryService.update(dormitory);
        }
    }

    @ApiOperation("删除楼栋")
    @PostMapping("/deleteDormitory")
    @Override
    public WebApiReturnResultModel deleteDormitory(@RequestBody @Validated CommonIdRequestDTO commonId){
        dormitoryService.deleteCascade(commonId.getId());
        return WebApiReturnResultModel.ofSuccess();
    }

}
