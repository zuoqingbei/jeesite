/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.FormateJsonToStringByAnnotation;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsBanner;
import com.qdch.portal.modules.cms.service.CmsBannerService;

/**
 * 轮播图管理Controller
 * @author zuoqb
 * @version 2018-03-13
 */
@Controller
public class CmsBannerController extends BaseController {

	@Autowired
	private CmsBannerService cmsBannerService;
	
	@ModelAttribute
	public CmsBanner get(@RequestParam(required=false) String id) {
		CmsBanner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBannerService.get(id);
		}
		if (entity == null){
			entity = new CmsBanner();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsBanner:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsBanner/list", ""})
	public String list(CmsBanner cmsBanner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBanner> page = cmsBannerService.findPage(new Page<CmsBanner>(request, response), cmsBanner); 
		model.addAttribute("page", page);
		return "modules/cms/cmsBannerList";
	}

	@RequiresPermissions("cms:cmsBanner:view")
	@RequestMapping(value = "${adminPath}/cms/cmsBanner/form")
	public String form(CmsBanner cmsBanner, Model model) {
		model.addAttribute("cmsBanner", cmsBanner);
		return "modules/cms/cmsBannerForm";
	}

	@RequiresPermissions("cms:cmsBanner:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsBanner/save")
	public String save(CmsBanner cmsBanner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBanner)){
			return form(cmsBanner, model);
		}
		try {
			cmsBannerService.save(cmsBanner);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存轮播图管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBanner/list?repage";
	}
	
	@RequiresPermissions("cms:cmsBanner:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsBanner/delete")
	public String delete(CmsBanner cmsBanner, RedirectAttributes redirectAttributes) {
		cmsBannerService.delete(cmsBanner);
		addMessage(redirectAttributes, "删除轮播图管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBanner/list?repage";
	}
	/**
	 * 
	 * @todo   前台获取轮播图
	 * @time   2018年3月15日 下午1:01:23
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(method=RequestMethod.GET,value = {"${portalPath}/cms/cmsBanner/list"})
	@ResponseBody
	public String bannerList(HttpServletRequest request, HttpServletResponse response) {
		String limit=request.getParameter("limit");
		if(StringUtils.isBlank(limit)){
			limit="2";
		};
		List<CmsBanner> list = cmsBannerService.findBannerList(0,Integer.parseInt(limit));
		return this.resultSuccessData(request,response, "轮播数据获取成功", list);
	}

}