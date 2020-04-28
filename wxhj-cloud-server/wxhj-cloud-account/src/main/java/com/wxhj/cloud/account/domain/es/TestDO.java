package com.wxhj.cloud.account.domain.es;

import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseEntity;
import lombok.Data;

/**
 * @author wxpjf
 * @date 2020/4/28 14:27
 */
@Data
@ESDocument(index="testindex")
public class TestDO extends ElasticSearchBaseEntity {
    String a;
    String b;
}
