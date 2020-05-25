//package com.wxhj.cloud.feignClient.dto;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.models.auth.In;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//
///**
// * @Author cya
// * @Date 2020/5/12
// * @Version V1.0
// **/
//@ToString
//@ApiModel(value = "通用包含id和type的请求")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class CommonIdOrgTypeRequestDTO {
//    @ApiModelProperty(value = "id")
//    @NotBlank
//    private String id;
//
//    @ApiModelProperty(value = "组织类型")
//    @Min(-1)
//    @Max(99)
//    private Integer orgType;
//}
