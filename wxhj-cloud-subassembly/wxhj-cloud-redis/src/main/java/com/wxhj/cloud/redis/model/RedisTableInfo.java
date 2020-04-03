/** 
 * @fileName: RedisTableInfo.java  
 * @author: pjf
 * @date: 2019年12月3日 上午11:29:31 
 */

package com.wxhj.cloud.redis.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.HumpUtil;

/**
 * @className RedisTableInfo.java
 * @author pjf
 * @date 2019年12月3日 上午11:29:31
 */

@Data
@ToString

public class RedisTableInfo {

	private String tableName;
	private String databaseName;
	private List<String> tableKeyList;
	private List<Field> fieldList;
	private Class<?> redisTableClass;

	public String getRedisKey() {
		return RedisKeyStaticClass.TABLE_REDIS_KEY.concat(databaseName).concat("#").concat(tableName);
	}

	public RedisTableInfo() {
		super();
	}

	public RedisTableInfo(Class<?> tclass, String databaseName) {
		this.databaseName = databaseName;
		redisTableClass = tclass;
		Table tableAnnotation = tclass.getAnnotation(Table.class);
		tableKeyList = new ArrayList<String>();
		tableName = tableAnnotation.name();
		fieldList = Arrays.asList(tclass.getDeclaredFields());
		fieldList.forEach(q -> {
			q.setAccessible(true);
			if (q.getAnnotation(Id.class) != null) {
				tableKeyList.add(q.getName());
			}
		});
	}
}
