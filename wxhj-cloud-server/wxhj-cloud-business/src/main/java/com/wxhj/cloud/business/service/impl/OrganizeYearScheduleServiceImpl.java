package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.mapper.OrganizeYearScheduleMapper;
import com.wxhj.cloud.business.service.OrganizeYearScheduleRecService;
import com.wxhj.cloud.business.service.OrganizeYearScheduleService;
import com.wxhj.cloud.core.enums.LegalVocationTypeEnum;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020/5/12 5:34 下午
 */
@Service
public class OrganizeYearScheduleServiceImpl implements OrganizeYearScheduleService {

    @Resource
    OrganizeYearScheduleMapper organizeYearScheduleMapper;
    @Resource
    OrganizeYearScheduleRecService organizeYearScheduleRecService;

    @Override
    @Cacheable(value = "organize_year_schedule_rec", key = "args[0] + ':' + args[2]", unless = "#result eq null")
    public Integer selectCacheByIdAndDate(String id, Map<String, OrganizeYearScheduleRecDO> map, String dateStr) {
        OrganizeYearScheduleRecDO organizeYearScheduleRec = map.get(dateStr);
        if (organizeYearScheduleRec != null) {
            return organizeYearScheduleRec.getStatus();
        }
        // 正常工作日状态
        return LegalVocationTypeEnum.NORMAL_ON_WORK.getCode();
    }

    @Override
    public OrganizeYearScheduleDO selectByPrimaryKey(String id) {
        return organizeYearScheduleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrganizeYearSchedule(String id) {
        organizeYearScheduleMapper.deleteByPrimaryKey(id);
        // 删除对应的规则
        organizeYearScheduleRecService.delete(id);
    }

    @Override
    public void submitOrganizeYearScheduleCascade(OrganizeYearScheduleDO organizeYearSchedule, List<OrganizeYearScheduleRecVO> list) {
        if (Strings.isNullOrEmpty(organizeYearSchedule.getId())) {
            organizeYearSchedule.setId(UUID.randomUUID().toString());
            organizeYearSchedule.setCreateTime(new Date());
            organizeYearScheduleMapper.insert(organizeYearSchedule);
        } else {
            organizeYearScheduleMapper.updateByPrimaryKeySelective(organizeYearSchedule);
        }
    }

    @Override
    public List<OrganizeYearScheduleDO> listAllByOrganizeId(String organizeId) {
        Example example = new Example(OrganizeYearScheduleDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId);
        return organizeYearScheduleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<OrganizeYearScheduleDO> listByOrganizeIdAndName(IPageRequestModel iPageRequestModel, String organizeId, String nameValue) {
        Example example = new Example(OrganizeYearScheduleDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("fullName", "%" + nameValue + "%");
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> organizeYearScheduleMapper.selectByExample(example));
    }
}
