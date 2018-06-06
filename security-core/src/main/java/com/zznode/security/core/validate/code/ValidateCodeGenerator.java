package com.zznode.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 0:09 2018/3/23
 */
public interface ValidateCodeGenerator {
    ValidateCode createCode(ServletWebRequest request);
}
