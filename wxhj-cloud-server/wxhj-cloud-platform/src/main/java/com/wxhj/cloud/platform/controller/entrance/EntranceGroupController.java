/**
 * @className EntranceGroupController.java
 * @admin jwl
 * @date 2020年1月13日 上午10:04:37
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
import com.wxhj.cloud.feignClient.business.EntranceGroupClient;
import com.wxhj.cloud.feignClient.business.request.ListEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.EntranceGroupVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className EntranceGroupController.java
 * @admin jwl
 * @date 2020年1月13日 上午10:04:37
 */
@Api(tags = "通行组接口")
@RequestMapping("/entranceManage/entranceGroup")
@RestController
public class EntranceGroupController {
	@Resource
	EntranceGroupClient entranceGroupClient;
	
	@ApiOperation(value = "获取通行规则",response=EntranceGroupVO.class)
	@PostMapping("/listEntranceGroup")
	@LcnTransaction
	public WebApiReturnResultModel listEntranceGroup(
			@Validated @RequestBody ListEntranceGroupRequestDTO listEntranceGroup) {
		return entranceGroupClient.listEntranceGroup(listEntranceGroup);
	}
	
	@ApiOperation(value = "编辑通行规则")
	@PostMapping("/submitEntranceGroup")
	@LcnTransaction
	public WebApiReturnResultModel submitEntranceGroup(
			@Validated @RequestBody SubmitEntranceGroupRequestDTO submitEntranceGroupRequest) {
		return entranceGroupClient.submitEntranceGroup(submitEntranceGroupRequest);
	}
	
	@PostMapping("/importMapEntrAuth")
	public WebApiReturnResultModel importMapEntrAuth(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return entranceGroupClient.importMapEntrAuth(commonIdRequest);
	}
	
	@ApiOperation(value = "删除选中通行规则")
	@PostMapping("/deleteEntranceGroup")
	@LcnTransaction
	public WebApiReturnResultModel deleteEntranceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return entranceGroupClient.deleteEntranceGroup(commonIdRequest);
	}

	@ApiOperation(value = "根据编号查询通行规则")
	@PostMapping("/selectEntranceGroup")
	@LcnTransaction
	public WebApiReturnResultModel selectEntranceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return entranceGroupClient.selectEntranceGroup(commonIdRequest);
	}

}
