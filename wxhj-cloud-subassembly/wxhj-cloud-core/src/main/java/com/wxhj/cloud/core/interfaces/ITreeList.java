/** 
 * @fileName: ITreeList.java  
 * @author: pjf
 * @date: 2019年11月13日 下午3:24:55 
 */

package com.wxhj.cloud.core.interfaces;

import java.util.List;

/**
 * @className ITreeList.java
 * @author pjf
 * @date 2019年11月13日 下午3:24:55   
*/
/**
 * @className ITreeList.java
 * @author pjf
 * @date 2019年11月13日 下午3:24:55
 */

public interface ITreeList<T> extends ITreeElement {

	List<T> getChildren();
	void setChildren(List<T> tList);
}
