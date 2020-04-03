/** 
 * @fileName: StatReceiverRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:58:21 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className StatReceiverRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:58:21   
*/
/** 
 * @className StatReceiverRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:58:21 
*/

public class StatReceiverRequest extends SmsRequest {
    /**
     * The URL parameter<br>
     * The phone number of End-User. It only support single user, only number included, whose pattern is similar to
     * 13800138000
     */
    private String phoneNumber;

    public StatReceiverRequest withPhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StatReceiverRequest [phoneNumber=" + phoneNumber + "]";
    }

}
