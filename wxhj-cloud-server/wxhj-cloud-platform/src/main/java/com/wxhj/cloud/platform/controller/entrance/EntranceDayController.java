/**
 * @className EntranceDayController.java
 * @admin jwl
 * @date 2020年1月10日 下午2:45:58
 */
package com.wxhj.cloud.platform.controller.entrance;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.EntranceDayClient;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.EntranceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.EntranceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className EntranceDayController.java
 * @admin jwl
 * @date 2020年1月10日 下午2:45:58
 */
@RequestMapping("/entranceManage/entranceDay")
@RestController
@Api(tags = "通行时间段接口")
public class EntranceDayController {
	@Resource
	EntranceDayClient entranceDayClient;
	
	@ApiOperation(value = "查询时间段",response = EntranceDayVO.class)
	@PostMapping("/listEntranceDay")
	@LcnTransaction
	public WebApiReturnResultModel listEntranceDay(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		return entranceDayClient.listEntranceDay(commonListPageRequest);
	}
	
	@ApiOperation("编辑时间段")
	@PostMapping("/submitEntranceDay")
	@LcnTransaction
	public WebApiReturnResultModel submitEntranceDay(
			@Validated @RequestBody SubmitEntranceDayRequestDTO submitEntranceDay) {
		return entranceDayClient.submitEntranceDay(submitEntranceDay);
	}
	
	@ApiOperation("按编号获取时间段")
	@PostMapping("/selectEntranceDayById")
	@LcnTransaction
	public WebApiReturnResultModel selectEntranceDayById(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return entranceDayClient.selectEntranceDayById(commonIdRequest);
	}
	
	@ApiOperation("删除选中时间段")
	@PostMapping("/deleteEntranceDay")
	@LcnTransaction
	public WebApiReturnResultModel deleteEntranceDay(
			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
		return entranceDayClient.deleteEntranceDay(commonIdListRequest);
	}
	
	@ApiOperation(value="按组织编号获取时间段",response = EntranceDayAllVO.class)
	@PostMapping("/listEntranceDayOrganizeId")
	public WebApiReturnResultModel listEntranceDayOrganizeId(
			@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
		return entranceDayClient.listEntranceDayOrganizeId(commonOrganizeRequest);
	}
}
