/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.dao.CmsPraiseDao;
import com.qdch.portal.modules.cms.entity.CmsShare;
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
import com.qdch.portal.modules.cms.entity.CmsPraise;
import com.qdch.portal.modules.cms.service.CmsPraiseService;

/**
 * 用户赞 踩记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsPraiseController extends BaseController {

	@Autowired
	private CmsPraiseService cmsPraiseService;

	@Autowired
	private CmsPraiseDao cmsPraiseDao;
	@ModelAttribute
	public CmsPraise get(@RequestParam(required=false) String id) {
		CmsPraise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsPraiseService.get(id);
		}
		if (entity == null){
			entity = new CmsPraise();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsPraise:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsPraise/list"})
	public String list(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsPraise> page = cmsPraiseService.findPage(new Page<CmsPraise>(request, response), cmsPraise); 
		model.addAttribute("page", page);
		return "modules/cms/cmsPraiseList";
	}

	@RequiresPermissions("cms:cmsPraise:view")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/form")
	public String form(CmsPraise cmsPraise, Model model) {
		model.addAttribute("cmsPraise", cmsPraise);
		return "modules/cms/cmsPraiseForm";
	}

	@RequiresPermissions("cms:cmsPraise:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/save")
	public String save(CmsPraise cmsPraise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsPraise)){
			return form(cmsPraise, model);
		}
		cmsPraiseService.save(cmsPraise);
		addMessage(redirectAttributes, "保存用户赞 踩记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPraise/list?repage";
	}
	
	@RequiresPermissions("cms:cmsPraise:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/delete")
	public String delete(CmsPraise cmsPraise, RedirectAttributes redirectAttributes) {
		cmsPraiseService.delete(cmsPraise);
		addMessage(redirectAttributes, "删除用户赞 踩记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPraise/list?repage";
	}


	/**
	 * 得到某条资讯的踩的数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/getTradeCount")
	public void  getTradeCount(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response){
		try {
			cmsPraiseDao.getTradeCount(cmsPraise);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", null);
			return;
		}
		this.resultSuccessData(request,response, "操作成功", null);
	}

	/**
	 * 得到某条资讯的赞的数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/getPraiseCount")
	public void  getPraiseCount(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response){
		try {
			cmsPraiseDao.getPraiseCount(cmsPraise);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", null);
			return;
		}
		this.resultSuccessData(request,response, "操作成功", null);
	}

	/**
	 * 用户是否操作过
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/isOperate")
	public void  isOperate(CmsPraise cmsPraise,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		try {
			flag = cmsPraiseService.getDynamicSelf(cmsPraise);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", flag);
			return;
		}
		this.resultSuccessData(request,response, "操作成功", flag);
	}

}