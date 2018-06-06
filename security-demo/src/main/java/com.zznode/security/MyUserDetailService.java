package com.zznode.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 13:23 2018/2/19
 */
@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("表单登录用户名："+s);
        return buildUser(s);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        logger.info("社交登录用户ID："+s);
        return buildUser(s);
    }

    private SocialUserDetails buildUser(String s) {
        String passwordEncode = passwordEncoder.encode("123456");
        logger.info("数据库的密码是：" + passwordEncode);
        return new SocialUser(s, passwordEncode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
