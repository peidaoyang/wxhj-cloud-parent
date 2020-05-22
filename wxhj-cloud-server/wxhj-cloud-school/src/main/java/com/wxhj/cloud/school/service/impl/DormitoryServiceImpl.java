package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.DormitoryDO;
import com.wxhj.cloud.school.mapper.DormitoryMapper;
import com.wxhj.cloud.school.service.DormitoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Resource
    DormitoryMapper dormitoryMapper;

    @Override
    public void insert(DormitoryDO dormitory) {
        dormitoryMapper.insertSelective(dormitory);
    }

    @Override
    public void update(DormitoryDO dormitory) {
        dormitoryMapper.updateByPrimaryKeySelective(dormitory);
    }

    @Override
    @Transactional
    public void deleteCascade(String id) {
        dormitoryMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<DormitoryDO> listDormitory(String organizeId) {
        Example example = new Example(DormitoryDO.class);
        example.createCriteria().andEqualTo("organizeId",organizeId);
        return dormitoryMapper.selectByExample(example);
    }

    @Override
    public DormitoryDO select(String id) {
        return dormitoryMapper.selectByPrimaryKey(id);
    }


}
