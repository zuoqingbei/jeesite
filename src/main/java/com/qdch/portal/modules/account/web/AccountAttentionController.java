/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.account.service.AccountAttentionService;
import com.qdch.portal.modules.sys.dao.UserDao;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.utils.UserUtils;

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
	/**用户添加关注
	 * @author lianjiming
	 * @version 2018-03-13
	 * @param accountAttention 用户关注实体
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"addAttention", ""})
	@ResponseBody
	public void addAttention(HttpServletRequest request,HttpServletResponse response,AccountAttention accountAttention){
		try {
			//获取请求参数
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			//封装对象
			accountAttention.setId(IdGen.uuid());
			accountAttention.setFromUser(fromUser);
			accountAttention.setToUser(toUser);
			accountAttention.setCreateDate(new Date());
			
			//查询是否关注
			List<AccountAttention> findAccountAttention = accountAttentionService.findAccountAttention(accountAttention);
			
			if(findAccountAttention==null||findAccountAttention.size()==0){//没有关注
				//读取 redis缓存
				Set<String> set = JedisUtils.getSet("addAttentionCache_"+accountAttention.getFromUser());
				if(set==null){
					set=new HashSet<String>();
				}
				set.add(accountAttention.getToUser());
				//写入redis缓存
				JedisUtils.setSet("addAttentionCache_"+accountAttention.getFromUser(), set, 0);
				
				//共同关注
				//在redis中 查询关注者和被关注者的交集
				String keyFromUser = "addAttentionCache_"+accountAttention.getFromUser();//关注者的key
				String keyToUser = "addAttentionCache_"+accountAttention.getToUser();//被关注者的key
				String[] keys=new String[]{keyFromUser,keyToUser};
				Set<String> sinter = JedisUtils.sinter(keys);
				List<User> list = new ArrayList<User>();
				
				if(sinter!=null&&sinter.size()>0){//有共同关注
					//user 查询共同的关注
					String[] array = JedisUtils.Set2Array(sinter);//集合转换数组
					list=UserUtils.findCommonAttention(array);
				}
				accountAttentionService.saveAttention(accountAttention);//存入数据库
				this.resultSuccessData(request,response, "关注成功", list);
			}else{//已经关注
				this.resultSuccessData(request,response, "已经关注", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**用户取消关注
	 * @author lianjiming
	 * @version 2018-03-15
	 * @param accountAttention 用户关注实体
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public void delete(AccountAttention accountAttention, HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取请求参数
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			//封装对象
			accountAttention.setId(IdGen.uuid());
			accountAttention.setFromUser(fromUser);
			accountAttention.setToUser(toUser);
			accountAttention.setCreateDate(new Date());
			//查询是否关注
			List<AccountAttention> list = accountAttentionService.findAccountAttention(accountAttention);
			
			if(list!=null&&list.size()!=0){//已经关注
				
				//读取缓存
				String keyFromUser = "addAttentionCache_"+accountAttention.getFromUser();//关注者的key
				Set<String> set = JedisUtils.getSet(keyFromUser);
				//删除缓存
				JedisUtils.del(keyFromUser);
				
				//设置已关注记录的id
				String id = null;
				for(int i = 0;i <list.size();i++){
					id = list.get(i).getId();
				}
				accountAttention.setId(id);
				//从数据库中删除记录 （修改delFlag 值 0：正常；1：删除；2：审核）
				accountAttentionService.delete(accountAttention);
				this.resultSuccessData(request,response, "取消关注成功", null);
			}else{//未关注
				this.resultSuccessData(request,response, "未关注", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**我的关注/我的粉丝
	 * @author lianjiming
	 * @version 2018-03-15
	 * @param accountAttention 用户关注实体
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"listMyAttention", ""})
	@ResponseBody
	public void listMyAttention(AccountAttention accountAttention, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			//获取请求参数 
			Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
			Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
			//查询我的关注
			String fromUser = request.getParameter("fromUser");
			//我的粉丝
			String toUser = request.getParameter("toUser");
			accountAttention.setFromUser(fromUser);
			accountAttention.setToUser(toUser);
			Page<AccountAttention> page = accountAttentionService.findPage(new Page<AccountAttention>(pageNo, pageSize), accountAttention); 
			this.resultSuccessData(request,response, "我的关注", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//----------------------------------------------------------------------------------------------
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
	


}
