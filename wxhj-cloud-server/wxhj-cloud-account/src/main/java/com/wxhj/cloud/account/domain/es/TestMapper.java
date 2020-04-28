package com.wxhj.cloud.account.domain.es;

import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseMapper;
import org.springframework.stereotype.Component;

/**
 * @author wxpjf
 * @date 2020/4/28 14:32
 */
@Component
public class TestMapper extends ElasticSearchBaseMapper<TestDO> {
    @Override
    protected Class<TestDO> getTClass() {
        return TestDO.class;
    }
}
