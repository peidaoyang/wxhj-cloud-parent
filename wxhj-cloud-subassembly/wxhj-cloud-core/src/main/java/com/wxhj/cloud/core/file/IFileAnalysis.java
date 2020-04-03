/** 
 * @fileName: IFileAnalysisFactory.java  
 * @author: pjf
 * @date: 2019年12月10日 下午5:37:43 
 */

package com.wxhj.cloud.core.file;

import java.util.List;

/**
 * @className IFileAnalysisFactory.java
 * @author pjf
 * @date 2019年12月10日 下午5:37:43   
*/
/**
 * @className IFileAnalysisFactory.java
 * @author pjf
 * @date 2019年12月10日 下午5:37:43
 */

public interface IFileAnalysis<T> {
	List<T> fileAnalysis(byte[] fileByte);
}
