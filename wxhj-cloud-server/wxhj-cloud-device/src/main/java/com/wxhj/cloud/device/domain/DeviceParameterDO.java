/**
 * @fileName: DeviceParameterDO.java
 * @author: pjf
 * @date: 2019年11月28日 下午2:14:54
 */

package com.wxhj.cloud.device.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @className DeviceParameterDO.java
 * @author pjf
 * @date 2019年11月28日 下午2:14:54
 */
@Table(name = "device_parameter")
@ToString
@Data
public class DeviceParameterDO {
    @Id
    private String deviceId;
    private String organizeId;
    private String sceneId;
    private String parameterUrl;
    private Long parameterVersion;
    private String deviceName;
    private Integer deviceType;

    @ApiModelProperty(value = "是否考勤(0为不启用)")
    private Integer isAttendance;
    @ApiModelProperty(value = "是否门禁(0为不启用)")
    private Integer isEntrance;
    @ApiModelProperty(value = "是否消费(0为不启用)")
    private Integer isConsume;
    @ApiModelProperty(value = "是否班车(0为不启用)")
    private Integer isFlight;
    @ApiModelProperty(value = "是否访客(0为不启用)")
    private Integer isVisit;
}
