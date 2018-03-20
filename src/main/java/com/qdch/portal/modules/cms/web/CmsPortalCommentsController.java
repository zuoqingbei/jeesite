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
import com.qdch.portal.modules.cms.entity.CmsPortalComments;
import com.qdch.portal.modules.cms.service.CmsPortalCommentsService;

/**
 * 门户评论Controller
 * @author wangfeng
 * @version 2018-03-20
 */
@Controller
public class CmsPortalCommentsController extends BaseController {

	@Autowired
	private CmsPortalCommentsService cmsPortalCommentsService;
	
	@ModelAttribute
	public CmsPortalComments get(@RequestParam(required=false) String id) {
		CmsPortalComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsPortalCommentsService.get(id);
		}
		if (entity == null){
			entity = new CmsPortalComments();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsPortalComments:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsPortalComments/list"})
	public String list(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsPortalComments> page = cmsPortalCommentsService.findPage(new Page<CmsPortalComments>(request, response), cmsPortalComments); 
		model.addAttribute("page", page);
		return "modules/cms/cmsPortalCommentsList";
	}

	@RequiresPermissions("cms:cmsPortalComments:view")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/form")
	public String form(CmsPortalComments cmsPortalComments, Model model) {
		model.addAttribute("cmsPortalComments", cmsPortalComments);
		return "modules/cms/cmsPortalCommentsForm";
	}

	@RequiresPermissions("cms:cmsPortalComments:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/save")
	public String save(CmsPortalComments cmsPortalComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsPortalComments)){
			return form(cmsPortalComments, model);
		}
		cmsPortalCommentsService.save(cmsPortalComments);
		addMessage(redirectAttributes, "保存门户评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPortalComments/list?repage";
	}
	
	@RequiresPermissions("cms:cmsPortalComments:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/delete")
	public String delete(CmsPortalComments cmsPortalComments, RedirectAttributes redirectAttributes) {
		cmsPortalCommentsService.delete(cmsPortalComments);
		addMessage(redirectAttributes, "删除门户评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPortalComments/list?repage";
	}

}