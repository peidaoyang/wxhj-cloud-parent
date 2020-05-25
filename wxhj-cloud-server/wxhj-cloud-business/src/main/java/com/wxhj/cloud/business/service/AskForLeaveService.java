package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;


import java.time.LocalDateTime;
import java.util.List;

/**
 * 请假service
 * @author daxiong
 * @date 2020-04-07 13:38
 */
public interface AskForLeaveService {

    /**
     * 插入之前，判断是否有交差的请假
     * @author daxiong
     * @date 2020/4/17 3:44 下午
     * @param askForLeave
     * @return java.lang.Boolean
     */
    Boolean validateBeforeInsert(AskForLeaveDO askForLeave);

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
     * 根据账户id分页查询请假记录列表
     * @param iPageRequestModel
     * @param accountId
     * @return
     */
    PageInfo<AskForLeaveDO> listPageByAccountIdAndStatus(IPageRequestModel iPageRequestModel,String accountId,Integer status);

    /**
     * 根据id获取请假记录
     * @param id
     * @return
     */
    AskForLeaveDO selectById(String id);

    /**
     * 根据账户id获取请假记录列表
     * @param accountId    账号id
     * @param statusList   请假状态，是个list
     * @param beginTime    开始时间界限，不能为null
     * @param endTime      结束时间界限，不能为null
     * @return
     */
    List<AskForLeaveDO> listByAccountIdAndStatusLimitTime(
            String accountId, List<Integer> statusList, LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 删除请假记录
     * @author daxiong
     * @date 2020-04-08 09:23
     * @param id    主键id
     * @return void
     */
    void delete(String id);

    /**
     * 删除请假记录
     * @author daxiong
     * @date 2020-04-08 09:23
     * @param idList    主键id集合
     * @return void
     */
    void deleteByIdList(List<String> idList);

    /**
     * 请假审核
     * @param id
     * @param status
     * @see com.wxhj.cloud.core.enums.ApproveStatusEnum
     */
    void check(String id, Integer status);
}
