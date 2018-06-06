package com.zznode.security.app;

import com.zznode.security.core.properties.SecurityConstants;
import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.validate.code.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 14:43 2018/6/4
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer zznodeSecuritySocialConfig;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationHandler)
                .failureHandler(myAuthenticationFailureHandler);
        http.apply(zznodeSecuritySocialConfig)
                .and()
                .authorizeRequests()//对请求进行授权
                .antMatchers("/authentication/require", securityProperties.getBrowsers().getLoginPage(), "/code/*").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();//关闭跨站防护

    }
}
