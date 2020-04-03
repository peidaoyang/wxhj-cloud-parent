package com.wxhj.cloud.account.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table(name="auto_synchro_authority")
public class AutoSynchroAuthorityDO {
	@Id
	private String authorityGroupId;
	
	private Integer autoSynchro;
}
