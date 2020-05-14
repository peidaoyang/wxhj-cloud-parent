/**
 * @fileName: MapSceneAccountDO.java
 * @author: pjf
 * @date: 2019年11月27日 下午1:31:52
 */

package com.wxhj.cloud.account.domain;


import com.wxhj.cloud.core.interfaces.IModelInitialization;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @className MapSceneAccountDO.java
 * @author pjf
 * @date 2019年11月27日 下午1:31:52
 */

@Table(name = "map_scene_account")
@Data
@ToString
public class MapSceneAccountDO implements IModelInitialization {
    @Id
    private String id;
    private String sceneId;
    private String accountId;
    private Integer totalCount;
    private LocalDateTime lastDatetime;

    @Override
    public void initialization() {
        id = UUID.randomUUID().toString();
        totalCount = 0;
        lastDatetime = LocalDateTime.now();
    }
}
