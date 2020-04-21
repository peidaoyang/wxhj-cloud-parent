/** 
 * @fileName: MapListenListDO.java  
 * @author: pjf
 * @date: 2019年10月31日 下午1:42:04 
 */

package com.wxhj.cloud.account.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

/**
 * @className MapListenListDO.java
 * @author pjf
 * @date 2019年10月31日 下午1:42:04
 */

@Table(name = "map_listen_list")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MapListenListDO {
	@Id
	private Long id;

	private Integer operateType;

	private String sceneId;

	private String accountId;

	private Integer syncMark;

}
