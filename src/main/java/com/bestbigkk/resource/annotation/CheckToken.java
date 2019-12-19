package com.bestbigkk.resource.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
*  @author: xugongkai
*  @data: 2019-12-19 14:03:39
*  @describe: 校验Token
**/

@Inherited
@Documented
@Target({METHOD})
@Retention(RUNTIME)
public @interface CheckToken {
    String propertyName() default "token";
}
