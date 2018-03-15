package com.qdch.portal.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 左青贝
 * 2018年3月15日15:59:11
 * annotation注解接口 决定属性是否传向手机端
 * 当在实体属性上引入@Json的时候才能拼接入json字
 * 符串
 */
@Documented  
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE, ElementType.FIELD,  
	       ElementType.METHOD, ElementType.PARAMETER})  
public @interface Json {
	public boolean isJson() default true;
}
