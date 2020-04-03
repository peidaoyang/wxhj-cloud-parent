/** 
 * @fileName: AccountInfoServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月31日 上午9:34:06 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.mapper.AccountInfoMapper;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.core.config.LocalIdConfig;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className AccountInfoServiceImpl.java
 * @author pjf
 * @date 2019年10月31日 上午9:34:06
 */

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

	@Resource
	AccountInfoMapper accountInfoMapper;
	@Resource
	LocalIdConfig localIdConfig;

//	@Override
//	public boolean isExistByPhoneNumber(String phoneNumber) {
//
//		Example example = new Example(AccountInfoDO.class);
//		example.createCriteria().andEqualTo("phoneNumber", phoneNumber);
//		int selectCountByExample = accountInfoMapper.selectCountByExample(example);
//		return selectCountByExample > 0;
//	}
//
	@Override
	public boolean isExistByPhoneNumberAndOrg(String phoneNumber, String organizeId) {

		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("phoneNumber", phoneNumber).andEqualTo("organizeId", organizeId);
		int selectCountByExample = accountInfoMapper.selectCountByExample(example);
		return selectCountByExample > 0;
	}

	private Long selectAccountSequence() {
		String terminal = localIdConfig.getLocalId();
		String sequenceTableName = "account_sequence_generator";
		accountInfoMapper.replaceSequence(sequenceTableName, terminal);
		return accountInfoMapper.selectSequence(sequenceTableName, terminal);
	}

	@Override
	@Transactional
	public String insert(AccountInfoDO accountInfo) {
		String id = String.format("%010d", selectAccountSequence());
		accountInfo.setAccountId(id);
		accountInfoMapper.insert(accountInfo);
		return id;
	}

	@Override
	@Transactional
	public void insertList(List<AccountInfoDO> accountInfoList) {
		for (AccountInfoDO accountInfoDO : accountInfoList) {
			this.insert(accountInfoDO);
		}
	}

	@Override
	public PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage(String fullName, String organizeId,
			IPageRequestModel paginationRequestModel) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("name", "%" + fullName + "%");
		PageInfo<AccountInfoDO> accountInfoList = PageUtil.selectPageList(paginationRequestModel,
				() -> accountInfoMapper.selectByExample(example));
		return accountInfoList;
	}

	@Override
	public PageInfo<AccountInfoDO> listByNamePage(String fullName, String organizeId,
			IPageRequestModel paginationRequestModel) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("name", "%" + fullName + "%");
		PageInfo<AccountInfoDO> accountInfoList = PageUtil.selectPageList(paginationRequestModel,
				() -> accountInfoMapper.selectByExample(example));
		return accountInfoList;
	}

	@Override
	public PageInfo<AccountInfoDO> listByNameAndChildrenOrg(String fullName, String organizeId,
			IPageRequestModel pageRequestModel) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("childOrganizeId", organizeId).andLike("name", "%" + fullName + "%")
				.andEqualTo("isFrozen", 0);
		PageInfo<AccountInfoDO> accountInfoList = PageUtil.selectPageList(pageRequestModel,
				() -> accountInfoMapper.selectByExample(example));
		return accountInfoList;
	}

	@Override
	public PageInfo<AccountInfoDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId,
			IPageRequestModel pageRequestModel) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andIn("childOrganizeId", organizeId).andLike("name", "%" + fullName + "%");
		PageInfo<AccountInfoDO> accountInfoList = PageUtil.selectPageList(pageRequestModel,
				() -> accountInfoMapper.selectByExample(example));
		return accountInfoList;
	}

//	
//	@Override
//	public List<AccountInfoDO> listByName(String fullName, List<String> organizeId) {
//		Example example = new Example(AccountInfoDO.class);
//		example.createCriteria().andIn("organizeId", organizeId).andLike("name", "%" + fullName + "%");
//		return accountInfoMapper.selectByExample(example);
//	}

	@Override
	public AccountInfoDO selectByAccountId(String accountId) {
		return accountInfoMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public void update(AccountInfoDO accountInfo) {
		accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
	}

	@Override
	public AccountInfoDO selectByOrganizeIdAndPhone(String organizeId, String phone) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("phoneNumber", phone);
		return accountInfoMapper.selectOneByExample(example);
	}

	@Override
	public List<AccountInfoDO> listByPhoneNumber(String phoneNumber) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("phoneNumber", phoneNumber);
		return accountInfoMapper.selectByExample(example);
	}

	@Override
	public List<AccountInfoDO> listByOrganizeId(String organizeId) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return accountInfoMapper.selectByExample(example);
	}

	@Override
	public void recharge(String id, Integer amount) {
		accountInfoMapper.recharge(amount, id);
	}

	@Override
	public void charging(String accountId, Integer amount) {
		accountInfoMapper.charging(amount, accountId);
	}

	@Override
	public List<AccountInfoDO> listByChildOrganIdList(List<String> organizeList) {
		Example example = new Example(AccountInfoDO.class);
		example.createCriteria().andIn("childOrganizeId", organizeList);
		return accountInfoMapper.selectByExample(example);
	}

	@Override
	@Transactional
	public void deleteCascade(AccountInfoDO accountInfo) {
		accountInfoMapper.deleteByPrimaryKey(accountInfo.getAccountId());
	}

	@Override
	public AccountInfoDO selectByNoAndOrganizeId(String no, int noType, String organizeId) {
		Example example = new Example(AccountInfoDO.class);
		//Criteria criteria = example.createCriteria();
		
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("organizeId", organizeId);
		switch (noType) {
		// 手机号
		case 1:
			criteria.andEqualTo("phoneNumber", no);
			break;
		// 身份证号
		case 2:
			criteria.andEqualTo("idNumber", no);
			break;
		// 其他id
		case 3:
			break;
		default:
			break;
		}
		
		//example.and(criteria);
		// example.createCriteria().andEqualTo("organizeId", organizeId).and
		return accountInfoMapper.selectOneByExample(example);
	}

}
