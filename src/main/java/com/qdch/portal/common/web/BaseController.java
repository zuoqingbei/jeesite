/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.beanvalidator.BeanValidators;
import com.qdch.portal.common.config.Constant;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.DateUtils;
import com.qdch.portal.common.utils.FormateJsonToStringByAnnotation;
import com.qdch.portal.common.utils.HttpRequestDeviceUtils;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController  implements Constant{

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;
	
	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;
	
	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;
	/**
	 * 门户前端访问基础路径
	 */
	@Value("${portalPath}")
	protected String portalPath;
	/**
	 * 门户前端对接用户访问基础路径
	 */
	@Value("${jointPath}")
	protected String jointPath;
	/**
	 * 门户前端页面文件夹名称
	 */
	@Value("${portalPage}")
	protected String portalPage;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			//子这里 可以做手机或者PC控制 进行加密处理
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	protected HashMap< String, Object> mapJson(Page page,Object status,Object msg) {
		HashMap< String, Object> map=new HashMap<String, Object>();
		map.put("status",status);
		map.put("msg", msg);
		map.put("count",page.getCount());
		map.put("totalPage",page.getTotalPage());
		map.put("data", page.getList());
		return map;
	}
	/**
	 * @todo   返回成功数据不带tag
	 * @time   2018年3月12日 下午6:27:36
	 * @author zuoqb
	 * @return_type   String
	 */
	protected String resultSuccessData(HttpServletRequest request,HttpServletResponse response,String msg,Object data) {
		return this.resultData(request,response,"success", msg,"", data);
	}
	protected String resultFaliureData(HttpServletRequest request,HttpServletResponse response,String msg,Object data) {
		return this.resultData(request,response,"faliure", msg,"", data);
	}
	/**
	 * @todo   返回数据
	 * @time   2018年3月12日 下午6:27:48
	 * @author zuoqb
	 * @return_type   String
	 */
	protected String resultData(HttpServletRequest request,HttpServletResponse response,Object status,String msg,String tag,Object data) {
		if(data instanceof Map){
			//return renderString(response,  , "application/json");
			return JsonMapper.toJsonString(data);
		}else{
			if(HttpRequestDeviceUtils.isMobileDevice(request)){
				HashMap< String, Object> map=new HashMap<String, Object>();
				map.put("status", status);
				map.put("msg", msg);
				map.put("data", data);
				return JsonMapper.toJsonString(map);
				//return renderString(response,  new FormateJsonToStringByAnnotation().jsonFromObject(status, msg,data), "application/json");
			}else{
				HashMap< String, Object> map=new HashMap<String, Object>();
				map.put("status", status);
				map.put("msg", msg);
				map.put("data", data);
				//return renderString(response,   JsonMapper.toJsonString(map), "application/json");
				return JsonMapper.toJsonString(map);
			}
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
		});
	}
	/**
	 * 
	 * @time   2018年3月3日 上午11:21:22
	 * @author zuoqb
	 * @todo   重写返回页面方法
	 * @param  @param request
	 * @param  @param view
	 * @param  @return
	 * @return_type   String
	 */
	public String render(HttpServletRequest request,String view) {
		boolean mobile = HttpRequestDeviceUtils.isMobileDevice(request);//判断是否手机站点
		//处理手机占位符
		view=dealMobileView(mobile, view);
		Boolean isDev =Global.getConfig("demoMode").equals("true");
		if(isDev){
			System.out.println("-------------Render-----------------------");
			System.out.println("view_path:"+view);
			System.out.println("------------------------------------------");
		}
		return view;
	}

	/**
	 * 如果需要根据手机自带把web页面切换到手机页面
	 * 则可以再view中使用占位符。如：render("/account/pay"+MOBILE_VIEW_PLACEHOLDER+"pay.html");	
	 * 如果是手机就好默认吧占位符替换成"/m/"
	 * @see 注意: 手机文件夹要 命名为  m
	 * @param mobile
	 * @param view
	 * @return
	 * @author zuoqb
	 * @date   2018年3月3日11:19:40
	 */
	private String dealMobileView(boolean mobile,String view){
		if(view.indexOf(MOBILE_VIEW_PLACEHOLDER)!=-1){
			//标示需要判断手机访问 or PC访问
			if(mobile){
				view=MOBILE_VIEW_FOLDER+portalPage+view.replace(MOBILE_VIEW_PLACEHOLDER, "/");
			}else{
				view=portalPage+view.replace(MOBILE_VIEW_PLACEHOLDER, "/");
			}
		}else{
			//只有电脑PC页面
			view=portalPage+"/"+view;
		}
		return  view;
	}
	
	
	
}
 
