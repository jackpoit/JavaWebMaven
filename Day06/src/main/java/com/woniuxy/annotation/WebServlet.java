package com.woniuxy.annotation;


import java.lang.annotation.*;


@Documented //在最终生产javadoc时带着注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {
	String name() default "";
	String[] value() default "";
	String[] urlPattrerns() default "";

}
