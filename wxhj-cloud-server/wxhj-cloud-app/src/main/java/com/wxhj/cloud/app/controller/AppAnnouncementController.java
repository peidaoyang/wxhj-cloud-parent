package com.wxhj.cloud.app.controller;

import javax.annotation.Resource;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.AnnouncementClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "app公告相关业务")
@RestController
@RequestMapping("/appAnnouncement")
public class AppAnnouncementController {
	@Resource
	AnnouncementClient announcementClient;

	@ApiOperation("获取公告信息")
	@PostMapping("/appAnnouncementList")
	@LcnTransaction
	public WebApiReturnResultModel appAnnouncementList(@RequestBody @Validated CommonListPageRequestDTO commonListPageRequest) {
		return announcementClient.appAnnouncementList(commonListPageRequest);
	}

	@ApiOperation("根据id获取公告详情")
	@PostMapping("/selectById")
	@LcnTransaction
	public WebApiReturnResultModel selectById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
		return announcementClient.selectById(commonIdRequest);
	}


}
