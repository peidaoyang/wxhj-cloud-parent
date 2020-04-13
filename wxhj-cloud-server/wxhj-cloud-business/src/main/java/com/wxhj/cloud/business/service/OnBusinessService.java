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
     * @param status       出差状态
     * @param beginTime    出差开始时间
     * @param endTime      出差结束时间
     * @return
     */
    List<OnBusinessDO> listByAccountIdAndStatusLimitTime(String accountId, Integer status, Date beginTime, Date endTime);

}
