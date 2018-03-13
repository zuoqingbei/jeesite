/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.sys.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qdch.portal.common.service.BaseService;
import com.qdch.portal.common.utils.DateUtils;
import com.qdch.portal.common.utils.HttpRequestDeviceUtils;
import com.qdch.portal.modules.sys.utils.LogUtils;

/**
 * 日志拦截器
 * @author ThinkGem
 * @version 2014-8-19
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			long beginTime = System.currentTimeMillis();//1、开始时间  
			startTimeThreadLocal.set(beginTime); //线程绑定变量（该数据只有当前请求的线程可见）  
			String reqURL = request.getRequestURL().toString();
			request.setAttribute("startTime", beginTime);
			if (handler instanceof HandlerMethod) {
				StringBuilder sb = new StringBuilder(1000);
				HandlerMethod h = (HandlerMethod) handler;
				//Controller 的包名  
				sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
				//方法名称  
				sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
				//请求方式  post\put\get 等等  
				sb.append("RequestMethod    : ").append(request.getMethod()).append("\n");
				//所有的请求参数  
				sb.append("Params    : ").append(HttpRequestDeviceUtils.getParameterStr(request)).append("\n");
				//部分请求链接  
				sb.append("URI       : ").append(request.getRequestURI()).append("\n");
				//完整的请求链接  
				sb.append("AllURI    : ").append(reqURL).append("\n");
				//请求方的 ip地址  
				sb.append("request IP: ").append(HttpRequestDeviceUtils.getRemoteIP(request)).append("\n");
				logger.info(sb.toString());
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// 保存日志
		/*LogUtils.saveLog(request, handler, ex, null);

		// 打印JVM信息。
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
			long endTime = System.currentTimeMillis(); //2、结束时间  
			logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
					new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
					DateUtils.formatDateTime(endTime - beginTime), request.getRequestURI(), Runtime.getRuntime()
							.maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime
							.getRuntime().freeMemory() / 1024 / 1024, (Runtime.getRuntime().maxMemory()
							- Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
			//删除线程变量中的数据，防止内存泄漏
			startTimeThreadLocal.remove();
		}*/

	}

}
