package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.business.mapper.view.ViewAttendanceSummaryMatchingFinalMapper;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryMatchingFinalService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.core.utils.MathUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.vo.ViewAccountAttendanceMatchingFinalVO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020/4/21 1:49 下午
 */
@Service
public class ViewAttendanceSummaryMatchingFinalServiceImpl implements ViewAttendanceSummaryMatchingFinalService {
    @Resource
    ViewAttendanceSummaryMatchingFinalMapper viewAttendanceSummaryMatchingFinalMapper;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    @Override
    public List<ViewAccountAttendanceMatchingFinalVO> gatherAccountAttendanceInfo(List<ViewAttendanceSummaryMatchingFinalDO> viewAttendanceSummaryList) {
        Map<String, ViewAccountAttendanceMatchingFinalVO> map = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
        viewAttendanceSummaryList.forEach(q -> {
            String accountId = q.getAccountId();
            ViewAccountAttendanceMatchingFinalVO item = map.get(accountId);
            item = item == null ? dozerBeanMapper.map(q, ViewAccountAttendanceMatchingFinalVO.class) : item;
            summaryAttendanceInfo(item, q);
            map.put(accountId, item);
        });
        return new ArrayList<>(map.values());
    }

    /**
     * 汇总人员考勤信息
     *
     * @param item
     * @param q
     * @return void
     * @author daxiong
     * @date 2020/5/12 3:03 下午
     */
    private void summaryAttendanceInfo(ViewAccountAttendanceMatchingFinalVO item, ViewAttendanceSummaryMatchingFinalDO q) {
        Integer sequence;
        Integer workStatus;
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                sequence = q.getSequence1();
                workStatus = q.getWorkStatus1();
            } else if (i == 1) {
                sequence = q.getSequence2();
                workStatus = q.getWorkStatus2();
            } else {
                sequence = q.getSequence3();
                workStatus = q.getWorkStatus3();
            }
            if (sequence == null) {
                continue;
            }
            item.setNeedCards(MathUtil.add(item.getNeedCards(), 2));
            workStatus = workStatus == null ? -1 : workStatus;
            switch (workStatus) {
                case 6:
                    item.setAbsentTimes(MathUtil.add(item.getAbsentTimes(), 1));
                    item.setMissCardTimes(MathUtil.add(item.getMissCardTimes(), 2));
                    break;
                case 5:
                case 4:
                    item.setMissCardTimes(MathUtil.add(item.getMissCardTimes(), 1));
                    break;
                case 3:
                    item.setLateTimes(MathUtil.add(item.getLateTimes(), 1));
                    item.setEarlyTimes(MathUtil.add(item.getEarlyTimes(), 1));
                    break;
                case 2:
                    item.setEarlyTimes(MathUtil.add(item.getEarlyTimes(), 1));
                    break;
                case 1:
                    item.setLateTimes(MathUtil.add(item.getLateTimes(), 1));
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
        item.setRealCards(MathUtil.subGreatThenZero(item.getNeedCards(), item.getMissCardTimes()));
        int dayStatus = q.getDayStatus() == null ? -1 : q.getDayStatus();
        if (dayStatus == -1) {
            item.setRestDays(MathUtil.add(item.getRestDays(), 1));
        } else if (dayStatus != 6) {
            item.setWorkDays(MathUtil.add(item.getWorkDays(), 1));
        }
        item.setLeaveTotal(MathUtil.add(item.getLeaveTotal(), q.getLeaveTotal()));
        item.setLeaveTotalStr(DateUtil.minute2Hour(item.getLeaveTotal()));
        item.setTravelTotal(MathUtil.add(item.getTravelTotal(), q.getTravelTotal()));
        item.setTravelTotalStr(DateUtil.minute2Hour(item.getTravelTotal()));
        item.setWorkTotal(MathUtil.add(item.getWorkTotal(), q.getWorkTotal()));
        item.setWorkTotalStr(DateUtil.minute2Hour(item.getWorkTotal()));
        item.setLateTotal(MathUtil.add(item.getLateTotal(), q.getLateTotal()));
        item.setEarlyTotal(MathUtil.add(item.getEarlyTotal(), q.getEarlyTotal()));
        item.setWorkAvgTimeStr(DateUtil.minute2Hour(MathUtil.div(item.getWorkTotal(), item.getWorkDays())));
    }

    @Override
    public PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePage(IPageRequestModel pageRequestModel,
                                                                             Date beginTime, Date endTime, String organizeId, String nameValue) {
        Example example = new Example(ViewAttendanceSummaryMatchingFinalDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId).andBetween("datetime", beginTime, endTime);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("accountName", "%" + nameValue + "%");
        }
        return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMatchingFinalMapper.selectByExample(example));
    }

    @Override
    public List<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePageNoPage(Date beginTime, Date endTime,
                                                                               String organizeId, String nameValue) {
        Example example = new Example(ViewAttendanceSummaryMatchingFinalDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId).andBetween("datetime", beginTime, endTime);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("accountName", "%" + nameValue + "%");
        }
        return viewAttendanceSummaryMatchingFinalMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                            Date endTime, String accountId) {
        Example example = new Example(ViewAttendanceSummaryMatchingFinalDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andBetween("datetime", beginTime, endTime);
        return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMatchingFinalMapper.selectByExample(example));
    }

}
