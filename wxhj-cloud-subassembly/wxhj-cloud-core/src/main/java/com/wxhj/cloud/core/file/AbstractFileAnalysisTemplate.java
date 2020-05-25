/** 
 * @fileName: IFileAnalysisFactory.java  
 * @author: pjf
 * @date: 2019年12月6日 上午9:46:49 
 */

package com.wxhj.cloud.core.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxhj.cloud.core.utils.JSON;
import com.wxhj.cloud.core.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @className IFileAnalysisFactory.java
 * @author pjf
 * @date 2019年12月6日 上午9:46:49
 */
@Slf4j
public abstract class AbstractFileAnalysisTemplate<T> implements IFileAnalysis<T> {
//	public String getRowSplitString() {
//		return "\r\n";
//	}

//	public String getColumeSplitString() {
//		return ",";
//	}

//	public String getCharset() {
//		return "GBK";
//	}

	protected abstract List<String> getColumeNameList();

	protected abstract Class<T> getTclass();

//	@Override
//	public List<T> fileAnalysis(byte[] fileByte) {
//		List<T> tList = new ArrayList<>();
//		try {
//			List<String> rowStr = new ArrayList(
//					Arrays.asList(new String(fileByte, getCharset()).split(getRowSplitString())));
//			rowStr.remove(0);// 去掉第一行的标题
//			rowStr.forEach(q -> {
//				String[] columeList = q.split(getColumeSplitString());
//				int size = columeList.length;
//				Map<String, String> mapTemp = new HashMap<>();
//				for (int i = 0; i < size; i++) {
//					mapTemp.put(getColumeNameList().get(i), columeList[i]);
//				}
//				String jsonString = JSON.toJSONString(mapTemp);
//				T tTemp = JSON.parseObject(jsonString, getTclass());
//				tList.add(tTemp);
//			});
//		} catch (UnsupportedEncodingException e) {
//			log.error(e.getMessage());
//		}
//		return tList;
//	}

	@Override
	public List<T> fileAnalysis(byte[] fileByte) {
		List<T> tList = new ArrayList<>();
		try {
			List<String[]> dataList = ExcelUtil.readExcel(fileByte);
			dataList.forEach(q -> {
				String[] columeList = q;
				int size = columeList.length;
				Map<String, String> mapTemp = new HashMap<>();
				for (int i = 0; i < size; i++) {
					mapTemp.put(getColumeNameList().get(i), columeList[i]);
				}
				String jsonString = JSON.toJSONString(mapTemp);
				T tTemp = JSON.parseObject(jsonString, getTclass());
				tList.add(tTemp);
			});
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return tList;
	}
}
