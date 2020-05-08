/**
 * @fileName: FaceDataDownloadResponseDTO.java
 * @author: pjf
 * @date: 2019年12月5日 上午9:50:37
 */

package com.wxhj.common.device.dto.response;

import com.wxhj.common.device.vo.FaceChangeRecVO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

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

    private Long faceMinIndex = -1L;
    private Long faceMaxIndex = -1L;
    private String deviceId;
    private String sceneId;
    private List<FaceChangeRecVO> faceDataList;
}
