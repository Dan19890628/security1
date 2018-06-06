package com.zznode.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by sc_ma on 2018/1/24.
 * MyConstraint 泛型，要验证的注解
 * Object 泛型，要验证字段的类型
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    @Override
    public void initialize(MyConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
