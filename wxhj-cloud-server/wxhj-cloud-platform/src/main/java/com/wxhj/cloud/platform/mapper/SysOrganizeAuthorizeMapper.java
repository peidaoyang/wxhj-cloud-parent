package com.wxhj.cloud.platform.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wxhj.cloud.driud.common.BaseMapper;
import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;

//@mapper
public interface SysOrganizeAuthorizeMapper extends BaseMapper<SysOrganizeAuthorizeDO> {

	@Insert({ "insert into sys_organize_authorize (", 
		"id,module_id,organize_id,creator_time,creator_user_id) ",
			"select uuid() as id,module_id,#{organizeId},",
			"SYSDATE(),#{userId} from sys_organize_authorize_defalut" })
	void insertDefalut(@Param("organizeId") String organizeId, @Param("userId") String userId);

	
}
