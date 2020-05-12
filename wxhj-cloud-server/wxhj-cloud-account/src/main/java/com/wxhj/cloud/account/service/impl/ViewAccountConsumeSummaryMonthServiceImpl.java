package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.view.ViewAccountConsumeSummaryMonthDO;
import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryMonthDO;
import com.wxhj.cloud.account.mapper.view.ViewAccountConsumeSummaryMonthMapper;
import com.wxhj.cloud.account.service.ViewAccountConsumeSummaryMonthService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
@Service
public class ViewAccountConsumeSummaryMonthServiceImpl implements ViewAccountConsumeSummaryMonthService {
    @Resource
    ViewAccountConsumeSummaryMonthMapper viewAccountConsumeSummaryMonthMapper;


    @Override
    public ViewAccountConsumeSummaryMonthDO select(String accountId, Integer month) {
        Example example = new Example(ViewAccountConsumeSummaryMonthDO.class);
        example.createCriteria().andEqualTo("accountId",accountId).andEqualTo("consumeMonth",month);
        ViewAccountConsumeSummaryMonthDO consumeTotal = viewAccountConsumeSummaryMonthMapper.selectOneByExample(example);
        if(consumeTotal == null){
            consumeTotal = new ViewAccountConsumeSummaryMonthDO();
            consumeTotal.setCount(0);
            consumeTotal.setTotalAmount(0);
        }
        return consumeTotal;
    }
}
