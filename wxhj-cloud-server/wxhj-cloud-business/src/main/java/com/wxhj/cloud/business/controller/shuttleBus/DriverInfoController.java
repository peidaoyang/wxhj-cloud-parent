package com.wxhj.cloud.business.controller.shuttleBus;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.DriverInfoDO;
import com.wxhj.cloud.business.service.shuttleBus.DriverInfoService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.DriverInfoClient;
import com.wxhj.cloud.feignClient.business.request.DriverListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitDriverInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.DriverListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: DriverInfoController.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:13:32
 */
@RestController
@RequestMapping("/driver")
public class DriverInfoController implements DriverInfoClient {
	@Resource
	DriverInfoService driverInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@PostMapping("/driverList")
	@ApiOperation("获取司机信息")
	@Override
	public WebApiReturnResultModel driverList(@RequestBody @Validated DriverListRequestDTO driverList) {
		PageInfo<DriverInfoDO> driverInfoList=driverInfoService.select(driverList, driverList.getOrganizeId(),
					driverList.getNameValue(),driverList.getType());

		List<DriverListVO> driverResponseList = driverInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DriverListVO.class)).collect(Collectors.toList());

		try {
			driverResponseList = (List<DriverListVO>) accessedRemotelyService
					.accessedOrganizeList(driverResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(driverInfoList, driverResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);

	}

	@PostMapping("/driverInfoById")
	@ApiOperation("根据id获取司机信息")
	@Override
	public WebApiReturnResultModel driverInfoById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofSuccess(driverInfoService.select(commonIdRequest.getId()));
	}

	@PostMapping("/submitDriverInfo")
	@ApiOperation("新增/修改 司机信息")
	@Override
	public WebApiReturnResultModel submitDriverInfo(
			@RequestBody @Validated SubmitDriverInfoRequestDTO submitDriverInfo) {
		DriverInfoDO driverInfoDO = dozerBeanMapper.map(submitDriverInfo, DriverInfoDO.class);
		String id;
		if (Strings.isNullOrEmpty(submitDriverInfo.getId())) {
			id = driverInfoService.insert(driverInfoDO, submitDriverInfo.getUserId());
		} else {
			driverInfoService.update(driverInfoDO);
			id = driverInfoDO.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@PostMapping("/deleteDriver")
	@ApiOperation("删除信息")
	@Override
	public WebApiReturnResultModel deleteDriver(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		driverInfoService.delete(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}
}
