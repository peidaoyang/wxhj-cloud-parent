package com.wxhj.cloud.account.domain.view;

import javax.persistence.Table;

import lombok.Data;

@Table(name="view_authority_account")
@Data
public class ViewAuthorityAccountDO {
	private String authorityGroupId;
	private String fullName;
	private String accountId;
	private Integer type;
	private String organizeId;
}
