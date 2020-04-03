package com.wxhj.cloud.platform.mapper.redis;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.domain.SceneInfoDO;
import com.wxhj.cloud.redis.mapper.AbstractRedisMapper;
@Component
public class SceneInfoRedisMapper extends AbstractRedisMapper<SceneInfoDO>{

	@Override
	public String getDatabaseName() {
		return "platfromDB";
	}

	@Override
	public Class<SceneInfoDO> getTClass() {
		return SceneInfoDO.class;
	}

}
