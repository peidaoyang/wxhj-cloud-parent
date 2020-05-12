package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.view.ViewAccountConsumeSummaryMonthDO;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
public interface ViewAccountConsumeSummaryMonthService {
    /**
     *
     * @param accountId
     * @param month yyyyMM
     * @return
     */
    ViewAccountConsumeSummaryMonthDO select(String accountId,Integer month);
}
