package com.wxhj.cloud.gateway.mapper;

import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseMapper;
import com.wxhj.cloud.gateway.entity.WebLogAnnotationDO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020/4/26 4:10 下午
 */
@Component
public class ApiLogAnnotationEsMapper extends ElasticSearchBaseMapper<WebLogAnnotationDO> {
    @Override
    protected Class<WebLogAnnotationDO> getTClass() {
        return WebLogAnnotationDO.class;
    }
}
