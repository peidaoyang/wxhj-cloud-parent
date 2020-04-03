package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubmitMapAccountAuthListRequestDTO {

	private List<MapAccountAuthorityBO> mapAccountAuthorityList;
}
