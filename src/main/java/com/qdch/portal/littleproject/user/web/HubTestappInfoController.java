/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.littleproject.user.web;

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
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.user.entity.HubTestappInfo;
import com.qdch.portal.littleproject.user.service.HubTestappInfoService;

/**
 * 基础人员电话信息维护Controller
 * @author wangsw
 * @version 2018-05-03
 */
@Controller
public class HubTestappInfoController extends BaseController {

	@Autowired
	private HubTestappInfoService hubTestappInfoService;
	
	@ModelAttribute
	public HubTestappInfo get(@RequestParam(required=false) String id) {
		HubTestappInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hubTestappInfoService.get(id);
		}
		if (entity == null){
			entity = new HubTestappInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("user:hubTestappInfo:view")
	@RequestMapping(value = {"${adminPath}/user/hubTestappInfo/list"})
	public String list(HubTestappInfo hubTestappInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HubTestappInfo> page = hubTestappInfoService.findPage(new Page<HubTestappInfo>(request, response), hubTestappInfo); 
		model.addAttribute("page", page);
		return "littleproject/user/hubTestappInfoList";
	}

	@RequiresPermissions("user:hubTestappInfo:view")
	@RequestMapping(value = "${adminPath}/user/hubTestappInfo/form")
	public String form(HubTestappInfo hubTestappInfo, Model model) {
		if(StringUtils.isNotEmpty(hubTestappInfo.getFtel())){
			hubTestappInfo = hubTestappInfoService.getDetail(hubTestappInfo.getFtel());
		}
		model.addAttribute("hubTestappInfo", hubTestappInfo);
		return "littleproject/user/hubTestappInfoForm";
	}

	@RequiresPermissions("user:hubTestappInfo:edit")
	@RequestMapping(value = "${adminPath}/user/hubTestappInfo/save")
	public String save(HubTestappInfo hubTestappInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hubTestappInfo)){
			return form(hubTestappInfo, model);
		}
		hubTestappInfoService.save(hubTestappInfo);
		addMessage(redirectAttributes, "保存基础信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/user/hubTestappInfo/list?repage";
	}
	
	@RequiresPermissions("user:hubTestappInfo:edit")
	@RequestMapping(value = "${adminPath}/user/hubTestappInfo/delete")
	public String delete(HubTestappInfo hubTestappInfo, RedirectAttributes redirectAttributes) {
		hubTestappInfoService.delete(hubTestappInfo);
		addMessage(redirectAttributes, "删除基础信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/user/hubTestappInfo/list?repage";
	}

}