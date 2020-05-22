package com.wxhj.cloud.platform.controller.school.dormitoryManage;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomRecClient;
import com.wxhj.cloud.feignClient.school.requestDTO.InsertRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.UpdateRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.vo.InsertRoomRecVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@RequestMapping("/school/roomRec")
@RestController
@Api(tags="床位设置")
public class RoomRecController {
    @Resource
    RoomRecClient roomRecClient;

    @ApiOperation(value = "入住信息分页查询")
    @PostMapping("/listRoomRec")
    @LcnTransaction
    public WebApiReturnResultModel listRoomRec(@RequestBody @Validated ListRoomRecRequestDTO listRoomRec){
        return roomRecClient.listRoomRec(listRoomRec);
    }

    @ApiOperation(value = "新增入住人员", response = InsertRoomRecVO.class)
    @PostMapping("/insertRoomRec")
    @LcnTransaction
    public WebApiReturnResultModel insertRoomRec(@RequestBody @Validated InsertRoomRecRequestDTO insertRoomRec){
        return roomRecClient.insertRoomRec(insertRoomRec);
    }

    @ApiOperation(value = "修改入住人员")
    @PostMapping("/updateRoomRec")
    @LcnTransaction
    public WebApiReturnResultModel updateRoomRec(@RequestBody @Validated UpdateRoomRecRequestDTO updateRoomRec){
        return roomRecClient.updateRoomRec(updateRoomRec);
    }

    @ApiOperation(value = "删除入住人员")
    @PostMapping("/deleteRoomRec")
    @LcnTransaction
    public WebApiReturnResultModel deleteRoomRec(@RequestBody @Validated CommonIdListRequestDTO commonIdList){
        return roomRecClient.deleteRoomRec(commonIdList);
    }

    @ApiOperation(value = "根据房间查询床位信息")
    @PostMapping("/listRoomRecByRoomId")
    @LcnTransaction
    public WebApiReturnResultModel listRoomRecByRoomId(@RequestBody @Validated CommonIdRequestDTO commonId){
        return roomRecClient.listRoomRecByRoomId(commonId);
    }
}
