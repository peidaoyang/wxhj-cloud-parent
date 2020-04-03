/** 
 * @fileName: FaceChangeDO.java  
 * @author: pjf
 * @date: 2019年11月21日 上午11:46:46 
 */

package com.wxhj.cloud.faceServer.domian;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className FaceChangeDO.java
 * @author pjf
 * @date 2019年11月21日 上午11:46:46
 */

@Data
@Table(name = "face_change")
@ToString
public class FaceChangeDO {
	@Id
	private String id;
	private Long minIndex;
	private Long maxIndex;
}
