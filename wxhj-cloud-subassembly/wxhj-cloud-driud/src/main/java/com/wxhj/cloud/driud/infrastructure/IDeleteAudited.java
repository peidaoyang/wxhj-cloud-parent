/** 
 * @fileName: IDeleteAudited.java  
 * @author: pjf
 * @date: 2019年10月14日 下午5:09:44 
 */

package com.wxhj.cloud.driud.infrastructure;

import java.util.Date;

/**
 * @className IDeleteAudited.java
 * @author pjf
 * @date 2019年10月14日 下午5:09:44   
*/


public interface IDeleteAudited {
	Integer getIsDeleteMark();

	void setIsDeleteMark(Integer isDeleteMark);

	Date getDeleteTime();

	void setDeleteTime(Date deleteTime);

	String getDeleteUserId();

	void setDeleteUserId(String deleteUserId);

}
