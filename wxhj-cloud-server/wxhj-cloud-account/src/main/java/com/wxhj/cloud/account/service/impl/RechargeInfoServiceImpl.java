package com.wxhj.cloud.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.account.mapper.RechargeInfoMapper;
import com.wxhj.cloud.account.service.RechargeInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: RechargeInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年1月31日 下午3:22:05
 */
@Service
public class RechargeInfoServiceImpl implements RechargeInfoService {
	@Resource
	RechargeInfoMapper rechargeInfoMapper;

	@Override
	public String insert(RechargeInfoDO rechargeInfo, String userId) {
		rechargeInfo.create(userId);
		rechargeInfo.initialization();
		rechargeInfoMapper.insert(rechargeInfo);
		return rechargeInfo.getId();
	}

	@Override
	public PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel pageRequestModel, String nameValue, Integer type,
			Integer payType, Date startTime, Date endTime, String organizeId) {
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("type", type).andEqualTo("payType", payType)
				.andLike("accountId", "%" + nameValue + "%").andBetween("creatorTime", startTime, endTime)
				.andEqualTo("organizeId", organizeId);

		PageInfo<RechargeInfoDO> list = PageUtil.selectPageList(pageRequestModel,
				() -> rechargeInfoMapper.selectByExample(example));
		return list;
	}

	@Override
	public PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel iPageRequestModel, Date startTime, Date endTime,
			String accountId) {
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("accountId", accountId).andBetween("creatorTime", startTime, endTime);
		return PageUtil.selectPageList(iPageRequestModel, () -> rechargeInfoMapper.selectByExample(example));
	}
	
	@Override
	public void delete(String id) {
		rechargeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<RechargeInfoDO> list(String organizeId,Date time) {
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andGreaterThanOrEqualTo("creatorTime",time);
		return rechargeInfoMapper.selectByExample(example);
	}
}
