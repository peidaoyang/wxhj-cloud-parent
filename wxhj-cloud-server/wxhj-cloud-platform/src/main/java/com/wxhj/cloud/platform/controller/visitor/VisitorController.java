/**
 * 
 */
package com.wxhj.cloud.platform.controller.visitor;

import javax.annotation.Resource;

import com.wxhj.cloud.platform.dto.request.VisitorInfoOrgListRequestDTO;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.business.request.CheckVisRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitVisitorRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.VisitorInfoListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: VisitorController.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:40:15
 */
@RequestMapping("/visitManage/visitor")
@RestController
@Api(tags = "访客信息接口")
public class VisitorController {
	@Resource
	VisitorInfoClient visitorInfoClient;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	Mapper dozerBeanMapper;

	@PostMapping("/visitorInfoList")
	@ApiOperation(value = "查询访客信息", response = VisitorInfoListVO.class)
	@ApiResponse(code = 200, message = "请求成功", response = VisitorInfoListVO.class)
	@LcnTransaction
	public WebApiReturnResultModel visitorInfoList(
			@RequestBody @Validated VisitorInfoOrgListRequestDTO visitorInfoOrgList) {
		List<String> organizeIdList = sysOrganizeService.selectByParentIdRecursion(visitorInfoOrgList.getOrganizeId())
				.stream().map(q -> q.getId()).collect(Collectors.toList());
		organizeIdList.add(visitorInfoOrgList.getOrganizeId());

		VisitorInfoListRequestDTO visitorInfoListRequest = dozerBeanMapper.map(visitorInfoOrgList,VisitorInfoListRequestDTO.class);
		visitorInfoListRequest.setOrganizeIdList(organizeIdList);
		return visitorInfoClient.visitorInfoList(visitorInfoListRequest);
	}

	@PostMapping("/submitVisitor")
	@ApiOperation("新增/修改访客信息")
	@LcnTransaction
	public WebApiReturnResultModel submitVisitor(@RequestBody @Validated SubmitVisitorRequestDTO submitVisitorRequest) {
		return visitorInfoClient.submitVisitor(submitVisitorRequest);
	}

	@PostMapping("/checkVis")
	@ApiOperation("审核访客")
	@LcnTransaction
	public WebApiReturnResultModel checkVis(@RequestBody @Validated CheckVisRequestDTO checkVisRequest) {
		return visitorInfoClient.checkVis(checkVisRequest);
	}

	@PostMapping("/deleteVisitor")
	@ApiOperation("删除访客")
	@LcnTransaction
	public WebApiReturnResultModel deleteVisitor(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		return visitorInfoClient.deleteVisitor(commonIdListRequest);
	}
}
