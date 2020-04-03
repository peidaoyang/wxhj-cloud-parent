/** 
 * @fileName: PaginationDefRequestModel.java  
 * @author: pjf
 * @date: 2019年11月5日 下午2:56:39 
 */

package com.wxhj.cloud.core.model.pagination;

import lombok.Data;

/**
 * @className PaginationDefRequestModel.java
 * @author pjf
 * @date 2019年11月5日 下午2:56:39   
*/

@Data
public class PageDefRequestModel implements IPageRequestModel {

	private Integer rows;

	private Integer page;
	private String orderBy;
}
