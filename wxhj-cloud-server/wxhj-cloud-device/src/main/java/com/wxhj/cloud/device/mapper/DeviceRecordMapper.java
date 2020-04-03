/** 
 * @fileName: DeviceRecordMapper.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:27:32 
 */

package com.wxhj.cloud.device.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wxhj.cloud.device.domain.DeviceRecordDO;
import com.wxhj.cloud.driud.common.BaseMapper;


/**
 * @className DeviceRecordMapper.java
 * @author pjf
 * @date 2020年2月11日 下午3:27:32
 */

@Mapper
public interface DeviceRecordMapper extends BaseMapper<DeviceRecordDO> {

}
