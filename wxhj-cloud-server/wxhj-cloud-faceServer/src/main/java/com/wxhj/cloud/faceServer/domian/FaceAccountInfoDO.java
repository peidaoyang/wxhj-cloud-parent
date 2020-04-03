/** 
 * @fileName: FaceAcountInfo.java  
 * @author: pjf
 * @date: 2019年11月1日 下午2:52:26 
 */

package com.wxhj.cloud.faceServer.domian;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.feignClient.bo.IFaceImageModel;

import lombok.Data;

/**
 * @className FaceAcountInfo.java
 * @author pjf
 * @date 2019年11月1日 下午2:52:26   
*/
/**
 * @className FaceAcountInfo.java
 * @author pjf
 * @date 2019年11月1日 下午2:52:26
 */
@Table(name = "face_account_info")
@Data
public class FaceAccountInfoDO implements IFaceImageModel{
	@Id
	private String accountId; 
	private String faceTocken;
	private Double plusLeft;
	private Double plusTop;
	private Double width;
	private Double height;
	private Integer rotation;
	private String imageName;
	private Double faceLiveness;
	private Double frr1e4;
	private Double frr1e3;
	private Double frr1e2;
	private String name;
	private String organizeId;

	private String idNumber;
	private String phoneNumber;
}
