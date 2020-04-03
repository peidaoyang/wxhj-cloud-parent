/**
 * 
 */
package com.wxhj.cloud.platform.controller.visitor;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.VisitInfoClient;
import com.wxhj.cloud.feignClient.business.request.VisitInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.VisitInfoListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @ClassName: VisitInfoController.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:51:14
 */
@RestController
@RequestMapping("/visitManage/visit")
@Api(tags = "访客记录接口")
public class VisitInfoController {
	@Resource
	VisitInfoClient visitInfoClient;

	@ApiOperation(value = "访客记录查询(分页接口)", response = VisitInfoListVO.class, responseContainer = "List")
	@ApiResponse(code = 200,message = "请求成功",response=VisitInfoListVO.class)
	@PostMapping("/visitInfoList")
	public WebApiReturnResultModel visitInfoList(@RequestBody @Validated VisitInfoListRequestDTO visitInfoListRequest) {
		return visitInfoClient.visitInfoList(visitInfoListRequest);
	}
}
