/** 
 * @fileName: FaceChangeRecRedisDO.java  
 * @author: pjf
 * @date: 2019年12月5日 上午8:41:46 
 */

package com.wxhj.cloud.redis.domain;

import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

/**
 * @className FaceChangeRecRedisDO.java
 * @author pjf
 * @date 2019年12月5日 上午8:41:46   
*/
/**
 * @className FaceChangeRecRedisDO.java
 * @author pjf
 * @date 2019年12月5日 上午8:41:46
 */
@ToString
@Data
public class FaceChangeRecRedisDO {
	private String id;

	private Long currentIndex;

	private Long masterId;

	private String accountId;

	private String imageName;

	private Integer operateType;
	
	private String idNumber;
	
	private String name;

	private String phoneNumber;

	private String cardNumber;
}
