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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.account.service.AccountAttentionService;

/**
 * 用户关注Controller
 * @author lianjiming
 * @version 2018-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/account/accountAttention")
public class AccountAttentionController extends BaseController {

	@Autowired
	private AccountAttentionService accountAttentionService;
	
	@ModelAttribute
	public AccountAttention get(@RequestParam(required=false) String id) {
		AccountAttention entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountAttentionService.get(id);
		}
		if (entity == null){
			entity = new AccountAttention();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(AccountAttention accountAttention, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountAttention> page = accountAttentionService.findPage(new Page<AccountAttention>(request, response), accountAttention); 
		model.addAttribute("page", page);
		return "modules/account/accountAttentionList";
	}

	@RequestMapping(value = "form")
	public String form(AccountAttention accountAttention, Model model) {
		model.addAttribute("accountAttention", accountAttention);
		return "modules/account/accountAttentionForm";
	}

	//@RequiresPermissions("account:accountAttention:edit")
	@RequestMapping(value = "save")
	public String save(AccountAttention accountAttention, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountAttention)){
			return form(accountAttention, model);
		}
		accountAttentionService.save(accountAttention);
		addMessage(redirectAttributes, "保存用户关注成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountAttention/?repage";
	}
	
	//@RequiresPermissions("account:accountAttention:edit")
	@RequestMapping(value = "delete")
	public String delete(AccountAttention accountAttention, RedirectAttributes redirectAttributes) {
		accountAttentionService.delete(accountAttention);
		addMessage(redirectAttributes, "删除用户关注成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountAttention/?repage";
	}

}