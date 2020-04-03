/** 
 * @fileName: ViewControlUtil.java  
 * @author: pjf
 * @date: 2019年11月13日 下午2:19:30 
 */

package com.wxhj.cloud.platform.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wxhj.cloud.core.interfaces.ITreeElement;
import com.wxhj.cloud.core.interfaces.ITreeList;
import com.wxhj.cloud.feignClient.vo.TreeListControlVO;

/**
 * @className ViewControlUtil.java
 * @author pjf
 * @date 2019年11月13日 下午2:19:30
 */

public class ViewControlUtil {

	public static List<TreeListControlVO> buildTreeListControl(List<? extends ITreeElement> treeList, String parentId) {
		List<TreeListControlVO> treeListControlList = new ArrayList<>();
		treeListControlList = treeList.stream().filter(q -> q.getParentId().equals(parentId))
				.map(q -> new TreeListControlVO(q.getId(), q.getParentId(), q.getFullName(), q.getLayers(),
						q.getSortCode(), buildTreeListControl(treeList, q.getId())))
				.collect(Collectors.toList());
		//
		if (treeListControlList.size() == 0) {
			treeListControlList = null;
		}
		//
		return treeListControlList;
	}

	public static <TTreeList extends ITreeList, TTreeElement extends ITreeElement> List<TTreeList> buildTreeList(
			List<TTreeElement> treeElementList, String parentId, Function<TTreeElement, TTreeList> function) {
		List<TTreeList> returnTreeList = new ArrayList<>();
		returnTreeList = treeElementList.stream().filter(q -> q.getParentId().equals(parentId)).map(q -> {
			TTreeList treeListTemp = function.apply(q);
			treeListTemp.setChildren(buildTreeList(treeElementList, q.getId(), function));
			return treeListTemp;
		}).collect(Collectors.toList());
		return returnTreeList;
	}

	public static <T extends ITreeElement> List<T> treeElementFilterRecursion(List<T> treeElementList,
			Predicate<T> predicate, String parentid) {
		List<T> sysOrganizeList = treeElementList.stream().filter(q -> q.getParentId().equals(parentid))
				.collect(Collectors.toList());
		List<T> returnTreeElementList = new ArrayList<>();
		for (T tTemp : sysOrganizeList) {
			List<T> treeElementListTemp = treeElementFilterRecursion(treeElementList, predicate, tTemp.getId());
			returnTreeElementList.addAll(treeElementListTemp);
			if (treeElementListTemp.size() > 0 || predicate.test(tTemp)) {
				returnTreeElementList.add(tTemp);
			}
		}
		return returnTreeElementList;
	}
}
