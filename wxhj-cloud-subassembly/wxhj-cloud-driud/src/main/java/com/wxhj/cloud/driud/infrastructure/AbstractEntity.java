/** 
 * @fileName: IEntity.java  
 * @author: pjf
 * @date: 2019年10月14日 下午5:08:27 
 */

package com.wxhj.cloud.driud.infrastructure;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @className IEntity.java
 * @author pjf
 * @date 2019年10月14日 下午5:08:27   
*/

public abstract class AbstractEntity<TEntity> {
	// 创建
	public String create(String userid) {
		ICreationAudited entity = (ICreationAudited) this;
		String key= UUID.randomUUID().toString();
		entity.setId(key);
		entity.setCreatorUserId(userid);
		entity.setCreatorTime(LocalDateTime.now());
		return key;
	}
	//修改
    public void modify(String userid)
    {
    	IModificationAudited entity = (IModificationAudited)this;

    	entity.setLastModifyUserId(userid);
		entity.setLastModifyTime(LocalDateTime.now());
    }
    //删除
    public void remove(String userid)
    {
    	IDeleteAudited entity = (IDeleteAudited)this;
    	entity.setDeleteUserId(userid);
		entity.setDeleteTime(LocalDateTime.now());
		entity.setIsDeleteMark(1);
    }
}
