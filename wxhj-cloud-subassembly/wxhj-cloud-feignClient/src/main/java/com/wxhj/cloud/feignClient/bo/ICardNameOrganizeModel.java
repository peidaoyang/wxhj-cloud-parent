package com.wxhj.cloud.feignClient.bo;

/**
 * @ClassName: IUserModle.java
 * @author: cya
 * @Date: 2020年3月18日 下午1:18:00
 */
public interface ICardNameOrganizeModel {
    String getOrganizeId();

    void setOrganizeId(String organizeId);

    Integer getCardType();

    void setCardType(Integer cardType);

    String getCardName();

    void setCardName(String cardName);
}
