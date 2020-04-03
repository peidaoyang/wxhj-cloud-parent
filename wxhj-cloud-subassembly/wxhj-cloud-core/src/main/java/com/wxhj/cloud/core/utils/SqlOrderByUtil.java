/** 
 * @fileName: SqlOrderByUtil.java  
 * @author: pjf
 * @date: 2019年12月5日 上午11:26:38 
 */

package com.wxhj.cloud.core.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanComparator;

import com.google.common.base.Strings;

/**
 * @className SqlOrderByUtil.java
 * @author pjf
 * @date 2019年12月5日 上午11:26:38   
*/

public class SqlOrderByUtil {

	public static Comparator orderBySqlToComparator(String orderBySql) {
		if (Strings.isNullOrEmpty(orderBySql)) {
			return null;
		}
		List<String> orderByList = Arrays.asList(orderBySql.split(","));

		List<BeanComparator> beanComparatorList = orderByList.stream().map(q -> {
			String[] splitTemp = q.split(" ");
			Comparator comparatorTemp = Comparator.naturalOrder();
			if (splitTemp.length > 1 && splitTemp[1].equals("desc")) {
				comparatorTemp = Comparator.reverseOrder();
			}
			return new BeanComparator(HumpUtil.lineToHump(splitTemp[0]), comparatorTemp);
		}).collect(Collectors.toList());

		Comparator comparator = null;
		for (BeanComparator beanComparator : beanComparatorList) {
			if (comparator == null) {
				comparator = beanComparator;
			} else {
				comparator = comparator.thenComparing(beanComparator);
			}
		}
		return comparator;
	}
}
