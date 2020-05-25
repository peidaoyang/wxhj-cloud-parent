package com.wxhj.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.IDeviceRecord;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@Table(name = "wechat_qr_consume")
public class WechatQrConsumeDO implements IDeviceRecord {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordDatetime;
    //设备号_设备流水号_时间戳
    @Id
    private String orderNumber;
    //设备流水号
    private Long serialNumber;
    //用户id
    private String accountId;
    //场景id
    private String sceneId;
    //设备id
    private String deviceId;
    //组织id
    private String organizeId;
    //
    private String appid;
    //商户号
    private String mchId;
    //设备号
//    public String getDeviceInfo() {
//        return this.deviceId;
//    }
    //随机字符串
    private String nonceStr;
    //商品描述
    private String body;
    //商品详情
    private String detail;

//    public String getOutTradeNo() {
//        return this.orderNumber;
//    }
    //订单金额
    private Integer totalFee;
    //付款码
    private String authCode;
}
