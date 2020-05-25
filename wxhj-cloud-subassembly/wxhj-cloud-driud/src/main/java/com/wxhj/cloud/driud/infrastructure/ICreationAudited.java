/** 
 * @fileName: ICreationAudited.java  
 * @author: pjf
 * @date: 2019年10月14日 下午5:09:12 
 */

package com.wxhj.cloud.driud.infrastructure;


import java.time.LocalDateTime;

/**
 * @className ICreationAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:09:12   
*/


public interface ICreationAudited {

	String getId();

	void setId(String id);

	LocalDateTime getCreatorTime();

	void setCreatorTime(LocalDateTime creatorTime);

	String getCreatorUserId();

	void setCreatorUserId(String creatorUserId);

}
