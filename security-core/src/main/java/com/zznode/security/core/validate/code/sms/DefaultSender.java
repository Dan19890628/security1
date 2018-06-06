package com.zznode.security.core.validate.code.sms;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 22:39 2018/3/27
 */
public class DefaultSender implements SmsCodeSender {
    @Override
    public void sendCode(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送验证码" + code);
    }
}
