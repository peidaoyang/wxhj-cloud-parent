package com.wxhj.cloud.feignClient.school;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.school.fallback.DormitoryClientFallBack;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitDormitoryRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schoolServer",fallback = DormitoryClientFallBack.class)
public interface DormitoryClient {
    @PostMapping("/dormitory/listDormitory")
    WebApiReturnResultModel listDormitory(@RequestBody CommonListPageRequestDTO commonListPage);

    @PostMapping("/dormitory/listDormitoryByOrgId")
    WebApiReturnResultModel listDormitoryByOrgId(@RequestBody  CommonIdRequestDTO commonId);

    @PostMapping("/dormitory/submitDormitory")
    WebApiReturnResultModel submitDormitory(@RequestBody  SubmitDormitoryRequestDTO submitDormitory);

    @PostMapping("/dormitory/deleteDormitory")
    WebApiReturnResultModel deleteDormitory(@RequestBody  CommonIdRequestDTO commonId);

    @PostMapping("/dormitory/dormitoryInfo")
    WebApiReturnResultModel dormitoryInfo(@RequestBody @Validated CommonIdRequestDTO commonId);
}
