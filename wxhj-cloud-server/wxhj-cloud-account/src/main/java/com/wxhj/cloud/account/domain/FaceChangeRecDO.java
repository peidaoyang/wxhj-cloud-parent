package com.wxhj.cloud.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "face_change_rec")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FaceChangeRecDO {

    private String id;

    private Long currentIndex;
    @Id
    private Long masterId;

    private String accountId;

    private String imageUrl;

    private Integer operateType;

    private String idNumber;

    private String name;

    private String phoneNumber;
}
