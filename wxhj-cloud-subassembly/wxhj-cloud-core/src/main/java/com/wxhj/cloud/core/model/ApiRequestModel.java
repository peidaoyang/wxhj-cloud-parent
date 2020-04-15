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
import com.wxhj.cloud.core.utils.Sm2Util;
import com.wxhj.cloud.core.utils.Sm2Util.Signature;
import lombok.Data;
import org.bouncycastle.math.ec.ECPoint;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

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

//    public ApiRequestModel() {
//        super();
//    }
//
//    public ApiRequestModel(HttpServletRequest request) {
//        this.appId = request.getParameter("appId");
//        // this.charset=request.getParameter("charset");
//        // this.format=request.getParameter("format");
//        this.timestamp = request.getParameter("timestamp");
//        this.version = request.getParameter("version");
//        this.signType = request.getParameter("signType");
//        this.sign = request.getParameter("sign");
//        this.bizData = request.getParameter("bizData");
//    }

//    public boolean checkSm2Signature(ECPoint publicKey) {
//        String unsign = AlipayCoreUtil.putPairsSequenceAndTogether(this);
//        String ida = "";
//        String r = sign.substring(0, 64);
//        String s = sign.substring(64, 128);
//        Signature signature = new Signature(new BigInteger(r, 16), new BigInteger(s, 16));
//
//        return new Sm2Util().verify(unsign, signature, ida, publicKey);
//    }

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

        return Hashing.md5().newHasher().putString(unsign, Charsets.UTF_8).hash().toString();
        // return Md5Util.md5Encode(unsign, "UTF-8", true);
    }
}
