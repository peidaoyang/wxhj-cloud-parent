/**
 * @className EntranceDataService.java
 * @author jwl
 * @date 2020年1月17日 下午1:14:52
 */
package com.wxhj.cloud.business.service;


import com.wxhj.cloud.business.domain.EntranceDataDO;

import java.time.LocalDateTime;


/**
 * @className EntranceDataService.java
 * @author jwl
 * @date 2020年1月17日 下午1:14:52
 */
public interface EntranceDataService {
	void insert(EntranceDataDO entranceData);
	int listCount(String organizeId, LocalDateTime recordDatetime);
}
