package com.api.webservice.annotation;

import com.api.webservice.utils.EnumUtils;

import java.lang.annotation.*;

/**
 * 用户角色注解
 *
 * @author h.cai
 * @date 2018/06/15
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface UserAnnotation {
    EnumUtils.Role[] roles() default {};
}


