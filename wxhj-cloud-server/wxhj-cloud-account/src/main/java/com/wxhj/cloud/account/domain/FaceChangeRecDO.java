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
public class FaceChangeRecDO implements Cloneable {
    @Id
    private String id;
    @Id
    private Long currentIndex;

    private Long masterId;

    private Integer operateType;

    private String accountId;

    private String imageName;
    private String idNumber;

    private String name;

    private String phoneNumber;

    private String cardNumber;

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

    public boolean judgeLegally() {
        return this.imageName != null ||
                this.idNumber != null ||
                this.name != null ||
                this.phoneNumber != null ||
                this.cardNumber != null;
    }

}
