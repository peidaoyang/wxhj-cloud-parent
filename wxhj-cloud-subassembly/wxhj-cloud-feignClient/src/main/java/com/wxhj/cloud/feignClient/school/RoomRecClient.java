package com.wxhj.cloud.feignClient.school;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.fallback.RoomRecClientFallBack;
import com.wxhj.cloud.feignClient.school.requestDTO.InsertRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.UpdateRoomRecRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schoolServer",fallback = RoomRecClientFallBack.class)
public interface RoomRecClient {
    @PostMapping("/roomRec/listRoomRec")
    WebApiReturnResultModel listRoomRec(@RequestBody ListRoomRecRequestDTO listRoomRec);

    @PostMapping("/roomRec/insertRoomRec")
    WebApiReturnResultModel insertRoomRec(@RequestBody InsertRoomRecRequestDTO insertRoomRec);

    @PostMapping("/roomRec/updateRoomRec")
    WebApiReturnResultModel updateRoomRec(@RequestBody UpdateRoomRecRequestDTO updateRoomRec);

    @PostMapping("/roomRec/deleteRoomRec")
    WebApiReturnResultModel deleteRoomRec(@RequestBody CommonIdListRequestDTO commonIdList);

    @PostMapping("/roomRec/listRoomRecByRoomId")
    WebApiReturnResultModel listRoomRecByRoomId(@RequestBody CommonIdRequestDTO commonId);
}
