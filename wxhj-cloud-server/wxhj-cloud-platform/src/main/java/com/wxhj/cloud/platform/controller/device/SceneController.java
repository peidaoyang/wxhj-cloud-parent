/** 
 * @fileName: SceneManage.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:02:57 
 */

package com.wxhj.cloud.platform.controller.device;

import java.util.ArrayList;
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
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.vo.AuthorityBySceneIdVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.SceneClient;
//import com.wxhj.cloud.feignClient.platform.request.ListSceneByIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.request.ListSceneInfoByIdRequestDTO;
import com.wxhj.cloud.feignClient.platform.response.SceneVO;
import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;
import com.wxhj.cloud.platform.domain.SceneInfoDO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.domain.view.ViewSceneInfoDO;
import com.wxhj.cloud.platform.dto.request.SubmitSceneInfoRequestDTO;
import com.wxhj.cloud.platform.service.SceneInfoService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.service.ViewSceneInfoService;
import com.wxhj.cloud.platform.transaction.service.SceneTransactionService;
import com.wxhj.cloud.platform.vo.ListScenePageVO;
import com.wxhj.cloud.platform.vo.OptionalSceneListByOrgVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className SceneManage.java
 * @author pjf
 * @date 2019年11月13日 上午10:02:57
 */

@RestController
@RequestMapping("/device/scene")
@Api(tags = "场景接口")
public class SceneController implements SceneClient {
	@Resource
	SceneInfoService sceneInfoService;
	@Resource
	ViewSceneInfoService viewSceneInfoService;
	@Resource
	SceneTransactionService sceneTransactionService;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	MapperClient mapperClient;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AuthorityGroupClient authorityGroupClient;

	@ApiOperation(value="分页查询场景",response = ViewSceneInfoDO.class)
	@PostMapping("/listScenePage")
	public WebApiReturnResultModel listScenePage(
			@Validated @RequestBody() CommonListPageRequestDTO commonListPageRequest) throws WuXiHuaJieFeignError {
		PageInfo<ViewSceneInfoDO> listPage = viewSceneInfoService.listByOrganizeIdAndScenceNamePage(
				commonListPageRequest, commonListPageRequest.getOrganizeId(), commonListPageRequest.getNameValue());
		List<ListScenePageVO> listScene = listPage.getList().stream().map(q-> dozerBeanMapper.map(q, ListScenePageVO.class)).collect(Collectors.toList());
		List<String> sceneIdList = listScene.stream().map(q-> q.getId()).collect(Collectors.toList());
		
		List<AuthorityBySceneIdVO> authorityList = FeignUtil.formatArrayClass(authorityGroupClient
				.authorityBySceneId(new CommonIdListRequestDTO(sceneIdList)), AuthorityBySceneIdVO.class);
		
		listScene.forEach(q ->{
			List<AuthorityBySceneIdVO> authorityListTemp = authorityList.stream().filter(p-> p.getSceneId().equals(q.getId())).collect(Collectors.toList());
			q.setAuthorityList(authorityListTemp);
		});
		
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
				listScene, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	@ApiOperation("修改和编辑场景")
	@PostMapping("/submitSceneInfo")
	public WebApiReturnResultModel submitSceneInfo(
			@Validated @RequestBody() SubmitSceneInfoRequestDTO submitSceneInfoRequest) {
		return sceneTransactionService.submitSceneInfoExecute(submitSceneInfoRequest);
	}
	
	@ApiOperation("删除场景")
	@PostMapping("/deleteSceneInfo")
	public WebApiReturnResultModel deleteSceneInfo(
			@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
		return sceneTransactionService.deleteSceneInfoExecute(commonIdRequest.getId());
	}
	
	
	@ApiOperation(value="根据编号获取场景",response = SceneVO.class)
	@Override
	@PostMapping("/selectSceneInfoById")
	public WebApiReturnResultModel selectSceneInfoById(
			@Validated @RequestBody() ListSceneInfoByIdRequestDTO selectSceneInfoByIdRequest) {
		List<SceneVO> listScene = new ArrayList<>();
		selectSceneInfoByIdRequest.getId().forEach(q -> {
			SceneVO authorityScene = new SceneVO();
			authorityScene.setSceneName(sceneInfoService.selectById(q).getSceneName());
			authorityScene.setSceneId(q);
			listScene.add(authorityScene);
		});
		return WebApiReturnResultModel.ofSuccess(listScene);
	}
	
	@ApiOperation(value="根据场景id获取场景信息",response=SceneInfoDO.class)
	@PostMapping("/listSceneByIdList")
	@Override
	public WebApiReturnResultModel listSceneByIdList(
			@RequestBody CommonIdListRequestDTO commonIdListRequest) {
		List<SceneInfoDO> listByIdList = sceneInfoService.listByIdList(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess(listByIdList);
	}
	
	@ApiOperation(value="根据组织选择场景信息",response=DropDownListControlVO.class)
	@PostMapping("/optionalSceneList")
	public WebApiReturnResultModel optionalSceneList(
			@Validated @RequestBody() CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<SceneInfoDO> sceneInfoList = sceneInfoService.listByOrganizeId(commonOrganizeRequest.getOrganizeId());
		List<DropDownListControlVO> dropDownListControlList = sceneInfoList.stream()
				.map(q -> new DropDownListControlVO(q.getId(), q.getSceneName(), q.getOrganizeId()))
				.collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(dropDownListControlList);
	}
	
	@ApiOperation(value="获取当前组织及其子组织下所有的场景",response = OptionalSceneListByOrgVO.class)
	@PostMapping("/optionalSceneListByOrg")
	public WebApiReturnResultModel optionalSceneListByOrg(
			@Validated @RequestBody() CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<SysOrganizeDO> orgList = sysOrganizeService
				.selectByParentIdRecursion(commonOrganizeRequest.getOrganizeId());
		orgList.add(sysOrganizeService.selectById(commonOrganizeRequest.getOrganizeId()));

		List<OptionalSceneListByOrgVO> responseList = orgList.stream()
				.map(q -> dozerBeanMapper.map(q, OptionalSceneListByOrgVO.class)).collect(Collectors.toList());
		List<SceneInfoDO> sceneInfoList = sceneInfoService.select();

		responseList.forEach(q -> {
			List<SceneInfoDO> p = sceneInfoList.stream().filter(k -> k.getOrganizeId().equals(q.getId()))
					.collect(Collectors.toList());
			if (p.size() > 0) {
				q.setSceneList(p);
			}
		});

		responseList = responseList.stream().filter(q -> q.getSceneList() != null && q.getSceneList().size() > 0)
				.collect(Collectors.toList());
		if (responseList.size() == 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_DATA);
		}
		return WebApiReturnResultModel.ofSuccess(responseList);
	}
	
	
	
	//预留
//	@ApiOperation("已权限组列表")
//	@PostMapping("/selectedAuthList")
//	@LcnTransaction
//	public WebApiReturnResultModel selectedAuthList(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
//
//		WebApiReturnResultModel webApiReturnResultModel = mapperClient.listBySceneIdFromMapAuthScene(commonIdRequest);
//		if (!webApiReturnResultModel.resultSuccess()) {
//			return webApiReturnResultModel;
//		}
//		String jsonStr = JSON.toJSONString(webApiReturnResultModel.getData());
//		List<MapAuthoritySceneVO> mapAuthoritySceneList = JSON.parseArray(jsonStr, MapAuthoritySceneVO.class);
//		List<String> authorityGroupIdList = mapAuthoritySceneList.stream().map(q -> q.getAuthorityGroupId())
//				.collect(Collectors.toList());
//		return WebApiReturnResultModel.ofSuccess(authorityGroupIdList);
//	}
//
//
//
//
//
}
