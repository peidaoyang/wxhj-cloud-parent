/** 
 * @fileName: BathInsertPlus.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:01:51 
 */

package com.wxhj.cloud.driud.mapper;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.additional.dialect.oracle.InsertListMapper;

/**
 * @className BathInsertPlus.java
 * @author pjf
 * @date 2019年10月16日 下午2:01:51
 */

@Service
@Slf4j
public class BathInsertPlus<T> {
	public int bathInsert(List<T> members, InsertListMapper<T> baseMapper) {
		int result = 0;
		if (members == null || members.size() == 0) {
			return result;
		}
		// 每批commit的个数
		int batchCount = 100;
		// 每批最后一个的下标
		int batchLastIndex = batchCount;
		for (int index = 0; index < members.size();) {
			if (batchLastIndex >= members.size()) {
				batchLastIndex = members.size();
				log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
				result += baseMapper.insertList(members.subList(index, batchLastIndex));
				// 数据插入完毕，退出循环
				break;
			} else {
				log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
				result += baseMapper.insertList(members.subList(index, batchLastIndex));
				// 设置下一批下标
				index = batchLastIndex;
				batchLastIndex = index + (batchCount - 1);
			}
		}
		return result;
	}

	public int insertCrossEvaluation(List<T> members, InsertListMapper<T> baseMapper) {

		int result = 0;
		if (members == null || members.size() == 0) {
			return result;
		}
		// TODO Auto-generated method stub
		// 每批commit的个数
		int batchCount = 100;
		// 每批最后一个的下标
		int batchLastIndex = batchCount;
		for (int index = 0; index < members.size();) {

			if (batchLastIndex >= members.size()) {
				batchLastIndex = members.size();
				log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
				result += baseMapper.insertList(members.subList(index, batchLastIndex));
				// 数据插入完毕，退出循环
				break;
			} else {
				log.info("index:" + index + " batchLastIndex:" + batchLastIndex);
				result += baseMapper.insertList(members.subList(index, batchLastIndex));
				// 设置下一批下标
				index = batchLastIndex;
				batchLastIndex = index + (batchCount - 1);
			}
		}

		return result;
	}
}
