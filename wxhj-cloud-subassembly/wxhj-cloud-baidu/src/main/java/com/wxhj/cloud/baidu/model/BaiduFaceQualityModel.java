package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @author wxpjf
 * @date 2020/4/28 10:20
 */
@Data
public class BaiduFaceQualityModel {
    Double illumination;
    Double blur;
    Double completeness;
    BaiduFaceOcclusionModel occlusion;
}
