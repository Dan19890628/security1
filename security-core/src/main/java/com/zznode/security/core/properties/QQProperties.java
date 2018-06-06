package com.zznode.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 14:21 2018/5/11
 */
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
