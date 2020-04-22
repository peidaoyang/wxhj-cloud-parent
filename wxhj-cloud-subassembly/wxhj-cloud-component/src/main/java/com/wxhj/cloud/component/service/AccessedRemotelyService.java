/** 
 * @fileName: AccessedRemotelyService.java  
 * @author: pjf
 * @date: 2020年2月6日 下午3:17:29 
 */

package com.wxhj.cloud.component.service;

import java.util.List;

import com.wxhj.cloud.core.enums.PlatformEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.feignClient.bo.*;

/**
 * @className AccessedRemotelyService.java
 * @author pjf
 * @date 2020年2月6日 下午3:17:29   
*/
/**
 * @className AccessedRemotelyService.java
 * @author pjf
 * @date 2020年2月6日 下午3:17:29
 */
public interface AccessedRemotelyService {

	List<? extends IOrganizeSceneModel> accessedOrganizeSceneList(List<? extends IOrganizeSceneModel> organizeSceneList)
			throws WuXiHuaJieFeignError;

	List<? extends IOrganizeModel> accessedOrganizeList(List<? extends IOrganizeModel> organizeModelList)
			throws WuXiHuaJieFeignError;

	List<? extends IOrganizeChildrenOrganizeModel> accessedOrganizeChildrenList(
			List<? extends IOrganizeChildrenOrganizeModel> organizeChildrenOrgModelList) throws WuXiHuaJieFeignError;

//	List<? extends ISceneModel> accessedSceneList(List<? extends ISceneModel> sceneModelList)
//			throws WuXiHuaJieFeignError;

	List<? extends IPlatformEnumModel> accessedPlatformEnumList(
			List<? extends IPlatformEnumModel> platformEnumModelList, PlatformEnum platformEnum)
			throws WuXiHuaJieFeignError;

	List<? extends IOrganizeUserModel> accessedOrganizeUserList(
			List<? extends IOrganizeUserModel> organizeUserModelList) throws WuXiHuaJieFeignError;

	List<? extends IAccountOrganizeModel> accessedAccountOrganizeList(
			List<? extends IAccountOrganizeModel> organizeUserModelList) throws WuXiHuaJieFeignError;

	List<? extends IAuthoritySynchroModel> accessdAuthoritySynchroList(
			List<? extends IAuthoritySynchroModel> authoritySynchroModelList) throws WuXiHuaJieFeignError;
//	List<? extends IFaceImageModel> accessedFaceImageList(List<? extends IFaceImageModel> faceImageList)
//			throws WuXiHuaJieFeignError;
//	
//	  List<? extends IFaceImageModel> accessedFaceList(List<? extends
//	  IFaceImageModel> faceImageList);

}
