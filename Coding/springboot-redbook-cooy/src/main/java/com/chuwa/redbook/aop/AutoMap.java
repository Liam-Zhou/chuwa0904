package com.chuwa.redbook.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // This annotation can be applied to methods
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoMap {
    Class<?> source();
    Class<?> target();
}
