package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.mapper.OnBusinessMapper;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020-04-07 13:59
 */
@Service
public class OnBusinessServiceImpl implements OnBusinessService {

    @Resource
    OnBusinessMapper onBusinessMapper;

    @Override
    public void delete(String id) {
        onBusinessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String insert(OnBusinessDO onBusiness) {
        String id = UUID.randomUUID().toString();
        onBusiness.setId(id);
        onBusiness.setCreateTime(new Date());
        onBusinessMapper.insert(onBusiness);
        return id;
    }

    @Override
    public void update(OnBusinessDO onBusiness) {
        onBusinessMapper.updateByPrimaryKeySelective(onBusiness);
    }

    @Override
    public PageInfo<OnBusinessDO> listPageByOrgIdAndStatusAndName(IPageRequestModel iPageRequestModel,
                                                                   String organizeId, String nameValue, Integer status) {
        Example example = new Example(AskForLeaveDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("accountName", "%" + nameValue + "%");
        }
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> onBusinessMapper.selectByExample(example));
    }


}
