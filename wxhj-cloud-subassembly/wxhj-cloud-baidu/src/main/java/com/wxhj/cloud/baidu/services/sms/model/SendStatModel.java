/** 
 * @fileName: SendStatModel.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:43:05 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import java.util.List;

/**
 * @className SendStatModel.java
 * @author pjf
 * @date 2020年2月28日 上午10:43:05   
*/
/** 
 * @className SendStatModel.java
 * @author pjf
 * @date 2020年2月28日 上午10:43:05 
*/

public class SendStatModel {

    private int sendCount;
    private int successCount;
    private List<String> failList;

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public List<String> getFailList() {
        return failList;
    }

    public void setFailList(List<String> failList) {
        this.failList = failList;
    }

    @Override
    public String toString() {
        return "SendStatModel [sendCount=" + sendCount + ", successCount=" + successCount + ", failList=" + failList
                + "]";
    }

}

