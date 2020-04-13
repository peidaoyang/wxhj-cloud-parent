package com.wxhj.cloud.component.service;

import com.wxhj.cloud.component.dto.*;
import com.wxhj.cloud.wechat.WXPayConfig;

public interface PaymentService {
    MicroPayResponseDTO wechatQrCodePayment(WXPayConfig payConfig, MicroPayRequestDTO microPay) throws Exception;

    H5UnifiedOrderResponseDTO wechatH5UnifiedOrder(WXPayConfig payConfig, H5UnifiedOrderRequestDTO h5UnifiedOrderRequest) throws Exception;

    H5OrderQueryResponseDTO wechatH5OrderQuery(WXPayConfig payConfig, H5OrderQueryRequestDTO h5OrderQueryRequest) throws Exception;

}
