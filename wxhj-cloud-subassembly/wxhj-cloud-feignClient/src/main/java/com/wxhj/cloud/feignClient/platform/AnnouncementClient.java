package com.wxhj.cloud.feignClient.platform;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.AnnouncementClientFallBack;

@FeignClient(name = "platformServer", fallback = AnnouncementClientFallBack.class)
public interface AnnouncementClient {
	@PostMapping("/systemManage/announcement/NewestAnnouncement")
	WebApiReturnResultModel newestAnnouncement(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
