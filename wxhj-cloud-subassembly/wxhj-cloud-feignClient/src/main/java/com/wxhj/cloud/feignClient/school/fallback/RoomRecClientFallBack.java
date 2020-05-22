package com.wxhj.cloud.feignClient.school.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomRecClient;
import com.wxhj.cloud.feignClient.school.requestDTO.InsertRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.UpdateRoomRecRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Component
public class RoomRecClientFallBack implements RoomRecClient {
    @Override
    public WebApiReturnResultModel listRoomRec(ListRoomRecRequestDTO listRoomRec) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel insertRoomRec(InsertRoomRecRequestDTO insertRoomRec) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel updateRoomRec(UpdateRoomRecRequestDTO updateRoomRec) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteRoomRec(CommonIdListRequestDTO commonIdList) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listRoomRecByRoomId(CommonIdRequestDTO commonId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
