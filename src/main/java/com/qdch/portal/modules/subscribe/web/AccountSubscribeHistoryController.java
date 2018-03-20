/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.subscribe.web;

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
import com.qdch.portal.modules.subscribe.entity.AccountSubscribeHistory;
import com.qdch.portal.modules.subscribe.service.AccountSubscribeHistoryService;

/**
 * 用户订阅历史Controller
 * @author lianjiming
 * @version 2018-03-19
 */

@Controller
public class AccountSubscribeHistoryController extends BaseController {

	@Autowired
	private AccountSubscribeHistoryService accountSubscribeHistoryService;
	
	//订阅list查询
	//@RequiresPermissions("subscribe:accountSubscribeHistory:view")
	@RequestMapping(value = {"${adminPath}/subscribe/accountSubscribeHistory/list"})
	public String list(AccountSubscribeHistory accountSubscribeHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountSubscribeHistory> page = accountSubscribeHistoryService.findPage(new Page<AccountSubscribeHistory>(request, response), accountSubscribeHistory); 
		model.addAttribute("page", page);
		return "modules/subscribe/accountSubscribeHistoryList";
	}
	
	
	
	
	
	@ModelAttribute
	public AccountSubscribeHistory get(@RequestParam(required=false) String id) {
		AccountSubscribeHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountSubscribeHistoryService.get(id);
		}
		if (entity == null){
			entity = new AccountSubscribeHistory();
		}
		return entity;
	}
	


	//@RequiresPermissions("subscribe:accountSubscribeHistory:view")
	@RequestMapping(value = "${adminPath}/subscribe/accountSubscribeHistory/form")
	public String form(AccountSubscribeHistory accountSubscribeHistory, Model model) {
		model.addAttribute("accountSubscribeHistory", accountSubscribeHistory);
		return "modules/subscribe/accountSubscribeHistoryForm";
	}

	//@RequiresPermissions("subscribe:accountSubscribeHistory:edit")
	@RequestMapping(value = "${adminPath}/subscribe/accountSubscribeHistory/save")
	public String save(AccountSubscribeHistory accountSubscribeHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountSubscribeHistory)){
			return form(accountSubscribeHistory, model);
		}
		accountSubscribeHistoryService.save(accountSubscribeHistory);
		addMessage(redirectAttributes, "保存用户订阅历史成功");
		return "redirect:"+Global.getAdminPath()+"/subscribe/accountSubscribeHistory/list?repage";
	}
	
	//@RequiresPermissions("subscribe:accountSubscribeHistory:edit")
	@RequestMapping(value = "${adminPath}/subscribe/accountSubscribeHistory/delete")
	public String delete(AccountSubscribeHistory accountSubscribeHistory, RedirectAttributes redirectAttributes) {
		accountSubscribeHistoryService.delete(accountSubscribeHistory);
		addMessage(redirectAttributes, "删除用户订阅历史成功");
		return "redirect:"+Global.getAdminPath()+"/subscribe/accountSubscribeHistory/list?repage";
	}

}