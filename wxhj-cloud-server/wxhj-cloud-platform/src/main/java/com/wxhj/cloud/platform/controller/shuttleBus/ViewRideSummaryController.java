/**
 * 
 */
package com.wxhj.cloud.platform.controller.shuttleBus;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.ViewRideSummaryClient;
import com.wxhj.cloud.feignClient.business.request.ViewRideSummaryListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.ViewRideSummaryListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: ViewRideSummaryController.java
 * @author: cya
 * @Date: 2020年2月6日 下午3:13:14 
 */
@RestController
@RequestMapping("/shuttleBusManage/viewRideSummary")
@Api(tags = "班次汇总")
public class ViewRideSummaryController {
	@Resource
	ViewRideSummaryClient viewRideSummaryClient;
	
	@ApiOperation(value="班次汇总分页查询",response=ViewRideSummaryListVO.class)
	@PostMapping("/viewRideSummaryList")
	@LcnTransaction
	public WebApiReturnResultModel viewRideSummaryList(@RequestBody @Validated ViewRideSummaryListRequestDTO viewRideSummaryListRequest) {
		return viewRideSummaryClient.viewRideSummaryList(viewRideSummaryListRequest);
	}
	
}
