package com.wxhj.cloud.business.controller.yearSchedule;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.NationLegalVocationDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.service.NationLegalVocationService;
import com.wxhj.cloud.business.service.OrganizeYearScheduleRecService;
import com.wxhj.cloud.business.service.OrganizeYearScheduleService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.OrganizeYearScheduleClient;
import com.wxhj.cloud.feignClient.business.request.SubmitOrganizeYearScheduleDTO;
import com.wxhj.cloud.feignClient.business.request.UseNationLegalVocationDTO;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/5/8 5:53 下午
 */
@RestController
@RequestMapping("/yearSchedule")
@Api(tags = "组织年度日期安排管理")
@Slf4j
public class OrganizeYearScheduleController implements OrganizeYearScheduleClient {

    @Resource
    OrganizeYearScheduleService organizeYearScheduleService;
    @Resource
    OrganizeYearScheduleRecService organizeYearScheduleRecService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    NationLegalVocationService nationLegalVocationService;

    @Override
    @ApiOperation(value = "获取组织年度日期安排列表", response = OrganizeYearScheduleVO.class)
    @PostMapping("/listYearSchedule")
    public WebApiReturnResultModel listYearSchedule(@RequestBody @Validated CommonListPageRequestDTO commonListPageRequest) {
        PageInfo<OrganizeYearScheduleDO> pageInfo = organizeYearScheduleService.listByOrganizeIdAndName(commonListPageRequest,
                commonListPageRequest.getOrganizeId(), commonListPageRequest.getNameValue());
        List<OrganizeYearScheduleVO> collect = pageInfo.getList().stream().
                map(q -> dozerBeanMapper.map(q, OrganizeYearScheduleVO.class)).collect(Collectors.toList());
        try {
            collect = (List<OrganizeYearScheduleVO>) accessedRemotelyService.accessedOrganizeList(collect);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
                pageInfo, collect, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @Override
    @ApiOperation(value = "获取所有的组织年度日期安排列表", response = OrganizeYearScheduleVO.class)
    @PostMapping("/listAllYearSchedule")
    public WebApiReturnResultModel listAllYearSchedule(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
        List<OrganizeYearScheduleDO> list = organizeYearScheduleService.listAllByOrganizeId(commonOrganizeRequest.getOrganizeId());
        List<OrganizeYearScheduleVO> collect = list.stream().
                map(q -> dozerBeanMapper.map(q, OrganizeYearScheduleVO.class)).collect(Collectors.toList());
        try {
            collect = (List<OrganizeYearScheduleVO>) accessedRemotelyService.accessedOrganizeList(collect);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess(collect);
    }

    @Override
    @ApiOperation(value = "获取某个节假日规则的所有日期类型")
    @PostMapping("/getTotalDayType")
    public WebApiReturnResultModel getTotalDayType(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        List<Integer> totalDayType = organizeYearScheduleRecService.getTotalDayType(commonIdRequest.getId());
        return WebApiReturnResultModel.ofSuccess(totalDayType);
    }

    @Override
    @ApiOperation(value = "更新组织年度日期安排")
    @PostMapping("/submitYearSchedule")
    public WebApiReturnResultModel submitYearSchedule(@RequestBody @Validated SubmitOrganizeYearScheduleDTO submitOrganizeYearSchedule) {
        OrganizeYearScheduleDO organizeYearScheduleDO = dozerBeanMapper.map(submitOrganizeYearSchedule, OrganizeYearScheduleDO.class);
        organizeYearScheduleService.submitOrganizeYearScheduleCascade(organizeYearScheduleDO, submitOrganizeYearSchedule.getList());
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @ApiOperation(value = "删除组织年度日期安排")
    @PostMapping("/deleteYearSchedule")
    public WebApiReturnResultModel deleteYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        organizeYearScheduleService.deleteOrganizeYearSchedule(commonIdRequestDTO.getId());
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @ApiOperation(value = "应用国家法定节假日", response = OrganizeYearScheduleRecVO.class)
    @PostMapping("/useNationLegalVocation")
    public WebApiReturnResultModel useNationLegalVocation(@RequestBody @Validated UseNationLegalVocationDTO useNationLegalVocation) {
        // 获取国家法定节假日
        List<NationLegalVocationDO> nationLegalVocations = nationLegalVocationService.listNationLegalVocation(
                useNationLegalVocation.getBeginTime(), useNationLegalVocation.getEndTime());

        List<OrganizeYearScheduleRecVO> collect = nationLegalVocations.stream()
                .map(q -> dozerBeanMapper.map(q, OrganizeYearScheduleRecVO.class)).collect(Collectors.toList());

        return WebApiReturnResultModel.ofSuccess(collect);
    }

    @Override
    @ApiOperation(value = "编辑组织年度日期安排", response = SubmitOrganizeYearScheduleDTO.class)
    @PostMapping("/editYearSchedule")
    public WebApiReturnResultModel editYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        OrganizeYearScheduleDO organizeYearSchedule = organizeYearScheduleService.selectByPrimaryKey(commonIdRequestDTO.getId());
        SubmitOrganizeYearScheduleDTO submitOrganizeYearSchedule = dozerBeanMapper.map(organizeYearSchedule, SubmitOrganizeYearScheduleDTO.class);

        List<OrganizeYearScheduleRecDO> organizeYearScheduleRecs = organizeYearScheduleRecService.listByRefId(commonIdRequestDTO.getId());
        List<OrganizeYearScheduleRecVO> yearScheduleRecs = organizeYearScheduleRecs.stream()
                .map(q -> dozerBeanMapper.map(q, OrganizeYearScheduleRecVO.class)).collect(Collectors.toList());

        submitOrganizeYearSchedule.setList(yearScheduleRecs);
        return WebApiReturnResultModel.ofSuccess(submitOrganizeYearSchedule);
    }

}
