/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}