package com.wxhj.cloud.account.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "face_change")
@ToString
public class FaceChangeDO {
    @Id
    private String id;
    private Long minIndex;
    private Long maxIndex;
}