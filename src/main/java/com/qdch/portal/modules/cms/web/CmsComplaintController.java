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
import com.qdch.portal.modules.cms.entity.CmsComplaint;
import com.qdch.portal.modules.cms.service.CmsComplaintService;

/**
 * 投诉Controller
 * @author zuoqb
 * @version 2018-03-15
 */
@Controller
public class CmsComplaintController extends BaseController {

	@Autowired
	private CmsComplaintService cmsComplaintService;
	
	@ModelAttribute
	public CmsComplaint get(@RequestParam(required=false) String id) {
		CmsComplaint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsComplaintService.get(id);
		}
		if (entity == null){
			entity = new CmsComplaint();
		}
		return entity;
	}
	
	/*@RequiresPermissions("cms:cmsComplaint:view")*/
	@RequestMapping(value = {"${adminPath}/cms/cmsComplaint/list"})
	public String list(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsComplaint> page = cmsComplaintService.findPage(new Page<CmsComplaint>(request, response), cmsComplaint); 
		model.addAttribute("page", page);
		return "modules/cms/cmsComplaintList";
	}

	/*@RequiresPermissions("cms:cmsComplaint:view")*/
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/form")
	public String form(CmsComplaint cmsComplaint, Model model) {
		model.addAttribute("cmsComplaint", cmsComplaint);
		return "modules/cms/cmsComplaintForm";
	}

	/*@RequiresPermissions("cms:cmsComplaint:edit")*/
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/save")
	public String save(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);
		addMessage(redirectAttributes, "保存投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}
	
	/*@RequiresPermissions("cms:cmsComplaint:edit")*/
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/delete")
	public String delete(CmsComplaint cmsComplaint, RedirectAttributes redirectAttributes) {
		cmsComplaintService.delete(cmsComplaint);
		addMessage(redirectAttributes, "删除投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}

}