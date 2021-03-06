package com.wxhj.cloud.account.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.event.AncestorEvent;

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
			Integer payType, LocalDateTime startTime, LocalDateTime endTime, String organizeId, Integer cardType) {
		cardType = cardType == null ? 0 : cardType;
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("type", type).andEqualTo("payType", payType)
				.andLike("accountId", "%" + nameValue + "%")
				.andBetween("creatorTime", startTime, endTime)
				.andEqualTo("organizeId", organizeId)
				.andEqualTo("cardType", cardType);
		PageInfo<RechargeInfoDO> list = PageUtil.selectPageList(pageRequestModel,
				() -> rechargeInfoMapper.selectByExample(example));
		return list;
	}

	@Override
	public PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel iPageRequestModel,
													 LocalDateTime startTime, LocalDateTime endTime,String accountId, Integer cardType) {
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("accountId", accountId)
				.andBetween("creatorTime", startTime, endTime)
				.andEqualTo("cardType", cardType);
		return PageUtil.selectPageList(iPageRequestModel, () -> rechargeInfoMapper.selectByExample(example));
	}

	@Override
	public void delete(String id) {
		rechargeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<RechargeInfoDO> list(String organizeId, LocalDateTime time) {
		Example example = new Example(RechargeInfoDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId)
				.andGreaterThanOrEqualTo("creatorTime",time);
		return rechargeInfoMapper.selectByExample(example);
	}

	@Override
	public void revoke(String id, Integer isRevoke) {
		RechargeInfoDO rechargeInfo = new RechargeInfoDO();
		rechargeInfo.setId(id);
		rechargeInfo.setIsRevoke(isRevoke);
		rechargeInfoMapper.updateByPrimaryKeySelective(rechargeInfo);
	}
}
