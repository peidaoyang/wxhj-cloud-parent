/** 
 * @fileName: MapSceneAccountMapper.java  
 * @author: pjf
 * @date: 2019年11月27日 下午1:35:26 
 */

package com.wxhj.cloud.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.wxhj.cloud.account.domain.MapSceneAccountDO;
import com.wxhj.cloud.driud.common.BaseMapper;

/**
 * @className MapSceneAccountMapper.java
 * @author pjf
 * @date 2019年11月27日 下午1:35:26
 */

//@mapper
public interface MapSceneAccountMapper extends BaseMapper<MapSceneAccountDO> {
	@Update({ "update map_scene_account set total_count=total_count+1,last_datetime=SYSDATE() where id=#{id}" })
	void addTotalCount(String id);
	@Update({ "update map_scene_account set total_count=total_count-1,last_datetime=SYSDATE() where id=#{id}" })
	void subtractTotalCount(String id);
}
