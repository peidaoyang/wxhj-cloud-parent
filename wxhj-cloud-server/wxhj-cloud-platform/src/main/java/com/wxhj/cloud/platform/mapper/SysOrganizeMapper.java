/** 
 * @fileName: MapOrganizeUserMapper.java  
 * @author: pjf
 * @date: 2019年10月9日 下午3:40:09 
 */

package com.wxhj.cloud.platform.mapper;

import com.wxhj.cloud.platform.cache.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import com.wxhj.cloud.driud.common.BaseMapper;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;

/**
 * @className MapOrganizeUserMapper.java
 * @author pjf
 * @date 2019年10月9日 下午3:40:09   
*/

@Mapper
//@CacheNamespace(implementation = MybatisRedisCache.class)
public interface SysOrganizeMapper extends BaseMapper<SysOrganizeDO>{
	

}
