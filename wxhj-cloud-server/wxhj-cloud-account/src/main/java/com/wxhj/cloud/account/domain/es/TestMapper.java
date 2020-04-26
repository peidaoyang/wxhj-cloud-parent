package com.wxhj.cloud.account.domain.es;

import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseMapper;
import org.springframework.stereotype.Component;

/**
 * @author wxpjf
 * @date 2020/4/24 15:37
 */
@Component
public class TestMapper extends ElasticSearchBaseMapper<testDO> {

    @Override
    protected Class<testDO> getTClass() {
        return testDO.class;
    }
}
