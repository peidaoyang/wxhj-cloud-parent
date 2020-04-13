/**
 * 
 */
package com.wxhj.cloud.business.service.impl.shuttleBus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.DriverInfoDO;
import com.wxhj.cloud.business.mapper.DriverInfoMapper;
import com.wxhj.cloud.business.service.shuttleBus.DriverInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: DriverInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月4日 下午5:34:55 
 */
@Service
public class DriverInfoServiceImpl implements DriverInfoService{
	@Resource
	DriverInfoMapper driverInfoMapper;
	
	@Override
	public PageInfo<DriverInfoDO> select(IPageRequestModel pageRequestModel, String organizeId, String nameValue,String field) {
		Example example = new Example(DriverInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike(field, "%" + nameValue + "%");
		PageInfo<DriverInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
				() -> driverInfoMapper.selectByExample(example));
		return pageAttendanceData;
	}

//	@Override
//	public PageInfo<DriverInfoDO> selectByAccountId(IPageRequestModel pageRequestModel, String organizeId, String nameValue) {
//		Example example = new Example(DriverInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("accountId", "%" + nameValue + "%");
//		PageInfo<DriverInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
//				() -> driverInfoMapper.selectByExample(example));
//		return pageAttendanceData;
//	}
//	
//	@Override
//	public PageInfo<DriverInfoDO> selectByName(IPageRequestModel pageRequestModel, String organizeId, String nameValue) {
//		Example example = new Example(DriverInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("name", "%" + nameValue + "%");
//		PageInfo<DriverInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
//				() -> driverInfoMapper.selectByExample(example));
//		return pageAttendanceData;
//	}
	
	@Override
	public DriverInfoDO select(String id) {
		return driverInfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String insert(DriverInfoDO driverInfo,String userId) {
		driverInfo.create(userId);
		driverInfoMapper.insert(driverInfo);
		return driverInfo.getId();
	}

	@Override
	public void update(DriverInfoDO driverInfo) {
//		driverInfoMapper.updateByPrimaryKey(driverInfo);
		driverInfoMapper.updateByPrimaryKeySelective(driverInfo);
	}

	@Override
	@Transactional
	public void delete(List<String> jobIdList) {
		jobIdList.forEach(q-> driverInfoMapper.deleteByPrimaryKey(q));
	}
}
