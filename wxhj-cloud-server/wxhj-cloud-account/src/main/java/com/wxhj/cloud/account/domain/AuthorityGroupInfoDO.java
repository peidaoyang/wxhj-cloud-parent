package com.wxhj.cloud.account.domain;

import javax.persistence.Table;

import javax.persistence.Id;

import lombok.Data;

@Table(name="authority_group_info")
@Data
public class AuthorityGroupInfoDO {
	@Id
	private String id;
	
	private String organizeId;
	
	private String fullName;
	
	private Integer type;
	
	
}
