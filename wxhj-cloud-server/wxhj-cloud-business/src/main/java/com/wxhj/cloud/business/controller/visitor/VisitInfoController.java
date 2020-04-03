/**
 * 
 */
package com.wxhj.cloud.business.controller.visitor;

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
import com.wxhj.cloud.business.domain.VisitInfoDO;
import com.wxhj.cloud.business.service.visitor.VisitInfoService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.VisitInfoClient;
import com.wxhj.cloud.feignClient.business.request.VisitInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.VisitInfoListVO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: VisitInfoController.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:40:26
 */
@RestController
@RequestMapping("/visitInfo")
public class VisitInfoController implements VisitInfoClient {
	@Resource
	VisitInfoService visitInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("访客记录查询")
	@PostMapping("/visitInfoList")
	@Override
	public WebApiReturnResultModel visitInfoList(@RequestBody @Validated VisitInfoListRequestDTO vInfoListRequest) {
		PageInfo<VisitInfoDO> visitList = visitInfoService.selectByName(vInfoListRequest,
				vInfoListRequest.getOrganizeId(), vInfoListRequest.getBeginTime(), vInfoListRequest.getEndTime());

		List<VisitInfoListVO> visitResponseList = visitList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, VisitInfoListVO.class)).collect(Collectors.toList());

		try {
			visitResponseList = (List<VisitInfoListVO>) accessedRemotelyService.accessedOrganizeList(visitResponseList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(visitList,
				visitResponseList, new PageDefResponseModel());

		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

}
