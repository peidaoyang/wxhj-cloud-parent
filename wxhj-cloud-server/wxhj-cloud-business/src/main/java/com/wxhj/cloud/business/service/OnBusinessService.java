package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.util.Date;
import java.util.List;

/**
 * 出差service
 * @author daxiong
 * @date 2020-04-07 13:38
 */
public interface OnBusinessService {

    /**
     * 插入之前，判断是否有交差的出差
     * @author daxiong
     * @date 2020/4/17 3:44 下午
     * @param onBusinessDO
     * @return java.lang.Boolean
     */
    Boolean validateBeforeInsert(OnBusinessDO onBusinessDO);

    /**
     * 插入出差记录
     * @param onBusiness
     * @return
     */
    String insert(OnBusinessDO onBusiness);

    /**
     * 更新出差记录
     * @param onBusiness
     */
    void update(OnBusinessDO onBusiness);

    /**
     * 删除出差记录
     * @param id
     */
    void delete(String id);

    /**
     * 删除出差记录
     * @param idList
     */
    void deleteByIdList(List<String> idList);

    /**
     * 获取出差记录列表
     * @param iPageRequestModel 分页信息
     * @param organizeId    组织id
     * @param nameValue     要搜索的值-出差用户名
     * @param status        出差状态
     * @return
     */
    PageInfo<OnBusinessDO> listPageByOrgIdAndStatusAndName(IPageRequestModel iPageRequestModel,
                                                            String organizeId, String nameValue, Integer status);

    /**
     * 根据账户id获取出差记录列表
     * @param accountId    账号id
     * @param statusList   出差状态，是个list
     * @param beginTime    出差开始时间
     * @param endTime      出差结束时间
     * @return
     */
    List<OnBusinessDO> listByAccountIdAndStatusLimitTime(String accountId, List<Integer> statusList, Date beginTime, Date endTime);

    /**
     * 审核
     * @param status 审核状态
     * @see com.wxhj.cloud.core.enums.ApproveStatusEnum
     * @param id
     */
    void check(Integer status,String id);
}
