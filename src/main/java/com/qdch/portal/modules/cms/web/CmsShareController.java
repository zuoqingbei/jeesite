/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.sys.utils.UserUtils;
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
import com.qdch.portal.modules.cms.entity.CmsShare;
import com.qdch.portal.modules.cms.service.CmsShareService;

/**
 * 用户分享记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsShareController extends BaseController {

	@Autowired
	private CmsShareService cmsShareService;
	
	@ModelAttribute
	public CmsShare get(@RequestParam(required=false) String id) {
		CmsShare entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsShareService.get(id);
		}
		if (entity == null){
			entity = new CmsShare();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsShare:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsShare/list"})
	public String list(CmsShare cmsShare, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsShare> page = cmsShareService.findPage(new Page<CmsShare>(request, response), cmsShare); 
		model.addAttribute("page", page);
		return "modules/cms/cmsShareList";
	}

	@RequiresPermissions("cms:cmsShare:view")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/form")
	public String form(CmsShare cmsShare, Model model) {
		model.addAttribute("cmsShare", cmsShare);
		return "modules/cms/cmsShareForm";
	}

	@RequiresPermissions("cms:cmsShare:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/save")
	public String save(CmsShare cmsShare, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsShare)){
			return form(cmsShare, model);
		}
		cmsShareService.save(cmsShare);
		addMessage(redirectAttributes, "保存用户分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsShare/list?repage";
	}
	
	@RequiresPermissions("cms:cmsShare:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/delete")
	public String delete(CmsShare cmsShare, RedirectAttributes redirectAttributes) {
		cmsShareService.delete(cmsShare);
		addMessage(redirectAttributes, "删除用户分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsShare/list?repage";
	}

	/**
	 * 保存分享内容
	 * @param cmsShare
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsShare/saveShare")
	public void saveShare(CmsShare cmsShare,Model model, HttpServletRequest request,HttpServletResponse response){
		try {
			cmsShare.setUser(UserUtils.getUser());
			cmsShareService.save(cmsShare);
		} catch (Exception e) {
			e.printStackTrace();
			 this.resultSuccessData(request,response, "保存数据失败", null);
		}
		this.resultSuccessData(request,response, "保存数据成功", null);

	}

}