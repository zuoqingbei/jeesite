/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.dao.CmsActivityOrganizationDao;
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
import com.qdch.portal.modules.cms.entity.CmsActivityOrganization;
import com.qdch.portal.modules.cms.service.CmsActivityOrganizationService;

/**
 * 活动组织结构Controller
 * @author wangfeng
 * @version 2018-03-21
 */
@Controller
public class CmsActivityOrganizationController extends BaseController {

	@Autowired
	private CmsActivityOrganizationService cmsActivityOrganizationService;


	@Autowired
	private CmsActivityOrganizationDao cmsActivityOrganizationDao;
	
	@ModelAttribute
	public CmsActivityOrganization get(@RequestParam(required=false) String id) {
		CmsActivityOrganization entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsActivityOrganizationService.get(id);
		}
		if (entity == null){
			entity = new CmsActivityOrganization();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsActivityOrganization:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsActivityOrganization/list"})
	public String list(CmsActivityOrganization cmsActivityOrganization, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsActivityOrganization> page = cmsActivityOrganizationService.findPage(new Page<CmsActivityOrganization>(request, response), cmsActivityOrganization); 
		model.addAttribute("page", page);
		return "modules/cms/cmsActivityOrganizationList";
	}

	@RequiresPermissions("cms:cmsActivityOrganization:view")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityOrganization/form")
	public String form(CmsActivityOrganization cmsActivityOrganization, Model model) {
		model.addAttribute("cmsActivityOrganization", cmsActivityOrganization);
		return "modules/cms/cmsActivityOrganizationForm";
	}

	@RequiresPermissions("cms:cmsActivityOrganization:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityOrganization/save")
	public String save(CmsActivityOrganization cmsActivityOrganization, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, cmsActivityOrganization)){
                return form(cmsActivityOrganization, model);
            }
			cmsActivityOrganizationService.save(cmsActivityOrganization);
			addMessage(redirectAttributes, "保存活动组织结构成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityOrganization/list?repage";
	}
	
	@RequiresPermissions("cms:cmsActivityOrganization:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityOrganization/delete")
	public String delete(CmsActivityOrganization cmsActivityOrganization, RedirectAttributes redirectAttributes) {
		cmsActivityOrganizationService.delete(cmsActivityOrganization);
		addMessage(redirectAttributes, "删除活动组织结构成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityOrganization/list?repage";
	}


	@RequestMapping(value = "${portalPath}/cms/cmsActivityOrganization/getByActivity")
	public void getByActivity(CmsActivityOrganization cmsActivityOrganization,HttpServletRequest request,
							  HttpServletResponse response){
		CmsActivityOrganization cmsActivityOrganization1 = null;
		try {
			 cmsActivityOrganization1 = cmsActivityOrganizationDao.getByActivity(cmsActivityOrganization);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", cmsActivityOrganization1);
			return;
		}

		this.resultSuccessData(request,response, "获取数据成功", cmsActivityOrganization1);

	}

}