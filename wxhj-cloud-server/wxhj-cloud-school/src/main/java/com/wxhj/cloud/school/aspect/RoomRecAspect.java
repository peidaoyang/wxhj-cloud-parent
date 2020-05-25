package com.wxhj.cloud.school.aspect;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.DateError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;
import com.wxhj.cloud.feignClient.account.request.DeleteByAccountIdAndAuthorityIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthListRequestDTO;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.service.RoomRecService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/21
 * @Version V1.0
 **/
@Aspect
@Component
public class RoomRecAspect {
    @Resource
    MapperClient mapperClient;
    @Resource
    RoomRecService roomRecService;

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomRecService.insertCascade(..))")
    public void roomRecInsertCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomRecService.updateCascade(..))")
    public void roomRecUpdateCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomRecService.deleteCascade(..))")
    public void roomRecDeleteCut(){}

    @Pointcut("execution(public void com.wxhj.cloud.school.service.RoomRecService.shameDeleteCascade(..))")
    public void roomRecShameDeleteCascadeCut(){}

    @Before(value = "roomRecInsertCut()")
    public void roomRecInsert(JoinPoint joinPoint){
        List<RoomRecDO> roomRecDOList = (List<RoomRecDO>) joinPoint.getArgs()[0];
        List<MapAccountAuthorityBO>  mapAccountAuthorityList= roomRecDOList.stream().filter(q-> q.getAccountId() != null).map(q->new MapAccountAuthorityBO(null,q.getDormitoryId(),q.getAccountId())).collect(Collectors.toList());
        if(mapAccountAuthorityList.size()>0){
            WebApiReturnResultModel webApiReturnResultModel = mapperClient.submitMapAccountAuthorityList(new SubmitMapAccountAuthListRequestDTO(mapAccountAuthorityList));
            if(!webApiReturnResultModel.resultSuccess()){
                throw new DateError(WebResponseState.SCHOOL_ROOM_ACCOUNT_ERROR);
            }
        }
    }

    @Before(value = "roomRecUpdateCut()")
    public void roomRecUpdate(JoinPoint joinPoint) throws Throwable{
        RoomRecDO roomRecDO = (RoomRecDO)joinPoint.getArgs()[0];
        RoomRecDO deleteRoomRec = roomRecService.select(roomRecDO.getId());
        if(!roomRecDO.getAccountId().equals(deleteRoomRec.getAccountId())){
            //如果切换人员，则意味着此人需要从权限组踢出
            WebApiReturnResultModel webApiReturnResultModel = mapperClient.deleteByAccountIdAndAuthorityId(new DeleteByAccountIdAndAuthorityIdRequestDTO(deleteRoomRec.getDormitoryId(),deleteRoomRec.getAccountId()));
            if (!webApiReturnResultModel.resultSuccess()) {
                throw new Throwable("权限组删除人员失败");
            }
            if(roomRecDO.getAccountId() != null){
                webApiReturnResultModel = mapperClient.submitMapAccountAuthorityList(new SubmitMapAccountAuthListRequestDTO(Arrays.asList(new MapAccountAuthorityBO(null,deleteRoomRec.getDormitoryId(),roomRecDO.getAccountId()))));
                if (!webApiReturnResultModel.resultSuccess()) {
                    throw new Throwable("权限组添加人员失败");
                }
            }
        }
    }

    @Before(value = "roomRecDeleteCut()")
    public void roomRecDelete(JoinPoint joinPoint) throws Throwable{
        String roomRecId = (String)joinPoint.getArgs()[0];
        RoomRecDO deleteRoomRec = roomRecService.select(roomRecId);
        if(deleteRoomRec.getAccountId() != null){
            WebApiReturnResultModel webApiReturnResultModel = mapperClient.deleteByAccountIdAndAuthorityId(new DeleteByAccountIdAndAuthorityIdRequestDTO(deleteRoomRec.getDormitoryId(),deleteRoomRec.getAccountId()));
            if (!webApiReturnResultModel.resultSuccess()) {
                throw new Throwable("权限组删除人员失败");
            }
        }
    }

    @Before(value = "roomRecShameDeleteCascadeCut()")
    public void roomRecShameDeleteCascade(JoinPoint joinPoint) throws Throwable{
        String roomRecId = (String)joinPoint.getArgs()[0];
        RoomRecDO deleteRoomRec = roomRecService.select(roomRecId);
        if(deleteRoomRec.getAccountId() != null){
            WebApiReturnResultModel webApiReturnResultModel = mapperClient.deleteByAccountIdAndAuthorityId(new DeleteByAccountIdAndAuthorityIdRequestDTO(deleteRoomRec.getDormitoryId(),deleteRoomRec.getAccountId()));
            if (!webApiReturnResultModel.resultSuccess()) {
                throw new Throwable("权限组删除人员失败");
            }
        }
    }
}
