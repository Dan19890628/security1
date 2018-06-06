package com.zznode.browser.security;

import com.zznode.browser.security.authentication.MyAuthenticationFailureHandler;
import com.zznode.browser.security.authentication.MyAuthenticationHandler;
import com.zznode.browser.security.logout.ZznodeLogOutSuccessHandler;
import com.zznode.browser.security.session.ZznodeSessionExpiredSessionStrategy;
import com.zznode.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.validate.code.SmsCodeFilter;
import com.zznode.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 11:19 201 8/2/17
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationHandler myAuthenticationHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer zznodeSecuritySocialConfig;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//将验证码filter加在UsernamePasswordAuthenticationFilter之前
                .formLogin()//采用表单的方式进行身份认证
                .loginPage("/authentication/require")// 登录页面
                .loginProcessingUrl("/authentication/form") // 处理登录请求的url
                .successHandler(myAuthenticationHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid") // 设置session失效后跳转的请求地址
                .maximumSessions(1) // 设置session最大数量为1，这样设置可以让用户最新登录的踢掉之前登录的session。
                .maxSessionsPreventsLogin(true) // 当session达到最大数量时，阻止后来的登录行为
                .expiredSessionStrategy(new ZznodeSessionExpiredSessionStrategy()) //可以做记录，是谁踢掉了之前的登录
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut") // 配置退出的请求地址
                .logoutSuccessUrl("/login.html")//配置退出登录成功后跳转的页面
                .logoutSuccessHandler(logoutSuccessHandler) // 此项配置和successUrl是互斥的，配置了handler后successUrl就会失效
                .deleteCookies("JSESSIONID")// 删除cookie
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowsers().getRememberMeSeconds())

                .and()
                .authorizeRequests()//对请求进行授权
                .antMatchers("/authentication/require", securityProperties.getBrowsers().getLoginPage(), "/code/*").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable()//关闭跨站防护
                .apply(smsCodeAuthenticationSecurityConfig) //将短息验证的配置加入
                .and()
                .apply(zznodeSecuritySocialConfig);
    }
}
