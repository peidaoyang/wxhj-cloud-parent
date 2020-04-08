///**
// * @fileName: FaceAccountInfoServiceImpl.java
// * @author: pjf
// * @date: 2019年11月1日 下午3:01:34
// */
//
//package com.wxhj.cloud.faceServer.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import com.wxhj.cloud.faceServer.service.FaceAccountInfoService;
//import org.springframework.stereotype.Service;
//
//import com.wxhj.cloud.faceServer.domian.FaceAccountInfoDO;
//import com.wxhj.cloud.faceServer.mapper.FaceAcountInfoMapper;
//
//import tk.mybatis.mapper.entity.Example;
//
///**
// * @className FaceAccountInfoServiceImpl.java
// * @author pjf
// * @date 2019年11月1日 下午3:01:34
// */
//
//@Service
//public class FaceAccountInfoServiceImpl implements FaceAccountInfoService {
//	@Resource
//	FaceAcountInfoMapper faceAcountInfoMapper;
//
//	@Override
//	public String insert(FaceAccountInfoDO faceAcountInfoDO) {
//		faceAcountInfoMapper.insert(faceAcountInfoDO);
//		return faceAcountInfoDO.getAccountId();
//	}
//
//	@Override
//	public FaceAccountInfoDO selectByAccountId(String accountId) {
//
//		return faceAcountInfoMapper.selectByPrimaryKey(accountId);
//	}
//
//	@Override
//	public void update(FaceAccountInfoDO faceAcountInfo) {
//		faceAcountInfoMapper.updateByPrimaryKey(faceAcountInfo);
//	}
//
//	@Override
//	public void deleteCascade(FaceAccountInfoDO faceAcountInfo) {
//		faceAcountInfoMapper.deleteByPrimaryKey(faceAcountInfo.getAccountId());
//	}
//
//	@Override
//	public List<FaceAccountInfoDO> listByAccountIdList(List<String> accountIdList) {
//		Example example = new Example(FaceAccountInfoDO.class);
//		example.createCriteria().andIn("accountId", accountIdList);
//		return faceAcountInfoMapper.selectByExample(example);
//		// return null;
//	}
//
//}
