package com.wxhj.cloud.component.service.impl;

import com.wxhj.cloud.component.dto.*;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.utils.BeanMapUtil;
import com.wxhj.cloud.wechat.WXPay;
import com.wxhj.cloud.wechat.WXPayConfig;
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
//        reqData.put("appid",payConfig.getAppID());
//        reqData.put("mch_id","");
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
