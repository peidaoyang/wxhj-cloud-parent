package com.wxhj.cloud.sso;

import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import org.springframework.stereotype.Component;

/**
 * @author wxpjf
 * @date 2020/5/12 8:40
 */
@Component
public class SsoUserOperation extends SsoRedisCacheOperation<SsoAuthenticationBO>{
    @Override
    public String getKey() {
        return RedisKeyStaticClass.SSO_USER;
    }

    @Override
    public Integer getExpireMinite() {
        return OtherStaticClass.SSO_REDIS_EXPIRE_MINITE;
    }
}
