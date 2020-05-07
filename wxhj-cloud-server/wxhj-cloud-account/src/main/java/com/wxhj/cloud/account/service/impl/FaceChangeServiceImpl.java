package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.mapper.FaceChangeMapper;
import com.wxhj.cloud.account.service.FaceChangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FaceChangeServiceImpl implements FaceChangeService {
    @Resource
    FaceChangeMapper faceChangeMapper;

    @Override
    public FaceChangeDO selectBySceneId(String sceneId) {
        Example example = new Example(FaceChangeDO.class);
        example.createCriteria().andEqualTo("id", sceneId);
        return faceChangeMapper.selectOneByExample(example);
    }

    @Override
    public List<FaceChangeDO> listBySceneIdList(List<String> idList) {

        Example example = new Example(FaceChangeDO.class);
        example.createCriteria().andIn("id", idList);
        return faceChangeMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public Long selectCurrentIndex(String id) {

        FaceChangeDO faceChange;
        faceChange = faceChangeMapper.selectByPrimaryKey(id);
        if (faceChange == null) {
            faceChange = new FaceChangeDO();
            faceChange.setId(id);
            faceChange.setMinIndex(0L);
            faceChange.setMaxIndex(0L);
            faceChangeMapper.insert(faceChange);
            return 0L;
        }
        faceChange.setMaxIndex(faceChange.getMaxIndex() + 1);
        faceChange.setMinIndex(null);
        faceChangeMapper.updateByPrimaryKeySelective(faceChange);
        return faceChange.getMaxIndex();
    }

    @Override
    public List<FaceChangeDO> listAll() {

        return faceChangeMapper.selectAll();
    }

}
