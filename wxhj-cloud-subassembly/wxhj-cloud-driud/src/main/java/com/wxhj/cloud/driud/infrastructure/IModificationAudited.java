/** 
 * @fileName: IModificationAudited.java  
 * @author: pjf
 * @date: 2019年10月14日 下午5:10:31 
 */

package com.wxhj.cloud.driud.infrastructure;

import java.util.Date;

/**
 * @className IModificationAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:10:31   
*/
/**
 * @className IModificationAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:10:31
 */

public interface IModificationAudited {
	Date getLastModifyTime();

	void setLastModifyTime(Date lastModifyTime);

	String getLastModifyUserId();

	void setLastModifyUserId(String lastModifyUserId);

}
