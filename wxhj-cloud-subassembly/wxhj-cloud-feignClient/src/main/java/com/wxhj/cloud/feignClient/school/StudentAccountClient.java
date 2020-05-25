package com.wxhj.cloud.feignClient.school;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.request.AccountByOtherCodeAndTypeRequestDTO;
import com.wxhj.cloud.feignClient.school.fallback.StudentAccountClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author cya
 * @Date 2020/5/22
 * @Version V1.0
 **/
@FeignClient(name = "schoolServer",fallback = StudentAccountClientFallBack.class)
public interface StudentAccountClient {
    @PostMapping("/studentAccount/accountByOtherCodeAndType")
    WebApiReturnResultModel accountByIdAndType(@RequestBody @Validated AccountByOtherCodeAndTypeRequestDTO accountByOtherCodeAndType);
}
