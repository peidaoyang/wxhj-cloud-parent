/** 
 * @fileName: SysOrganizeRedisMapper.java  
 * @author: pjf
 * @date: 2019年12月3日 下午3:35:06 
 */

package com.wxhj.cloud.platform.mapper.redis;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.redis.mapper.AbstractRedisMapper;

/**
 * @className SysOrganizeRedisMapper.java
 * @author pjf
 * @date 2019年12月3日 下午3:35:06   
*/
/**
 * @className SysOrganizeRedisMapper.java
 * @author pjf
 * @date 2019年12月3日 下午3:35:06
 */
@Component
public class SysOrganizeRedisMapper extends AbstractRedisMapper<SysOrganizeDO> {

	@Override
	public String getDatabaseName() {
		return "platformDB";
	}

	@Override
	public Class<SysOrganizeDO> getTClass() {
		
		return SysOrganizeDO.class;
	}
}
