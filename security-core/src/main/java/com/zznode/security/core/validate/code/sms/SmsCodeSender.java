package com.zznode.security.core.validate.code.sms;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 22:38 2018/3/27
 */
public interface SmsCodeSender {
    void sendCode(String mobile, String code);
}
