/** 
 * @fileName: StatReceiverResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:58:41 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className StatReceiverResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:58:41   
*/
/** 
 * @className StatReceiverResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:58:41 
*/

public class StatReceiverResponse extends SmsResponse {
    /**
     * The max count of allowed to receive message in 24 hours
     */
    private Integer maxReceivePerPhoneNumberDay;
    /**
     * The max count which is received successfully in last 24 hours
     */
    private Integer receivedToday;

    public Integer getMaxReceivePerPhoneNumberDay() {
        return maxReceivePerPhoneNumberDay;
    }

    public void setMaxReceivePerPhoneNumberDay(Integer maxReceivePerPhoneNumberDay) {
        this.maxReceivePerPhoneNumberDay = maxReceivePerPhoneNumberDay;
    }

    public Integer getReceivedToday() {
        return receivedToday;
    }

    public void setReceivedToday(Integer receivedToday) {
        this.receivedToday = receivedToday;
    }

    @Override
    public String toString() {
        return "StatReceiverResponse [maxReceivePerPhoneNumberDay=" + maxReceivePerPhoneNumberDay + ", receivedToday="
                + receivedToday + "]";
    }

}
