/** 
 * @fileName: QueryQuotaResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:57:39 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className QueryQuotaResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:57:39   
*/
/** 
 * @className QueryQuotaResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:57:39 
*/

public class QueryQuotaResponse extends SmsResponse {
    /**
     * The max count of sending message a day
     */
    private Integer maxSendPerDay;

    /**
     * The max count of allowed to receive message a day
     */
    private Integer maxReceivePerPhoneNumberDay;

    /**
     * The max count which is send in last 24 hours
     */
    private Integer sentToday;

    public Integer getMaxSendPerDay() {
        return maxSendPerDay;
    }

    public void setMaxSendPerDay(Integer maxSendPerDay) {
        this.maxSendPerDay = maxSendPerDay;
    }

    public Integer getMaxReceivePerPhoneNumberDay() {
        return maxReceivePerPhoneNumberDay;
    }

    public void setMaxReceivePerPhoneNumberDay(Integer maxReceivePerPhoneNumberDay) {
        this.maxReceivePerPhoneNumberDay = maxReceivePerPhoneNumberDay;
    }

    public Integer getSentToday() {
        return sentToday;
    }

    public void setSentToday(Integer sentToday) {
        this.sentToday = sentToday;
    }

    @Override
    public String toString() {
        return "QueryQuotaResponse [maxSendPerDay=" + maxSendPerDay + ", maxReceivePerPhoneNumberDay="
                + maxReceivePerPhoneNumberDay + ", sentToday=" + sentToday + "]";
    }

}
