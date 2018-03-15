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
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsNewsData;
import com.qdch.portal.modules.cms.service.CmsNewsDataService;
import com.qdch.portal.modules.cms.service.CmsNewsService;

/**
 * 资讯详表Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
//@RequestMapping(value = "${adminPath}/cms/cmsNewsData")
public class CmsNewsDataController extends BaseController {

	@Autowired
	private CmsNewsDataService cmsNewsDataService;
	
	@Autowired
	private CmsNewsService cmsNewsService;
	
	@ModelAttribute
	
	public CmsNewsData get(@RequestParam(required=false) String id) {
		CmsNewsData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsNewsDataService.get(id);
		}
		if (entity == null){
			entity = new CmsNewsData();
		}
		return entity;
	}
	
	@ModelAttribute
	
	public CmsNews getCmsNews(@RequestParam(required=false) String id) {
		CmsNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsNewsService.get(id);
			
		}
		if (entity == null){
			entity = new CmsNews();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsNewsData:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsNewsData/list", ""})
	public String list(CmsNewsData cmsNewsData, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Page<CmsNewsData> page = cmsNewsDataService.findPage(new Page<CmsNewsData>(request, response), cmsNewsData); 
			model.addAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modules/cms/cmsNewsDataList";
	}

	@RequiresPermissions("cms:cmsNewsData:view")
	@RequestMapping(value = "${adminPath}/cms/cmsNewsData/form")
	public String form(CmsNewsData cmsNewsData, Model model,HttpServletRequest request) {
		cmsNewsData.setTitleid(request.getParameter("titleid"));
		model.addAttribute("cmsNewsData", cmsNewsData);
		return "modules/cms/cmsNewsDataForm";
	}

	@RequiresPermissions("cms:cmsNewsData:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsNewsData/save")
	public String save(CmsNewsData cmsNewsData, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		try {
			if (!beanValidator(model, cmsNewsData)){
				return form(cmsNewsData, model,request);
			}
			cmsNewsDataService.save(cmsNewsData);
			addMessage(redirectAttributes, "保存资讯详表成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/cms/cmsNewsData/list?repage";
	}
	
	@RequiresPermissions("cms:cmsNewsData:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsNewsData/delete")
	public String delete(CmsNewsData cmsNewsData, RedirectAttributes redirectAttributes) {
		cmsNewsDataService.delete(cmsNewsData);
		addMessage(redirectAttributes, "删除资讯详表成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsNewsData/list?repage";
	}

}