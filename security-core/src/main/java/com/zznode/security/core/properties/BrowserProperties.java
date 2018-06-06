package com.zznode.security.core.properties;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 20:41 2018/3/20
 */
public class BrowserProperties {
    private String loginPage = "/login.html";
    private String signOutUrl;
    private LoginType loginType = LoginType.JSON;
    private int rememberMeSeconds = 3600;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }
}
