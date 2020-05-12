package com.wxhj.cloud.sso;

import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.sso.bo.AppAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import org.springframework.stereotype.Component;

/**
 * @author wxpjf
 * @date 2020/5/12 8:40
 */
@Component
public class SsoAppOperation extends SsoRedisCacheOperation<AppAuthenticationBO>{
    @Override
    public String getKey() {
        return RedisKeyStaticClass.SSO_APP_USER;
    }

    @Override
    public Integer getExpireMinite() {
        return OtherStaticClass.SSO_REDIS_EXPIRE_MINITE;
    }
}
