package com.wxhj.cloud.school.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.school.domain.DormitoryDO;

import java.util.List;

public interface DormitoryService {
    List<DormitoryDO> listDormitory(String organizeId);

    DormitoryDO select(String id);

    void insert(DormitoryDO dormitory);

    void update(DormitoryDO dormitory);

    void deleteCascade(String id);
}
