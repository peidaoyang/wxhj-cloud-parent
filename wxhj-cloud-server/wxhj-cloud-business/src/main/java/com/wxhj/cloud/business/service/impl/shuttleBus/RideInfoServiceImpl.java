package com.wxhj.cloud.business.service.impl.shuttleBus;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.RideInfoDO;
import com.wxhj.cloud.business.mapper.RideInfoMapper;
import com.wxhj.cloud.business.service.shuttleBus.RideInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: RideInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:47:42
 */
@Service
public class RideInfoServiceImpl implements RideInfoService {
	@Resource
	RideInfoMapper rideInfoMapper;
	
	@Override
	public PageInfo<RideInfoDO> listPage(IPageRequestModel pageRequestModel, String organizeId, String nameValue,String field,
			Date startTime, Date endTime) {
		Example example = new Example(RideInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("rideTime", startTime, endTime)
				.andLike(field, "%" + nameValue + "%");
		PageInfo<RideInfoDO> pageInfo = PageUtil.selectPageList(pageRequestModel,
				() -> rideInfoMapper.selectByExample(example));
		return pageInfo;
	}
	
//	@Override
//	public PageInfo<RideInfoDO> selectByAccountId(IPageRequestModel pageRequestModel, String organizeId, String nameValue,
//			Date startTime, Date endTime) {
//		Example example = new Example(RideInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("rideTime", startTime, endTime)
//				.andLike("accountId", "%" + nameValue + "%");
//		PageInfo<RideInfoDO> pageInfo = PageUtil.selectPageList(pageRequestModel,
//				() -> rideInfoMapper.selectByExample(example));
//		return pageInfo;
//	}
//
//	@Override
//	public PageInfo<RideInfoDO> selectByFlightId(IPageRequestModel pageRequestModel, String organizeId, String nameValue,
//			Date startTime, Date endTime) {
//		Example example = new Example(RideInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("rideTime", startTime, endTime)
//				.andLike("flightId", "%" + nameValue + "%");
//		PageInfo<RideInfoDO> pageInfo = PageUtil.selectPageList(pageRequestModel,
//				() -> rideInfoMapper.selectByExample(example));
//		return pageInfo;
//	}

	@Override
	public PageInfo<RideInfoDO> select(IPageRequestModel pageRequestModel, String accountId, Date startTime,
			Date endTime) {
		Example example = new Example(RideInfoDO.class);
		example.createCriteria().andEqualTo("accountId", accountId).andBetween("rideTime", startTime, endTime);
		return PageUtil.selectPageList(pageRequestModel, () -> rideInfoMapper.selectByExample(example));
	}

	@Override
	public void insert(RideInfoDO rideInfo) {
		rideInfoMapper.insert(rideInfo);
	}

}
