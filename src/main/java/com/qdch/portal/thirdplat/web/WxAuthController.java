/**
 * 微信认证
 */
package com.qdch.portal.thirdplat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.web.BaseController;
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
	@RequestMapping(value = {"${portalPath}/accountReport"})
	public String accountReport(RedirectAttributes  model,HttpServletRequest request, HttpServletResponse response){
		AccountThirdplat accountThirdplat=dealAuthorize(request);
		if(accountThirdplat!=null){
			model.addAttribute("accountId", accountThirdplat.getId());
			if(accountThirdplat.getUser()==null||StringUtils.isBlank(accountThirdplat.getUser().getId())){
				//之前进入 但是没有验证手机  跳转验证页面 
				return "redirect:" + portalPath+"/wx/userinfo";
			}else{
				//进入填报页面
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
				model.addAttribute("accountId", accountThirdplat.getId());
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
	 * @todo   用户填写手机号页面
	 * @time   2018年3月20日 下午4:15:34
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/wx/userinfo"})
	public String userinfo(Model model,HttpServletRequest request, HttpServletResponse response){
		String accountId=request.getParameter("accountId");
		System.out.println("----userinfo-----"+accountId);
		return render(request, "wechat/userinfo");
	}
	@RequestMapping(value = {"${portalPath}/wx/report"})
	public String report(Model model,HttpServletRequest request, HttpServletResponse response){
		String accountId=request.getParameter("accountId");
		System.out.println("----report-----"+accountId);
		return render(request, "wxreport/report");
	}
	
}
