/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.search.web;

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
import com.qdch.portal.thinker.search.entity.ThinkerHotSearch;
import com.qdch.portal.thinker.search.service.ThinkerHotSearchService;

/**
 * 热搜Controller
 * @author zuoqb
 * @version 2018-04-13
 */
@Controller
public class ThinkerHotSearchController extends BaseController {

	@Autowired
	private ThinkerHotSearchService thinkerHotSearchService;
	
	@ModelAttribute
	public ThinkerHotSearch get(@RequestParam(required=false) String id) {
		ThinkerHotSearch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerHotSearchService.get(id);
		}
		if (entity == null){
			entity = new ThinkerHotSearch();
		}
		return entity;
	}
	
	@RequiresPermissions("search:thinkerHotSearch:view")
	@RequestMapping(value = {"${adminPath}/search/thinkerHotSearch/list"})
	public String list(ThinkerHotSearch thinkerHotSearch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThinkerHotSearch> page = thinkerHotSearchService.findPage(new Page<ThinkerHotSearch>(request, response), thinkerHotSearch); 
		model.addAttribute("page", page);
		return "thinker/search/thinkerHotSearchList";
	}

	@RequiresPermissions("search:thinkerHotSearch:view")
	@RequestMapping(value = "${adminPath}/search/thinkerHotSearch/form")
	public String form(ThinkerHotSearch thinkerHotSearch, Model model) {
		model.addAttribute("thinkerHotSearch", thinkerHotSearch);
		return "thinker/search/thinkerHotSearchForm";
	}

	@RequiresPermissions("search:thinkerHotSearch:edit")
	@RequestMapping(value = "${adminPath}/search/thinkerHotSearch/save")
	public String save(ThinkerHotSearch thinkerHotSearch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thinkerHotSearch)){
			return form(thinkerHotSearch, model);
		}
		thinkerHotSearchService.save(thinkerHotSearch);
		addMessage(redirectAttributes, "保存热搜成功");
		return "redirect:"+Global.getAdminPath()+"/search/thinkerHotSearch/list?repage";
	}
	
	@RequiresPermissions("search:thinkerHotSearch:edit")
	@RequestMapping(value = "${adminPath}/search/thinkerHotSearch/delete")
	public String delete(ThinkerHotSearch thinkerHotSearch, RedirectAttributes redirectAttributes) {
		thinkerHotSearchService.delete(thinkerHotSearch);
		addMessage(redirectAttributes, "删除热搜成功");
		return "redirect:"+Global.getAdminPath()+"/search/thinkerHotSearch/list?repage";
	}

}