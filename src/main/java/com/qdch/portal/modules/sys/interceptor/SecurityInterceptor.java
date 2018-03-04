/*
 * Copyright (c) 2012 <a href="http://www.med626.com.cn">上海医师在线 信息技术有限公司</a>  All Rights Reserved.                	 
 */

package com.qdch.portal.modules.sys.interceptor;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qdch.portal.common.config.Constant;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.service.BaseService;
import com.qdch.portal.common.utils.HttpRequestDeviceUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.sys.entity.Role.RoleTypeEnum;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.security.FormAuthenticationFilter;
import com.qdch.portal.modules.sys.security.SecurityAuthorityAnnotation;
import com.qdch.portal.modules.sys.utils.UserUtils;




/** 
 * 类说明 ：权限拦截器 -只针对前台PC+APP
 * 1.是否需要进行权限过滤,对不需要权限的路径过滤,需要过滤的就进行过滤
 * 2.如果未登录去登录页面
 * 3.判断请求路径,是否该用户角色能登录
 * 4.如果已登录,且验证不通过的跳到403页面.
 * @author 	作者 : longjunfeng E-mail：463527083@qq.com
 * @version 创建时间：2016年7月4日 上午9:13:37 
 */
public class SecurityInterceptor extends BaseService implements HandlerInterceptor  {
	
	/**
	 * 不需要登录路径
	 */
	@SuppressWarnings("serial")
	public final static List<String> passUrls= new ArrayList<String>(){
	{
		//登录地址
		add(Global.getPortalPath()+"/login");
		add(Global.getPortalPath()+"/logout");
	}};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String parmeter = HttpRequestDeviceUtils.getParameterStr(request);  
		
		String action_key = request.getRequestURI();
		String to= request.getRequestURI() + (parmeter==null?"":"?"+parmeter);
		HandlerMethod handlerMethod=null;
		try {
			 handlerMethod = (HandlerMethod) handler;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("index".equals(handlerMethod.getMethod().getName()) ){
			action_key=action_key+"/";
		}
  
		//获取方法上的注解
		SecurityAuthorityAnnotation anno =handlerMethod.getMethod().getAnnotation(SecurityAuthorityAnnotation.class);
	
		//没找到就再获取类上的注解
		if(anno==null){
			anno=handlerMethod.getBean().getClass().getAnnotation(SecurityAuthorityAnnotation.class);
		}
//		1.对不需要权限的路径过滤,写死的,类的方法上加了不需要权限的.需要过滤的就进行过滤
		if(!isNeedSecurity(action_key, anno)){
			return true;
		}
		return dealSecurityAuthorityAnnotation(request, response, anno, action_key,to);
	}
	/**
	 * 
	 * @todo   用户是否有权限访问 带有权限注解的方法或者类
	 * @time   2018年3月4日 上午9:49:18
	 * @author zuoqb
	 * @return_type   boolean	true 通过可以访问  false 未通过不可以访问
	 */
	private boolean dealSecurityAuthorityAnnotation(HttpServletRequest request, HttpServletResponse response,SecurityAuthorityAnnotation anno,String action_key,String to) {
		//1.根据注解判断有没有权限
		if(anno==null){
			return true;
		}
		User user=UserUtils.getUser();
		//1.1判断登录
		if(anno.needLogin()){
			if(user==null||StringUtils.isBlank(user.getId())){
				//如果未登录去登录页面
				toLoginPage(request,response, action_key, to);
				return false;
			}
		}
		//1.2判断角色 未登录返回false,不包含角色返回false
		if(anno.needRoles()!=null&&anno.needRoles().length>0){
			if(user==null||StringUtils.isBlank(user.getId())){
				//如果未登录去登录页面
				toLoginPage(request,response, action_key, to);
				return false;
			}else if(user.getRoleTypes()==null||user.getRoleTypes().size()==0){
				//无权限
				request.setAttribute(Constant.STATUS, "error");
				request.setAttribute(Constant.MSG, "无访问权限！");
				try {
					request.getRequestDispatcher("/WEB-INF/views/portal/error/noauthority.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return false;
			}
			boolean isHasRole=false;
			for (RoleTypeEnum role : anno.needRoles()) {
				if(user.getRoleTypes().contains(role)){
					isHasRole=true;
					break;
				}
			}
			if(!isHasRole){
				request.setAttribute(Constant.STATUS, "error");
				request.setAttribute(Constant.MSG, "无访问权限！");
				try {
					request.getRequestDispatcher("/WEB-INF/views/portal/error/noauthority.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		}
		//其他权限 扩展
		return true;
	}
	/**
	 * 
	 * @time   2018年3月3日 下午9:02:32
	 * @author zuoqb
	 * @todo  1.是否需要进行权限过滤,对不需要权限的路径过滤,需要过滤的就进行过滤
	 * @return	true 需要权限过滤 	false 不需要权限过滤
	 * @param  @param action_key
	 * @param  @param anno
	 * @param  @return
	 * @return_type   boolean
	 */
	public boolean isNeedSecurity(String action_key,SecurityAuthorityAnnotation anno){
		//1.是否是不需要处理的url
		if(isPass(action_key)){
			return false;
		}
		//2.是否类或者方法上有权限的注解
		if(anno!=null){
			return true;
		}
		//3.是否不在包含的需要角色访问的菜单路径之中,包含在就需要登录,不包含就不需要登录
		boolean allPass=true;
		for (RoleTypeEnum one : RoleTypeEnum.values()) {
			if(action_key.startsWith(one.getPath())){
				allPass=false;
				break;
			}
		}
		if(allPass){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @time   2018年3月3日 下午9:00:57
	 * @author zuoqb
	 * @todo   是否放行，不需要登录检测
	 * @param  @param action_key
	 * @param  @return
	 * @return_type   boolean
	 */
	public static boolean isPass(String action_key){
		//这个条件特殊 推广活动 extend
		if((action_key.contains("/extend/"))){
			return true;
		}
		for (String string : passUrls) {
			if(action_key.startsWith(string)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @todo   * 跳转到登录页面
	 *  1.超级后台调到 /a/login
		2.平台页面调到/portal/login
	 * @time   2018年3月4日 上午9:29:01
	 * @author zuoqb
	 * @return_type   void
	 */
	public void toLoginPage(HttpServletRequest request, HttpServletResponse response,String action_key ,String to){
	/*	boolean isAjax = HttpRequestDeviceUtils.isAjaxRequet(request);
		if(isAjax){
			request.setAttribute(Constant.STATUS, "error");
			request.setAttribute(Constant.MSG, "登录过期，请刷新页面重新登录！");
		}*/
		String toUrl="";
		if(action_key.startsWith(Global.getAdminPath()+"/")){
			toUrl=Global.getAdminPath()+"/login?"+FormAuthenticationFilter.DEFAULT_TO_PARAM+"="+to;
		}else if(action_key.startsWith(Global.getPortalPath()+"/")
				||action_key.startsWith(Global.getJointPath()+"/")){
			toUrl=Global.getPortalPath()+"/login?"+FormAuthenticationFilter.DEFAULT_TO_PARAM+"="+to;
		}
		if(StringUtils.isNotBlank(toUrl)){
			try {
				response.sendRedirect(toUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
