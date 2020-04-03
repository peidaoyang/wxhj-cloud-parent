/** 
 * @fileName: MapListenListVO.java  
 * @author: pjf
 * @date: 2019年11月7日 上午10:36:16 
 */

package com.wxhj.cloud.feignClient.account.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @className MapListenListVO.java
 * @author pjf
 * @date 2019年11月7日 上午10:36:16   
*/
/**
 * @className MapListenListVO.java
 * @author pjf
 * @date 2019年11月7日 上午10:36:16
 */
@Data
@ToString
public class MapListenListVO {
	private Long id;

	private Integer operateType;

	private String sceneId;

	private String accountId;

	private Integer syncMark;
}
