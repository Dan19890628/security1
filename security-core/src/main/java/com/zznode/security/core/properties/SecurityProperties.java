package com.zznode.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 20:38 2018/3/20
 */
@ConfigurationProperties(prefix = "zznode.security")
public class SecurityProperties {
    private BrowserProperties browsers = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties socailProperties = new SocialProperties();

    public SocialProperties getSocailProperties() {
        return socailProperties;
    }

    public void setSocailProperties(SocialProperties socailProperties) {
        this.socailProperties = socailProperties;
    }

    public BrowserProperties getBrowsers() {
        return browsers;
    }

    public void setBrowsers(BrowserProperties browser) {
        this.browsers = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
