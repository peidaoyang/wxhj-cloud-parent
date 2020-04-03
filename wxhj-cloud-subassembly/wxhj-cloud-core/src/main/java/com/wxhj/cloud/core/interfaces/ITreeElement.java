/** 
 * @fileName: ITreeList.java  
 * @author: pjf
 * @date: 2019年11月13日 下午2:15:53 
 */

package com.wxhj.cloud.core.interfaces;

/**
 * @className ITreeList.java
 * @author pjf
 * @date 2019年11月13日 下午2:15:53   
*/
/**
 * @className ITreeList.java
 * @author pjf
 * @date 2019年11月13日 下午2:15:53
 */

public interface ITreeElement {

	String getId();

	void setId(String id);

	String getParentId();

	void setParentId(String parentId);

	String getFullName();

	void setFullName(String fullName);

	Integer getLayers();

	void setLayers(Integer lagers);

	Integer getSortCode();

	void setSortCode(Integer sortCode);

}
