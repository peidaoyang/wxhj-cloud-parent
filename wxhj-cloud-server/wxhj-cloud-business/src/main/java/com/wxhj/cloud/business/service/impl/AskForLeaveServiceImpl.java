package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.mapper.AskForLeaveMapper;
import com.wxhj.cloud.business.service.AskForLeaveService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020-04-07 13:59
 */
@Service
public class AskForLeaveServiceImpl implements AskForLeaveService {

    @Resource
    AskForLeaveMapper askForLeaveMapper;

    @Override
    public void update(AskForLeaveDO askForLeave) {
        askForLeaveMapper.updateByPrimaryKeySelective(askForLeave);
    }

    @Override
    public String insert(AskForLeaveDO askForLeave) {
        String id = UUID.randomUUID().toString();
        askForLeave.setId(id);
        askForLeave.setCreateTime(new Date());
        askForLeaveMapper.insert(askForLeave);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdList(List<String> idList) {
        idList.forEach(item -> delete(item));
    }

    @Override
    public void delete(String id) {
        askForLeaveMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AskForLeaveDO> listByAccountIdAndStatusLimitTime(String accountId, Integer status, Date beginTime, Date endTime) {
        Example example = new Example(AskForLeaveDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("accountId", accountId);
        if (status != null) {
            // 只查询审批通过的请假记录
            criteria.andEqualTo("status", status);
        }
        if (beginTime != null) {
            criteria.andGreaterThanOrEqualTo("startTime", beginTime);
        }
        if (endTime != null) {
            criteria.andLessThanOrEqualTo("endTime", endTime);
        }
        return askForLeaveMapper.selectByExample(example);
    }

    @Override
    public PageInfo<AskForLeaveDO> listPageByOrgIdAndStatusAndName(IPageRequestModel iPageRequestModel,
                                                                   String organizeId, String nameValue, Integer status) {
        Example example = new Example(AskForLeaveDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("accountName", "%" + nameValue + "%");
        }
        if (status != null && status != 0) {
            criteria.andEqualTo("status", status);
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> askForLeaveMapper.selectByExample(example));
    }


}
