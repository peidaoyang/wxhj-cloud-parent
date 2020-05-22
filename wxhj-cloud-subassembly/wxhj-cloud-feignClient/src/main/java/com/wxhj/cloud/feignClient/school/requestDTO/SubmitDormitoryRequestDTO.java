package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@Data
@ApiModel(value = "新增楼栋")
public class SubmitDormitoryRequestDTO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "组织编号")
    @NotBlank
    private String organizeId;
    @ApiModelProperty(value = "楼栋号")
    @Min(0)
    private Integer number;
    @ApiModelProperty(value = "楼层数")
    @Min(0)
    private Integer floorNumber;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty(value="是否自动同步人员权限,0：不自动，1：自动")
	@Min(0)
    private Integer autoSynchro;

    @ApiModelProperty(value = "场景idList")
    private String sceneId;
//    @ApiModelProperty(value = "用户编号")
//    private List<String> accountIdList;

}
