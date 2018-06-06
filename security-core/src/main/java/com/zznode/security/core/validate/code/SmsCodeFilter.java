package com.zznode.security.core.validate.code;

import com.zznode.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:33 2018/3/22
 */

public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private SecurityProperties securityProperties;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(), ",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (matcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ValidateCode sessionSmsCode = (ValidateCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY_SMS);
        String codeRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
        if (StringUtils.isBlank(codeRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (sessionSmsCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (sessionSmsCode.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(sessionSmsCode.getCode(), codeRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
