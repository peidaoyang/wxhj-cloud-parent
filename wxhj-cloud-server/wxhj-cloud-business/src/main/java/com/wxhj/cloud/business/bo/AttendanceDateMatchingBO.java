/** 
 * @fileName: AttendanceDateMatching.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:39:30 
 */

package com.wxhj.cloud.business.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className AttendanceDateMatching.java
 * @author pjf
 * @date 2019年12月12日 上午11:39:30
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDateMatchingBO {
	// Y 匹配年,M 匹配月,D 匹配日,W 匹配星期,HD 匹配节假日
	private String matchingType;
	// ture为正常匹配,false为取反匹配
	private Boolean matchingNot;

	private List<String> matchingValue;

	public boolean matching(Date date) {
		String matchingStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		switch (matchingType) {
		case "Y":
			sdf = new SimpleDateFormat("yyyy");
			matchingStr = sdf.format(date);
			break;
		case "M":
			sdf = new SimpleDateFormat("MM");
			matchingStr = sdf.format(date);
			break;
		case "D":
			sdf = new SimpleDateFormat("DD");
			matchingStr = sdf.format(date);
			break;
		case "W":
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Integer w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			w = w == 0 ? 7 : w;
			matchingStr = w.toString();
			break;
		default:
			return false;
		}

		return matchingValue.contains(matchingStr) == matchingNot;

	}
}
