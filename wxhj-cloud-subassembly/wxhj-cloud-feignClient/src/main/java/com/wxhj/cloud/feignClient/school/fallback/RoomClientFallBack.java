package com.wxhj.cloud.feignClient.school.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomClient;
import com.wxhj.cloud.feignClient.school.requestDTO.BatchSubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomByDormitoryIdRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitRoomRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Component
public class RoomClientFallBack implements RoomClient {
    @Override
    public WebApiReturnResultModel listRoom(ListRoomRequestDTO listRoom) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listRoomByDormitoryId(ListRoomByDormitoryIdRequestDTO listRoomByDormitoryId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel submitRoom(SubmitRoomRequestDTO submitRoom) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteRoom(CommonIdListRequestDTO commonIdList) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel batchSubmitRoom(BatchSubmitRoomRequestDTO batchSubmitRoom) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
