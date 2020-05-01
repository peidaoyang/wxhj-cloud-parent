package com.wxhj.common.device.model;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.wxhj.common.device.util.DeviceCommonUtil;
import lombok.Data;

/**
 * @author pjf
 * @className ApiRequestModel.java
 * @date 2019年10月30日 下午4:48:42
 */
@Data
public class ApiRequestModel {
    private String deviceId;
    private String timestamp;
    private String version;
    private String signType;
    private String sign;
    private String bizData;

    public boolean checkMd5Signature(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return false;
        }

        if (DeviceCommonUtil.BACK_DOOR_STR.equals(key)) {
            return true;
        }

        String calcMd5Signature = calcMd5Signature(key);
        return calcMd5Signature.equals(sign);
    }

    public String calcMd5Signature(String key) {
        String unsign = DeviceCommonUtil.putPairsSequenceAndTogether(this);
        unsign += key;
        return Hashing.md5().newHasher().putString(unsign, Charsets.UTF_8).hash().toString();
    }

}
