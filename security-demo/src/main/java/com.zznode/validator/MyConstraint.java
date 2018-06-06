package com.zznode.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sc_ma on 2018/1/24.
 */
@Target({ElementType.METHOD, ElementType.FIELD})//注解位置:方法和字段上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Constraint(validatedBy = MyConstraintValidator.class)


public @interface MyConstraint {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
