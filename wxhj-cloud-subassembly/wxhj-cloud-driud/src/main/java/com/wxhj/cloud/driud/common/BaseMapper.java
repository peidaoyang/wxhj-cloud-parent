/** 
 * @fileName: BaseMapper.java  
 * @author: pjf
 * @date: 2019年10月8日 下午2:01:32 
 */

package com.wxhj.cloud.driud.common;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @className BaseMapper.java
 * @author pjf
 * @date 2019年10月8日 下午2:01:32
 */
//, IdsMapper<T>
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
	@Insert({ "REPLACE INTO ${sequenceTableName} (terminal_id) VALUES (#{terminal})" })
	int replaceSequence(String sequenceTableName, String terminal);

	@Select({ "SELECT id  FROM ${sequenceTableName} where terminal_id = #{terminal} LIMIT 1;" })
	Long selectSequence(String sequenceTableName, String terminal);
}
