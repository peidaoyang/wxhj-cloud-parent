/**
 * 
 */
package com.wxhj.cloud.app.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.business.request.SubmitVisitorRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoAppRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: VisitorController.java
 * @author: cya
 * @Date: 2020年2月11日 下午6:40:48 
 */
@Api(tags="访客相关接口")
@RestController
@RequestMapping("/visit")
public class VisitController {
	@Resource
	VisitorInfoClient visitorInfoClient;
	
	@ApiOperation("新增/修改访客信息")
	@PostMapping("/submitVisitor")
	public WebApiReturnResultModel submitVisitor(@RequestBody @Validated SubmitVisitorRequestDTO submitVisitorRequest) {
		return visitorInfoClient.submitVisitor(submitVisitorRequest);
	}
	
	@ApiOperation("访客信息查询")
	@PostMapping("/visitorInfoApp")
	public WebApiReturnResultModel visitorInfoApp(@RequestBody @Validated VisitorInfoAppRequestDTO visitorInfoAppRequest) {
		return visitorInfoClient.visitorInfoApp(visitorInfoAppRequest);
	}

}
