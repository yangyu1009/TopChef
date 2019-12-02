package com.topchef.demo.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //生成文档
public @interface Log {
    String value() default "";
}
