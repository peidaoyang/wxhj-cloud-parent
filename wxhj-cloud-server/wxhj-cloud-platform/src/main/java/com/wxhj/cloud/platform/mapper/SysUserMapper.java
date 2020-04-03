/** 
 * @fileName: MapOrganizeUserMapper.java  
 * @author: pjf
 * @date: 2019骞�10鏈�9鏃� 涓嬪崍3:40:09 
 */

package com.wxhj.cloud.platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.wxhj.cloud.driud.common.BaseMapper;
import com.wxhj.cloud.platform.domain.SysUserDO;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {

}
