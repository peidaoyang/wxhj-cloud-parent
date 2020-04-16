/** 
 * @fileName: DeviceStateMapper.java  
 * @author: pjf
 * @date: 2020年2月11日 下午2:14:12 
 */

package com.wxhj.cloud.device.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.wxhj.cloud.device.domain.DeviceStateDO;
import com.wxhj.cloud.driud.common.BaseMapper;

/**
 * @className DeviceStateMapper.java
 * @author pjf
 * @date 2020年2月11日 下午2:14:12   
*/
/**
 * @className DeviceStateMapper.java
 * @author pjf
 * @date 2020年2月11日 下午2:14:12
 */
//@mapper
public interface DeviceStateMapper extends BaseMapper<DeviceStateDO> {

	@Insert({ "REPLACE INTO device_state (device_id,organize_id,scene_id,parameter_version,",
			"face_serial_number,device_time,last_time,device_name,device_type)",
			" VALUES (#{deviceId},#{organizeId},#{sceneId},#{parameterVersion},#{faceSerialNumber},",
			"#{deviceTime, jdbcType=TIMESTAMP},#{lastTime, jdbcType=TIMESTAMP},#{deviceName},#{deviceType})" })
	int replace(DeviceStateDO deviceState);
}
