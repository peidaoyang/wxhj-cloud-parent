/** 
 * @fileName: FaceDataDownloadResponseDTO.java  
 * @author: pjf
 * @date: 2019年12月5日 上午9:50:37 
 */

package com.wxhj.common.device.dto.response;

import java.util.List;

import com.wxhj.common.device.vo.FaceChangeRecRedisVO;

import lombok.Data;
import lombok.ToString;

/**
 * @className FaceDataDownloadResponseDTO.java
 * @author pjf
 * @date 2019年12月5日 上午9:50:37   
*/
/**
 * @className FaceDataDownloadResponseDTO.java
 * @author pjf
 * @date 2019年12月5日 上午9:50:37
 */
@Data
@ToString
public class FaceDataDownloadResponseDTO {
	private Long startIndex;
	private Long endIndex;

	private Long faceMinIndex;
	private Long faceMaxIndex;
	private String deviceId;
	private String sceneId;
	private List<FaceChangeRecRedisVO> faceDataList;
}
