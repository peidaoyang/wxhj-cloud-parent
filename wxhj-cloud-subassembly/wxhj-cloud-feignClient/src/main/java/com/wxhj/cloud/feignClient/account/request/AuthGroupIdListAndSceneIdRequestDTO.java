package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthGroupIdListAndSceneIdRequestDTO {
	private  List<String>  authGroupIdList;
	private String sceneId;
}
