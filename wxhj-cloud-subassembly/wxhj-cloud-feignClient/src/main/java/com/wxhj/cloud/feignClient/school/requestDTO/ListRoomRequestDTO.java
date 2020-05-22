package com.wxhj.cloud.feignClient.school.requestDTO;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@ApiModel(value = "")
@Data
public class ListRoomRequestDTO extends CommonListPageRequestDTO {
    @ApiModelProperty(value = "宿舍类型",example = "0：男生宿舍,1：女生宿舍,2：教职工宿舍")
    @Min(0)
    @Max(10)
    private Integer type;
    @ApiModelProperty(value = "楼栋编号")
    @NotBlank
    private String dormitoryId;
}
