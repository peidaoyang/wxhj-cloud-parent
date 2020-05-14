package com.wxhj.cloud.account.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.view.ViewRechargeAccountDO;
import com.wxhj.cloud.account.mapper.view.ViewRechargeAccountMapper;
import com.wxhj.cloud.account.service.ViewRechargeAccountService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ViewRechargeAccountServiceImpl implements ViewRechargeAccountService {
	@Resource
	ViewRechargeAccountMapper viewRechargeAccountMapper;

	@Override
	public List<ViewRechargeAccountDO> select(String nameValue, Integer type, Integer payType, LocalDateTime startTime,
											  LocalDateTime endTime, String organizeId) {
		Example example = new Example(ViewRechargeAccountDO.class);
		example.createCriteria().andEqualTo("type", type).andEqualTo("payType", payType)
				.andLike("accountId", "%" + nameValue + "%")
				.andBetween("creatorTime", startTime, endTime)
				.andEqualTo("organizeId", organizeId);
		return viewRechargeAccountMapper.selectByExample(example);
	}

}