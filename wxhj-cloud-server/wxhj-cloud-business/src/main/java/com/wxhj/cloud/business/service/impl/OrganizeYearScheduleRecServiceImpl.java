package com.wxhj.cloud.business.service.impl;

import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.mapper.OrganizeYearScheduleRecMapper;
import com.wxhj.cloud.business.service.OrganizeYearScheduleRecService;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/5/13 4:13 下午
 */
@Service
public class OrganizeYearScheduleRecServiceImpl implements OrganizeYearScheduleRecService {

    @Resource
    OrganizeYearScheduleRecMapper organizeYearScheduleRecMapper;

    @Override
    public Map<Integer, List<OrganizeYearScheduleRecDO>> listGroupByDayStatus(String refId) {
        Example example = new Example(OrganizeYearScheduleRecDO.class);
        example.createCriteria().andEqualTo("organizeYearScheduleId", refId);
        List<OrganizeYearScheduleRecDO> organizeYearScheduleRecs = organizeYearScheduleRecMapper.selectByExample(example);
        Map<Integer, List<OrganizeYearScheduleRecDO>> result = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
        organizeYearScheduleRecs.forEach(q -> {
            Integer status = q.getStatus();
            List<OrganizeYearScheduleRecDO> list = result.get(status);
            list = list == null ? new ArrayList<>(SystemStaticClass.INIT_CAPACITY) : list;
            list.add(q);
            result.put(status, list);
        });
        return result;
    }

    @Override
    public List<OrganizeYearScheduleRecDO> listByRefId(String refId) {
        Example example = new Example(OrganizeYearScheduleRecDO.class);
        example.createCriteria().andEqualTo("organizeYearScheduleId", refId);
        return organizeYearScheduleRecMapper.selectByExample(example);
    }

    @Override
    public void delete(String refId) {
        Example example = new Example(OrganizeYearScheduleRecDO.class);
        example.createCriteria().andEqualTo("organizeYearScheduleId", refId);
        organizeYearScheduleRecMapper.deleteByExample(example);
    }

    @Override
    public List<Integer> getTotalDayType(String organizeYearScheduleId) {
        List<Integer> integers = organizeYearScheduleRecMapper.organizeYearScheduleMapper(organizeYearScheduleId);
        List<Integer> collect = integers.stream().filter(q -> q != 2 && q != 3).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void insertBatch(List<OrganizeYearScheduleRecDO> organizeYearScheduleRecList) {
        organizeYearScheduleRecList.forEach(this::insert);
    }

    @Override
    public void insert(OrganizeYearScheduleRecDO organizeYearScheduleRec) {
        try {
            organizeYearScheduleRecMapper.insert(organizeYearScheduleRec);
        } catch (Exception e) {
            organizeYearScheduleRecMapper.updateByPrimaryKeySelective(organizeYearScheduleRec);
        }
    }
}
