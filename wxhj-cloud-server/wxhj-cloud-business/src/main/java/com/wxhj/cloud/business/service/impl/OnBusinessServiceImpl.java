package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.mapper.OnBusinessMapper;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIdList(List<String> idList) {
        idList.forEach(item -> delete(item));
    }

    @Override
    public Boolean validateBeforeInsert(OnBusinessDO onBusinessDO) {
        List<Integer> statusList = Arrays.asList(ApproveStatusEnum.APPROVE_SUCCESS.getCode(), ApproveStatusEnum.APPROVING.getCode());
        List<OnBusinessDO> onBusinesses = listByAccountIdAndStatusLimitTime(onBusinessDO.getAccountId(),
                statusList, onBusinessDO.getStartTime(), onBusinessDO.getEndTime());
        return onBusinesses.size() == 0;
    }

    @Override
    public String insert(OnBusinessDO onBusiness) {
        String id = UUID.randomUUID().toString();
        onBusiness.setId(id);
        onBusiness.setCreateTime(new Date());
        onBusiness.setStatus(ApproveStatusEnum.APPROVING.getCode());
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
        Example example = new Example(OnBusinessDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(nameValue)) {
            criteria.andLike("accountName", "%" + nameValue + "%");
        }
        if (status != null && status != 0) {
            criteria.andEqualTo("status", status);
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> onBusinessMapper.selectByExample(example));
    }

    @Override
    public List<OnBusinessDO> listByAccountIdAndStatusLimitTime(String accountId, List<Integer> statusList, Date beginTime, Date endTime) {
        if (Strings.isNullOrEmpty(accountId) || beginTime == null || endTime == null) {
            return new ArrayList<>();
        }
        Example example = new Example(OnBusinessDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andIn("status", statusList)
                .andLessThan("startTime", endTime).andGreaterThan("endTime", beginTime);
        return onBusinessMapper.selectByExample(example);
    }

}
