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
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.service.CmsCollectionService;

/**
 * 用户收藏记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsCollectionController extends BaseController {

	@Autowired
	private CmsCollectionService cmsCollectionService;
	
	@ModelAttribute
	public CmsCollection get(@RequestParam(required=false) String id) {
		CmsCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsCollectionService.get(id);
		}
		if (entity == null){
			entity = new CmsCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsCollection:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsCollection/list"})
	public String list(CmsCollection cmsCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsCollection> page = cmsCollectionService.findPage(new Page<CmsCollection>(request, response), cmsCollection); 
		model.addAttribute("page", page);
		return "modules/cms/cmsCollectionList";
	}

	@RequiresPermissions("cms:cmsCollection:view")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/form")
	public String form(CmsCollection cmsCollection, Model model) {
		model.addAttribute("cmsCollection", cmsCollection);
		return "modules/cms/cmsCollectionForm";
	}

	@RequiresPermissions("cms:cmsCollection:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/save")
	public String save(CmsCollection cmsCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsCollection)){
			return form(cmsCollection, model);
		}
		cmsCollectionService.save(cmsCollection);
		addMessage(redirectAttributes, "保存用户收藏记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsCollection/list?repage";
	}
	
	@RequiresPermissions("cms:cmsCollection:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/delete")
	public String delete(CmsCollection cmsCollection, RedirectAttributes redirectAttributes) {
		cmsCollectionService.delete(cmsCollection);
		addMessage(redirectAttributes, "删除用户收藏记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsCollection/list?repage";
	}

}