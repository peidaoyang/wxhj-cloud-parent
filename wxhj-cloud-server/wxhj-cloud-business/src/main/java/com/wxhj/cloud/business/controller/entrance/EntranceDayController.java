/**
 * @className EntranceDayController.java
 * @author jwl
 * @date 2020年1月10日 上午10:05:13
 */
package com.wxhj.cloud.business.controller.entrance;

import java.util.List;
import java.util.UUID;
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
import com.wxhj.cloud.business.domain.EntranceDayDO;
import com.wxhj.cloud.business.domain.EntranceDayRecDO;
import com.wxhj.cloud.business.dto.response.EntranceDayResponseDTO;
import com.wxhj.cloud.business.service.EntranceDayRecService;
import com.wxhj.cloud.business.service.EntranceDayService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.EntranceDayClient;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.EntranceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.EntranceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className EntranceDayController.java
 * @author jwl
 * @date 2020年1月10日 上午10:05:13
 */
@RestController
@RequestMapping("/entranceDay")
public class EntranceDayController implements EntranceDayClient {
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	EntranceDayService entranceDayService;
	@Resource
	EntranceDayRecService entranceDayRecService;

	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("查询时间段")
	@PostMapping("/listEntranceDay")
	@Override
	public WebApiReturnResultModel listEntranceDay(@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		
		PageInfo<EntranceDayDO> entranceDaylist =entranceDayService.listByNameAndOrgan(commonListPageRequest,
				commonListPageRequest.getOrganizeId(), commonListPageRequest.getNameValue());

		List<EntranceDayVO> entranceDayResponselist = entranceDaylist.getList().stream()
				.map(q -> dozerBeanMapper.map(q, EntranceDayVO.class)).collect(Collectors.toList());
		
		try {
			entranceDayResponselist = (List<EntranceDayVO>) accessedRemotelyService.accessedOrganizeList(entranceDayResponselist);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(entranceDaylist, entranceDayResponselist, new PageDefResponseModel());
		
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	@ApiOperation("编辑时间段")
	@PostMapping("/submitEntranceDay")
	@Override
	public WebApiReturnResultModel submitEntranceDay(
			@Validated @RequestBody SubmitEntranceDayRequestDTO submitEntranceDay) {
		EntranceDayDO entranceDay = dozerBeanMapper.map(submitEntranceDay, EntranceDayDO.class);
		List<EntranceDayRecDO> entranceDayRecList = submitEntranceDay.getListEntranceDayRec().stream()
				.map(q -> dozerBeanMapper.map(q, EntranceDayRecDO.class)).collect(Collectors.toList());
		String id;
		if (Strings.isNullOrEmpty(submitEntranceDay.getId())) {
			entranceDay.setId(UUID.randomUUID().toString());
			entranceDayService.insertCascade(entranceDay, entranceDayRecList);
			id = entranceDay.getId();
		} else {
			for (EntranceDayRecDO entranceDayRecTemp : entranceDayRecList) {
				entranceDayRecTemp.setEntranceId(submitEntranceDay.getId());
			}
			entranceDayService.updateCascade(entranceDay, entranceDayRecList);
			id = entranceDay.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}
//<<<<<<< .mine
//
//	@SuppressWarnings("unchecked")
//	@ApiOperation("查询时间段")
//	@PostMapping("/listEntranceDay")
//	@Override
//	public WebApiReturnResultModel listEntranceDay(@Validated @RequestBody ListEntranceDayRequestDTO listEntranceDay) {
//
//		PageInfo<EntranceDayDO> entranceDaylist = entranceDayService.listByNameAndOrgan(listEntranceDay,
//				listEntranceDay.getOrganizeId(), listEntranceDay.getNameValue());
//
//		List<EntranceDayVO> entranceDayResponselist = entranceDaylist.getList().stream()
//				.map(q -> dozerBeanMapper.map(q, EntranceDayVO.class)).collect(Collectors.toList());
//
//		try {
//			entranceDayResponselist = (List<EntranceDayVO>) accessedRemotelyService
//					.accessedOrganizeList(entranceDayResponselist);
//		} catch (WuXiHuaJieFeignError e) {
//			// TODO Auto-generated catch block
//			return e.getWebApiReturnResultModel();
//		}
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
//				.initPageResponseModel(entranceDaylist, entranceDayResponselist, new PageDefResponseModel());
//
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
//	}
//
//||||||| .r91
//
//
//	@SuppressWarnings("unchecked")
//	@ApiOperation("查询时间段")
//	@PostMapping("/listEntranceDay")
//	@Override
//	public WebApiReturnResultModel listEntranceDay(@Validated @RequestBody ListEntranceDayRequestDTO listEntranceDay) {
//		
//		PageInfo<EntranceDayDO> entranceDaylist =entranceDayService.listByNameAndOrgan(listEntranceDay,
//				listEntranceDay.getOrganizeId(), listEntranceDay.getNameValue());
//
//		List<EntranceDayVO> entranceDayResponselist = entranceDaylist.getList().stream()
//				.map(q -> dozerBeanMapper.map(q, EntranceDayVO.class)).collect(Collectors.toList());
//		
//		entranceDayResponselist = (List<EntranceDayVO>) accessedRemotelyService.accessedOrganizeList(entranceDayResponselist);
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
//				.initPageResponseModel(entranceDaylist, entranceDayResponselist, new PageDefResponseModel());
//		
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
//	}
//
//=======
//	
//>>>>>>> .r107
	@ApiOperation("删除选中时间段")
	@PostMapping("/deleteEntranceDay")
	@Override
	public WebApiReturnResultModel deleteEntranceDay(
			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
		entranceDayService.delete(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("按编号获取时间段")
	@PostMapping("/selectEntranceDayById")
	@Override
	public WebApiReturnResultModel selectEntranceDayById(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		EntranceDayDO entranceDay = entranceDayService.selectById(commonIdRequest.getId());
		List<EntranceDayRecDO> listEntranceDay = entranceDayRecService.listById(commonIdRequest.getId());
		EntranceDayResponseDTO entranceDayResponse = dozerBeanMapper.map(entranceDay, EntranceDayResponseDTO.class);
		entranceDayResponse.setListEntranceDayRec(listEntranceDay);
		return WebApiReturnResultModel.ofSuccess(entranceDayResponse);
	}

	@ApiOperation("按组织编号获取时间段")
	@Override
	@PostMapping("/listEntranceDayOrganizeId")
	public WebApiReturnResultModel listEntranceDayOrganizeId(
			@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<EntranceDayDO> listEntranceDay = entranceDayService
				.listByOrganizeId(commonOrganizeRequest.getOrganizeId());
		List<EntranceDayAllVO> listResponse = listEntranceDay.stream()
				.map(q -> new EntranceDayAllVO(q.getId(), q.getFullName(), q.getTimeDescribe()))
				.collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(listResponse);
	}
}
