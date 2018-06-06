package com.zznode.security.core.validate.code;

import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.validate.code.sms.DefaultSender;
import com.zznode.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 0:15 2018/3/23
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender() {

        return new DefaultSender();
    }

}
