/**
 * 
 */
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
import com.wxhj.cloud.business.domain.RideInfoDO;
import com.wxhj.cloud.business.dto.response.RideInfoListByAccoutIdResponseDTO;
import com.wxhj.cloud.business.service.shuttleBus.RideInfoService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.RideInfoClient;
import com.wxhj.cloud.feignClient.business.request.RideInfoListByAccoutIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.RideInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.RideInfoVO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RideInfoController.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:25:31
 */
@RestController
@RequestMapping("/ride")
public class RideInfoController implements RideInfoClient {
	@Resource
	RideInfoService rideInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("乘车记录查询")
	@PostMapping("/rideInfoList")
	@Override
	public WebApiReturnResultModel rideInfoList(@RequestBody @Validated RideInfoListRequestDTO rideInfoListRequest) {
		PageInfo<RideInfoDO> pageInfoList = rideInfoService.listPage(rideInfoListRequest, rideInfoListRequest.getOrganizeId(),
					rideInfoListRequest.getNameValue(),rideInfoListRequest.getType() ,rideInfoListRequest.getBeginTime(),
					rideInfoListRequest.getEndTime());
	
		List<RideInfoVO> pageInfoResponseList = pageInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, RideInfoVO.class)).collect(Collectors.toList());
		try {
			pageInfoResponseList = (List<RideInfoVO>) accessedRemotelyService
					.accessedOrganizeList(pageInfoResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageInfoList,
				pageInfoResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("通过用户id查询乘车记录")
	@PostMapping("/rideInfoListByAccoutId")
	@Override
	public WebApiReturnResultModel rideInfoListByAccoutId(
			@RequestBody @Validated RideInfoListByAccoutIdRequestDTO rideInfoListByAccoutIdRequest) {
		PageInfo<RideInfoDO> rideInfoList = rideInfoService.select(rideInfoListByAccoutIdRequest,
				rideInfoListByAccoutIdRequest.getAccountId(), rideInfoListByAccoutIdRequest.getBeginTime(),
				rideInfoListByAccoutIdRequest.getEndTime());
		List<RideInfoListByAccoutIdResponseDTO> rideRepList = rideInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, RideInfoListByAccoutIdResponseDTO.class)).collect(Collectors.toList());
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(rideInfoList,
				rideRepList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
}
