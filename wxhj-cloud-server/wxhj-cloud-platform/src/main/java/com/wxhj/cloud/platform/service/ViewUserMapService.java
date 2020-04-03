/** 
 * @fileName: ViewUserMapServer.java  
 * @author: pjf
 * @date: 2019年10月11日 上午9:46:18 
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.view.ViewUserMapDO;

/**
 * @className ViewUserMapServer.java
 * @author pjf
 * @date 2019年10月11日 上午9:46:18   
*/

public interface ViewUserMapService {
	List<ViewUserMapDO> selectByAccount(String account,int loginType);
	
	ViewUserMapDO selectById(String id);
	
}
