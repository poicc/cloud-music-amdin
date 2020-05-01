package com.soft1851.music.admin.annotation;

import com.soft1851.music.admin.validator.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author CRQ
 */
@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UserNameValidator.class)
public @interface UsernameAttribute {
    String message() default "自定义：用户名输入异常";
    Class[] groups() default {};
    Class[] payload() default {};

}
