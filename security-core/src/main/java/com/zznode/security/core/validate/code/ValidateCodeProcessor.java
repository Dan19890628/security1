package com.zznode.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 22:54 2018/3/27
 */
public interface ValidateCodeProcessor {
    String SESSION_KEY = "SESSION_KEY_CODE";

    /**
     * 创建验证码
     * @param request
     */
    void createCode(ServletWebRequest request);
}
