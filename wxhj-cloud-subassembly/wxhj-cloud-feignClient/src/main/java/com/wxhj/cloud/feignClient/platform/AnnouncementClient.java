package com.wxhj.cloud.feignClient.platform;

import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.AnnouncementClientFallBack;

@FeignClient(name = "platformServer", fallback = AnnouncementClientFallBack.class)
public interface AnnouncementClient {
	@PostMapping("/systemManage/announcement/appAnnouncementList")
	WebApiReturnResultModel appAnnouncementList(@RequestBody CommonListPageRequestDTO commonListPageRequest);

	@PostMapping("/systemManage/announcement/selectById")
	WebApiReturnResultModel selectById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest);

}
