/** 
 * @fileName: deleteMapAuthSceneByIdRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月18日 上午9:27:58 
 */

package com.wxhj.cloud.feignClient.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className deleteMapAuthSceneByIdRequestDTO.java
 * @author pjf
 * @date 2019年11月18日 上午9:27:58
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMapAuthSceneByIdRequestDTO {
	//deleteType为0时id为sceneId,为1时id为authorityId
	private Integer deleteType;
	private String id;
}
