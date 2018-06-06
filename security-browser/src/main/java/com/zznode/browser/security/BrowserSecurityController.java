package com.zznode.browser.security;

import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 20:22 2018/3/20
 */
@RestController

public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();// session缓存
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); // 跳转工具
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)//返回401状态码
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();//获取跳转过来的url
            logger.info("跳转过来的Url是：" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowsers().getLoginPage());
            }
        }
        return new SimpleResponse("需要登录");
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid() {
        return new SimpleResponse("登录失效");
    }
}
