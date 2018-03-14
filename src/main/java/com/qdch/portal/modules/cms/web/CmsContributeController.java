/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.util.HashMap;

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
import com.qdch.portal.modules.cms.entity.CmsContribute;
import com.qdch.portal.modules.cms.service.CmsContributeService;

/**
 * 用户投稿Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsContribute")
public class CmsContributeController extends BaseController {

	@Autowired
	private CmsContributeService cmsContributeService;
	
	@ModelAttribute
	
	public CmsContribute get(@RequestParam(required=false) String id) {
		CmsContribute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsContributeService.get(id);
		}
		if (entity == null){
			entity = new CmsContribute();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsContribute:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsContribute cmsContribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsContribute> page = cmsContributeService.findPage(new Page<CmsContribute>(request, response), cmsContribute); 
		model.addAttribute("page", page);
		return "modules/cms/cmsContributeList";
	}

	@RequiresPermissions("cms:cmsContribute:view")
	@RequestMapping(value = "form")
	public String form(CmsContribute cmsContribute, Model model) {
		model.addAttribute("cmsContribute", cmsContribute);
		return "modules/cms/cmsContributeForm";
	}

	@RequiresPermissions("cms:cmsContribute:edit")
	@RequestMapping(value = "save")
	public String save(CmsContribute cmsContribute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsContribute)){
			return form(cmsContribute, model);
		}
		cmsContributeService.save(cmsContribute);
		addMessage(redirectAttributes, "保存用户投稿成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsContribute/?repage";
	}
	
	@RequiresPermissions("cms:cmsContribute:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsContribute cmsContribute, RedirectAttributes redirectAttributes) {
		cmsContributeService.delete(cmsContribute);
		addMessage(redirectAttributes, "删除用户投稿成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsContribute/?repage";
	}
	
	
	/**
	 * 改变投稿状态
	 * @param cmsContribute
	 * @param redirectAttributes
	 * @return
	 */

//	@RequiresPermissions("cms:cmsContribute:edit")
	@RequestMapping(value = "changeState")
	public void  changeState(CmsContribute cmsContribute, HttpServletResponse response) {
		try {
			cmsContributeService.changeState(cmsContribute);
//		HashMap< String, Object> r=new HashMap<String, Object>();
//		r.put("status", "value");
			 this.resultSuccessData(response, "修改成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}