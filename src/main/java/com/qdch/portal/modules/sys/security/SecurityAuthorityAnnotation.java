package com.qdch.portal.modules.sys.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qdch.portal.modules.sys.entity.Role.RoleTypeEnum;

/**
 * @todo 类说明 ：对action的访问权限的注解
 * 1.登录权限
 * 2.角色权限
 * @className SecurityAuthorityAnnotation.java
 * @time   2018年3月3日 下午9:11:36
 * @author zuoqb
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface SecurityAuthorityAnnotation {
	/**
	 * @todo   是否需要登录
	 * @time   2018年3月3日 下午9:14:42
	 * @author zuoqb
	 * @return_type  boolean	true需要 false不需要	默认需要登录
	 */
	boolean needLogin() default true;

	/**
	 * @todo   哪些角色类型可以访问
	 * @time   2018年3月3日 下午9:15:27
	 * @author zuoqb
	 * @return_type   RoleTypeEnum[]	在RoleTypeEnum枚举里面选择	默认为空
	 */
	RoleTypeEnum[] needRoles() default {};

}
