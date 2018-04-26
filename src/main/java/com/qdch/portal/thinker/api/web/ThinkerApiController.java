/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.api.web;

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
import com.qdch.portal.thinker.api.entity.ThinkerApi;
import com.qdch.portal.thinker.api.service.ThinkerApiService;

/**
 * api管理Controller
 * @author zuoqb
 * @version 2018-04-13
 */
@Controller
public class ThinkerApiController extends BaseController {

	@Autowired
	private ThinkerApiService thinkerApiService;
	
	@ModelAttribute
	public ThinkerApi get(@RequestParam(required=false) String id) {
		ThinkerApi entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerApiService.get(id);
		}
		if (entity == null){
			entity = new ThinkerApi();
		}
		return entity;
	}
	
	@RequiresPermissions("api:thinkerApi:view")
	@RequestMapping(value = {"${adminPath}/api/thinkerApi/list"})
	public String list(ThinkerApi thinkerApi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThinkerApi> page = thinkerApiService.findPage(new Page<ThinkerApi>(request, response), thinkerApi); 
		model.addAttribute("page", page);
		return "thinker/api/thinkerApiList";
	}
	
	@RequestMapping(value = {"${adminPath}/ets/list"})
	public String ets(ThinkerApi thinkerApi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThinkerApi> page = thinkerApiService.findPage(new Page<ThinkerApi>(request, response), thinkerApi); 
		model.addAttribute("page", page);
		return "thinker/ets/ets";
	}

	@RequiresPermissions("api:thinkerApi:view")
	@RequestMapping(value = "${adminPath}/api/thinkerApi/form")
	public String form(ThinkerApi thinkerApi, Model model) {
		model.addAttribute("thinkerApi", thinkerApi);
		return "thinker/api/thinkerApiForm";
	}

	@RequiresPermissions("api:thinkerApi:edit")
	@RequestMapping(value = "${adminPath}/api/thinkerApi/save")
	public String save(ThinkerApi thinkerApi, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thinkerApi)){
			return form(thinkerApi, model);
		}
		thinkerApiService.save(thinkerApi);
		addMessage(redirectAttributes, "保存api管理成功");
		return "redirect:"+Global.getAdminPath()+"/api/thinkerApi/list?repage";
	}
	
	@RequiresPermissions("api:thinkerApi:edit")
	@RequestMapping(value = "${adminPath}/api/thinkerApi/delete")
	public String delete(ThinkerApi thinkerApi, RedirectAttributes redirectAttributes) {
		thinkerApiService.delete(thinkerApi);
		addMessage(redirectAttributes, "删除api管理成功");
		return "redirect:"+Global.getAdminPath()+"/api/thinkerApi/list?repage";
	}

}