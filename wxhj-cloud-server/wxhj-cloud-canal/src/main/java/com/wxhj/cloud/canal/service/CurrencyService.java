/** 
 * @fileName: CurrencyService.java  
 * @author: pjf
 * @date: 2019年12月3日 上午9:05:25 
 */

package com.wxhj.cloud.canal.service;

import java.util.List;
import java.util.Map;

/**
 * @className CurrencyService.java
 * @author pjf
 * @date 2019年12月3日 上午9:05:25   
*/
/**
 * @className CurrencyService.java
 * @author pjf
 * @date 2019年12月3日 上午9:05:25
 */

public interface CurrencyService {

	List<String> listTableKey(String dataSourceName, String tableName);

	List<Map<String, String>> listTableContent(String dataSourceName, String tableName);
}
