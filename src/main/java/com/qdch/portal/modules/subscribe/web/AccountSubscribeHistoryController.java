/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.subscribe.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.IdGen;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.subscribe.entity.AccountSubscribeHistory;
import com.qdch.portal.modules.subscribe.service.AccountSubscribeHistoryService;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.service.SystemService;

/**
 * 用户订阅历史Controller
 * @author lianjiming
 * @version 2018-03-19
 */

@Controller
public class AccountSubscribeHistoryController extends BaseController {

	@Autowired
	private AccountSubscribeHistoryService accountSubscribeHistoryService;
	@Autowired
	private SystemService systemService;
	
	//列表查询
	@RequestMapping(value = {"${adminPath}/subscribe/accountSubscribeHistory/list"})
	public String list(AccountSubscribeHistory accountSubscribeHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountSubscribeHistory> page = accountSubscribeHistoryService.findPage(new Page<AccountSubscribeHistory>(request, response), accountSubscribeHistory); 
		model.addAttribute("page", page);
		return "modules/subscribe/accountSubscribeHistoryList";
	}
	//添加订阅,取消订阅
	@ResponseBody
	@RequestMapping(value = "${adminPath}/subscrisbe/accountSubscribeHistory/save")
	public void save(AccountSubscribeHistory accountSubscribeHistory,HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取请求参数
			String userId = request.getParameter("userId");
			String labelId = request.getParameter("labelId");
			String[] labelIds = labelId.split(",");
			accountSubscribeHistory.setUser(userId);
			String key = "accountSubscribeHistoryCache_"+accountSubscribeHistory.getUser();
			Set<String> set = JedisUtils.getSet(key);
			
			if(set!=null && set.size()>0){
				for (int i = 0; i < labelIds.length; i++) {
					accountSubscribeHistoryService.delete(accountSubscribeHistory);
				}
			}
			
			if(set==null || set.size()==0){
				set=new HashSet<String>();
				for (int i = 0; i < labelIds.length; i++) {
					//封装对象
					accountSubscribeHistory.setId(IdGen.uuid());
					accountSubscribeHistory.setUser(userId);
					accountSubscribeHistory.setCreateDate(new Date());
					accountSubscribeHistory.setLabId(labelIds[i]);
					set.add(labelIds[i]);
					JedisUtils.setSet(key, set, 0);
					accountSubscribeHistoryService.save(accountSubscribeHistory);
					this.resultSuccessData(request,response, "订阅成功", null);
				}
			}else{		
				JedisUtils.del(key);
				for (int i = 0; i < labelIds.length; i++) {
					//封装对象
					accountSubscribeHistory.setId(IdGen.uuid());
					accountSubscribeHistory.setUser(userId);
					accountSubscribeHistory.setCreateDate(new Date());
					accountSubscribeHistory.setLabId(labelIds[i]);
					set.add(labelIds[i]);
					JedisUtils.setSet(key, set, 0);
					accountSubscribeHistoryService.save(accountSubscribeHistory);
					this.resultSuccessData(request,response, "订阅成功", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除订阅
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
	@RequestMapping(value = "${adminPath}/subscribe/accountSubscribeHistory/delete")
	public String delete(AccountSubscribeHistory accountSubscribeHistory, RedirectAttributes redirectAttributes) {
		accountSubscribeHistoryService.delete(accountSubscribeHistory);
		addMessage(redirectAttributes, "删除用户订阅历史成功");
		return "redirect:"+Global.getAdminPath()+"/subscribe/accountSubscribeHistory/list?repage";
	}

}