package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.RefundDO;
import com.wxhj.cloud.account.mapper.RefundMapper;
import com.wxhj.cloud.account.service.RefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class RefundServiceImpl implements RefundService {
    @Resource
    RefundMapper refundMapper;

    @Override
    public void insert(RefundDO refund) {
        refund.setCreatorTime(LocalDateTime.now());
        refundMapper.insert(refund);
    }
}
