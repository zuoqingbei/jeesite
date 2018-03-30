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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.ViewRecommend;
import com.qdch.portal.modules.cms.service.ViewRecommendService;

/**
 * 推荐Controller
 * @author wangfeng
 * @version 2018-03-24
 */
@Controller
public class ViewRecommendController extends BaseController {

	@Autowired
	private ViewRecommendService viewRecommendService;
	
	@ModelAttribute
	public ViewRecommend get(@RequestParam(required=false) String id) {
		ViewRecommend entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = viewRecommendService.get(id);
		}
		if (entity == null){
			entity = new ViewRecommend();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:viewRecommend:view")
	@RequestMapping(value = {"${adminPath}/cms/viewRecommend/list"})
	public String list(ViewRecommend viewRecommend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ViewRecommend> page = viewRecommendService.findPage(new Page<ViewRecommend>(request, response), viewRecommend); 
		model.addAttribute("page", page);
		return "modules/cms/viewRecommendList";
	}

	@RequiresPermissions("cms:viewRecommend:view")
	@RequestMapping(value = "${adminPath}/cms/viewRecommend/form")
	public String form(ViewRecommend viewRecommend, Model model) {
		model.addAttribute("viewRecommend", viewRecommend);
		return "modules/cms/viewRecommendForm";
	}

	@RequiresPermissions("cms:viewRecommend:edit")
	@RequestMapping(value = "${adminPath}/cms/viewRecommend/save")
	public String save(ViewRecommend viewRecommend, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, viewRecommend)){
			return form(viewRecommend, model);
		}
		viewRecommendService.save(viewRecommend);
		addMessage(redirectAttributes, "保存推荐成功");
		return "redirect:"+Global.getAdminPath()+"/cms/viewRecommend/list?repage";
	}
	
	@RequiresPermissions("cms:viewRecommend:edit")
	@RequestMapping(value = "${adminPath}/cms/viewRecommend/delete")
	public String delete(ViewRecommend viewRecommend, RedirectAttributes redirectAttributes) {
		viewRecommendService.delete(viewRecommend);
		addMessage(redirectAttributes, "删除推荐成功");
		return "redirect:"+Global.getAdminPath()+"/cms/viewRecommend/list?repage";
	}

	@RequestMapping(value = "${portalPath}/cms/viewRecommend/getRecommend")
	@ResponseBody
	public String getRecommend(ViewRecommend recommend,HttpServletRequest request,HttpServletResponse response){
		try {
			Page<ViewRecommend> page = viewRecommendService.getRecommend(new Page<ViewRecommend>(request,response),
					recommend);
			return this.resultSuccessData(request,response, "操作成功", mapJson(page,"true","成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.resultSuccessData(request,response, "操作失败", false);
		}
	}

}