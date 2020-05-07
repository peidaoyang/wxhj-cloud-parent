/**
 * @fileName: MapListenListController.java
 * @author: pjf
 * @date: 2019年10月31日 下午2:30:43
 */

package com.wxhj.cloud.account.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.domain.view.ViewMapAccountAuthorityDO;
import com.wxhj.cloud.account.service.*;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.platform.SceneClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

//import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneRequestDTO;
//import com.wxhj.cloud.feignClient.account.request.MapAuthoritySceneRequestDTO;
//import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthRequestDTO;

/**
 * @author pjf
 * @className MapListenListController.java
 * @date 2019年10月31日 下午2:30:43
 */

@RestController
@RequestMapping("/mapper")
@Slf4j
public class MapperController implements MapperClient {
    @Resource
    MapListenListService mapListenListService;
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;
    @Resource
    ViewMapAccountAuthorityService viewMapAccountAuthorityService;
    @Resource
    MapAuthoritySceneService mapAuthoritySceneService;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    SceneClient sceneClient;
    @Resource
    MapAuthorityScenePlusService mapAuthorityScenePlusService;
    @Resource
    MapAccountAuthorityPlusService mapAccountAuthorityPlusService;




    // 权限组选择人员
//	@ApiOperation("权限组选择用户")
//	@PostMapping("/submitMapAccountAuth")
//	@Override
//	public WebApiReturnResultModel submitMapAccountAuth(
//			@Validated @RequestBody SubmitMapAccountAuthRequestDTO mapAccountAuth) {
//		mapAccountAuthorityService.update(mapAccountAuth.getAuthorityId(), mapAccountAuth.getAccountIdList());
//		return WebApiReturnResultModel.ofSuccess();
//	}


//	@ApiOperation("插入MapAuthorityScene")
//	@PostMapping("/submitMapAuthorityScene")
//	@Override
//	public WebApiReturnResultModel submitMapAuthorityScene(
//			@Validated @RequestBody MapAuthoritySceneRequestDTO mapAuthoritySceneRequestDTO) {
//		MapAuthoritySceneDO mapAuthorityScene = dozerBeanMapper.map(mapAuthoritySceneRequestDTO,
//				MapAuthoritySceneDO.class);
//		mapAuthoritySceneService.insertCascade(mapAuthorityScene);
//		return WebApiReturnResultModel.ofSuccess(null);
//	}

/*	@ApiOperation("删除MapAuthorityScene")
	@PostMapping("/deleteMapAuthorityScene")
	@Override
	public WebApiReturnResultModel deleteMapAuthorityScene(
			@RequestBody DeleteMapAuthSceneRequestDTO deleteMapAuthSceneRequestDTO) {
		int deleteCount = mapAuthoritySceneService.deleteCascade(deleteMapAuthSceneRequestDTO.getAuthorityGroupId(),
				deleteMapAuthSceneRequestDTO.getSceneId());
		if (deleteCount <= 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NOT_MATCHING_RECORD);
		}
		return WebApiReturnResultModel.ofSuccess();
	}*/

    @Override
    @PostMapping("/submitAuthGroupIdListAndSceneId")
    public WebApiReturnResultModel submitAuthGroupIdListAndSceneId(@RequestBody
                                                                           AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequest) {
        mapAuthorityScenePlusService.update(0, authGroupIdListAndSceneIdRequest.getAuthGroupIdList(),
                authGroupIdListAndSceneIdRequest.getSceneId());
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @PostMapping("/deleteMapAuthSceneById")
    public WebApiReturnResultModel deleteMapAuthSceneById(
            @RequestBody DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest) {

        if (deleteMapAuthSceneByIdRequest.getDeleteType().equals(0)) {
            mapAuthoritySceneService.deleteCascade(null, deleteMapAuthSceneByIdRequest.getId());

        } else {
            mapAuthoritySceneService.deleteCascade(deleteMapAuthSceneByIdRequest.getId(), null);
        }
        return WebApiReturnResultModel.ofSuccess();
    }

//	@PostMapping("/listBySceneIdFromMapAuthScene")
//	@Override
//	public WebApiReturnResultModel listBySceneIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest) {
//		MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
//		mapAuthorityScene.setSceneId(commonIdRequest.getId());
//		List<MapAuthoritySceneDO> mapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthorityScene);
//		List<MapAuthSceneResponseDTO> mapResponse = mapAuthoritySceneList.stream()
//				.map(q -> dozerBeanMapper.map(q, MapAuthSceneResponseDTO.class)).collect(Collectors.toList());
//		return WebApiReturnResultModel.ofSuccess(mapResponse);
//	}

//	@ApiOperation(value = "按权限组编号获取权限用户map集合")
//	@PostMapping("/listByAuthFromMapAuthAccount")
//	@Override
//	public WebApiReturnResultModel listByAuthFromMapAuthAccount(
//			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
//		List<MapAccountAuthorityDO> listMap = mapAccountAuthorityService
//				.listByAuthorityIdList(commonIdListRequest.getIdList());
//		List<MapAccountAuthResponseDTO> listResponse = listMap.stream()
//				.map(q -> dozerBeanMapper.map(q, MapAccountAuthResponseDTO.class)).collect(Collectors.toList());
//		return WebApiReturnResultModel.ofSuccess(listResponse);
//	}

    @ApiOperation(value = "根据权限编号获取场景集合")
    @PostMapping("/listByAuthIdFromMapAuthScene")
    @LcnTransaction
    @Override
    public WebApiReturnResultModel listByAuthIdFromMapAuthScene(
            @Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
        List<MapAuthoritySceneDO> listMap = mapAuthoritySceneService.listByAuthorityId(commonIdRequest.getId());
        List<String> listScene = listMap.stream().map(q -> q.getSceneId()).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(listScene);
    }

    @ApiOperation(value = "根据权限编号获取用户集合")
    @PostMapping("/listByAuthIdFromMapAuthAccount")
    @Override
    public WebApiReturnResultModel listByAuthIdFromMapAuthAccount(
            @Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
        List<MapAccountAuthorityDO> listMap = mapAccountAuthorityService.listByAuthorityId(commonIdRequest.getId());

        List<String> list = listMap.stream().map(q -> q.getAccountId()).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(list);
    }

    @PostMapping("/listViewMapAuthAccountByAuthId")
    @Override
    public WebApiReturnResultModel listViewMapAuthAccountByAuthId(@RequestBody CommonIdRequestDTO commonIdRequest) {
        List<ViewMapAccountAuthorityDO> listMap = viewMapAccountAuthorityService
                .listByAuthorityId(commonIdRequest.getId());
        return WebApiReturnResultModel.ofSuccess(listMap);
    }

    @Override
    @PostMapping("/deleteMapAccountAuthByAccountId")
    public WebApiReturnResultModel deleteMapAccountAuthByAccountId(@RequestBody CommonIdRequestDTO commonIdRequest) {
        List<MapAccountAuthorityDO> deleteByAccountId = mapAccountAuthorityPlusService
                .deleteByAccountId(commonIdRequest.getId());
        return WebApiReturnResultModel.ofSuccess(deleteByAccountId);
    }

    @Override
    @PostMapping("/submitMapAccountAuthorityList")
    public WebApiReturnResultModel submitMapAccountAuthorityList(
            @RequestBody SubmitMapAccountAuthListRequestDTO submitMapAccountAuthListRequest) {
        for (MapAccountAuthorityBO mapAccountAuthorityTemp : submitMapAccountAuthListRequest
                .getMapAccountAuthorityList()) {
            MapAccountAuthorityDO mapAccountAuthority = dozerBeanMapper.map(mapAccountAuthorityTemp,
                    MapAccountAuthorityDO.class);
            mapAccountAuthorityService.insertCascade(mapAccountAuthority);
        }
        return WebApiReturnResultModel.ofSuccess();
    }

}
