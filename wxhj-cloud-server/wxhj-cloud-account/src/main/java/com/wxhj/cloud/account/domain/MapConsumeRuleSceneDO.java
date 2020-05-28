package com.wxhj.cloud.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * (MapConsumeRuleScene)实体类
 *
 * @author makejava
 * @since 2020-05-28 10:47:57
 */
@Data
@Builder
public class MapConsumeRuleSceneDO implements Serializable {
    private static final long serialVersionUID = -28579705206986173L;
    /**
    * 消费规则id
    */
    @Id
    private String consumeRuleId;
    /**
    * 场景id
    */
    @Id
    private String sceneId;

}