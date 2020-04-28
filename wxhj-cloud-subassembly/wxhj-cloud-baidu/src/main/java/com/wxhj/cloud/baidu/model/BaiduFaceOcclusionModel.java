package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @author wxpjf
 * @date 2020/4/28 10:21
 */
@Data
public class BaiduFaceOcclusionModel {
    //	右眼遮挡比例，[0-1] ， 1表示完全遮挡
    Double right_eye;
    // 鼻子遮挡比例，[0-1] ， 1表示完全遮挡
    Double nose;
    // 嘴巴遮挡比例，[0-1] ， 1表示完全遮挡
    Double mouth;
    // 左眼遮挡比例，[0-1] ，1表示完全遮挡
    Double left_eye;
    // 左脸颊遮挡比例，[0-1] ， 1表示完全遮挡
    Double left_cheek;
    //下巴遮挡比例，，[0-1] ， 1表示完全遮挡
    Double chin_contour;
    //右脸颊遮挡比例，[0-1] ， 1表示完全遮挡
    Double right_cheek;
}
