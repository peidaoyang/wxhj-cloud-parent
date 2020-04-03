/** 
 * @fileName: ListTemplateResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:56:34 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import java.util.List;

/**
 * @className ListTemplateResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:56:34   
*/
/** 
 * @className ListTemplateResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:56:34 
*/

public class ListTemplateResponse extends SmsResponse {
    private List<GetTemplateDetailResponse> templateList;

    public List<GetTemplateDetailResponse> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<GetTemplateDetailResponse> templateList) {
        this.templateList = templateList;
    }

    @Override
    public String toString() {
        return "ListTemplateResponse [templateList=" + templateList + "]";
    }

}