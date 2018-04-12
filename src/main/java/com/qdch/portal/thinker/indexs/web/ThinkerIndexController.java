/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.indexs.web;

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
import com.qdch.portal.thinker.indexs.entity.ThinkerIndex;
import com.qdch.portal.thinker.indexs.service.ThinkerIndexService;

/**
 * 指标管理Controller
 * @author zuoqb
 * @version 2018-04-12
 */
@Controller
public class ThinkerIndexController extends BaseController {

	@Autowired
	private ThinkerIndexService thinkerIndexService;
	
	@ModelAttribute
	public ThinkerIndex get(@RequestParam(required=false) String id) {
		ThinkerIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerIndexService.get(id);
		}
		if (entity == null){
			entity = new ThinkerIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("indexs:thinkerIndex:view")
	@RequestMapping(value = {"${adminPath}/indexs/thinkerIndex/list"})
	public String list(ThinkerIndex thinkerIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThinkerIndex> page = thinkerIndexService.findPage(new Page<ThinkerIndex>(request, response), thinkerIndex); 
		model.addAttribute("page", page);
		return "thinker/indexs/thinkerIndexList";
	}

	@RequiresPermissions("indexs:thinkerIndex:view")
	@RequestMapping(value = "${adminPath}/indexs/thinkerIndex/form")
	public String form(ThinkerIndex thinkerIndex, Model model) {
		model.addAttribute("thinkerIndex", thinkerIndex);
		return "thinker/indexs/thinkerIndexForm";
	}

	@RequiresPermissions("indexs:thinkerIndex:edit")
	@RequestMapping(value = "${adminPath}/indexs/thinkerIndex/save")
	public String save(ThinkerIndex thinkerIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thinkerIndex)){
			return form(thinkerIndex, model);
		}
		thinkerIndexService.save(thinkerIndex);
		addMessage(redirectAttributes, "保存指标管理成功");
		return "redirect:"+Global.getAdminPath()+"/indexs/thinkerIndex/list?repage";
	}
	
	@RequiresPermissions("indexs:thinkerIndex:edit")
	@RequestMapping(value = "${adminPath}/indexs/thinkerIndex/delete")
	public String delete(ThinkerIndex thinkerIndex, RedirectAttributes redirectAttributes) {
		thinkerIndexService.delete(thinkerIndex);
		addMessage(redirectAttributes, "删除指标管理成功");
		return "redirect:"+Global.getAdminPath()+"/indexs/thinkerIndex/list?repage";
	}

}