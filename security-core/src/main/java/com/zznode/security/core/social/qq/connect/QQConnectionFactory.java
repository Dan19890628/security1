package com.zznode.security.core.social.qq.connect;

import com.zznode.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:05 2018/5/3
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
