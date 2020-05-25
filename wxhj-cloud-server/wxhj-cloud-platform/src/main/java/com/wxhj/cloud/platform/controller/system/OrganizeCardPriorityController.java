package com.wxhj.cloud.platform.controller.system;

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

/**
 * @author daxiong
 * @date 2020/5/21 5:55 下午
 */
@RestController
@Api(tags = "组织卡优先级管理")
@RequestMapping("/organizeCardPriority")
public class OrganizeCardPriorityController {

    @Resource
    OrganizeCardPriorityClient organizeCardPriorityClient;

    @PostMapping("/reorder")
    @ApiOperation("对卡的优先级重排序")
    public WebApiReturnResultModel reorder(@RequestBody @Validated OrganizeCardPriorityReOrderDTO organizeCardPriorityReOrder) {
        return organizeCardPriorityClient.reorder(organizeCardPriorityReOrder);
    }

    @PostMapping("/listOrganizeCardPriority")
    @ApiOperation(value = "获取组织的卡优先级",response = OrganizeCardPriorityVO.class)
    public WebApiReturnResultModel listOrganizeCardPriority(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
        return organizeCardPriorityClient.listOrganizeCardPriority(commonOrganizeRequest);
    }
}
