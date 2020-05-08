package com.wxhj.cloud.device.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceFaceExceptionDO;

import java.util.List;

/**
 * 设备人脸下发，异常信息上送service
 *
 * @author daxiong
 * @date 2020/5/7 11:42 上午
 */
public interface DeviceFaceExceptionService {

    /**
     * 根据组织id获取设备人脸错误列表
     *
     * @param iPageRequestModel
     * @param organizeId
     * @param sceneId
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.device.domain.DeviceFaceExceptionDO>
     * @author daxiong
     * @date 2020/5/7 1:21 下午
     */
    PageInfo<DeviceFaceExceptionDO> listByOrganizedAndSceneId(IPageRequestModel iPageRequestModel,
                                                              String organizeId, String sceneId);

    /**
     * 插入
     *
     * @param deviceFaceException
     * @return void
     * @author daxiong
     * @date 2020/5/7 1:24 下午
     */
    void insert(DeviceFaceExceptionDO deviceFaceException);

    /**
     * 修改 - 有值才修改
     *
     * @param deviceFaceException
     * @return void
     * @author daxiong
     * @date 2020/5/7 1:24 下午
     */
    void updateSelective(DeviceFaceExceptionDO deviceFaceException);

    /**
     * 批量忽略
     *
     * @param idList
     * @return void
     * @author daxiong
     * @date 2020/5/7 1:24 下午
     */
    void batchIgnore(List<String> idList);

}
