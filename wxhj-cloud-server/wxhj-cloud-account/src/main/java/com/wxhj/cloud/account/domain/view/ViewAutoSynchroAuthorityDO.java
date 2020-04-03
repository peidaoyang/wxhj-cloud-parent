package com.wxhj.cloud.account.domain.view;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="view_auto_synchro_authority")
public class ViewAutoSynchroAuthorityDO {
	@Id
	private String id;
	
	private String organizeId;
	
	private String fullName;
	
	private Integer type;
	
	private Integer autoSynchro;
}
