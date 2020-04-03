package com.wxhj.cloud.feignClient.face.bo;

import com.wxhj.cloud.feignClient.bo.IFaceImageModel;

import lombok.Data;

@Data
public class FaceAccountInfoBO implements IFaceImageModel {
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
