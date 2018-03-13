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
import com.qdch.portal.modules.cms.entity.CmsBanner;
import com.qdch.portal.modules.cms.service.CmsBannerService;

/**
 * 轮播图管理Controller
 * @author zuoqb
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsBanner")
public class CmsBannerController extends BaseController {

	@Autowired
	private CmsBannerService cmsBannerService;
	
	@ModelAttribute
	public CmsBanner get(@RequestParam(required=false) String id) {
		CmsBanner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBannerService.get(id);
		}
		if (entity == null){
			entity = new CmsBanner();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsBanner:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsBanner cmsBanner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBanner> page = cmsBannerService.findPage(new Page<CmsBanner>(request, response), cmsBanner); 
		model.addAttribute("page", page);
		return "modules/cms/cmsBannerList";
	}

	@RequiresPermissions("cms:cmsBanner:view")
	@RequestMapping(value = "form")
	public String form(CmsBanner cmsBanner, Model model) {
		model.addAttribute("cmsBanner", cmsBanner);
		return "modules/cms/cmsBannerForm";
	}

	@RequiresPermissions("cms:cmsBanner:edit")
	@RequestMapping(value = "save")
	public String save(CmsBanner cmsBanner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBanner)){
			return form(cmsBanner, model);
		}
		cmsBannerService.save(cmsBanner);
		addMessage(redirectAttributes, "保存轮播图管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBanner/?repage";
	}
	
	@RequiresPermissions("cms:cmsBanner:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsBanner cmsBanner, RedirectAttributes redirectAttributes) {
		cmsBannerService.delete(cmsBanner);
		addMessage(redirectAttributes, "删除轮播图管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBanner/?repage";
	}

}