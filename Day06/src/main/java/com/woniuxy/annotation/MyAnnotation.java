package com.woniuxy.annotation;

/**
 * @Author: rua
 * @Date: 2021/7/22 18:52
 * @Description: 自定义注解
 */

import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的本质就是一个接口
 * 元注解 :修饰注解的注解
 * 			@ Target(规范注解使用的位置)
 * 			ElementType.TYPE... 类,接口,枚举
 *   		@ Retention(规范注解使用的时期 三个阶段)
 *   		RetentionPolicy.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
public @interface MyAnnotation {
	String value();
}
