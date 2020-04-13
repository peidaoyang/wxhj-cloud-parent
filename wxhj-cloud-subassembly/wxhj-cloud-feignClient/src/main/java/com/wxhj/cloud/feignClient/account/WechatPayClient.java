package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.WechatPayClientFallBack;
import com.wxhj.cloud.feignClient.account.request.WechatH5OrderQueryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.WechatH5UnifiedOrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "accountServer", fallback = WechatPayClientFallBack.class)
public interface WechatPayClient {
    @PostMapping("/wechat/h5UnifiedOrder")
    WebApiReturnResultModel h5UnifiedOrder(@RequestBody WechatH5UnifiedOrderRequestDTO wechatH5UnifiedOrderRequest);
    @PostMapping("/wechat/h5OrderQuery")
    WebApiReturnResultModel h5OrderQuery(@RequestBody WechatH5OrderQueryRequestDTO wechatH5OrderQueryRequest);
}
