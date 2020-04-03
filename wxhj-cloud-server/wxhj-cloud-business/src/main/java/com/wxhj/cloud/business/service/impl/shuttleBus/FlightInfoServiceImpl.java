/**
 * 
 */
package com.wxhj.cloud.business.service.impl.shuttleBus;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.FlightInfoDO;
import com.wxhj.cloud.business.mapper.FlightInfoMapper;
import com.wxhj.cloud.business.service.shuttleBus.FlightInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: FlightInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月5日 下午5:16:32 
 */
@Service
public class FlightInfoServiceImpl implements FlightInfoService{
	@Resource
	FlightInfoMapper flightInfoMapper;
	
	@Override
	public PageInfo<FlightInfoDO> selectById(IPageRequestModel pageRequestModel, String organizeId, String nameValue) {
		Example example = new Example(FlightInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("flightNumber", "%" + nameValue + "%");
		PageInfo<FlightInfoDO> pageList = PageUtil.selectPageList(pageRequestModel,
				() -> flightInfoMapper.selectByExample(example));
		return pageList;
	}
	
	@Override
	public PageInfo<FlightInfoDO> selectByRoute(IPageRequestModel pageRequestModel, String organizeId, String nameValue) {
		Example example = new Example(FlightInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("routeNumber", "%" + nameValue + "%");
		PageInfo<FlightInfoDO> pageList = PageUtil.selectPageList(pageRequestModel,
				() -> flightInfoMapper.selectByExample(example));
		return pageList;
	}
	
	@Override
	public String insert(FlightInfoDO flightInfoDO) {
		flightInfoDO.setId(UUID.randomUUID().toString());
		flightInfoMapper.insert(flightInfoDO);
		return flightInfoDO.getId();
	}
	
	@Override
	public void update(FlightInfoDO flightInfoDO) {
		flightInfoMapper.updateByPrimaryKey(flightInfoDO);
	}

	@Override
	@Transactional
	public void delete(List<String> idList) {
		idList.forEach(q->flightInfoMapper.deleteByPrimaryKey(q));
	}

	@Override
	public List<FlightInfoDO> selectByRoute(String routeNumber) {
		Example example = new Example(FlightInfoDO.class);
		example.createCriteria().andEqualTo("routeNumber",routeNumber);
		return flightInfoMapper.selectByExample(example);
	}

	@Override
	public FlightInfoDO select(String id) {
		return flightInfoMapper.selectByPrimaryKey(id);
	}
}
