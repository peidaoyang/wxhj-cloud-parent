package com.wxhj.cloud.feignClient.school.requestDTO;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "入住管理分页信息查询")
public class ListRoomRecRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "楼栋id")
//    @NotBlank
    private String dormitoryId;
}
