package com.wxhj.cloud.school.controller.dormitoryManage;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomRecClient;
import com.wxhj.cloud.feignClient.school.requestDTO.InsertRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.UpdateRoomRecRequestDTO;
import com.wxhj.cloud.feignClient.school.vo.InsertRoomRecVO;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.service.RoomRecService;
import com.wxhj.cloud.school.service.ViewRoomRecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@RestController
@RequestMapping("/roomRec")
@Api(tags="入住设置")
public class RoomRecController implements RoomRecClient {
    @Resource
    RoomRecService roomRecService;
    @Resource
    ViewRoomRecService viewRoomRecService;
    @Resource
    Mapper mapper;

    @ApiOperation(value = "入住信息分页查询")
    @PostMapping("/listRoomRec")
    @Override
    public WebApiReturnResultModel listRoomRec(@RequestBody @Validated ListRoomRecRequestDTO listRoomRec){
        return WebApiReturnResultModel.ofSuccess(viewRoomRecService.listRoomRec(listRoomRec,listRoomRec.getNameValue(),listRoomRec.getDormitoryId()));
    }

    @ApiOperation(value = "根据房间查询床位信息")
    @PostMapping("/listRoomRecByRoomId")
    @Override
    public WebApiReturnResultModel listRoomRecByRoomId(@RequestBody @Validated CommonIdRequestDTO commonId){
        return WebApiReturnResultModel.ofSuccess(roomRecService.listByRoomId(commonId.getId()));
    }

    @ApiOperation(value = "新增入住人员", response = InsertRoomRecVO.class)
    @PostMapping("/insertRoomRec")
    @Override
    public WebApiReturnResultModel insertRoomRec(@RequestBody @Validated InsertRoomRecRequestDTO insertRoomRec){
        List<RoomRecDO> roomRecDOList = new ArrayList<>();
        List<InsertRoomRecVO> failRoomRecDOList = new ArrayList<>();
        insertRoomRec.getRoomRecList().forEach(q->{
            if(viewRoomRecService.select(q.getOtherCode(), insertRoomRec.getOrganizeId())>0){
                //同一个组织下学生只能入住一个宿舍
                failRoomRecDOList.add(new InsertRoomRecVO(q.getOtherCode(),q.getAccountName(), WebResponseState.SCHOOL_ROOM_ACCOUNT_EXCEED.getStandardMessage()));
            }else{
                roomRecDOList.add(new RoomRecDO(null,insertRoomRec.getRoomId(),insertRoomRec.getOrganizeId(),q.getNumber(),q.getOtherCode(),q.getAccountName(),q.getAccountId(),1,insertRoomRec.getOrganizeId()));
            }
        });
        roomRecService.insertCascade(roomRecDOList);

        //返回添加失败的学生
        return WebApiReturnResultModel.ofSuccess(failRoomRecDOList);
    }

    @ApiOperation(value = "修改入住人员")
    @PostMapping("/updateRoomRec")
    @Override
    public WebApiReturnResultModel updateRoomRec(@RequestBody @Validated UpdateRoomRecRequestDTO updateRoomRec){
        RoomRecDO roomRecDO = mapper.map(updateRoomRec,RoomRecDO.class);
        roomRecService.updateCascade(roomRecDO);
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation(value = "删除入住人员")
    @PostMapping("/deleteRoomRec")
    @Override
    public WebApiReturnResultModel deleteRoomRec(@RequestBody @Validated CommonIdListRequestDTO commonIdList){
        commonIdList.getIdList().forEach(q->{
            roomRecService.deleteCascade(q);
        });
        return WebApiReturnResultModel.ofSuccess();
    }
}
