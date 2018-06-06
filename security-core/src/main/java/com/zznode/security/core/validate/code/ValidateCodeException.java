package com.zznode.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:37 2018/3/22
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
