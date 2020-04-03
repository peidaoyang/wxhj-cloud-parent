/** 
 * @fileName: ListDeviceByIdListRequestDTO.java  
 * @author: pjf
 * @date: 2020年2月5日 下午12:47:28 
 */

package com.wxhj.cloud.feignClient.device.request;

import java.util.List;

import lombok.Data;

/**
 * @className ListDeviceByIdListRequestDTO.java
 * @author pjf
 * @date 2020年2月5日 下午12:47:28   
*/
/**
 * @className ListDeviceByIdListRequestDTO.java
 * @author pjf
 * @date 2020年2月5日 下午12:47:28
 */
@Data
public class ListDeviceByIdListRequestDTO {
	private List<String> deviceIdList;
}
