package com.wxhj.cloud.account.service.impl;

import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.mapper.AccountCardInfoMapper;
import com.wxhj.cloud.account.service.AccountCardInfoService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020/5/21 2:53 下午
 */
@Service
public class AccountCardInfoServiceImpl implements AccountCardInfoService {

    @Resource
    AccountCardInfoMapper accountCardInfoMapper;

    @Override
    public void update(AccountCardInfoDO accountCardInfo) {
        accountCardInfoMapper.updateByPrimaryKeySelective(accountCardInfo);
    }

    @Override
    public void recharge(AccountCardInfoDO accountCardInfo, Integer amount) {
        accountCardInfo.setBalance(accountCardInfo.getBalance() + amount);
        accountCardInfo.setRechargeTotalAmount(accountCardInfo.getRechargeTotalAmount() + amount);
        if (Strings.isNullOrEmpty(accountCardInfo.getId())) {
            String id = UUID.randomUUID().toString();
            accountCardInfo.setId(id);
            accountCardInfoMapper.insert(accountCardInfo);
        } else {
            update(accountCardInfo);
        }
    }

    @Override
    public List<AccountCardInfoDO> selectByAccountId(String accountId) {
        Example example = new Example(AccountCardInfoDO.class);
        example.createCriteria().andEqualTo("accountId", accountId);
        return accountCardInfoMapper.selectByExample(example);
    }

    @Override
    public AccountCardInfoDO selectByAccountIdAndCardType(String accountId, Integer cardType) {
        Example example = new Example(AccountCardInfoDO.class);
        example.createCriteria().andEqualTo("accountId", accountId)
                .andEqualTo("cardType", cardType);
        List<AccountCardInfoDO> accountCardInfos = accountCardInfoMapper.selectByExample(example);
        if (accountCardInfos.size() > 0) {
            return accountCardInfos.get(0);
        }
        return null;
    }

    @Override
    public void revoke(AccountCardInfoDO accountCardInfo, Integer amount) {
        accountCardInfo.setBalance(accountCardInfo.getBalance() + amount);
        update(accountCardInfo);
    }
}
