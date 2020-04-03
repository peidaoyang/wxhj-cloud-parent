package com.wxhj.cloud.feignClient.face;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 场景流水号客户端
 * @author daxiong
 * @date 2020-03-31 14:33
 */
@Component
@FeignClient(name = "faceServer")
public interface FaceChangeClient {
    /**
     * 根据场景ids获取流水号，列表
     * @param commonIdListRequestDTO
     * @return
     */
    @PostMapping("/faceChange/listBySceneId")
    WebApiReturnResultModel listBySceneId(@RequestBody CommonIdListRequestDTO commonIdListRequestDTO);

    /**
     * 根据场景ids获取流水号，列表
     * @param commonIdRequestDTO
     * @return
     */
    @PostMapping("/faceChange/selectBySceneId")
    WebApiReturnResultModel selectBySceneId(@RequestBody CommonIdRequestDTO commonIdRequestDTO);

}
