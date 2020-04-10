package com.wxhj.cloud.component.service.impl;

import com.wxhj.cloud.component.service.QrCodePaymentService;
import com.wxhj.cloud.wechat.IWXPayDomain;
import com.wxhj.cloud.wechat.WXPay;
import com.wxhj.cloud.wechat.WXPayConfig;
import com.wxhj.cloud.wechat.WXPayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class QrCodePaymentServiceImpl implements QrCodePaymentService {
    final TestWXPayConfig testWXPayConfig = new TestWXPayConfig();

    @Override
    public boolean qrCodeWechatPayment() {
        try {
            WXPay wxPay = new WXPay(testWXPayConfig);
            Map<String, String> reqData = new HashMap<>();
            reqData.put("nonce_str", "d21776ff16ce4bd48ea52eca9f956934");
            //reqData.put("sign", "d21776ff16ce4bd48ea52eca9f956934");
            reqData.put("body", "扣款消费测试1");
            reqData.put("out_trade_no", "20200410133301001");
            reqData.put("total_fee", "1");
            reqData.put("spbill_create_ip", "221.6.105.62");
            reqData.put("auth_code", "134633052167194550");

            Map<String, String> res = wxPay.microPay(reqData);


            log.info(res.toString());
        } catch (Exception ex) {

            return false;
        }

        //String url;
        return true;
    }

    class TestWXPayConfig extends WXPayConfig {
        @Override
        protected String getAppID() {
            return "wxc6e8024c5fc01f2c";
        }

        @Override
        protected String getMchID() {

            return "1377624502";
        }

        @Override
        protected String getKey() {
            return "9ce46665edbe4f5dbb93f84e975111d9";
        }

        @Override
        protected InputStream getCertStream() {
            return null;
        }

        @Override
        protected IWXPayDomain getWXPayDomain() {
            return new  TestWXPayDomain();
        }
    }

    class TestWXPayDomain implements IWXPayDomain {
        @Override
        public void report(String domain, long elapsedTimeMillis, Exception ex) {

        }

        @Override
        public DomainInfo getDomain(WXPayConfig config) {
            return new DomainInfo(WXPayConstants.DOMAIN_API, true);
        }
    }


}
