package com.wxhj.cloud.platform.controller.other;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import com.wxhj.cloud.platform.domain.OrganizePayInfoDO;
import com.wxhj.cloud.platform.dto.request.SubmitOrganizePayInfoRequestDTO;
import com.wxhj.cloud.platform.service.OrganizePayInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/other/organizePay")
@Api(tags = "支付信息")
public class OrganizePayInfoController implements OrganizePayInfoClient {
    @Resource
    OrganizePayInfoService organizePayInfoService;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    @ApiOperation(value = "查询商户支付信息")
    @PostMapping("/organizePayInfo")
    @Override
    public WebApiReturnResultModel organizePayInfo(@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
        OrganizePayInfoDO organizePayInfo =
                organizePayInfoService.select(commonOrganizeRequest.getOrganizeId());
        return WebApiReturnResultModel.ofSuccess(organizePayInfo);
    }

    @ApiOperation(value = "编辑商户支付信息")
    @PostMapping("/submitOrganizePayInfo")
    public WebApiReturnResultModel submitOrganizePayInfo(@RequestBody SubmitOrganizePayInfoRequestDTO submitOrganizePayInfoRequest) {
        OrganizePayInfoDO organizePayInfo = dozerBeanMapper.map(submitOrganizePayInfoRequest, OrganizePayInfoDO.class);
        if (Strings.isNullOrEmpty(organizePayInfo.getId())) {
            organizePayInfo.setId(submitOrganizePayInfoRequest.getOrganizeId());
            organizePayInfoService.insert(organizePayInfo);
        } else {
            organizePayInfoService.update(organizePayInfo);
        }
        return WebApiReturnResultModel.ofSuccess();


    }

}