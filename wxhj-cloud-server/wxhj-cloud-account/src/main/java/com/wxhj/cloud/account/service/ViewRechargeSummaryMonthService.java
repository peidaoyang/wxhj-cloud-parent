package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryMonthDO;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
public interface ViewRechargeSummaryMonthService {
    ViewRechargeSummaryMonthDO select(String accountId,Integer month);
}
