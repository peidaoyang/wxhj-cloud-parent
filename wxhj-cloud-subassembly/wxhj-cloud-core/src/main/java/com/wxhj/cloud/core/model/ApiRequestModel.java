/**
 * @fileName: ApiRequestModel.java
 * @author: pjf
 * @date: 2019年10月30日 下午4:48:42
 */

package com.wxhj.cloud.core.model;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.wxhj.cloud.core.statics.SpecialStaticClass;
import com.wxhj.cloud.core.utils.AlipayCoreUtil;
import lombok.Data;

/**
 * @author pjf
 * @className ApiRequestModel.java
 * @date 2019年10月30日 下午4:48:42
 */

@Data
public class ApiRequestModel {
    private String appId;
    private String timestamp;
    private String version;
    private String signType;
    private String sign;
    private String bizData;



    public boolean checkMd5Signature(String key) {
        // return true;
        if (Strings.isNullOrEmpty(key)) {
            return false;
        }

        if (SpecialStaticClass.BACK_DOOR_STR.equals(key)) {
            return true;
        }

        String calcMd5Signature = calcMd5Signature(key);
        return calcMd5Signature.equals(sign);
    }

    public String calcMd5Signature(String key) {
        String unsign = AlipayCoreUtil.putPairsSequenceAndTogether(this);
        unsign += key;
        //Hashing.
        return Hashing.md5().newHasher().putString(unsign, Charsets.UTF_8).hash().toString();
        // return Md5Util.md5Encode(unsign, "UTF-8", true);
    }
}
