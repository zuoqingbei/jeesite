/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.reports.web;

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
import com.qdch.portal.thinker.reports.entity.ThinkerReports;
import com.qdch.portal.thinker.reports.service.ThinkerReportsService;

/**
 * 报表管理Controller
 * @author zuoqb
 * @version 2018-04-13
 */
@Controller
public class ThinkerReportsController extends BaseController {

	@Autowired
	private ThinkerReportsService thinkerReportsService;
	
	@ModelAttribute
	public ThinkerReports get(@RequestParam(required=false) String id) {
		ThinkerReports entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerReportsService.get(id);
		}
		if (entity == null){
			entity = new ThinkerReports();
		}
		return entity;
	}
	
	@RequiresPermissions("reports:thinkerReports:view")
	@RequestMapping(value = {"${adminPath}/reports/thinkerReports/list"})
	public String list(ThinkerReports thinkerReports, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThinkerReports> page = thinkerReportsService.findPage(new Page<ThinkerReports>(request, response), thinkerReports); 
		model.addAttribute("page", page);
		return "thinker/reports/thinkerReportsList";
	}

	@RequiresPermissions("reports:thinkerReports:view")
	@RequestMapping(value = "${adminPath}/reports/thinkerReports/form")
	public String form(ThinkerReports thinkerReports, Model model) {
		model.addAttribute("thinkerReports", thinkerReports);
		return "thinker/reports/thinkerReportsForm";
	}

	@RequiresPermissions("reports:thinkerReports:edit")
	@RequestMapping(value = "${adminPath}/reports/thinkerReports/save")
	public String save(ThinkerReports thinkerReports, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, thinkerReports)){
			return form(thinkerReports, model);
		}
		thinkerReportsService.save(thinkerReports);
		addMessage(redirectAttributes, "保存报表管理成功");
		return "redirect:"+Global.getAdminPath()+"/reports/thinkerReports/list?repage";
	}
	
	@RequiresPermissions("reports:thinkerReports:edit")
	@RequestMapping(value = "${adminPath}/reports/thinkerReports/delete")
	public String delete(ThinkerReports thinkerReports, RedirectAttributes redirectAttributes) {
		thinkerReportsService.delete(thinkerReports);
		addMessage(redirectAttributes, "删除报表管理成功");
		return "redirect:"+Global.getAdminPath()+"/reports/thinkerReports/list?repage";
	}

}