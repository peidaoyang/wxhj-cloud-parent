/** 
 * @fileName: MapSceneAccountDO.java  
 * @author: pjf
 * @date: 2019年11月27日 下午1:31:52 
 */

package com.wxhj.cloud.account.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.core.interfaces.IModelInitialization;

import lombok.Data;
import lombok.ToString;

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
	private Date lastDatetime;

	@Override
	public void initialization() {
		id = UUID.randomUUID().toString();
		totalCount = 0;
		lastDatetime = new Date();
	}
}
