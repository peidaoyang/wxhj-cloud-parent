package com.wxhj.cloud.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "organize_pay_info")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizePayInfoDO {
    @Id
    private String id;
    private String wxAppid;
    private String wxMchId;
    private String wxApiKey;
}
