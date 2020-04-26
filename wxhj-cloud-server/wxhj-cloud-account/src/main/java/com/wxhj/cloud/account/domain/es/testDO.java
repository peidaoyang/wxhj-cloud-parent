package com.wxhj.cloud.account.domain.es;

import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxpjf
 * @date 2020/4/24 15:24
 */
@ESDocument(index = "testindex")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class testDO extends ElasticSearchBaseEntity {
    private String a;
    private String b;
}
