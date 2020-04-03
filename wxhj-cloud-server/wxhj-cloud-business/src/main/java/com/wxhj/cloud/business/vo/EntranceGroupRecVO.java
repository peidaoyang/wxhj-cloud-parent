/**
 * 
 */
package com.wxhj.cloud.business.vo;

import javax.persistence.Id;

import lombok.Data;

/**
 * @ClassName: EntranceGroupRecVO.java
 * @author: cya
 * @Date: 2020年3月16日 上午9:54:28 
 */
@Data
public class EntranceGroupRecVO {
	//通行组编号
	@Id
	private String entranceGroupId;
	//顺序
	private Integer serialNumber;
	//通行时间段编号
	private String entranceDayId;
	//时间描述
	private String timeDescribe;
}
