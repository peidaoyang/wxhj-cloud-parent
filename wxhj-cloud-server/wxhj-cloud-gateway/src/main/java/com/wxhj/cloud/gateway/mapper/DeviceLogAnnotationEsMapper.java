package com.wxhj.cloud.gateway.mapper;

import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseMapper;
import com.wxhj.cloud.gateway.entity.DeviceLogAnnotationDO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020/4/26 4:10 下午
 */
@Component
public class DeviceLogAnnotationEsMapper extends ElasticSearchBaseMapper<DeviceLogAnnotationDO> {
    @Override
    protected Class<DeviceLogAnnotationDO> getTClass() {
        return DeviceLogAnnotationDO.class;
    }
}
