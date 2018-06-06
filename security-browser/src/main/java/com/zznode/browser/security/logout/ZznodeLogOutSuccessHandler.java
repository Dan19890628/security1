package com.zznode.browser.security.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zznode.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 16:06 2018/5/15
 */
public class ZznodeLogOutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private SecurityProperties securityProperties;
    private ObjectMapper objectMapper = new ObjectMapper();
    public ZznodeLogOutSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        // TODO 退出成功后的处理逻辑
        String signOutUrl = securityProperties.getBrowsers().getSignOutUrl();
        if (StringUtils.isEmpty(signOutUrl)) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            httpServletResponse.sendRedirect(signOutUrl);
        }
    }
}
