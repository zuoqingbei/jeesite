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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsActivityEnter;
import com.qdch.portal.modules.cms.service.CmsActivityEnterService;

/**
 * 活动报名记录Controller
 * @author lianjiming
 * @version 2018-03-28
 */
@Controller
public class CmsActivityEnterController extends BaseController {

	@Autowired
	private CmsActivityEnterService cmsActivityEnterService;
	
	@ModelAttribute
	public CmsActivityEnter get(@RequestParam(required=false) String id) {
		CmsActivityEnter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsActivityEnterService.get(id);
		}
		if (entity == null){
			entity = new CmsActivityEnter();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsActivityEnter:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsActivityEnter/list"})
	public String list(CmsActivityEnter cmsActivityEnter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsActivityEnter> page = cmsActivityEnterService.findPage(new Page<CmsActivityEnter>(request, response), cmsActivityEnter); 
		model.addAttribute("page", page);
		return "modules/cms/cmsActivityEnterList";
	}

	@RequiresPermissions("cms:cmsActivityEnter:view")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityEnter/form")
	public String form(CmsActivityEnter cmsActivityEnter, Model model) {
		model.addAttribute("cmsActivityEnter", cmsActivityEnter);
		return "modules/cms/cmsActivityEnterForm";
	}

	@RequiresPermissions("cms:cmsActivityEnter:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityEnter/save")
	public String save(CmsActivityEnter cmsActivityEnter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsActivityEnter)){
			return form(cmsActivityEnter, model);
		}
		cmsActivityEnterService.save(cmsActivityEnter);
		addMessage(redirectAttributes, "保存活动报名记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityEnter/list?repage";
	}
	
	@RequiresPermissions("cms:cmsActivityEnter:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityEnter/delete")
	public String delete(CmsActivityEnter cmsActivityEnter, RedirectAttributes redirectAttributes) {
		cmsActivityEnterService.delete(cmsActivityEnter);
		addMessage(redirectAttributes, "删除活动报名记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityEnter/list?repage";
	}

}