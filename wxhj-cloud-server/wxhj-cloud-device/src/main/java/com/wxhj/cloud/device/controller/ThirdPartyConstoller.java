/** 
 * @fileName: ThirdPartyConstoller.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:33:02 
 */

package com.wxhj.cloud.device.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.device.domain.DeviceRecordDO;
import com.wxhj.cloud.device.dto.request.AffirmRecordRequestDTO;
import com.wxhj.cloud.device.dto.request.ListRecordTypeRequestDTO;
import com.wxhj.cloud.device.service.DeviceRecordService;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.device.request.DlodDevGlobPraRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className ThirdPartyConstoller.java
 * @author pjf
 * @date 2020年2月11日 下午3:33:02   
*/
/**
 * @className ThirdPartyConstoller.java
 * @author pjf
 * @date 2020年2月11日 下午3:33:02
 */
@RestController
@RequestMapping("/thirdParty")
public class ThirdPartyConstoller {
	@Resource
	DeviceRecordService deviceRecordService;
	@Resource
	DeviceGlobalParameterClient deviceGlobalParameterClient;

	@ApiOperation(value = "第三方拉取记录接口")
	@PostMapping("/listRecordType")
	public WebApiReturnResultModel listRecordType(@Validated @RequestBody ListRecordTypeRequestDTO listRecordType) {

		return WebApiReturnResultModel.ofSuccess(
				deviceRecordService.listByRecordTypeAndOragnizeId(0, listRecordType.getOrganizeId(), listRecordType));
	}

	@ApiOperation(value = "第三方确认记录接口")
	@PostMapping("/affirmRecord")
	public WebApiReturnResultModel affirmRecord(@Validated @RequestBody AffirmRecordRequestDTO affiramRecord) {
		DeviceRecordDO deviceRecordDO = new DeviceRecordDO();
		deviceRecordDO.setDataState(2);
		deviceRecordService.updateByIdList(deviceRecordDO, affiramRecord.getId());

		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "全局参数下载")
	@PostMapping("/downloadDevGlo")
	public WebApiReturnResultModel downloadDevGlo(@Validated @RequestBody DlodDevGlobPraRequestDTO devGlobPr) {
		deviceGlobalParameterClient.downloadDevGlo(devGlobPr);
		return WebApiReturnResultModel.ofSuccess();
	}
}
