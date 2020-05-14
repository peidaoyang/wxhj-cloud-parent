package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.AccountTypeDO;
import com.wxhj.cloud.account.mapper.AccountTypeMapper;
import com.wxhj.cloud.account.service.AccountTypeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Resource
    AccountTypeMapper accountTypeMapper;

    @Override
    public List<AccountTypeDO> listByOrgType(Integer orgType) {
        Example example = new Example(AccountTypeDO.class);
        example.createCriteria().andLike("orgType","%"+orgType+"%");
        return accountTypeMapper.selectByExample(example);
    }
}
