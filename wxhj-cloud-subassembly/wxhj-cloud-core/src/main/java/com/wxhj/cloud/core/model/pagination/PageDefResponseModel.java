/** 
 * @fileName: PaginationDefResponseModel.java  
 * @author: pjf
 * @date: 2019年11月5日 下午3:00:14 
 */

package com.wxhj.cloud.core.model.pagination;

import lombok.Data;

/**
 * @className PaginationDefResponseModel.java
 * @author pjf
 * @date 2019年11月5日 下午3:00:14
 */

@Data
public class PageDefResponseModel implements IPageResponseModel {
	Object rows;

	Integer page;

	Integer records;

	Long total;
}
