/** 
 * @fileName: PaginationUtil.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:03:47 
 */

package com.wxhj.cloud.driud.pagination;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

	/**
	 * 根据传入的list构造返回的分页信息
	 * @author daxiong
	 * @date 2020/5/12 10:37 上午
	 * @param page    开始页数
	 * @param rows    查询条数
	 * @param list    要分页的list数据
	 * @return com.wxhj.cloud.core.model.pagination.PageDefResponseModel
	 */
	public static PageDefResponseModel formatListPageResponse(Integer page, Integer rows, List<?> list) {
		int size = list.size();
		long totalPages = (long) Math.ceil((double) size / rows);
		list = list.stream().skip((page - 1) * rows).limit(rows).collect(Collectors.toList());

		PageDefResponseModel pageFormatResponseModel = new PageDefResponseModel();
		// 当前页
		pageFormatResponseModel.setPage(page);
		// 总页数
		pageFormatResponseModel.setTotal(totalPages);
		// 总记录数
		pageFormatResponseModel.setRecords(size);
		pageFormatResponseModel.setRows(list);
		return pageFormatResponseModel;
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
