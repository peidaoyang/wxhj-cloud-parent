/**
 * @fileName: PasswordUtil.java
 * @author: pjf
 * @date: 2019年10月11日 下午5:10:03
 */

package com.wxhj.cloud.core.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @className PasswordUtil.java
 * @author pjf
 * @date 2019年10月11日 下午5:10:03   
 */

/**
 * @className PasswordUtil.java
 * @author pjf
 * @date 2019年10月11日 下午5:10:03
 */
@Slf4j
public class PasswordUtil {

    public static String calculationPassword(String password, String key) {

        key = Hashing.md5().newHasher().putString(key, Charsets.UTF_8).hash().toString().substring(0, 8).toLowerCase();
        //key = Md5Util.md5(key).substring(0, 8).toLowerCase();
        try {
            password = DesUtil.encrypt(password, key).toLowerCase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }

        password = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString().substring(0, 32).toLowerCase();
        //password = Md5Util.md5(password).substring(0, 32).toLowerCase();
        return password;
    }

    public static String generatePasswordKey() {
        return UUID.randomUUID().toString().replace("-", "").substring(16);
    }
}
