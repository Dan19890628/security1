package com.zznode.security.core.validate.code.impl;

import com.zznode.security.core.validate.code.ValidateCodeGenerator;
import com.zznode.security.core.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 22:57 2018/3/27
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {
    /**
     * 收集系统中所有{@Link validateCodeGenerator}接口的实现，利用spring依赖查找功能实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;
}
