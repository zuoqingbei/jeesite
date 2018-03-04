/**
 * 清算所监管平台PC登陆
 */
package com.qdch.portal.modules.user.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.security.shiro.session.SessionDAO;
import com.qdch.portal.common.servlet.ValidateCodeServlet;
import com.qdch.portal.common.utils.CacheUtils;
import com.qdch.portal.common.utils.CookieUtils;
import com.qdch.portal.common.utils.IdGen;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.sys.security.FormAuthenticationFilter;
import com.qdch.portal.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class PortalLoginController extends BaseController{
	
	@Autowired
	private SessionDAO sessionDAO;
	
	/**
	 * 
	 * @time   2018年3月3日 下午3:34:48
	 * @author zuoqb
	 * @todo   登陆页面
	 * @param  @param request
	 * @param  @param response
	 * @param  @param model
	 * @param  @return
	 * @return_type   String
	 */
	@RequestMapping(value = "${portalPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_TO_PARAM, WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_TO_PARAM));
		// 如果已经登录，则跳转到首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + portalPath;
		}
		return render(request, "${pla}user/login");
	}

	/**
	 * 
	 * @time   2018年3月3日 下午3:34:13
	 * @author zuoqb
	 * @todo   TODO
	 * @param  @param request
	 * @param  @param response
	 * @param  @param model
	 * @param  @return
	 * @return_type   String
	 */
	@RequestMapping(value = "${portalPath}/login", method = RequestMethod.POST)
	public String dealLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		String to = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_TO_PARAM);
		if(StringUtils.isBlank(to)){
			to=portalPath;
		}
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + to;
		}
		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String msg = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		boolean isSuccess=false;
		FormAuthenticationFilter p=new FormAuthenticationFilter();
		AuthenticationToken token= p.createToken(request, response);
		Subject subject = SecurityUtils.getSubject();  
		try {  
	        subject.login(token);  
	        isSuccess=subject.isAuthenticated();
	    } catch (IncorrectCredentialsException e) {  
	        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
	    } catch (ExcessiveAttemptsException e) {  
	        msg = "登录失败次数过多";  
	    } catch (LockedAccountException e) {  
	        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
	    } catch (DisabledAccountException e) {  
	        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
	    } catch (ExpiredCredentialsException e) {  
	        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
	    } catch (UnknownAccountException e) {  
	        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
	    } catch (UnauthorizedException e) {  
	        msg = "您没有得到相应的授权！" + e.getMessage();  
	    }  
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, msg);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_TO_PARAM, to);
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		// 非授权异常，登录失败，验证码加1。
		if (!isSuccess||(!UnauthorizedException.class.getName().equals(exception)&&exception!=null
				&&StringUtils.isNotBlank(exception))){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
			//登陆失败
			return render(request, "${pla}user/login");
		}else{

			// 如果是手机登录，则返回JSON字符串
			if (mobile){
		        return renderString(response, model);
			}
			return "redirect:" + to;
		}
	}

	
	
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
	/**
	 * 
	 * @time   2018年3月3日 下午6:00:37
	 * @author zuoqb
	 * @todo   登出
	 * @param  @param request
	 * @param  @param response
	 * @param  @param model
	 * @param  @return
	 * @return_type   String
	 */
	@RequestMapping(value = "${portalPath}/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		UserUtils.getSubject().logout();
		//UserUtils.removeCache("");
		return "redirect:" + portalPath+"/login";
	}
	
}
