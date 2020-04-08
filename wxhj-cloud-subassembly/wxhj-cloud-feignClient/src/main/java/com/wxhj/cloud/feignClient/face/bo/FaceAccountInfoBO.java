package com.wxhj.cloud.feignClient.face.bo;


import lombok.Data;

@Data
public class FaceAccountInfoBO {
	private String accountId;
	private String imageName;

	private String name;
	private String organizeId;

	private String idNumber;
	private String phoneNumber;
}
