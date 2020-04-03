package com.wxhj.cloud.business.service.impl.shuttleBus;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.RouteInfoDO;
import com.wxhj.cloud.business.mapper.RouteInfoMapper;
import com.wxhj.cloud.business.service.shuttleBus.RouteInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName: RouteInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月4日 下午3:30:30 
 */
@Service
public class RouteInfoServiceImpl implements RouteInfoService{
	@Resource
	RouteInfoMapper routeInfoMapper;

	@Override
	public PageInfo<RouteInfoDO> select(IPageRequestModel pageRequestModel, String organizeId, 
			String nameValue,String type) {
		Example example = new Example(RouteInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike(type, "%" + nameValue + "%");
		PageInfo<RouteInfoDO> pageAttendanceData;
		if(type.equals("site")) {
			pageAttendanceData = selectBySite(pageRequestModel, organizeId, nameValue);
		}else {
			pageAttendanceData = PageUtil.selectPageList(pageRequestModel,() -> routeInfoMapper.selectByExample(example));
		}
		return pageAttendanceData;
	}
	
	private PageInfo<RouteInfoDO> selectBySite(IPageRequestModel pageRequestModel, String organizeId, String nameValue) {
		Example example = new Example(RouteInfoDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId",organizeId);
		
		Criteria criteriaOr = example.createCriteria();
		if(!Strings.isNullOrEmpty(nameValue)) {
			criteriaOr.orLike("startSite", "%" + nameValue + "%").orLike("endSite", "%" + nameValue + "%")
				.orLike("channelSite", "%" + nameValue + "%");
		}
		
		example.and(criteriaOr);
		PageInfo<RouteInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
				() -> routeInfoMapper.selectByExample(example));
		return pageAttendanceData;
	}
	
	
	@Override
	public List<RouteInfoDO> listByOrg(String organizeId) {
		Example example = new Example(RouteInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId);
		return routeInfoMapper.selectByExample(example);
	}
	
	
	
	@Override
	public String insert(RouteInfoDO routeInfo) {
		routeInfo.setId(UUID.randomUUID().toString());
		routeInfoMapper.insert(routeInfo);
		return routeInfo.getId();
	}

	@Override
	public void update(RouteInfoDO routeInfoDO) {
		routeInfoMapper.updateByPrimaryKey(routeInfoDO);
	}

	@Override
	@Transactional
	public void delete(List<String> routIdList) {
		routIdList.forEach(q -> routeInfoMapper.deleteByPrimaryKey(q));
	}

}
