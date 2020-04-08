package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * 请假service
 * @author daxiong
 * @date 2020-04-07 13:38
 */
public interface AskForLeaveService {

    /**
     * 插入请假记录
     * @param askForLeave
     * @return
     */
    String insert(AskForLeaveDO askForLeave);

    /**
     * 更新请假记录
     * @param askForLeave
     * @return
     */
    void update(AskForLeaveDO askForLeave);

    /**
     * 获取请假记录列表
     * @param iPageRequestModel 分页信息
     * @param organizeId    组织id
     * @param nameValue     要搜索的值-请假用户名
     * @param status        请假状态
     * @return
     */
    PageInfo<AskForLeaveDO> listPageByOrgIdAndStatusAndName(IPageRequestModel iPageRequestModel,
                                                            String organizeId, String nameValue, Integer status);

    /**
     * 删除请假记录
     * @author daxiong
     * @date 2020-04-08 09:23
     * @param id    主键id
     * @return void
     */
    void delete(String id);
}
