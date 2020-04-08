///**
// * @fileName: FcaeChangeSynchHandle.java
// * @author: pjf
// * @date: 2019年11月21日 上午11:25:16
// */
//
//package com.wxhj.cloud.faceServer.handle;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.annotation.Resource;
//
//import com.wxhj.cloud.faceServer.service.FaceAccountInfoService;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.google.common.base.Strings;
//import com.wxhj.cloud.component.job.AbstractAsynJobHandle;
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
//import com.wxhj.cloud.faceServer.domian.FaceAccountInfoDO;
//import com.wxhj.cloud.faceServer.domian.FaceChangeRecDO;
//import com.wxhj.cloud.faceServer.service.FaceChangeRecService;
//import com.wxhj.cloud.feignClient.account.MapperClient;
//import com.wxhj.cloud.feignClient.account.request.AsyncMapListenListRequestDTO;
//import com.wxhj.cloud.feignClient.account.request.ConfirmAsyncMapListenListRequestDTO;
//import com.wxhj.cloud.feignClient.account.vo.MapListenListVO;
//
///**
// * @className FcaeChangeSynchHandle.java
// * @author pjf
// * @date 2019年11月21日 上午11:25:16
// */
//
//@Component
//public class FaceChangeSynchHandle extends AbstractAsynJobHandle {
//
//	@Resource
//	MapperClient mapperClient;
//	@Resource
//	FaceChangeRecService faceChangeRecService;
//	@Resource
//    FaceAccountInfoService faceAccountInfoService;
//
//	@Override
//	@Transactional
//	public boolean execute() {
//		WebApiReturnResultModel asyncMapListenList = mapperClient.asyncMapListenList(new AsyncMapListenListRequestDTO(10));
//		if (asyncMapListenList.resultSuccess()) {
//			String jsonString = JSON.toJSONString(asyncMapListenList.getData());
//			PageDefResponseModel pageDefResponseModel = JSON.parseObject(jsonString, PageDefResponseModel.class);
//			List<MapListenListVO> mapListenListList = JSONArray
//					.parseArray(JSON.toJSONString(pageDefResponseModel.getRows()), MapListenListVO.class);
//			mapListenListList = mapListenListList.stream().filter(q -> !faceChangeRecService.existByMasterId(q.getId()))
//					.collect(Collectors.toList());
//			if (mapListenListList.size() <= 0) {
//				return true;
//			}
//			//
//			List<FaceChangeRecDO> faceChangeRecList = mapListenListList.stream().map(q -> {
//				FaceChangeRecDO faceChangeRec;
//				FaceAccountInfoDO faceAcountInfo = faceAccountInfoService.selectByAccountId(q.getAccountId());
//				//
//				String url = q.getOperateType() == 0 ? (faceAcountInfo == null ? null : faceAcountInfo.getImageName())
//						: null;
//				if (faceAcountInfo == null) {
//					return null;
//				}
//				if (Strings.isNullOrEmpty(faceAcountInfo.getName())) {
//					faceAcountInfo.setName("无");
//					faceAcountInfo.setPhoneNumber("0");
//					faceAcountInfo.setIdNumber("0");
//				}
//
//				faceChangeRec = new FaceChangeRecDO(q.getSceneId(), null, q.getId(), q.getAccountId(), url,
//						q.getOperateType(), faceAcountInfo.getIdNumber(), faceAcountInfo.getName(),
//						faceAcountInfo.getPhoneNumber());
//				return faceChangeRec;
//			}).collect(Collectors.toList());
//			//
//			faceChangeRecList = faceChangeRecList.stream()
//					.filter(q -> q != null && (q.getOperateType() == 1 || q.getImageUrl() != null))
//					.collect(Collectors.toList());
//			if (faceChangeRecList.size() > 0) {
//				faceChangeRecService.insertListCascade(faceChangeRecList);
//				List<Long> idList = faceChangeRecList.stream().map(q -> q.getMasterId()).collect(Collectors.toList());
//				// List<Long> idList = mapListenListList.stream().map(q ->
//				// q.getId()).collect(Collectors.toList());
//				ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest = new ConfirmAsyncMapListenListRequestDTO(
//						idList);
//				mapperClient.confirmAsyncMapListenList(confirmAsyncMapListenListRequest);
//			}
//
//		}
//		return true;
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//
//}
