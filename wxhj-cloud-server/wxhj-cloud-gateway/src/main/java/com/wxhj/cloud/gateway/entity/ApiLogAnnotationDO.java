package com.wxhj.cloud.gateway.entity;

import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import lombok.Data;

/**
 * 接口日志实体
 * @author daxiong
 * @date 2020/4/26 4:09 下午
 */
@Data
@ESDocument(index = "log-api")
public class ApiLogAnnotationDO extends CommonLogAnnotationDO {
}
