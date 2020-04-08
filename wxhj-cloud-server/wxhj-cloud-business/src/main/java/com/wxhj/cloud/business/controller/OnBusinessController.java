package com.wxhj.cloud.business.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.vo.OnBusinessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 出差controller
 *
 * @author daxiong
 * @date 2020-04-07 14:29
 */
@RestController
@RequestMapping("/onBusiness")
@Api(tags = "出差管理")
public class OnBusinessController {

    @Resource
    OnBusinessService onBusinessService;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    @PostMapping("/submitOnBusiness")
    @ApiOperation(value = "编辑出差记录")
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness) {
        OnBusinessDO onBusinessDO = dozerBeanMapper.map(onBusiness, OnBusinessDO.class);
        String id;
        if (Strings.isNullOrEmpty(onBusinessDO.getId())) {
            // TODO 判断是否需要审核
            onBusinessDO.setStatus(ApproveStatusEnum.APPROVING.getCode());
            id = onBusinessService.insert(onBusinessDO);
        } else {
            onBusinessService.update(onBusinessDO);
            id = onBusinessDO.getId();
        }
        return WebApiReturnResultModel.ofSuccess(id);
    }

    @PostMapping("/listOnBusiness")
    @ApiOperation(value = "获取出差记录列表", response = OnBusinessVO.class)
    public WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        // 获取参数
        Integer status = listAskForLeaveRequest.getStatus();
        String nameValue = listAskForLeaveRequest.getNameValue();
        String organizeId = listAskForLeaveRequest.getOrganizeId();

        // 获取分页查询的信息
        PageInfo<OnBusinessDO> onBusinessDOPageInfo = onBusinessService.listPageByOrgIdAndStatusAndName(listAskForLeaveRequest, organizeId, nameValue, status);

        // 将分页信息中的data转成要返回的类型
        List<OnBusinessVO> onBusinessList = onBusinessDOPageInfo.getList().stream().map(m -> {
            OnBusinessVO onBusinessVO = dozerBeanMapper.map(m, OnBusinessVO.class);
            // 设置审核状态的中文描述
            onBusinessVO.setStatusName(ApproveStatusEnum.getByCode(onBusinessVO.getStatus()).getDesc());
            return onBusinessVO;
        }).collect(Collectors.toList());
        // 构造分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(onBusinessDOPageInfo,
                onBusinessList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @PostMapping("/deleteOnBusiness")
    @ApiOperation("删除出差记录")
    public WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        onBusinessService.delete(commonIdRequestDTO.getId());
        return WebApiReturnResultModel.ofSuccess();
    }
}
