package com.wxhj.cloud.school.controller.dormitoryManage;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.school.RoomClient;
import com.wxhj.cloud.feignClient.school.requestDTO.BatchSubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomByDormitoryIdRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.ListRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitRoomRequestDTO;
import com.wxhj.cloud.feignClient.school.vo.ListRoomByDormitoryIdVO;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.domain.view.ViewRoomTotalDO;
import com.wxhj.cloud.school.service.RoomService;
import com.wxhj.cloud.school.service.ViewRoomTotalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@RestController
@RequestMapping("/room")
@Api(tags="房间设置")
public class RoomController implements RoomClient {
    @Resource
    RoomService roomService;
    @Resource
    ViewRoomTotalService viewRoomTotalService;
    @Resource
    Mapper mapper;

    @ApiOperation("分页查询房间信息")
    @PostMapping("/listRoom")
    @Override
    public WebApiReturnResultModel listRoom(@RequestBody @Validated ListRoomRequestDTO listRoom){
        PageInfo<ViewRoomTotalDO> pageList = viewRoomTotalService.list(listRoom,listRoom.getNameValue(),listRoom.getOrganizeId(),listRoom.getType(),listRoom.getDormitoryId());
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageList, pageList.getList(), new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("根据楼栋id和楼层查询房间信息")
    @PostMapping("/listRoomByDormitoryId")
    @Override
    public WebApiReturnResultModel listRoomByDormitoryId(@RequestBody @Validated ListRoomByDormitoryIdRequestDTO listRoomByDormitoryId){
        List<ListRoomByDormitoryIdVO> voList = roomService.list(listRoomByDormitoryId.getDormitoryId(),listRoomByDormitoryId.getFloorNumber(),listRoomByDormitoryId.getType()).stream().map(q-> mapper.map(q,ListRoomByDormitoryIdVO.class)).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(voList);
    }

    @ApiOperation("新增房间")
    @PostMapping("/submitRoom")
    @Override
    public WebApiReturnResultModel submitRoom(@RequestBody @Validated SubmitRoomRequestDTO submitRoom){
        RoomDO roomDO = mapper.map(submitRoom,RoomDO.class);
        if(Strings.isNullOrEmpty(submitRoom.getId())){
            roomService.insertCascade(roomDO);
        }else{
            roomService.updateCascade(roomDO);
        }
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("批量添加房间")
    @PostMapping("/batchSubmitRoom")
    @Override
    public WebApiReturnResultModel batchSubmitRoom(@RequestBody @Validated BatchSubmitRoomRequestDTO batchSubmitRoom){
        List<RoomDO> roomDOList = new ArrayList<>();
        for (int i = batchSubmitRoom.getMinFloor();i<batchSubmitRoom.getMaxFloor()+1;i++){
            for(int q=1;q<batchSubmitRoom.getRoomSize()+1;q++){
                //宿舍房间号为:楼栋号+楼层号+房间号
                int roomNumber = Integer.parseInt(batchSubmitRoom.getDormitoryNumber()+""+i+q);
                roomDOList.add(new RoomDO(UUID.randomUUID().toString(),roomNumber,batchSubmitRoom.getBedNumber(),i,batchSubmitRoom.getType(),batchSubmitRoom.getDormitoryId(),batchSubmitRoom.getOrganizeId()));
            }
        }
        roomService.insertListCascade(roomDOList);
        return WebApiReturnResultModel.ofSuccess();
    }


    @ApiOperation("删除房间")
    @PostMapping("/deleteRoom")
    @Override
    public WebApiReturnResultModel deleteRoom(@RequestBody @Validated CommonIdListRequestDTO commonIdList){
        commonIdList.getIdList().forEach(q -> {
            roomService.deleteCascade(q);
        });
        return WebApiReturnResultModel.ofSuccess();
    }

}
