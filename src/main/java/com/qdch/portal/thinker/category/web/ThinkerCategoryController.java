/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.category.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.thinker.category.entity.ThinkerCategory;
import com.qdch.portal.thinker.category.service.ThinkerCategoryService;

/**
 * 分类Controller
 * @author zuoqb
 * @version 2018-04-12
 */
@Controller
public class ThinkerCategoryController extends BaseController {

	@Autowired
	private ThinkerCategoryService thinkerCategoryService;
	
	@ModelAttribute
	public ThinkerCategory get(@RequestParam(required=false) String id) {
		ThinkerCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerCategoryService.get(id);
		}
		if (entity == null){
			entity = new ThinkerCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("category:thinkerCategory:view")
	@RequestMapping(value = {"${adminPath}/category/thinkerCategory/list"})
	public String list(ThinkerCategory thinkerCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ThinkerCategory> list = thinkerCategoryService.findList(thinkerCategory); 
		model.addAttribute("list", list);
		return "thinker/category/thinkerCategoryList";
	}

	@RequiresPermissions("category:thinkerCategory:view")
	@RequestMapping(value = "${adminPath}/category/thinkerCategory/form")
	public String form(ThinkerCategory thinkerCategory, Model model) {
		if (thinkerCategory.getParent()!=null && StringUtils.isNotBlank(thinkerCategory.getParent().getId())){
			thinkerCategory.setParent(thinkerCategoryService.get(thinkerCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(thinkerCategory.getId())){
				ThinkerCategory thinkerCategoryChild = new ThinkerCategory();
				thinkerCategoryChild.setParent(new ThinkerCategory(thinkerCategory.getParent().getId()));
				List<ThinkerCategory> list = thinkerCategoryService.findList(thinkerCategory); 
				if (list.size() > 0){
					thinkerCategory.setSort(list.get(list.size()-1).getSort());
					if (thinkerCategory.getSort() != null){
						thinkerCategory.setSort(thinkerCategory.getSort() + 30);
					}
				}
			}
		}
		if (thinkerCategory.getSort() == null){
			thinkerCategory.setSort(30);
		}
		model.addAttribute("thinkerCategory", thinkerCategory);
		return "thinker/category/thinkerCategoryForm";
	}

	@RequiresPermissions("category:thinkerCategory:edit")
	@RequestMapping(value = "${adminPath}/category/thinkerCategory/save")
	public String save(ThinkerCategory thinkerCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thinkerCategory)){
			return form(thinkerCategory, model);
		}
		thinkerCategoryService.save(thinkerCategory);
		addMessage(redirectAttributes, "保存分类成功");
		return "redirect:"+Global.getAdminPath()+"/category/thinkerCategory/list?repage";
	}
	
	@RequiresPermissions("category:thinkerCategory:edit")
	@RequestMapping(value = "${adminPath}/category/thinkerCategory/delete")
	public String delete(ThinkerCategory thinkerCategory, RedirectAttributes redirectAttributes) {
		thinkerCategoryService.delete(thinkerCategory);
		addMessage(redirectAttributes, "删除分类成功");
		return "redirect:"+Global.getAdminPath()+"/category/thinkerCategory/list?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "${adminPath}/category/thinkerCategory/treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ThinkerCategory> list = thinkerCategoryService.findList(new ThinkerCategory());
		for (int i=0; i<list.size(); i++){
			ThinkerCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}