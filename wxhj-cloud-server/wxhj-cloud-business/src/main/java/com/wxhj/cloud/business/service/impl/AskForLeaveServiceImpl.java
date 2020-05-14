package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.mapper.AskForLeaveMapper;
import com.wxhj.cloud.business.service.AskForLeaveService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Boolean validateBeforeInsert(AskForLeaveDO askForLeave) {
        List<Integer> statusList = Arrays.asList(ApproveStatusEnum.APPROVE_SUCCESS.getCode(), ApproveStatusEnum.APPROVING.getCode());
        List<AskForLeaveDO> askForLeaves = listByAccountIdAndStatusLimitTime(askForLeave.getAccountId(),
                statusList, askForLeave.getStartTime(), askForLeave.getEndTime());
        return askForLeaves.size() == 0;
    }

    @Override
    public String insert(AskForLeaveDO askForLeave) {
        String id = UUID.randomUUID().toString();
        askForLeave.setId(id);
        askForLeave.setCreateTime(LocalDateTime.now());
        askForLeave.setStatus(ApproveStatusEnum.APPROVING.getCode());
        askForLeaveMapper.insert(askForLeave);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdList(List<String> idList) {
        idList.forEach(item -> delete(item));
    }

    @Override
    public void check(String id, Integer status) {
        AskForLeaveDO askForLeave = new AskForLeaveDO();
        askForLeave.setId(id);
        askForLeave.setStatus(status);
        askForLeaveMapper.updateByPrimaryKeySelective(askForLeave);
    }

    @Override
    public void delete(String id) {
        askForLeaveMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AskForLeaveDO> listByAccountIdAndStatusLimitTime(String accountId, List<Integer> statusList, LocalDateTime beginTime, LocalDateTime endTime) {
        if (Strings.isNullOrEmpty(accountId) || beginTime == null || endTime == null) {
            return new ArrayList<>();
        }
        Example example = new Example(AskForLeaveDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andIn("status", statusList)
                .andLessThan("startTime", endTime).andGreaterThan("endTime", beginTime);
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

    @Override
    public PageInfo<AskForLeaveDO> listPageByAccountIdAndStatus(IPageRequestModel iPageRequestModel, String accountId, Integer status) {
        Example example = new Example(AskForLeaveDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andEqualTo("status", status);
        return PageUtil.selectPageList(iPageRequestModel, () -> askForLeaveMapper.selectByExample(example));
    }

    @Override
    public AskForLeaveDO selectById(String id) {
        return askForLeaveMapper.selectByPrimaryKey(id);
    }


}
