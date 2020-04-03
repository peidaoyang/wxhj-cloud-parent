package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020-04-03 14:14
 */
@FeignClient(name = "accountServer")
public interface MapSceneAccountClient {
	/**
	 * 打包场景关联的人,人脸信息
	 * @param fileDownloadRequestDTO
	 * @return
	 */
	@PostMapping("/scene/account/packFaceBySceneId")
	WebApiReturnResultModel packFaceBySceneId(@RequestBody @Validated FileDownloadRequestDTO fileDownloadRequestDTO);
}
