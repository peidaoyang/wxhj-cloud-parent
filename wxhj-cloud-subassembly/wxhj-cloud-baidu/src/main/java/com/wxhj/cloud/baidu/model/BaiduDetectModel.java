package com.wxhj.cloud.baidu.model;

import lombok.Data;

import java.util.List;

/**
 * @author wxpjf
 * @date 2020/4/28 10:14
 */
@Data
public class BaiduDetectModel {
    Integer face_num;
    List<BaiduDetectFaceInfoModel> face_list;
}
