package com.zznode.security.core.social.qq.connect;

import com.zznode.security.core.social.qq.api.QQ;
import com.zznode.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 23:41 2018/4/19
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String s) {
        return new QQImpl(s, appId);
    }
}
