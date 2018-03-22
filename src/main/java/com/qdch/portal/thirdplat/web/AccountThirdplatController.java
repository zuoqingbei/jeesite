/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thirdplat.web;

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
import com.qdch.portal.thirdplat.entity.AccountThirdplat;
import com.qdch.portal.thirdplat.service.AccountThirdplatService;

/**
 * 第三方账号Controller
 * @author zuoqb
 * @version 2018-03-20
 */
@Controller
public class AccountThirdplatController extends BaseController {

	@Autowired
	private AccountThirdplatService accountThirdplatService;
	
	@ModelAttribute
	public AccountThirdplat get(@RequestParam(required=false) String id) {
		AccountThirdplat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountThirdplatService.get(id);
		}
		if (entity == null){
			entity = new AccountThirdplat();
		}
		return entity;
	}
	
	@RequiresPermissions("thirdplat:accountThirdplat:view")
	@RequestMapping(value = {"${adminPath}/thirdplat/accountThirdplat/list"})
	public String list(AccountThirdplat accountThirdplat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountThirdplat> page = accountThirdplatService.findPage(new Page<AccountThirdplat>(request, response), accountThirdplat); 
		model.addAttribute("page", page);
		return "portal/thirdplat/accountThirdplatList";
	}

	@RequiresPermissions("thirdplat:accountThirdplat:view")
	@RequestMapping(value = "${adminPath}/thirdplat/accountThirdplat/form")
	public String form(AccountThirdplat accountThirdplat, Model model) {
		model.addAttribute("accountThirdplat", accountThirdplat);
		return "portal/thirdplat/accountThirdplatForm";
	}

	@RequiresPermissions("thirdplat:accountThirdplat:edit")
	@RequestMapping(value = "${adminPath}/thirdplat/accountThirdplat/save")
	public String save(AccountThirdplat accountThirdplat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountThirdplat)){
			return form(accountThirdplat, model);
		}
		accountThirdplatService.save(accountThirdplat);
		addMessage(redirectAttributes, "保存第三方账号成功");
		return "redirect:"+Global.getAdminPath()+"/thirdplat/accountThirdplat/list?repage";
	}
	
	@RequiresPermissions("thirdplat:accountThirdplat:edit")
	@RequestMapping(value = "${adminPath}/thirdplat/accountThirdplat/delete")
	public String delete(AccountThirdplat accountThirdplat, RedirectAttributes redirectAttributes) {
		accountThirdplatService.delete(accountThirdplat);
		addMessage(redirectAttributes, "删除第三方账号成功");
		return "redirect:"+Global.getAdminPath()+"/thirdplat/accountThirdplat/list?repage";
	}

}