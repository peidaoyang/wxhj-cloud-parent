package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.FaceChangeClientFallback;
import com.wxhj.cloud.feignClient.account.request.ListFaceChangeRecRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountServer", fallback = FaceChangeClientFallback.class)
public interface FaceChangeClient {
    @PostMapping("/faceChange/listFaceChange")
    WebApiReturnResultModel listFaceChange(@RequestBody CommonIdListRequestDTO commonIdListRequest);

    @PostMapping("/faceChange/listFaceChangeRec")

    WebApiReturnResultModel listFaceChangeRec(@RequestBody ListFaceChangeRecRequestDTO listFaceChangeRecRequest);
}
