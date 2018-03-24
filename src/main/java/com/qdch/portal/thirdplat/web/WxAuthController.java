/**
 * 微信认证
 */
package com.qdch.portal.thirdplat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Constant;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.security.FormAuthenticationFilter;
import com.qdch.portal.modules.sys.service.SystemService;
import com.qdch.portal.modules.sys.utils.UserUtils;
import com.qdch.portal.thirdplat.entity.AccessToken;
import com.qdch.portal.thirdplat.entity.AccountThirdplat;
import com.qdch.portal.thirdplat.entity.WxUserInfo;
import com.qdch.portal.thirdplat.service.AccountThirdplatService;
import com.qdch.portal.thirdplat.utils.WxpubOAuth;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class WxAuthController extends BaseController{
	@Autowired
	private SystemService systemService;
	@Autowired
	private AccountThirdplatService accountThirdplatService;
	/**
	 * @todo   TODO
	 * @time   2018年3月21日 上午10:40:12
	 * @author zuoqb
	 * @return_type   生成认证请求链接
	 */
	//@SecurityAuthorityAnnotation(needLogin=true)
	@RequestMapping(value = {"${portalPath}/wx/auth"}, method = RequestMethod.GET)
	public String wxAuth(Model model,HttpServletRequest request, HttpServletResponse response){
		String to=request.getParameter("to");
		String scope=request.getParameter("scope");
		String redictUrl=null;
		if("snsapi_userinfo".equals(scope)){
			redictUrl=WxpubOAuth.createOauthUrlForCode(to, true);
		}else{
			redictUrl=WxpubOAuth.createOauthUrlForCode(to, false);
		}
		System.out.println(redictUrl);
		return "redirect:" + redictUrl;
	}
	/**
	 * @todo   处理微信用户验证
	 * @time   2018年3月21日 上午10:00:16
	 * @author zuoqb
	 * @return_type   AccountThirdplat
	 */
	public  AccountThirdplat dealAuthorize(HttpServletRequest request){
		String code=request.getParameter("code");
		AccountThirdplat accountThirdplat=null;
		if(StringUtils.isNotBlank(code)){
			AccessToken accessToken=WxpubOAuth.getAccessTokenByCode(code);
			WxUserInfo wxUserInfo=WxpubOAuth.getUserInfo(accessToken);
			accountThirdplat=accountThirdplatService.getByPlatKey(new AccountThirdplat("wechat", accessToken.getOpenid()));
			if(accountThirdplat==null){
				//用户第一次进入 第一步 保存第三方平台信息 第二步 让用户验证手机号
				accountThirdplat=new AccountThirdplat(wxUserInfo);
				accountThirdplatService.save(accountThirdplat);
			}else{
				//更新用户信息
				accountThirdplat=accountThirdplat.updateInfo(accountThirdplat, wxUserInfo);
				accountThirdplatService.save(accountThirdplat);
			}
		}
		return accountThirdplat;
	}
	@RequestMapping(value = {"${portalPath}/wx/authorize"})
	@ResponseBody
	public String authorize(HttpServletRequest request, HttpServletResponse response){
		String code=request.getParameter("code");
		AccessToken accessToken=WxpubOAuth.getAccessTokenByCode(code);
		WxUserInfo wxUserInfo=WxpubOAuth.getUserInfo(accessToken);
		AccountThirdplat accountThirdplat=accountThirdplatService.getByPlatKey(new AccountThirdplat("wechat", accessToken.getOpenid()));
		if(accountThirdplat==null){
			//用户第一次进入 第一步 保存第三方平台信息 第二步 让用户验证手机号
			accountThirdplat=new AccountThirdplat(wxUserInfo);
			accountThirdplatService.save(accountThirdplat);
		}else{
			//更新用户信息
			accountThirdplat=accountThirdplat.updateInfo(accountThirdplat, wxUserInfo);
			accountThirdplatService.save(accountThirdplat);
		}
		return this.renderString(response, JsonMapper.toJsonString(accountThirdplat), "application/json");
	}
	@RequestMapping(value = {"${portalPath}/accountReport"})
	public String accountReport(RedirectAttributes  model,HttpServletRequest request, HttpServletResponse response){
		AccountThirdplat accountThirdplat=dealAuthorize(request);
		if(accountThirdplat!=null){
			if(accountThirdplat.getUser()==null||StringUtils.isBlank(accountThirdplat.getUser().getId())){
				//之前进入 但是没有验证手机  跳转验证页面 
				model.addAttribute("accountId", accountThirdplat.getId());
				model.addAttribute("to", portalPath+"/wx/report");//认证成功 要跳转页面
				return "redirect:" + portalPath+"/wx/userinfo";
			}else{
				//进入填报页面
				model.addAttribute("userId", accountThirdplat.getUser().getId());
				return "redirect:" + portalPath+"/wx/report";
			}
		}else{
			//code验证失败
			return "portal/error/noauthority";
		}
	}
	
	/*@RequestMapping(value = {"${portalPath}/accountReport"})
	public String accountReport(RedirectAttributes  model,HttpServletRequest request, HttpServletResponse response){
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			AccessToken accessToken=WxpubOAuth.getAccessTokenByCode(code);
			WxUserInfo wxUserInfo=WxpubOAuth.getUserInfo(accessToken);
			AccountThirdplat accountThirdplat=accountThirdplatService.getByPlatKey(new AccountThirdplat("wechat", accessToken.getOpenid()));
			if(accountThirdplat==null){
				//用户第一次进入 第一步 保存第三方平台信息 第二步 让用户验证手机号
				accountThirdplat=new AccountThirdplat(wxUserInfo);
				accountThirdplatService.save(accountThirdplat);
				model.addAttribute("accountId", accountThirdplat.getId());
				return "redirect:" + portalPath+"/wx/userinfo";
			}else{
				model.addAttribute("userId", accountThirdplat.getUser().getId());
				//更新用户信息
				accountThirdplat=accountThirdplat.updateInfo(accountThirdplat, wxUserInfo);
				accountThirdplatService.save(accountThirdplat);
				if(accountThirdplat.getUser()==null||StringUtils.isBlank(accountThirdplat.getUser().getId())){
					//之前进入 但是没有验证手机  跳转验证页面 
					return "redirect:" + portalPath+"/wx/userinfo";
				}else{
					//进入填报页面
					return "redirect:" + portalPath+"/wx/report";
				}
			}
		}else{
			//code验证失败
			return "portal/error/noauthority";
		}
	}*/
	
	/**
	 * 
	 * @todo   用户完善个人信息页面
	 * @time   2018年3月20日 下午4:15:34
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/wx/userinfo"})
	public String userinfo(Model model,HttpServletRequest request, HttpServletResponse response){
		String accountId=request.getParameter("accountId");
		String to=request.getParameter("to");
		System.out.println("----userinfo-----"+accountId+"---"+to);
		request.setAttribute("accountId", accountId);
		request.setAttribute("to", to);
		return render(request, "wechat/userinfo");
	}
	/**
	 * 
	 * @todo   微信用户个人信息保存/注册功能
	 * @time   2018年3月21日 下午4:36:23
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/wx/register"})
	public String register(RedirectAttributes model,HttpServletRequest request, HttpServletResponse response){
		String accountId=request.getParameter("accountId");
		String tel=request.getParameter("tel");
		String userName=request.getParameter("name");
		String to=request.getParameter("to");
		AccountThirdplat accountThirdplat=accountThirdplatService.get(accountId);
		User user=null;
		if(accountThirdplat!=null){
			accountThirdplat.setMobile(tel);
			if(StringUtils.isBlank(accountThirdplat.getUserId())){
				//生成新用户
				user=new User(userName,accountThirdplat);
				try {
					systemService.saveUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 清除当前用户缓存
				if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
					UserUtils.clearCache();
				}
			}else{
				user=systemService.getUser(accountThirdplat.getUserId());
				if(user==null){
					//生成新用户
					user=new User(userName,accountThirdplat);
					try {
						systemService.saveUser(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 清除当前用户缓存
					if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
						UserUtils.clearCache();
					}
				}else{
					user.setMobile(tel);
					user.setName(userName);
					systemService.updateUserInfo(user);
				}
				
			}
			//更新第三方账号
			accountThirdplat.setUser(user);
			accountThirdplat.setUserId(accountThirdplat.getUserId());
			accountThirdplat.setMobile(tel);
			accountThirdplatService.save(accountThirdplat);
			//用户登录
			UserUtils.login(request, response, user);
		}
		model.addAttribute("userId", user.getId());
		return "redirect:" +to;
	}
	@RequestMapping(value = {"${portalPath}/wx/report"})
	public String report(Model model,HttpServletRequest request, HttpServletResponse response){
		String userId=request.getParameter("userId");
		System.out.println("----report-----"+userId);
		return render(request, "wechat/report");
	}
	
}