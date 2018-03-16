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
import com.qdch.portal.modules.cms.entity.CmsComment;
import com.qdch.portal.modules.cms.service.CmsCommentService;

/**
 * 评论表Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsCommentController extends BaseController {

	@Autowired
	private CmsCommentService cmsCommentService;
	
	@ModelAttribute
	public CmsComment get(@RequestParam(required=false) String id) {
		CmsComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsCommentService.get(id);
		}
		if (entity == null){
			entity = new CmsComment();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsComment:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsComment/list"})
	public String list(CmsComment cmsComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsComment> page = cmsCommentService.findPage(new Page<CmsComment>(request, response), cmsComment); 
		model.addAttribute("page", page);
		return "modules/cms/cmsCommentList";
	}

	@RequiresPermissions("cms:cmsComment:view")
	@RequestMapping(value = "${adminPath}/cms/cmsComment/form")
	public String form(CmsComment cmsComment, Model model) {
		model.addAttribute("cmsComment", cmsComment);
		return "modules/cms/cmsCommentForm";
	}

	@RequiresPermissions("cms:cmsComment:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComment/save")
	public String save(CmsComment cmsComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsComment)){
			return form(cmsComment, model);
		}
		cmsCommentService.save(cmsComment);
		addMessage(redirectAttributes, "保存评论表成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComment/list?repage";
	}
	
	@RequiresPermissions("cms:cmsComment:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComment/delete")
	public String delete(CmsComment cmsComment, RedirectAttributes redirectAttributes) {
		cmsCommentService.delete(cmsComment);
		addMessage(redirectAttributes, "删除评论表成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComment/list?repage";
	}

}