package com.wxhj.cloud.school.aspect;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.DateError;
import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.service.RoomRecService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@Aspect
@Component
public class RoomAspect {
    @Resource
    RoomRecService roomRecService;

    @Pointcut("execution(public String com.wxhj.cloud.school.service.RoomService.insertCascade(..))")
    public void roomInsertCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomService.insertListCascade(..))")
    public void roomInsertListCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomService.updateCascade(..))")
    public void roomUpdateCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomService.deleteCascade(..))")
    public void roomDeleteCut(){}

    @AfterReturning(returning = "rObject", value = "roomInsertCut()")
    public void roomInsert(JoinPoint joinPoint,Object rObject){
        String id = (String)rObject;
        RoomDO room = (RoomDO)joinPoint.getArgs()[0];
        List<RoomRecDO> roomRecDOList = new ArrayList<>();
        //根据床位数自动创建对应的床位号
        for(int i=1;i<room.getBedNumber()+1;i++){
            roomRecDOList.add(new RoomRecDO(id,room.getDormitoryId(), i,0,room.getOrganizeId()));
        }
        roomRecService.insertCascade(roomRecDOList);
    }

    @After(value = "roomInsertListCut()")
    public void roomInsertList(JoinPoint joinPoint){
        List<RoomDO> roomDOList = (List<RoomDO>)joinPoint.getArgs()[0];
        List<RoomRecDO> roomRecDOList = new ArrayList<>();
        roomDOList.forEach(q->{
            for(int i=1;i<q.getBedNumber()+1;i++){
                roomRecDOList.add(new RoomRecDO(q.getId(),q.getDormitoryId(), i,0,q.getOrganizeId()));
            }
        });
        roomRecService.insertCascade(roomRecDOList);
    }

    @Before(value = "roomUpdateCut()")
    public void roomUpdate(JoinPoint joinPoint){
        RoomDO roomDO = (RoomDO)joinPoint.getArgs()[0];
        List<RoomRecDO> roomRecDOList = roomRecService.listByRoomId(roomDO.getId());
        int oldbedNum = roomRecDOList.size();
        if(oldbedNum != roomDO.getBedNumber()){
            int bedNum = roomDO.getBedNumber();
            roomRecDOList = roomRecDOList.stream().filter(q -> q.getOtherCode() != null).collect(Collectors.toList());
            int maxResideNumber = 0;
            maxResideNumber = roomRecDOList.size()>0?roomRecDOList.stream().max(Comparator.comparing(RoomRecDO::getNumber)).get().getNumber():0;

            if(bedNum < maxResideNumber){
                //如果已经入住床位大于修改的床位数，则不需要做更新
                throw new DateError(WebResponseState.SCHOOL_ROOM_EXCEED_ERROR);
            }else{
                List<RoomRecDO> newRoomRecList = new ArrayList<>();
                for(int i=maxResideNumber+1;i < bedNum+1;i++){
                    newRoomRecList.add(new RoomRecDO(roomDO.getId(),roomDO.getDormitoryId(),i,0,roomDO.getOrganizeId()));
                }
                roomRecService.deleteByRoomIdAndGreaterThanNumber(roomDO.getId(),maxResideNumber);
                roomRecService.insertCascade(newRoomRecList);
            }
        }

    }

    @Before(value = "roomDeleteCut()")
    public void roomDelete(JoinPoint joinPoint){
        String id = (String)joinPoint.getArgs()[0];
        List<RoomRecDO> roomRecDoList = roomRecService.listByRoomId(id);
        roomRecDoList.forEach(q->{
            roomRecService.deleteCascade(q.getId());
        });
    }
}
