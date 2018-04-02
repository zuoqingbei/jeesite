/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.MessageUtils;
import com.qdch.portal.common.utils.SendMsgUtil;
import com.qdch.portal.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.account.entity.AccountMobileCode;
import com.qdch.portal.modules.account.service.AccountMobileCodeService;

/**
 * 短信验证码Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class AccountMobileCodeController extends BaseController {

	@Autowired
	private AccountMobileCodeService accountMobileCodeService;
	
	@ModelAttribute
	public AccountMobileCode get(@RequestParam(required=false) String id) {
		AccountMobileCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountMobileCodeService.get(id);
		}
		if (entity == null){
			entity = new AccountMobileCode();
		}
		return entity;
	}
	
	@RequiresPermissions("account:accountMobileCode:view")
	@RequestMapping(value = {"${adminPath}/account/accountMobileCode/list"})
	public String list(AccountMobileCode accountMobileCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountMobileCode> page = accountMobileCodeService.findPage(new Page<AccountMobileCode>(request, response), accountMobileCode); 
		model.addAttribute("page", page);
		return "modules/account/accountMobileCodeList";
	}

	@RequiresPermissions("account:accountMobileCode:view")
	@RequestMapping(value = "${adminPath}/account/accountMobileCode/form")
	public String form(AccountMobileCode accountMobileCode, Model model) {
		model.addAttribute("accountMobileCode", accountMobileCode);
		return "modules/account/accountMobileCodeForm";
	}

	@RequiresPermissions("account:accountMobileCode:edit")
	@RequestMapping(value = "${adminPath}/account/accountMobileCode/save")
	public String save(AccountMobileCode accountMobileCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountMobileCode)){
			return form(accountMobileCode, model);
		}
		accountMobileCodeService.save(accountMobileCode);
		addMessage(redirectAttributes, "保存短信验证码成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountMobileCode/list?repage";
	}
	
	@RequiresPermissions("account:accountMobileCode:edit")
	@RequestMapping(value = "${adminPath}/account/accountMobileCode/delete")
	public String delete(AccountMobileCode accountMobileCode, RedirectAttributes redirectAttributes) {
		accountMobileCodeService.delete(accountMobileCode);
		addMessage(redirectAttributes, "删除短信验证码成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountMobileCode/list?repage";
	}

	/**
	 * 保存手机验证码
	 * @param
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "${portalPath}/account/accountMobileCode/saveCheckCode")
//	public void saveCheckCode(AccountMobileCode accountMobileCode,HttpServletRequest request,HttpServletResponse response){
//		try {
////			accountMobileCode.setUser(UserUtils.getUser());
//			accountMobileCodeService.save(accountMobileCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.resultFaliureData(request,response, "保存数据失败", false);
//			return ;
//		}
//		this.resultSuccessData(request,response, "保存数据成功", true);
//
//	}

	@RequestMapping(value = "${portalPath}/account/accountMobileCode/sendCheckCode")
	@ResponseBody
	public String sendCheckCode(HttpServletRequest request,HttpServletResponse response){
		try{
			String mobile = request.getParameter("mobile");
			if(mobile == null ||mobile.equals("")){
				return  this.resultFaliureData(request,response, "请先输入手机号", false);

			}
//			String codes = request.getParameter("codes");
			String uasge = request.getParameter("uasge")==null?"0":request.getParameter("uasge");
			String str = SendMsgUtil.presend(mobile,uasge);
			if(str.equals("true")){
//				AccountMobileCode accountMobileCode = new AccountMobileCode();
//				accountMobileCode.setMobile(mobile);
//				accountMobileCode.setCodes(JedisUtils.get("MessageCache"+mobile));
//				accountMobileCode.setUsed("0"); //0--未使用 1--已使用
//				accountMobileCode.setUasge(uasge);
//				accountMobileCodeService.save(accountMobileCode);
				return this.resultSuccessData(request,response, "操作成功", null);
			}else if(str.equals("false")){
				return this.resultFaliureData(request,response, "操作失败", null);
			}else{
				return this.resultFaliureData(request,response, str, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", null);
		}

		}

	/**
	 * 校验验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/account/accountMobileCode/checkIndentifyCode")
	@ResponseBody
	public String  checkIndentifyCode(HttpServletRequest request,HttpServletResponse response){
		String mobile = request.getParameter("mobile");
		String codes = request.getParameter("codes");

		if(StringUtils.isBlank(mobile)||StringUtils.isBlank(codes)){
			return this.resultFaliureData(request,response, "请先输入手机号和验证码", false);
		}
		String str = SendMsgUtil.checkIndentifyCode(mobile,codes);
		if(str.equals("true")){
			return this.resultSuccessData(request,response, "操作成功", null);
		}else if(str.equals("false")){
			return this.resultFaliureData(request,response, "操作失败", null);
		}else {
			return this.resultFaliureData(request,response, str, null);
		}


	}


}
