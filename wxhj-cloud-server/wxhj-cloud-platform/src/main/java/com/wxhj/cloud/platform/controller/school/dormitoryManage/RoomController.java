package com.wxhj.cloud.platform.controller.school.dormitoryManage;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomClient;
import com.wxhj.cloud.feignClient.school.requestDTO.BatchSubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomByDormitoryIdRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.vo.ListRoomByDormitoryIdVO;
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
@RestController
@RequestMapping("/school/room")
@Api(tags="房间设置")
public class RoomController {
    @Resource
    RoomClient roomClient;

    @ApiOperation(value = "分页查询房间信息")
    @PostMapping("/listRoom")
    @LcnTransaction
    public WebApiReturnResultModel listRoom(@RequestBody @Validated ListRoomRequestDTO listRoom){
        return roomClient.listRoom(listRoom);
    }

    @ApiOperation(value = "根据楼栋id和楼层查询房间信息",response = ListRoomByDormitoryIdVO.class)
    @PostMapping("/listRoomByDormitoryId")
    @LcnTransaction
    public WebApiReturnResultModel listRoomByDormitoryId(@RequestBody @Validated ListRoomByDormitoryIdRequestDTO listRoomByDormitoryId){
        return roomClient.listRoomByDormitoryId(listRoomByDormitoryId);
    }

    @ApiOperation("新增房间")
    @PostMapping("/submitRoom")
    @LcnTransaction
    public WebApiReturnResultModel submitRoom(@RequestBody @Validated SubmitRoomRequestDTO submitRoom){
        return roomClient.submitRoom(submitRoom);
    }

    @ApiOperation("删除房间")
    @PostMapping("/deleteRoom")
    @LcnTransaction
    public WebApiReturnResultModel deleteRoom(@RequestBody @Validated CommonIdListRequestDTO commonIdList){
        return roomClient.deleteRoom(commonIdList);
    }

    @ApiOperation("批量添加房间")
    @PostMapping("/batchSubmitRoom")
    @LcnTransaction
    public WebApiReturnResultModel batchSubmitRoom(@RequestBody @Validated BatchSubmitRoomRequestDTO batchSubmitRoom){
        return roomClient.batchSubmitRoom(batchSubmitRoom);
    }
}
