/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 用户关注Controller
 * @author lianjiming
 * @version 2018-03-12
 */
@Controller
public class AccountAttentionController extends BaseController {

	@Autowired
	private AccountAttentionService accountAttentionService;
	
	//查询用户信息
	@RequestMapping(value = {"${portalPath}/account/getUserInfo"})
	@ResponseBody
	public void getUserInfo (User user,HttpServletRequest request,HttpServletResponse response){
		//获取请求参数
		try {
			String userId = request.getParameter("userId");
			if(userId != null){
				user.setId(userId);
				List<User> list = UserUtils.getUserInfo(user);
				this.resultSuccessData(request,response, "用户信息", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**用户添加关注
	 * @author lianjiming
	 * @version 2018-03-13
	 * @param accountAttention 用户关注实体
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"${portalPath}/account/accountAttention/addAttention"})
	@ResponseBody
	public void addAttention(HttpServletRequest request,HttpServletResponse response,AccountAttention accountAttention){
		try {
			//获取请求参数
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			//封装对象
			accountAttention.setFromUser(fromUser);
			accountAttention.setToUser(toUser);
			//查询是否关注
			List<AccountAttention> findAccountAttention = accountAttentionService.findAccountAttention(accountAttention);
			//自己不能关注自己
			if(fromUser.equals(toUser)){
				this.resultSuccessData(request,response, "不能关注", null);
				return;
			}
			if((findAccountAttention==null||findAccountAttention.size()==0)){//没有关注
				//读取 redis缓存
				Set<String> set = JedisUtils.getSet("addAttentionCache_"+accountAttention.getFromUser());
				if(set==null){
					set=new HashSet<String>();
				}
				set.add(accountAttention.getToUser());
				//写入redis缓存
				JedisUtils.setSet("addAttentionCache_"+accountAttention.getFromUser(), set, 0);
				accountAttentionService.saveAttention(accountAttention);
				this.resultSuccessData(request,response, "关注成功", null);
			}else{//已经关注
				this.resultSuccessData(request,response, "已经关注", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**获取共同关注/好友推荐
	 * @author lianjiming
	 * @version 2018-03-13
	 * @param accountAttention 用户关注实体
	 * @param request type 
	 * @param response
	 * @param type type="recommend"为共同关注 ,type="recommend"为好友推荐
	 */
	@RequestMapping(value = {"${portalPath}/account/accountAttention/commonAttention"})
	@ResponseBody
	public void commonAttention(HttpServletRequest request,HttpServletResponse response){
		try {
			//获取请求参数
			String fromUser = request.getParameter("fromUser");
			String toUser = request.getParameter("toUser");
			String type = request.getParameter("type");
			
			//在redis中 查询关注者和被关注者的交集
			String keyFromUser = "addAttentionCache_"+fromUser;//关注者的key
			String keyToUser = "addAttentionCache_"+toUser;//被关注者的key
			//关注方 关注 被关注方 向关注方推荐好友  应该得到关注方不存在的好友，所以应该取差集  参数被关注方在前
			String[] keys=new String[]{keyToUser,keyFromUser};
			Set<String> sinter =null;
			List<User> list = new ArrayList<User>();
			//共同关注
			if("common".equals(type)){
				sinter=JedisUtils.sinter(keys);//交集
				if(sinter!=null&&sinter.size()>0){
					//user 查询共同的关注
					list=UserUtils.findCommonAttention(JedisUtils.Set2Array(sinter));
				}
				this.resultSuccessData(request,response, "获取共同关注", list);
			//好友推荐
			}else if("recommend".equals(type)){
				sinter=JedisUtils.sdiff(keys);//差集
				if(sinter!=null&&sinter.size()>0){
					//user 查询不同的关注
					list=UserUtils.findCommonAttention(JedisUtils.Set2Array(sinter));
				}
				this.resultSuccessData(request,response, "好友推荐", list);
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
	@RequestMapping(value = "${portalPath}/account/accountAttention/delete")
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
				//删除缓存
				String keyFromUser = "addAttentionCache_"+accountAttention.getFromUser();//关注者的key
				//通过获取set
				Set<String> set = JedisUtils.getSet("addAttentionCache_"+accountAttention.getFromUser());
				//删除集合中对应的值
				set.remove(accountAttention.getToUser());
				//把set再次写入缓存
				JedisUtils.setSet("addAttentionCache_"+accountAttention.getFromUser(), set, 0);
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
	@RequestMapping(value = {"${portalPath}/account/accountAttention/listMyAttention"})
	@ResponseBody
	public void listMyAttention(AccountAttention accountAttention, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			//获取请求参数 
			String strPageNo = request.getParameter("pageNo");	
			String strPageSize = request.getParameter("pageSize");	
			if((strPageNo!=null||strPageNo.length()>0)||(strPageSize!=null||strPageSize.length()>0)){
				Integer pageNo = Integer.valueOf(strPageNo);
				Integer pageSize = Integer.valueOf(strPageSize);
				//查询我的关注
				String fromUser = request.getParameter("fromUser");
				//我的粉丝
				String toUser = request.getParameter("toUser");
				//封装对象
			if(fromUser != null || toUser != null){
				accountAttention.setFromUser(fromUser);
				accountAttention.setToUser(toUser);
				}
				//分页查询
				Page<AccountAttention> page = accountAttentionService.findPage(new Page<AccountAttention>(pageNo, pageSize), accountAttention); 
				this.resultSuccessData(request,response, "", mapJson(page,"success","获取数据成功"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//列表查询
	@RequestMapping(value = {"${adminPath}/account/accountAttention/list"})
	public String list(AccountAttention accountAttention, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccountAttention> page = accountAttentionService.findPage(new Page<AccountAttention>(request, response), accountAttention); 
		model.addAttribute("page", page);
		return "modules/account/accountAttentionList";
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
	



	@RequestMapping(value = "${adminPath}/account/accountAttention/form")
	public String form(AccountAttention accountAttention, Model model) {
		model.addAttribute("accountAttention", accountAttention);
		return "modules/account/accountAttentionForm";
	}

	//@RequiresPermissions("account:accountAttention:edit")
	@RequestMapping(value = "${adminPath}/account/accountAttention/save")
	public String save(AccountAttention accountAttention, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountAttention)){
			return form(accountAttention, model);
		}
		accountAttentionService.save(accountAttention);
		addMessage(redirectAttributes, "保存用户关注成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountAttention/list?repage";
	}

}
