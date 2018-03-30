/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

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
import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.cms.service.CmsActivityService;

/**
 * 活动Controller
 * @author lianjiming
 * @version 2018-03-28
 */
@Controller
public class CmsActivityController extends BaseController {

	@Autowired
	private CmsActivityService cmsActivityService;
	
	//列表展示
	//@RequiresPermissions("cms:cmsActivity:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsActivity/list"})
	public String list(CmsActivity cmsActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsActivity> page = cmsActivityService.findPage(new Page<CmsActivity>(request, response), cmsActivity); 
		model.addAttribute("page", page);
		return "modules/cms/cmsActivityList";
	}
	
	//添加活动（发布活动）
	//@RequiresPermissions("cms:cmsActivity:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivity/save")
	public String save(CmsActivity cmsActivity, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, cmsActivity)){
				return form(cmsActivity, model);
			}
			cmsActivityService.save(cmsActivity);
			addMessage(redirectAttributes, "添加活动成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivity/list?repage";
	}
	
	
	
	@ModelAttribute
	public CmsActivity get(@RequestParam(required=false) String id) {
		CmsActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsActivityService.get(id);
		}
		if (entity == null){
			entity = new CmsActivity();
		}
		return entity;
	}
	


	//@RequiresPermissions("cms:cmsActivity:view")
	@RequestMapping(value = "${adminPath}/cms/cmsActivity/form")
	public String form(CmsActivity cmsActivity, Model model) {
		model.addAttribute("cmsActivity", cmsActivity);
		return "modules/cms/cmsActivityForm";
	}


	
	//@RequiresPermissions("cms:cmsActivity:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivity/delete")
	public String delete(CmsActivity cmsActivity, RedirectAttributes redirectAttributes) {
		cmsActivityService.delete(cmsActivity);
		addMessage(redirectAttributes, "删除活动成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivity/list?repage";
	}

}