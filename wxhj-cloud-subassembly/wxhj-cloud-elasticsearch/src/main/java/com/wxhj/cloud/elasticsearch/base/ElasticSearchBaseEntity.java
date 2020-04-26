package com.wxhj.cloud.elasticsearch.base;

/**
 * @author wxpjf
 * @date 2020/4/24 14:28
 */

//@JsonIgnoreProperties("id")
public abstract class ElasticSearchBaseEntity {

    private String id;

    public void putId(String id) {

        this.id = id;
    }

    public String fetchId() {
        return id;
    }


}
