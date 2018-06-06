package com.zznode.browser.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zznode.security.core.properties.LoginType;
import com.zznode.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:25 2018/3/21
 */
@Component("myAuthenticationHandler")
public class MyAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        // 判断配置的登录返回类型，如果是返回JSON，返回JSON字符串，否则跳转至登录页面
        if (LoginType.JSON.equals(securityProperties.getBrowsers().getLoginType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }

    }
}
