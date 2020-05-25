/**
 * @fileName: AttendanceDateMatching.java
 * @author: pjf
 * @date: 2019年12月12日 上午11:39:30
 */

package com.wxhj.cloud.business.bo;

import com.wxhj.cloud.core.utils.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author pjf
 * @className AttendanceDateMatching.java
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


    public boolean matching(LocalDate date) {

        String matchingStr = "";
        switch (matchingType) {
            case "Y":
                matchingStr = DateFormat.getStringDate(date, "yyyy");
                break;
            case "M":
                matchingStr = DateFormat.getStringDate(date, "MM");
                break;
            case "D":
                matchingStr = DateFormat.getStringDate(date, "DD");
                break;
            case "W":
                matchingStr = String.valueOf(date.getDayOfWeek().getValue());
                break;
            default:
                return false;
        }

        return matchingValue.contains(matchingStr) == matchingNot;

    }
}
