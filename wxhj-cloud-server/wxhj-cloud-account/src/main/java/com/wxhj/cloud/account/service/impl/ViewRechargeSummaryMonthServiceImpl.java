package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryMonthDO;
import com.wxhj.cloud.account.mapper.view.ViewRechargeSummaryMonthMapper;
import com.wxhj.cloud.account.service.ViewRechargeSummaryMonthService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
@Service
public class ViewRechargeSummaryMonthServiceImpl implements ViewRechargeSummaryMonthService {
    @Resource
    ViewRechargeSummaryMonthMapper viewRechargeSummaryMonthMapper;


    @Override
    public ViewRechargeSummaryMonthDO select(String accountId,Integer month) {
        Example example = new Example(ViewRechargeSummaryMonthDO.class);
        example.createCriteria().andEqualTo("accountId",accountId).andEqualTo("rechargeMonth",month);

        ViewRechargeSummaryMonthDO rechargeTotal = viewRechargeSummaryMonthMapper.selectOneByExample(example);
        if(rechargeTotal == null){
            rechargeTotal = new ViewRechargeSummaryMonthDO();
            rechargeTotal.setTotalAmount(0);
            rechargeTotal.setCount(0);
        }
        return rechargeTotal;
    }
}
