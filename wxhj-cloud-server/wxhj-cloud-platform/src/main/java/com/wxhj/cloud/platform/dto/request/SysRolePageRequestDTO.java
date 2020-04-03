//package com.wxhj.cloud.platform.dto.request;
//
//import java.util.List;
//
//import javax.validation.constraints.Min;
//
//import com.esotericsoftware.kryo.NotNull;
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
///**
// * 
// * @className SysRolePageRequestDTO.java
// * @author pjf
// * @date 2019年11月6日 上午8:44:43
// */
//@ToString
//@Data
//@ApiModel(description = "角色信息分页查询对象")
//public class SysRolePageRequestDTO implements IPageRequestModel {
//	@ApiModelProperty(value = "关键字",example="")
//	@NotNull
//	private String nameValue;
//	@ApiModelProperty(value = "条数",example="5")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页",example="1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段",example="sortCode asc")
//	private String orderBy;
//	@ApiModelProperty(value = "当前登录账户的子商户列表")
//	private List<String> organizeChildList;
//	@ApiModelProperty(value = "当前登录账户的商户id",example="c0ac19a5-a2ea-4f19-86d5-c33c58fd0f79")
//	@NotNull
//	private String currentOrganizeId;
//}
