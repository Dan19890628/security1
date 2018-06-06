package com.zznode.code;

import com.zznode.security.core.validate.code.ImageCode;
import com.zznode.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 0:21 2018/3/23
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode createCode(ServletWebRequest request) {
        System.out.print("更高级的图形验证码");
        return null;
    }
}
