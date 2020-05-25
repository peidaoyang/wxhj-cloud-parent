package com.wxhj.cloud.feignClient.business.request;

import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/14 9:44 上午
 */
@Data
@Api(tags = "保存组织年度日期管理规则DTO")
public class SaveOrganizeYearScheduleRecDTO {
    @ApiModelProperty("关联的id")
    @NotBlank
    private String organizeYearScheduleId;

    @ApiModelProperty("规则列表")
    private List<OrganizeYearScheduleRecVO> list;
}
