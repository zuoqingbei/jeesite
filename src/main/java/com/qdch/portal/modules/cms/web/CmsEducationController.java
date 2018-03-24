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
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.service.CmsEducationService;

/**
 * 投资教育Controller
 * @author wangfeng
 * @version 2018-03-21
 */
@Controller
public class CmsEducationController extends BaseController {

	@Autowired
	private CmsEducationService cmsEducationService;
	
	@ModelAttribute
	public CmsEducation get(@RequestParam(required=false) String id) {
		CmsEducation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsEducationService.get(id);
		}
		if (entity == null){
			entity = new CmsEducation();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsEducation:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsEducation/list"})
	public String list(CmsEducation cmsEducation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsEducation> page = cmsEducationService.findPage(new Page<CmsEducation>(request, response), cmsEducation); 
		model.addAttribute("page", page);
		return "modules/cms/cmsEducationList";
	}

	@RequiresPermissions("cms:cmsEducation:view")
	@RequestMapping(value = "${adminPath}/cms/cmsEducation/form")
	public String form(CmsEducation cmsEducation, Model model) {
		model.addAttribute("cmsEducation", cmsEducation);
		return "modules/cms/cmsEducationForm";
	}

	@RequiresPermissions("cms:cmsEducation:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsEducation/save")
	public String save(CmsEducation cmsEducation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsEducation)){
			return form(cmsEducation, model);
		}
		cmsEducationService.save(cmsEducation);
		addMessage(redirectAttributes, "保存投资教育成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsEducation/list?repage";
	}
	
	@RequiresPermissions("cms:cmsEducation:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsEducation/delete")
	public String delete(CmsEducation cmsEducation, RedirectAttributes redirectAttributes) {
		cmsEducationService.delete(cmsEducation);
		addMessage(redirectAttributes, "删除投资教育成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsEducation/list?repage";
	}

}