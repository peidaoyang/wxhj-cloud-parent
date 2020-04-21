package com.wxhj.cloud.account.domain;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "face_change_rec")
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
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
