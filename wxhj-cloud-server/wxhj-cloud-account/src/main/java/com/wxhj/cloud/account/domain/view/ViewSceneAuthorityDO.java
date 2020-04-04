package com.wxhj.cloud.account.domain.view;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="view_scene_authority")
public class ViewSceneAuthorityDO {
	private String sceneId;
	private String authorityId;
	private String organizeId;
	private String fullName;
	private Integer type;
}
