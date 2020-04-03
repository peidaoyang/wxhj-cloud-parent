package com.wxhj.cloud.account.dto.account;

import java.util.List;

import com.wxhj.cloud.feignClient.face.bo.FaceChangeBO;

import lombok.Data;

/**
 * 精简版FaceAccountInfoDO，与写文件交互的dto，字段比FaceAccountInfoDO中的少
 * @author daxiong
 * @date 2020-03-31 16:50
 */
@Data
public class FileFaceAccountDTO {
    /**
     * 场景流水号信息
     */
    private FaceChangeBO faceChange;

    /**
     * 账号输出到文件的信息
     */
    private List<FileFaceAccountInfoDTO> accountInfoList;

}
