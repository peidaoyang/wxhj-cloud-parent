package com.wxhj.cloud.feignClient.school;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.fallback.RoomClientFallBack;
import com.wxhj.cloud.feignClient.school.requestDTO.BatchSubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomByDormitoryIdRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitRoomRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schoolServer",fallback = RoomClientFallBack.class)
public interface RoomClient {
    @PostMapping("/room/listRoom")
    WebApiReturnResultModel listRoom(@RequestBody ListRoomRequestDTO listRoom);

    @PostMapping("/room/listRoomByDormitoryId")
    WebApiReturnResultModel listRoomByDormitoryId(@RequestBody ListRoomByDormitoryIdRequestDTO listRoomByDormitoryId);

    @PostMapping("/room/submitRoom")
    WebApiReturnResultModel submitRoom(@RequestBody SubmitRoomRequestDTO submitRoom);

    @PostMapping("/room/deleteRoom")
    WebApiReturnResultModel deleteRoom(@RequestBody CommonIdListRequestDTO commonIdList);

    @PostMapping("/room/batchSubmitRoom")
    WebApiReturnResultModel batchSubmitRoom(@RequestBody BatchSubmitRoomRequestDTO batchSubmitRoom);
}
