/** 
 * @fileName: PaginationResponseModel.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:05:24 
 */

package com.wxhj.cloud.core.model.pagination;


/**
 * @className PaginationResponseModel.java
 * @author pjf
 * @date 2019年10月16日 下午2:05:24
 */

public interface IPageResponseModel {
	Object getRows();

	void setRows(Object obj);

	Integer getPage();

	void setPage(Integer page);

	Integer getRecords();

	void setRecords(Integer records);

	Long getTotal();

	void setTotal(Long total);
}
