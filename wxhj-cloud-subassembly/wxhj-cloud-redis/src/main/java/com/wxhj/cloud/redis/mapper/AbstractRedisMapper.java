/** 
 * @fileName: AbstractRedisMapper.java  
 * @author: pjf
 * @date: 2019年12月3日 上午11:12:30 
 */

package com.wxhj.cloud.redis.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.SqlOrderByUtil;
import com.wxhj.cloud.redis.model.RedisTableInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @className AbstractRedisMapper.java
 * @author pjf
 * @date 2019年12月3日 上午11:12:30
 */
@Slf4j
public abstract class AbstractRedisMapper<T> {
	@Resource
	RedisTemplate redisTemplate;

	private static final int redisOneRowCount = 50;
	private static Map<String, RedisTableInfo> tableInfoMap = new HashMap<>();

	/*
	 * 反射获取RedisTableInfo
	 */
	protected RedisTableInfo getTableInfo() {
		String className = getTClass().getName();
		RedisTableInfo redisTableInfo = tableInfoMap.get(className);
		if (redisTableInfo == null) {
			redisTableInfo = new RedisTableInfo(getTClass(), getDatabaseName());
			tableInfoMap.put(className, redisTableInfo);
		}
		return redisTableInfo;
	}

	/*
	 * 获取库名
	 */
	public abstract String getDatabaseName();

	/*
	 * 获取条件
	 */
	protected Map<String, Object> getConditionMap(T t) {
		RedisTableInfo redisTableInfo = getTableInfo();
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		redisTableInfo.getFieldList().forEach(q -> {
			try {
				Object obj = q.get(t);
				if (obj != null) {
					conditionMap.put(q.getName(), obj);
				}
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
			}
		});
		return conditionMap;
	}

	/*
	 * 分离出主键条件
	 */
	protected List<String> getKeyCondition(Map<String, Object> conditionMap, List<String> keyList) {
		List<String> keyCondition = new ArrayList<>();
		keyList.forEach(q -> {
			Object objTemp = conditionMap.get(q);
			if (objTemp != null) {
				keyCondition.add(q.concat(":").concat(objTemp.toString()));
			}
		});
		return keyCondition;
	}

	/*
	 * 获取该数据库所有haskey
	 */
	protected List<String> redisHasKey() {
		RedisTableInfo redisTableInfo = getTableInfo();
		List<String> hasKeyList = new ArrayList<>();
		Set<String> keys = redisTemplate.opsForHash().keys(redisTableInfo.getRedisKey());
		return new ArrayList<String>(keys);
	}

	/*
	 * 对获取的haskey进行筛选
	 */
	protected List<String> redisHasKeyByKey(List<String> keyCondition) {
		RedisTableInfo redisTableInfo = getTableInfo();
		List<String> keyList = redisTableInfo.getTableKeyList();
		if (keyCondition.size() != 0 && keyCondition.size() == keyList.size()) {
			String haskey = Joiner.on(",").join(keyCondition);
			return Arrays.asList(haskey);
		}
		if (keyCondition.size() == 0) {
			return redisHasKey();
		}
		return redisHasKey().stream().filter(q -> {
			List<String> splitList = Arrays.asList(q.split(","));
			for (String keyTemp : keyList) {
				if (!splitList.contains(keyTemp)) {
					return false;
				}
			}
			return true;
		}).collect(Collectors.toList());
	}

	/*
	 * 对获取的haskey进行筛选
	 */
	protected List<String> redisHasKeyByKey(Predicate<T> keyPredicate) {
		RedisTableInfo redisTableInfo = getTableInfo();

		List<String> hasKeyList = redisHasKey().stream().filter(q -> {
			String[] splitColumn = q.split(",");
			Map<String, String> mapKeyTemp = new HashMap();
			for (String splitTemp : splitColumn) {
				String[] splitCell = splitTemp.split(":");
				mapKeyTemp.put(splitCell[0], splitCell[1]);
			}
			String jsonString = JSON.toJSONString(mapKeyTemp);
			T keyT = (T) JSON.parseObject(jsonString, redisTableInfo.getRedisTableClass());
			return keyPredicate.test(keyT);
		}).collect(Collectors.toList());
		return hasKeyList;
	}

	public List<T> select(Predicate<T> keyPredicate) {
		List<String> redisHasKeyList = redisHasKey();
		return selecAllByHasKey(redisHasKeyList).stream().filter(q -> keyPredicate.test(q))
				.collect(Collectors.toList());
	}

	public List<T> selectAll() {
		List<String> redisHasKeyList = redisHasKey();
		return selecAllByHasKey(redisHasKeyList);
	}

	public abstract Class<T> getTClass();

	protected List<T> selecAllByHasKey(List<String> redisHasKey) {
		RedisTableInfo redisTableInfo = getTableInfo();
		List<T> tableValueList = new ArrayList<>();
		Iterables.partition(redisHasKey, redisOneRowCount).forEach(q -> {
			List<String> multiGetTemp = (List<String>) redisTemplate.opsForHash().multiGet(redisTableInfo.getRedisKey(),
					q);
			tableValueList.addAll(multiGetTemp.stream().filter(p -> !Strings.isNullOrEmpty(p)).map(p -> {
				return (T) JSON.parseObject(p, redisTableInfo.getRedisTableClass());
			}).collect(Collectors.toList()));
		});
		return tableValueList;
	}

	public List<T> select(T t) {
		RedisTableInfo redisTableInfo = getTableInfo();
		Map<String, Object> conditionMap = getConditionMap(t);
		List<String> keyCondition = getKeyCondition(conditionMap, redisTableInfo.getTableKeyList());

		List<String> redisHasKey = redisHasKeyByKey(keyCondition);
		List<T> tableValueList = selecAllByHasKey(redisHasKey);
		// 删除key条件字段
		redisTableInfo.getTableKeyList().forEach(q -> {
			conditionMap.remove(q);
		});
		//
		if (conditionMap.size() == 0) {
			return tableValueList;
		}
		return tableValueList.stream().filter(q -> {
			for (Entry<String, Object> entryTemp : conditionMap.entrySet()) {
				Optional<Field> findFirst = redisTableInfo.getFieldList().stream()
						.filter(p -> p.getName().equals(entryTemp.getKey())).findFirst();
				try {
					if (!entryTemp.getValue().equals(findFirst.get().get(q))) {
						return false;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.error(e.getMessage());
				}
			}
			return true;
		}).collect(Collectors.toList());
	}

	public IPageResponseModel selectPage(Predicate<T> keyPredicate, IPageRequestModel pageRequestModel) {
		List<T> tList = select(keyPredicate);
		return listPage(tList, pageRequestModel);
	}

	public IPageResponseModel selectPage(T t, IPageRequestModel pageRequestModel) {
		List<T> tList = select(t);
		return listPage(tList, pageRequestModel);

	}

	protected IPageResponseModel listPage(List<T> tList, IPageRequestModel pageRequestModel) {
		Comparator comparator = SqlOrderByUtil.orderBySqlToComparator(pageRequestModel.getOrderBy());
		if (comparator != null) {
			Collections.sort(tList, comparator);
		}
		List<List<T>> partitionList = Lists.partition(tList, pageRequestModel.getRows());
		IPageResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel.setPage(pageRequestModel.getPage());
		pageDefResponseModel.setRecords(partitionList.size());
		pageDefResponseModel.setTotal((long) tList.size());
		List<T> rows = new ArrayList<>();
		if (partitionList.size() >= pageRequestModel.getPage()) {
			rows = partitionList.get(pageRequestModel.getPage() - 1);
		}
		pageDefResponseModel.setRows(rows);
		return pageDefResponseModel;
	}
}
