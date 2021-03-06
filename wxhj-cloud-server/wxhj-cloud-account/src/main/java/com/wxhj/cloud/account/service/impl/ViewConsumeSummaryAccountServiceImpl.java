/**
 * @fileName: ViewConsumeSummaryAccountServiceImpl.java
 * @author: pjf
 * @date: 2020年2月5日 下午1:50:15
 */

package com.wxhj.cloud.account.service.impl;


import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.view.ViewConsumeSummaryAccountDO;
import com.wxhj.cloud.account.mapper.view.ViewConsumeSummaryAccountMapper;
import com.wxhj.cloud.account.service.ViewConsumeSummaryAccountService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @className ViewConsumeSummaryAccountServiceImpl.java
 * @author pjf
 * @date 2020年2月5日 下午1:50:15   
 */

/**
 * @className ViewConsumeSummaryAccountServiceImpl.java
 * @author pjf
 * @date 2020年2月5日 下午1:50:15
 */
@Service
public class ViewConsumeSummaryAccountServiceImpl implements ViewConsumeSummaryAccountService {

    @Resource
    ViewConsumeSummaryAccountMapper viewConsumeSummaryAccountMapper;

    @Override
    public PageInfo<ViewConsumeSummaryAccountDO> listPage(IPageRequestModel iPageRequestModel, String organizeId,
                                                          String name, LocalDate beginTime, LocalDate endTime) {
        return listPage(iPageRequestModel, organizeId, null, name, beginTime, endTime);
    }

    @Override
    public PageInfo<ViewConsumeSummaryAccountDO> listPage(IPageRequestModel iPageRequestModel, String organizeId,
                                                          String accountId, String name, LocalDate beginTime, LocalDate endTime) {
        Example example = new Example(ViewConsumeSummaryAccountDO.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId)
                .andLike("name", "%" + name + "%")
                .andBetween("consumeDate",
                        beginTime, endTime);
        if (!Strings.isNullOrEmpty(accountId)) {
            criteria.andEqualTo("accountId", accountId);
        }
        return PageUtil.selectPageList(iPageRequestModel,
                () -> viewConsumeSummaryAccountMapper.selectByExample(example));
    }


    @Override
    public List<ViewConsumeSummaryAccountDO> list(String organizeId, String name, LocalDate beginTime, LocalDate endTime) {
        Example example = new Example(ViewConsumeSummaryAccountDO.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId)
                .andLike("name", "%" + name + "%")
                .andBetween("consumeDate",beginTime, endTime);
        return viewConsumeSummaryAccountMapper.selectByExample(example);
    }

}
