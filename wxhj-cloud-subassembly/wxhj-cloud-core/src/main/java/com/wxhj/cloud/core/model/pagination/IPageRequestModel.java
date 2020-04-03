/** 
 * @fileName: PaginationRequestModel.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:04:46 
 */

package com.wxhj.cloud.core.model.pagination;


/**
 * @className PaginationRequestModel.java
 * @author pjf
 * @date 2019年10月16日 下午2:04:46
 */

//@Data
public interface IPageRequestModel {
	/**
	 * 每页行数
	 */
	// protected Integer rows;
	Integer getRows();

	void setRows(Integer rows);

	Integer getPage();

	void setPage(Integer page);

	String getOrderBy();

	void setOrderBy(String orderBy);
	/**
	 * 当前页
	 */
//	protected Integer page;
//	protected String orderBy;
}
