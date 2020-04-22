package com.wxhj.cloud.account.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "face_change_rec")
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class FaceChangeRecDO  {

    private String id;

    private Long currentIndex;
    @Id
    private Long masterId;

    private String accountId;

    private String imageName;

    private Integer operateType;

    private String idNumber;

    private String name;

    private String phoneNumber;

    @Override
    public Object clone() {
        FaceChangeRecDO temp = null;
        try {
            temp = (FaceChangeRecDO) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return temp;
        }
    }
}
