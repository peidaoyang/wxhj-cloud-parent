package com.wxhj.cloud.feignClient.account.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapAccountAuthorityBO {
	private String id;
	private String authorityGroupId;
	private String accountId;
}
