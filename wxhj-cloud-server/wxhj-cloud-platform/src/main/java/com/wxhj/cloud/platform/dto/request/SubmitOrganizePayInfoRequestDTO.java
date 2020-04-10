package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel( description = "组织支付信息对象编辑")
public class SubmitOrganizePayInfoRequestDTO {
    private String id;
    private String wxAppid;
    private String wxMchId;
    private String wxApiKey;

    private String organizeId;
}
