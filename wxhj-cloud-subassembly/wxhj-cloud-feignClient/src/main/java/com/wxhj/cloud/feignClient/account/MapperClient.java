/**
 * @fileName: MapoerClient.java
 * @author: pjf
 * @date: 2019年11月8日 上午8:36:12
 */

package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.MapperClientFallBack;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author pjf
 * @className MapoerClient.java
 * @date 2019年11月8日 上午8:36:12
 */

@FeignClient(name = "accountServer", fallback = MapperClientFallBack.class)
public interface MapperClient {
    /**
     * @param asyncMapListenListRequest
     * @return
     * @author pjf
     * @date 2019年11月6日 上午9:56:21
     */
    @PostMapping("/mapper/asyncMapListenList")
    WebApiReturnResultModel asyncMapListenList(@RequestBody AsyncMapListenListRequestDTO asyncMapListenListRequest);

    /**
     * @param confirmAsyncMapListenListRequest
     * @return
     * @author pjf
     * @date 2019年11月6日 上午9:56:27
     */
    @PostMapping("/mapper/confirmAsyncMapListenList")
    WebApiReturnResultModel confirmAsyncMapListenList(
            @RequestBody ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest);


    /*
     * 批量添加权限组人员
     */
    //@PostMapping("/mapper/submitMapAccountAuth")
    //WebApiReturnResultModel submitMapAccountAuth(@Validated @RequestBody SubmitMapAccountAuthRequestDTO mapAccountAuth);

    /**
     * @param mapAuthoritySceneRequestDTO
     * @return
     * @author pjf
     * @date 2019年11月6日 下午4:43:02
     */
//    @PostMapping("/mapper/submitMapAuthorityScene")
//    WebApiReturnResultModel submitMapAuthorityScene(
//            @RequestBody MapAuthoritySceneRequestDTO mapAuthoritySceneRequestDTO);

    // @PostMapping("/mapper/submitMapAuthoritySceneList")
    // WebApiReturnResultModel
    // submitMapAuthoritySceneList(MapAuthoritySceneListRequestDTO
    // mapAuthoritySceneListRequest);

    //绑定场景和权限组
    @PostMapping("/mapper/submitAuthGroupIdListAndSceneId")
    WebApiReturnResultModel submitAuthGroupIdListAndSceneId(@RequestBody AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequest);

    /**
     * @param deleteMapAuthSceneRequestDTO
     * @return
     * @author pjf
     * @date 2019年11月6日 下午4:57:28
     */
//    @PostMapping("/mapper/deleteMapAuthorityScene")
//    WebApiReturnResultModel deleteMapAuthorityScene(
//            @RequestBody DeleteMapAuthSceneRequestDTO deleteMapAuthSceneRequestDTO);

    //删除场景权限组对应表中指定数据
    @PostMapping("/mapper/deleteMapAuthSceneById")
    WebApiReturnResultModel deleteMapAuthSceneById(
            @RequestBody DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest);

    //删除人员场景对应表中的指定人员
    @PostMapping("/mapper/deleteMapAccountAuthByAccountId")
    WebApiReturnResultModel deleteMapAccountAuthByAccountId(@RequestBody CommonIdRequestDTO commonIdRequest);

//    @PostMapping("/mapper/listBySceneIdFromMapAuthScene")
//    WebApiReturnResultModel listBySceneIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest);

    //
    @PostMapping("/mapper/listByAuthIdFromMapAuthScene")
    WebApiReturnResultModel listByAuthIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest);

    @PostMapping("/mapper/listByAuthIdFromMapAuthAccount")
    WebApiReturnResultModel listByAuthIdFromMapAuthAccount(@RequestBody CommonIdRequestDTO commonIdRequest);

//    @PostMapping("/mapper/listByAuthFromMapAuthAccount")
//    WebApiReturnResultModel listByAuthFromMapAuthAccount(@RequestBody CommonIdListRequestDTO commonIdListRequest);

    //
    @PostMapping("/mapper/listViewMapAuthAccountByAuthId")
    WebApiReturnResultModel listViewMapAuthAccountByAuthId(@RequestBody CommonIdRequestDTO commonIdRequest);


    @PostMapping("/mapper/submitMapAccountAuthorityList")
    WebApiReturnResultModel submitMapAccountAuthorityList(
            @RequestBody SubmitMapAccountAuthListRequestDTO submitMapAccountAuthListRequest);
}
