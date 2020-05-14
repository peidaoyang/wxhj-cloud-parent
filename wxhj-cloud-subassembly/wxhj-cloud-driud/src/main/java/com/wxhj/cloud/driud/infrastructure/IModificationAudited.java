/** 
 * @fileName: IModificationAudited.java  
 * @author: pjf
 * @date: 2019年10月14日 下午5:10:31 
 */

package com.wxhj.cloud.driud.infrastructure;



/**
 * @className IModificationAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:10:31   
*/

import java.time.LocalDateTime;

/**
 * @className IModificationAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:10:31
 */

public interface IModificationAudited {
	LocalDateTime getLastModifyTime();

	void setLastModifyTime(LocalDateTime lastModifyTime);

	String getLastModifyUserId();

	void setLastModifyUserId(String lastModifyUserId);

}
