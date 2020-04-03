/**
 * @className AttendanceGroupController.java
 * @admin jwl
 * @date 2019年12月13日 下午2:41:52
 */
package com.wxhj.cloud.business.controller.attenance;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AttendanceGroupDO;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAuthoritySceneDO;
import com.wxhj.cloud.business.service.AttendanceDayService;
import com.wxhj.cloud.business.service.AttendanceGroupRecService;
import com.wxhj.cloud.business.service.AttendanceGroupService;
import com.wxhj.cloud.business.service.CurrentAuthoritySceneService;
import com.wxhj.cloud.business.service.ViewAttendanceGroupAttendanceService;
import com.wxhj.cloud.business.vo.AttendanceGroupAllVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.AuthorityType;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.bo.ViewMapAccountAuthorityBO;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.AttendanceGroupClient;
import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.response.AttendanceGroupResponseDTO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceGroupVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className AttendanceGroupController.java
 * @admin jwl
 * @date 2019年12月13日 下午2:41:52
 */
@RestController
@RequestMapping("/attendanceGroup")
public class AttendanceGroupController implements AttendanceGroupClient {
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AttendanceGroupService attendanceGroupService;
	@Resource
	AttendanceGroupRecService attendanceGroupRecService;
	@Resource
	AttendanceDayService AttendanceDayService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	MapperClient mapperClient;
	@Resource
	ViewAttendanceGroupAttendanceService viewAttendanceGroupAttendanceService;
	@Resource
	CurrentAuthoritySceneService currentAuthoritySceneService;

	@PostMapping("/listAttendanceGroup")
	@ApiOperation("获取考勤组")
	@Override
	public WebApiReturnResultModel listAttendanceGroup(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		PageInfo<AttendanceGroupDO> attendanceGroupList = attendanceGroupService.listAttendanceGroup(
				commonListPageRequest, commonListPageRequest.getNameValue(), commonListPageRequest.getOrganizeId());
		List<ListAttendanceGroupVO> attendanceGroupReponseList = attendanceGroupList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ListAttendanceGroupVO.class)).collect(Collectors.toList());
		try {
			attendanceGroupReponseList = (List<ListAttendanceGroupVO>) accessedRemotelyService
					.accessedOrganizeList(attendanceGroupReponseList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(attendanceGroupList, attendanceGroupReponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@PostMapping("/submitAttendanceGroup")
	@ApiOperation("编辑考勤组")
	@Transactional
	@Override
	public WebApiReturnResultModel submitAttendanceGroup(
			@Validated @RequestBody SubmitAttendanceGroupRequestDTO submitAttendanceGroup) {
		try {
			// 权限组的group
			String authorizeId = submitAuthorityGroup(submitAttendanceGroup);
			// 本地映射到权限组的group
			AttendanceGroupDO attendanceGroup = dozerBeanMapper.map(submitAttendanceGroup, AttendanceGroupDO.class);
			attendanceGroup.setId(authorizeId);
			List<AttendanceGroupRecDO> attendanceGroupRecList = submitAttendanceGroup.getAttendanceGroupRecList()
					.stream().map(q -> {
						AttendanceGroupRecDO attendanceGroupRecTemp = dozerBeanMapper.map(q,
								AttendanceGroupRecDO.class);
						attendanceGroupRecTemp.setAttendanceGroupId(authorizeId);
						return attendanceGroupRecTemp;
					}).collect(Collectors.toList());
			//此处需要做事物回滚
			if (Strings.isNullOrEmpty(submitAttendanceGroup.getId())) {
				attendanceGroupService.insertCascade(attendanceGroup, attendanceGroupRecList);
			} else {
				attendanceGroupService.updateCascade(attendanceGroup, attendanceGroupRecList);
			}

		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		return WebApiReturnResultModel.ofSuccess();
	}

//<<<<<<< .mine
//||||||| .r83
//	private void insertAttendanceGroup(SubmitAttendanceGroupRequestDTO submitAttendanceGroup, String authorizeId) {
//		AttendanceGroupDO attendanceGroup = dozerBeanMapper.map(submitAttendanceGroup, AttendanceGroupDO.class);
//		attendanceGroup.setId(authorizeId);
//		List<AttendanceGroupRecDO> attendanceGroupRecList = submitAttendanceGroup.getAttendanceGroupRecList().stream()
//				.map(q -> {
//					AttendanceGroupRecDO attendanceGroupRecTemp = dozerBeanMapper.map(q, AttendanceGroupRecDO.class);
//					attendanceGroupRecTemp.setAttendanceGroupId(authorizeId);
//					return attendanceGroupRecTemp;
//				}).collect(Collectors.toList());
//
//		if (Strings.isNullOrEmpty(submitAttendanceGroup.getId())) {
//			attendanceGroupService.insertCascade(attendanceGroup, attendanceGroupRecList);
//		} else {
//			attendanceGroupService.updateCascade(attendanceGroup, attendanceGroupRecList);
//		}
//	}

//=======
//	private void insertAttendanceGroup(SubmitAttendanceGroupRequestDTO submitAttendanceGroup, String authorizeId) {
//		AttendanceGroupDO attendanceGroup = dozerBeanMapper.map(submitAttendanceGroup, AttendanceGroupDO.class);
//		attendanceGroup.setId(authorizeId);
//		List<AttendanceGroupRecDO> attendanceGroupRecList = submitAttendanceGroup.getAttendanceGroupRecList().stream()
//				.map(q -> {
//					AttendanceGroupRecDO attendanceGroupRecTemp = dozerBeanMapper.map(q, AttendanceGroupRecDO.class);
//					attendanceGroupRecTemp.setAttendanceGroupId(authorizeId);
//					return attendanceGroupRecTemp;
//				}).collect(Collectors.toList());
//
//		if (Strings.isNullOrEmpty(submitAttendanceGroup.getId())) {
//			attendanceGroupService.insertCascade(attendanceGroup, attendanceGroupRecList);
//		} else {
//			attendanceGroupService.updateCascade(attendanceGroup, attendanceGroupRecList);
//		}
//	}

	private String submitAuthorityGroup(SubmitAttendanceGroupRequestDTO submitAttendanceGroup)
			throws WuXiHuaJieFeignError {
		WebApiReturnResultModel webApiReturnResultModel;
		// 权限组的group
		SubmitAuthorityGroupInfoRequestDTO submit = dozerBeanMapper.map(submitAttendanceGroup,
				SubmitAuthorityGroupInfoRequestDTO.class);
		submit.setType(AuthorityType.ATTENDANCE.getCode());

		webApiReturnResultModel = authorityGroupClient.submitAuthorityGroupInfo(submit);
		String authorizeId = FeignUtil.formatClass(webApiReturnResultModel, String.class);
		return authorizeId;
	}

	@PostMapping("/deleteAttendanceGroup")
	@ApiOperation("删除选中考勤组")
	@Override
	public WebApiReturnResultModel deleteAttendanceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		//此处需要做事物回滚
		attendanceGroupService.deleteCascade(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("增加应用考勤权限信息")
	@PostMapping("/insertCurrentAttendance")
	@Override
	public WebApiReturnResultModel insertCurrentAttendance(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {

		WebApiReturnResultModel apiReturnResultModel = mapperClient.listViewMapAuthAccountByAuthId(commonIdRequest);
		List<ViewMapAccountAuthorityBO> viewMapAccountAuthorityList = null;
		List<String> sceneIdList = null;
		try {
			viewMapAccountAuthorityList = FeignUtil.formatArrayClass(apiReturnResultModel,
					ViewMapAccountAuthorityBO.class);

			apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthScene(commonIdRequest);
			sceneIdList = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		List<CurrentAccountAuthorityDO> listAccountAuth = viewMapAccountAuthorityList.stream()
				.map(q -> dozerBeanMapper.map(q, CurrentAccountAuthorityDO.class)).collect(Collectors.toList());
		List<CurrentAuthoritySceneDO> listScene = sceneIdList.stream()
				.map(q -> new CurrentAuthoritySceneDO(null, commonIdRequest.getId(), q)).collect(Collectors.toList());

		currentAuthoritySceneService.deleteCascade(commonIdRequest.getId());
		// listScene
		currentAuthoritySceneService.insertListCascade(listScene, listAccountAuth, commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@PostMapping("/listAllAttendGroup")
	@ApiOperation("按组织编号获取考勤组")
	@Override
	public WebApiReturnResultModel listAllAttendGroup(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<AttendanceGroupDO> attendance = attendanceGroupService.listAll(commonOrganizeRequest.getOrganizeId());
		List<AttendanceGroupAllVO> listAll = attendance.stream()
				.map(q -> new AttendanceGroupAllVO(q.getId(), q.getFullName())).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(listAll);
	}

	@PostMapping("/selectAttendanceById")
	@ApiOperation("按编号获取考勤组")
	@Override
	public WebApiReturnResultModel selectAttendanceById(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		AttendanceGroupDO attendanceGroup = attendanceGroupService.selectAttendanceGroupById(commonIdRequest.getId());

		List<AttendanceGroupRecBO> viewAttendanceGroupRecList = viewAttendanceGroupAttendanceService
				.listById(commonIdRequest.getId()).stream().map(q -> dozerBeanMapper.map(q, AttendanceGroupRecBO.class))
				.collect(Collectors.toList());

		AttendanceGroupResponseDTO attendanceResponse = new AttendanceGroupResponseDTO();
		attendanceResponse = dozerBeanMapper.map(attendanceGroup, AttendanceGroupResponseDTO.class);
		attendanceResponse.setAttendanceGroupRec(viewAttendanceGroupRecList);

		try {

			WebApiReturnResultModel apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthAccount(commonIdRequest);
			List<String> listAccount = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
			apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthScene(commonIdRequest);
			List<String> listScene = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
			attendanceResponse.setAccountList(listAccount);
			attendanceResponse.setSceneList(listScene);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		return WebApiReturnResultModel.ofSuccess(attendanceResponse);
	}
}
