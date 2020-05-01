package com.wxhj.cloud.gateway.entity;

import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseEntity;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/4/26 4:09 下午
 */
@Data
@ESDocument(index = "log-device")
public class DeviceLogAnnotationDO extends CommonLogAnnotationDO {
}
