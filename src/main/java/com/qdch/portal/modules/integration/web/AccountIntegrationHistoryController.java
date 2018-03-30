/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.integration.web;

import java.util.Date;

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
import com.qdch.portal.common.utils.IdGen;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.integration.entity.AccountIntegrationHistory;
import com.qdch.portal.modules.integration.service.AccountIntegrationHistoryService;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.service.SystemService;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 用户活跃度记录Controller
 * @author lianjiming
 * @version 2018-03-23
 */
@Controller
public class AccountIntegrationHistoryController extends BaseController {

	@Autowired
	private AccountIntegrationHistoryService accountIntegrationHistoryService;
	@Autowired
	private SystemService systemService;
	
	//列表查询
	//@RequiresPermissions("integration:accountIntegrationHistory:view")
	@RequestMapping(value = {"${adminPath}/integration/accountIntegrationHistory/list"})
	public String list(AccountIntegrationHistory accountIntegrationHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountIntegrationHistory> page = accountIntegrationHistoryService.findPage(new Page<AccountIntegrationHistory>(request, response), accountIntegrationHistory); 
		model.addAttribute("page", page);
		return "modules/integration/accountIntegrationHistoryList";
	}
	
	//修改用户活跃度记录（包括加分和减分）
	//@RequiresPermissions("integration:accountIntegrationHistory:edit")
	@RequestMapping(value = "${portalPath}/integration/accountIntegrationHistory/saveIntegration")
	@ResponseBody
	public String saveIntegration(AccountIntegrationHistory accountIntegrationHistory,HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取请求参数
			String userId = request.getParameter("userId");//获取变更用户id
			String nums = request.getParameter("nums");//获取变更分值
			String reason = request.getParameter("reason");//获取变更原因
			//获取用户
			User user = UserUtils.get(userId);
			if(user!=null){
				String integration = user.getIntegration();//获取变更前活跃度
				if(integration==null){
					integration = "0";//默认用户初始活跃度为零分
				}
				if(nums==null){
					nums = "0";//变更分数为空，默认变更零分
				}
				//类型转换
				Double d_integration = Double.valueOf(integration);
				Double d_nums =  Double.valueOf(nums);
				//变更后活跃度
				Double allIntegration = d_integration + d_nums;
				//更新user表活跃度
				user.setIntegration(allIntegration+"");
				systemService.updateUserInfo(user);
				//封装数据
				accountIntegrationHistory.setUser(user);
				accountIntegrationHistory.setId(IdGen.uuid());
				accountIntegrationHistory.setAllIntegration(allIntegration+"");
				accountIntegrationHistory.setNums(nums);
				accountIntegrationHistory.setReason(reason);
				accountIntegrationHistory.setCreateDate(new Date());
				accountIntegrationHistoryService.save(accountIntegrationHistory);
			}
			return this.resultSuccessData(request,response, "保存用户活跃度记录成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败", null);
		}
	}
	
	@ModelAttribute
	public AccountIntegrationHistory get(@RequestParam(required=false) String id) {
		AccountIntegrationHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountIntegrationHistoryService.get(id);
		}
		if (entity == null){
			entity = new AccountIntegrationHistory();
		}
		return entity;
	}
	
	//@RequiresPermissions("integration:accountIntegrationHistory:view")
	@RequestMapping(value = "${adminPath}/integration/accountIntegrationHistory/form")
	public String form(AccountIntegrationHistory accountIntegrationHistory, Model model) {
		model.addAttribute("accountIntegrationHistory", accountIntegrationHistory);
		return "modules/integration/accountIntegrationHistoryForm";
	}

	
//	@RequiresPermissions("integration:accountIntegrationHistory:edit")
	@RequestMapping(value = "${adminPath}/integration/accountIntegrationHistory/delete")
	public String delete(AccountIntegrationHistory accountIntegrationHistory, RedirectAttributes redirectAttributes) {
		accountIntegrationHistoryService.delete(accountIntegrationHistory);
		addMessage(redirectAttributes, "删除用户活跃度记录成功");
		return "redirect:"+Global.getAdminPath()+"/integration/accountIntegrationHistory/list?repage";
	}
	
	//@RequiresPermissions("integration:accountIntegrationHistory:edit")
	@RequestMapping(value = "${adminPath}/integration/accountIntegrationHistory/save")
	public String save(AccountIntegrationHistory accountIntegrationHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountIntegrationHistory)){
			return form(accountIntegrationHistory, model);
		}
		accountIntegrationHistoryService.save(accountIntegrationHistory);
		addMessage(redirectAttributes, "保存用户活跃度记录成功");
		return "redirect:"+Global.getAdminPath()+"/integration/accountIntegrationHistory/list?repage";
	}
}