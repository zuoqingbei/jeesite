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
import com.qdch.portal.modules.cms.entity.CmsDailyListContent;
import com.qdch.portal.modules.cms.service.CmsDailyListContentService;

/**
 * 每日一览详情Controller
 * @author wangfeng
 * @version 2018-03-22
 */
@Controller
public class CmsDailyListContentController extends BaseController {

	@Autowired
	private CmsDailyListContentService cmsDailyListContentService;
	
	@ModelAttribute
	public CmsDailyListContent get(@RequestParam(required=false) String id) {
		CmsDailyListContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsDailyListContentService.get(id);
		}
		if (entity == null){
			entity = new CmsDailyListContent();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsDailyListContent:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsDailyListContent/list"})
	public String list(CmsDailyListContent cmsDailyListContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsDailyListContent> page = cmsDailyListContentService.findPage(new Page<CmsDailyListContent>(request, response), cmsDailyListContent); 
		model.addAttribute("page", page);
		return "modules/cms/cmsDailyListContentList";
	}

	@RequiresPermissions("cms:cmsDailyListContent:view")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyListContent/form")
	public String form(CmsDailyListContent cmsDailyListContent, Model model) {
		model.addAttribute("cmsDailyListContent", cmsDailyListContent);
		return "modules/cms/cmsDailyListContentForm";
	}

	@RequiresPermissions("cms:cmsDailyListContent:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyListContent/save")
	public String save(CmsDailyListContent cmsDailyListContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsDailyListContent)){
			return form(cmsDailyListContent, model);
		}
		cmsDailyListContentService.save(cmsDailyListContent);
		addMessage(redirectAttributes, "保存每日一览详情成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyListContent/list?repage";
	}
	
	@RequiresPermissions("cms:cmsDailyListContent:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyListContent/delete")
	public String delete(CmsDailyListContent cmsDailyListContent, RedirectAttributes redirectAttributes) {
		cmsDailyListContentService.delete(cmsDailyListContent);
		addMessage(redirectAttributes, "删除每日一览详情成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyListContent/list?repage";
	}

}