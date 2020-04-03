package com.wxhj.cloud.platform.vo;

import java.util.List;

import com.wxhj.cloud.platform.domain.SysRoleDO;

import lombok.Data;
/**
 * 
 * @className SysRoleVO.java
 * @author pjf
 * @date 2019年11月6日 上午8:39:04
 */
@Data
public class SysRoleVO extends SysRoleDO{
	List<String> children;
}
