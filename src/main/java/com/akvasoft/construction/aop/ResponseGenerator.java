package com.akvasoft.construction.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseGenerator {
    String errorCode() default "";

    String errorMessage() default "";

    boolean ignoreError() default false;
}
