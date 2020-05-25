package com.wxhj.cloud.account.controller;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.account.domain.OrganizeCardPriorityDO;
import com.wxhj.cloud.account.service.OrganizeCardPriorityService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.OrganizeCardPriorityClient;
import com.wxhj.cloud.feignClient.account.vo.OrganizeCardPriorityVO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.OrganizeCardPriorityReOrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/5/21 5:03 下午
 */
@RestController
@Api(tags = "组织卡优先级管理")
@RequestMapping("/organizeCardPriority")
public class OrganizeCardPriorityController implements OrganizeCardPriorityClient {

    @Resource
    OrganizeCardPriorityService organizeCardPriorityService;
    @Resource
    Mapper beanMapper;

    @Override
    @PostMapping("/reorder")
    @ApiOperation("对卡的优先级重排序")
    public WebApiReturnResultModel reorder(@RequestBody @Validated OrganizeCardPriorityReOrderDTO organizeCardPriorityReOrder) {
        List<OrganizeCardPriorityVO> list = organizeCardPriorityReOrder.getList();
        List<OrganizeCardPriorityDO> collect = list.stream().map(q -> beanMapper.map(q, OrganizeCardPriorityDO.class)).collect(Collectors.toList());
        organizeCardPriorityService.reorder(organizeCardPriorityReOrder.getOrganizeId(), collect);
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @PostMapping("/listOrganizeCardPriority")
    @ApiOperation(value = "获取组织的卡优先级", response = OrganizeCardPriorityVO.class)
    public WebApiReturnResultModel listOrganizeCardPriority(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
        List<OrganizeCardPriorityDO> organizeCardPriorities = organizeCardPriorityService.listByOrganizeId(commonOrganizeRequest.getOrganizeId());
        List<OrganizeCardPriorityVO> collect = organizeCardPriorities.stream().sorted(Comparator.comparingInt(OrganizeCardPriorityDO::getPriority))
                .map(q -> beanMapper.map(q, OrganizeCardPriorityVO.class)).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(collect);
    }
}
