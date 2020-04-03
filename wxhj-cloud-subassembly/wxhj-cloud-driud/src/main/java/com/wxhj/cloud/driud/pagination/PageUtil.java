/** 
 * @fileName: PaginationUtil.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:03:47 
 */

package com.wxhj.cloud.driud.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @className PaginationUtil.java
 * @author pjf
 * @date 2019年10月16日 下午2:03:47
 */
@Component
public class PageUtil implements IModelInitialization {
	@Resource
	DozerBeanMapper dozerBeanMapperTemp;

	static DozerBeanMapper dozerBeanMapper;

	@PostConstruct
	@Override
	public void initialization() {
		dozerBeanMapper = dozerBeanMapperTemp;

	}

	public static IPageRequestModel initPageRequestModel(IPageRequestModel paginationRequestModel,
			String defaultOrderBy) {
		if (null == paginationRequestModel.getPage()) {
			paginationRequestModel.setPage(1);
		}

		if (null == paginationRequestModel.getRows()) {
			paginationRequestModel.setRows(50);
		}
		if (Strings.isNullOrEmpty(paginationRequestModel.getOrderBy())) {
			paginationRequestModel.setOrderBy(defaultOrderBy);
		}
		return paginationRequestModel;
	}

	public static IPageRequestModel initPageRequestModel(IPageRequestModel paginationRequestModel) {
		return initPageRequestModel(paginationRequestModel, null);
	}

	public static <T> PageInfo<T> selectPageList(IPageRequestModel paginationRequestModel,
			Supplier<List<T>> supplierMethod) {
		return selectPageList(paginationRequestModel, null, supplierMethod);
	}

	private static <T> PageInfo<T> selectPageList(IPageRequestModel paginationRequestModel, String defaultOrderBy,
			Supplier<List<T>> supplierMethod) {
		paginationRequestModel = initPageRequestModel(paginationRequestModel, defaultOrderBy);
		
		PageHelper.startPage(paginationRequestModel.getPage(), paginationRequestModel.getRows(),
				paginationRequestModel.getOrderBy());
		PageInfo<T> pageInfo = new PageInfo<T>(supplierMethod.get());
		PageHelper.clearPage();
		return pageInfo;
	}

	public static IPageResponseModel initPageResponseModel(PageInfo<?> pageInfo,
			IPageResponseModel paginationResponseModel) {
		return initPageResponseModel(pageInfo, pageInfo.getList(), paginationResponseModel);
	}

	public static IPageResponseModel initPageResponseModel(PageInfo<?> pageInfo, List<?> dataList,
			IPageResponseModel paginationResponseModel) {
		// 当前页
		paginationResponseModel.setPage(pageInfo.getPageNum());
		// 总页数
		paginationResponseModel.setTotal(pageInfo.getTotal());
		// 总记录数
		paginationResponseModel.setRecords(pageInfo.getPages());
		paginationResponseModel.setRows(dataList);
		return paginationResponseModel;
	}

	public static IPageResponseModel initPageResponseModel(PageInfo<?> pageInfo,
			IPageResponseModel paginationResponseModel, Class<?> clazz) {
		// 当前页
		paginationResponseModel.setPage(pageInfo.getPageNum());
		// 总页数
		paginationResponseModel.setTotal(pageInfo.getTotal());
		// 总记录数
		paginationResponseModel.setRecords(pageInfo.getPages());
		List<Object> rowList = new ArrayList<>();
		for (Object objectTemp : pageInfo.getList()) {
			rowList.add(dozerBeanMapper.map(objectTemp, clazz));
		}
		paginationResponseModel.setRows(rowList);
		return paginationResponseModel;
	}

}
