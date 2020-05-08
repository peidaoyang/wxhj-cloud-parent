package com.wxhj.cloud.device.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceFaceExceptionDO;
import com.wxhj.cloud.device.mapper.DeviceFaceExceptionMapper;
import com.wxhj.cloud.device.service.DeviceFaceExceptionService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020/5/7 1:14 下午
 */
@Service
public class DeviceFaceExceptionServiceImpl implements DeviceFaceExceptionService {
    @Resource
    DeviceFaceExceptionMapper deviceFaceExceptionMapper;

    @Override
    public void batchIgnore(List<String> idList) {
        if (idList == null) {
            return;
        }
        idList.forEach(q -> updateSelective(DeviceFaceExceptionDO.builder().id(q).status(2).build()));
    }

    @Override
    public void updateSelective(DeviceFaceExceptionDO deviceFaceException) {
        deviceFaceExceptionMapper.updateByPrimaryKeySelective(deviceFaceException);
    }

    @Override
    public void insert(DeviceFaceExceptionDO deviceFaceException) {
        deviceFaceException.setId(UUID.randomUUID().toString());
        deviceFaceExceptionMapper.insert(deviceFaceException);
    }

    @Override
    public PageInfo<DeviceFaceExceptionDO> listByOrganizedAndSceneId(IPageRequestModel iPageRequestModel, String organizeId, String sceneId) {
        Example example = new Example(DeviceFaceExceptionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(sceneId)) {
            criteria.andEqualTo("sceneId", sceneId);
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> deviceFaceExceptionMapper.selectByExample(example));
    }
}
