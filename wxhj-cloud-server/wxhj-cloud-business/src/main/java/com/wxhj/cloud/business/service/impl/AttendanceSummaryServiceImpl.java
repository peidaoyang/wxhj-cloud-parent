package com.wxhj.cloud.business.service.impl;

import com.wxhj.cloud.business.domain.AttendanceSummaryDO;
import com.wxhj.cloud.business.mapper.AttendanceSummaryMapper;
import com.wxhj.cloud.business.service.AttendanceSummaryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/4/15 10:31 上午
 */
@Service
public class AttendanceSummaryServiceImpl implements AttendanceSummaryService {

    @Resource
    private AttendanceSummaryMapper attendanceSummaryMapper;

    @Override
    public void delete(String accountId, Date date) {
        Example example = new Example(AttendanceSummaryDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andEqualTo("dateTime", date);
        attendanceSummaryMapper.deleteByExample(example);
    }

    @Override
    public List<AttendanceSummaryDO> listByAccountId(String accountId) {
        Example example = new Example(AttendanceSummaryDO.class);
        example.createCriteria().andEqualTo("accountId", accountId);
        return attendanceSummaryMapper.selectByExample(example);
    }

    @Override
    public AttendanceSummaryDO selectByAccountAndDate(String accountId, Date date) {
        Example example = new Example(AttendanceSummaryDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andEqualTo("dateTime", date);
        List<AttendanceSummaryDO> attendanceSummaries = attendanceSummaryMapper.selectByExample(example);
        return attendanceSummaries.size() == 0 ? null : attendanceSummaries.get(0);
    }

    @Override
    public void insertList(List<AttendanceSummaryDO> attendanceSummaryList) {
        attendanceSummaryList.forEach(item -> attendanceSummaryMapper.insert(item));
    }

    @Override
    public void insert(AttendanceSummaryDO attendanceSummaryDO) {
        attendanceSummaryMapper.insert(attendanceSummaryDO);
    }

    @Override
    public void update(AttendanceSummaryDO attendanceSummaryDO) {
        Example example = new Example(AttendanceSummaryDO.class);
        example.createCriteria().andEqualTo("accountId", attendanceSummaryDO.getAccountId())
                .andEqualTo("dateTime", attendanceSummaryDO.getDatetime());
        attendanceSummaryMapper.updateByExampleSelective(attendanceSummaryDO, example);
    }
}
