package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.WechatPayClient;
import com.wxhj.cloud.feignClient.account.request.WechatH5OrderQueryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.WechatH5UnifiedOrderRequestDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WechatPayClientFallBack implements WechatPayClient {
    @Override
    public WebApiReturnResultModel h5UnifiedOrder(WechatH5UnifiedOrderRequestDTO wechatH5UnifiedOrderRequest ) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel h5OrderQuery(WechatH5OrderQueryRequestDTO wechatH5OrderQueryRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
