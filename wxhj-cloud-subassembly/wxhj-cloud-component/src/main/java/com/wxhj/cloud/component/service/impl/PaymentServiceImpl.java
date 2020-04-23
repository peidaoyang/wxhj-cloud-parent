package com.wxhj.cloud.component.service.impl;

import com.wxhj.cloud.component.dto.*;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.utils.BeanMapUtil;
import com.wxhj.cloud.wechat.WXPay;
import com.wxhj.cloud.wechat.WXPayConfig;
import com.wxhj.common.device.dto.response.MicroPayResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Override
    public MicroPayResponseDTO wechatQrCodePayment(WXPayConfig payConfig, MicroPayRequestDTO microPay) throws Exception {
        MicroPayResponseDTO microPayResponse = new MicroPayResponseDTO();


        microPay.setSpbillCreateIp(InetAddress.getLocalHost().getHostAddress());
        WXPay wxPay = new WXPay(payConfig);
        Map<String, String> reqData =
                BeanMapUtil.beanToMap(microPay, true);
//            Map<String, String> reqData = new HashMap<>();
//            reqData.put("nonce_str", "d21776ff16ce4bd48ea52eca9f956934");
//            reqData.put("body", "扣款消费测试1");
//            reqData.put("out_trade_no", "20200410133301001");
//            reqData.put("total_fee", "1");
//            reqData.put("spbill_create_ip", "221.6.105.62");
//            reqData.put("auth_code", "134633052167194550");
        Map<String, String> resData = wxPay.microPay(reqData);
        microPayResponse = BeanMapUtil.mapToBean(resData, MicroPayResponseDTO.class, true);

        return microPayResponse;
    }

    @Override
    public H5UnifiedOrderResponseDTO wechatH5UnifiedOrder(WXPayConfig payConfig, H5UnifiedOrderRequestDTO h5UnifiedOrderRequest) throws Exception {
        H5UnifiedOrderResponseDTO h5UnifiedOrderResponse = null;

        WXPay wxPay = new WXPay(payConfig);
        Map<String, String> reqData =
                BeanMapUtil.beanToMap(h5UnifiedOrderRequest, true);
        Map<String, String> resData = wxPay.unifiedOrder(reqData);

        h5UnifiedOrderResponse
                = BeanMapUtil.mapToBean(resData, H5UnifiedOrderResponseDTO.class, true);

        return h5UnifiedOrderResponse;
    }

    @Override
    public H5OrderQueryResponseDTO wechatH5OrderQuery(WXPayConfig payConfig, H5OrderQueryRequestDTO h5OrderQueryRequest) throws Exception {
        H5OrderQueryResponseDTO h5OrderQueryResponse;
        WXPay wxPay = new WXPay(payConfig);
        Map<String, String> reqData =
                BeanMapUtil.beanToMap(h5OrderQueryRequest, true);
        Map<String, String> resData = wxPay.orderQuery(reqData);
        h5OrderQueryResponse = BeanMapUtil.mapToBean(resData, H5OrderQueryResponseDTO.class, true);
        return h5OrderQueryResponse;
    }

}
