package com.wxhj.cloud.device.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (DeviceFaceException)实体类
 *
 * @author makejava
 * @since 2020-05-07 11:53:22
 */
@Data
@Table(name = "device_face_exception")
@Builder
public class DeviceFaceExceptionDO implements Serializable {
    private static final long serialVersionUID = -16653336336803767L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 场景id
    */
    private String sceneId;
    /**
    * 当前场景下的流水
    */
    private Long currentIndex;
    /**
    * 组织id
    */
    private String organizeId;
    /**
    * 设备编号
    */
    private String deviceNo;
    /**
    * 变更唯一流水
    */
    private Long masterId;
    /**
    * 用户id
    */
    private String accountId;
    /**
    * 图片url
    */
    private String imageName;
    /**
    * 操作标志
    */
    private Integer operateType;
    /**
    * 身份证号
    */
    private String idNumber;
    /**
    * 手机号
    */
    private String phoneNumber;
    /**
    * 姓名
    */
    private String name;
    /**
    * 卡号
    */
    private String cardNumber;
    /**
    * 异常原因
    */
    private String exceptionReason;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 创建时间
    */
    private Date createTime;

}