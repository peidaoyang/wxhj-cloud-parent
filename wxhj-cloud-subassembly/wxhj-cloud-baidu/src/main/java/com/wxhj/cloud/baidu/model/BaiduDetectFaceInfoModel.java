package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @author wxpjf
 * @date 2020/4/28 10:15
 */
@Data
public class BaiduDetectFaceInfoModel {
    String face_token;
    BaiduFaceImageLocationModel location;
    //人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大
    Double face_probability;

    BaiduFaceQualityModel quality;
   // Double spoofing;
}
