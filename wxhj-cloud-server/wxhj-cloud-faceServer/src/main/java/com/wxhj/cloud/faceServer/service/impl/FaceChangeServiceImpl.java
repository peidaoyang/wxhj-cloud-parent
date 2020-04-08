///**
// * @fileName: FaceChangeServiceImpl.java
// * @author: pjf
// * @date: 2019年11月21日 下午1:22:57
// */
//
//package com.wxhj.cloud.faceServer.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.wxhj.cloud.faceServer.domian.FaceChangeDO;
//import com.wxhj.cloud.faceServer.mapper.FaceChangeMapper;
//import com.wxhj.cloud.faceServer.service.FaceChangeService;
//import tk.mybatis.mapper.entity.Example;
//
///**
// * @className FaceChangeServiceImpl.java
// * @author pjf
// * @date 2019年11月21日 下午1:22:57
//*/
///**
// * @className FaceChangeServiceImpl.java
// * @author pjf
// * @date 2019年11月21日 下午1:22:57
// */
//@Service
//public class FaceChangeServiceImpl implements FaceChangeService {
//	@Resource
//	FaceChangeMapper faceChangeMapper;
//
//	@Override
//	public FaceChangeDO selectBySceneId(String sceneId) {
//		Example example = new Example(FaceChangeDO.class);
//		example.createCriteria().andEqualTo("id", sceneId);
//		return faceChangeMapper.selectOneByExample(example);
//	}
//
//	@Override
//	public List<FaceChangeDO> listBySceneId(List<String> idList) {
//
//		Example example = new Example(FaceChangeDO.class);
//		example.createCriteria().andIn("id", idList);
//		return faceChangeMapper.selectByExample(example);
//	}
//
//	@Override
//	@Transactional
//	public Long selectCurrentIndex(String id) {
//
//		FaceChangeDO faceChange;
//		faceChange = faceChangeMapper.selectByPrimaryKey(id);
//		if (faceChange == null) {
//			faceChange = new FaceChangeDO();
//			faceChange.setId(id);
//			faceChange.setMinIndex(0L);
//			faceChange.setMaxIndex(0L);
//			faceChangeMapper.insert(faceChange);
//			return 0L;
//		}
//		faceChange.setMaxIndex(faceChange.getMaxIndex() + 1);
//		faceChange.setMinIndex(null);
//		faceChangeMapper.updateByPrimaryKeySelective(faceChange);
//		return faceChange.getMaxIndex();
//	}
//
//	@Override
//	public List<FaceChangeDO> listAll() {
//		return faceChangeMapper.selectAll();
//	}
//}
