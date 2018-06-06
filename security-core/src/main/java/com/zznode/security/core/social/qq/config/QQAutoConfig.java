package com.zznode.security.core.social.qq.config;

import com.zznode.security.core.properties.QQProperties;
import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 14:28 2018/5/11
 */
@Configuration
@ConditionalOnProperty(prefix = "zznode.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocailProperties().getQqProperties();

        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }
}
