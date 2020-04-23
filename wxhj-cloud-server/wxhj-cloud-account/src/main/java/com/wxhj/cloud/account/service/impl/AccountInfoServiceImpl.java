/**
 * @fileName: AccountInfoServiceImpl.java
 * @author: pjf
 * @date: 2019年10月31日 上午9:34:06
 */

package com.wxhj.cloud.account.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.mapper.AccountInfoMapper;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.core.config.LocalIdConfig;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pjf
 * @className AccountInfoServiceImpl.java
 * @date 2019年10月31日 上午9:34:06
 */

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Resource
    AccountInfoMapper accountInfoMapper;
    @Resource
    LocalIdConfig localIdConfig;


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
            try{
                this.insert(accountInfoDO);
            } catch (Exception e){
                continue;
            }
        }
    }

    @Override
    public void updateFrozen(String accountId, Integer frozen) {
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(accountId);
        accountInfo.setIsFrozen(frozen);
        accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
    }

    @Override
    public void updatePassword(String accountId, String userPassword) {
        String key = PasswordUtil.generatePasswordKey();
        userPassword = PasswordUtil.calculationPassword(userPassword, key);
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(accountId);
        accountInfo.setUserPassword(userPassword);
        accountInfo.setUserSecretKey(key);
        accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
    }

    @Override
    public void insertFaceImage(String accountId, String imageName) {

        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(accountId);
        accountInfo.setIsFace(1);
        accountInfo.setImageName(imageName);
        accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
    }


    @Override
    public PageInfo<AccountInfoDO> listByNamePage(String fullName, String organizeId, String type,
                                                  IPageRequestModel paginationRequestModel) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId).andLike(type, "%" + fullName + "%");
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
    public PageInfo<AccountInfoDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId, String type,
                                                                          IPageRequestModel pageRequestModel) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andIn("childOrganizeId", organizeId).andLike(type, "%" + fullName + "%");
        PageInfo<AccountInfoDO> accountInfoList = PageUtil.selectPageList(pageRequestModel,
                () -> accountInfoMapper.selectByExample(example));
        return accountInfoList;
    }


    @Override
    public AccountInfoDO selectByAccountId(String accountId) {
        return accountInfoMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public List<AccountInfoDO> listByAccountId(List<String> accountIdList) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andIn("accountId", accountIdList);
        return accountInfoMapper.selectByExample(example);
    }

    @Override
    public void updateCascade(AccountInfoDO accountInfo) {
        accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
    }

    @Override
    public void revoke(Integer balance,Integer amount,String accountId) {
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(accountId);
        accountInfo.setAccountBalance(balance + amount);
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
    public int listByOrganizeIdAndIsFace(String organizeId) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("isFace", 1);
        return accountInfoMapper.selectCountByExample(example);
    }

    @Override
    public List<AccountInfoDO> listByAccountIdList(List<String> idList) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andIn("accountId", idList);
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
    public List<AccountInfoDO> listByChildOrgIdAndIsFace(List<String> organizeList) {
        Example example = new Example(AccountInfoDO.class);
        example.createCriteria().andIn("childOrganizeId", organizeList).andEqualTo("isFace", 1);
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
                criteria.andEqualTo("otherCode", no);
                break;
            default:
                break;
        }
        return accountInfoMapper.selectOneByExample(example);
    }


}
