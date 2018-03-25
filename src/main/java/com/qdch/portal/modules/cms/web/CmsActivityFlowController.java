/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.dao.CmsActivityFlowDao;
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
import com.qdch.portal.modules.cms.entity.CmsActivityFlow;
import com.qdch.portal.modules.cms.service.CmsActivityFlowService;

import java.util.List;

/**
 * 活动日程Controller
 * @author wangfeng
 * @version 2018-03-21
 */
@Controller
public class CmsActivityFlowController extends BaseController {

	@Autowired
	private CmsActivityFlowService cmsActivityFlowService;


	@Autowired
	private CmsActivityFlowDao cmsActivityFlowDao;

	@ModelAttribute
	public CmsActivityFlow get(@RequestParam(required=false) String id) {
		CmsActivityFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsActivityFlowService.get(id);
		}
		if (entity == null){
			entity = new CmsActivityFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsActivityFlow:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsActivityFlow/list"})
	public String list(CmsActivityFlow cmsActivityFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsActivityFlow> page = cmsActivityFlowService.findPage(new Page<CmsActivityFlow>(request, response), cmsActivityFlow); 
		model.addAttribute("page", page);
		return "modules/cms/cmsActivityFlowList";
	}

	@RequiresPermissions("cms:cmsActivityFlow:view")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityFlow/form")
	public String form(CmsActivityFlow cmsActivityFlow, Model model) {
		model.addAttribute("cmsActivityFlow", cmsActivityFlow);
		return "modules/cms/cmsActivityFlowForm";
	}

	@RequiresPermissions("cms:cmsActivityFlow:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityFlow/save")
	public String save(CmsActivityFlow cmsActivityFlow, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, cmsActivityFlow)){
                return form(cmsActivityFlow, model);
            }
			cmsActivityFlowService.save(cmsActivityFlow);
			addMessage(redirectAttributes, "保存活动日程成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityFlow/list?repage";
	}
	
	@RequiresPermissions("cms:cmsActivityFlow:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsActivityFlow/delete")
	public String delete(CmsActivityFlow cmsActivityFlow, RedirectAttributes redirectAttributes) {
		cmsActivityFlowService.delete(cmsActivityFlow);
		addMessage(redirectAttributes, "删除活动日程成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsActivityFlow/list?repage";
	}

	@RequestMapping(value = "${portalPath}/cms/cmsActivityFlow/getByActivity")
	public void getByActivity(CmsActivityFlow cmsActivityFlow,HttpServletRequest request,HttpServletResponse response){

		List<CmsActivityFlow> cmsActivityFlow1 = null;
		try {
			if(cmsActivityFlow.getActivityId() == null || cmsActivityFlow.getActivityId().equals("")){
				this.resultFaliureData(request,response, "请先输入活动的id", null);
				return;
			}
			cmsActivityFlow1 = cmsActivityFlowDao.getByActivity(cmsActivityFlow);
			this.resultSuccessData(request,response, "获取数据成功", cmsActivityFlow1);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "获取数据失败", null);
			return;
		}


	}

}