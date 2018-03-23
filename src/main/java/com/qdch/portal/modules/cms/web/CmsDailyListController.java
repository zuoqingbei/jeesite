/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsActivityService;
import com.qdch.portal.modules.cms.service.CmsNewsService;
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
import com.qdch.portal.modules.cms.entity.CmsDailyList;
import com.qdch.portal.modules.cms.service.CmsDailyListService;

import java.util.List;

/**
 * 每日一览Controller
 * @author wangfeng
 * @version 2018-03-22
 */
@Controller
public class CmsDailyListController extends BaseController {

	@Autowired
	private CmsDailyListService cmsDailyListService;

	@Autowired
	private CmsNewsService cmsNewsService;
	@Autowired
	private CmsActivityService cmsActivityService;
	@ModelAttribute
	public CmsDailyList get(@RequestParam(required=false) String id) {
		CmsDailyList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsDailyListService.get(id);
		}
		if (entity == null){
			entity = new CmsDailyList();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsDailyList:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsDailyList/list"})
	public String list(CmsDailyList cmsDailyList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsDailyList> page = cmsDailyListService.findPage(new Page<CmsDailyList>(request, response), cmsDailyList); 
		model.addAttribute("page", page);
		return "modules/cms/cmsDailyListList";
	}

	@RequiresPermissions("cms:cmsDailyList:view")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/form")
	public String form(CmsDailyList cmsDailyList, Model model,HttpServletRequest request,HttpServletResponse response) {

		Page<CmsNews> cmsNewsPage =  cmsNewsService.findPage(new Page<CmsNews>(request,response),new CmsNews());
		Page<CmsActivity> cmsActivityPage =  cmsActivityService.findPage(new Page<CmsActivity>(request,response),new CmsActivity());
		model.addAttribute("cmsNewsPage",cmsNewsPage);
		model.addAttribute("cmsActivityPage",cmsActivityPage);

		model.addAttribute("cmsDailyList", cmsDailyList);
		return "modules/cms/cmsDailyListForm";
	}

	@RequiresPermissions("cms:cmsDailyList:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/save")
	public String save(CmsDailyList cmsDailyList, Model model,
					   RedirectAttributes redirectAttributes,HttpServletRequest request,
					   HttpServletResponse response) {
		if (!beanValidator(model, cmsDailyList)){
			return form(cmsDailyList, model,request,response);
		}
		cmsDailyListService.save(cmsDailyList);
		addMessage(redirectAttributes, "保存每日一览成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyList/list?repage";
	}
	
	@RequiresPermissions("cms:cmsDailyList:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/delete")
	public String delete(CmsDailyList cmsDailyList, RedirectAttributes redirectAttributes) {
		cmsDailyListService.delete(cmsDailyList);
		addMessage(redirectAttributes, "删除每日一览成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyList/list?repage";
	}

}