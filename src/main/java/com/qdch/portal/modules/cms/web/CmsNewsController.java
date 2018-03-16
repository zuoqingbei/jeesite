/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.common.utils.JedisUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;

/**
 * 资讯Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
//
//@RequestMapping(value = "${adminPath}/cms/cmsNews")
public class CmsNewsController extends BaseController {

	@Autowired
	private CmsNewsService cmsNewsService;
	
	@ModelAttribute
	
	public CmsNews get(@RequestParam(required=false) String id) {
		CmsNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsNewsService.get(id);
		}
		if (entity == null){
			entity = new CmsNews();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsNews:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsNews/list", ""})
	public String list(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsNews> page = cmsNewsService.findPage(new Page<CmsNews>(request, response), cmsNews); 
		model.addAttribute("page", page);
		return "modules/cms/cmsNewsList";
	}

	@RequiresPermissions("cms:cmsNews:view")
	@RequestMapping(value = "${adminPath}/cms/cmsNews/form")
	public String form(CmsNews cmsNews, Model model) {
		model.addAttribute("cmsNews", cmsNews);
		return "modules/cms/cmsNewsForm";
	}

	@RequiresPermissions("cms:cmsNews:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsNews/save")
	public String save(CmsNews cmsNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsNews)){
			return form(cmsNews, model);
		}
		try {
			cmsNewsService.save(cmsNews);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存资讯成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsNews/list?repage";
	}
	
	@RequiresPermissions("cms:cmsNews:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsNews/delete")
	public String delete(CmsNews cmsNews, RedirectAttributes redirectAttributes) {
		cmsNewsService.delete(cmsNews);
		addMessage(redirectAttributes, "删除资讯成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsNews/list?repage";
	}

	/**
	 * 得到某条资讯详情
	 * @param cmsNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsNews/getNewsContent",method = RequestMethod.GET)
	public void getNewsContent(CmsNews cmsNews, HttpServletRequest request,HttpServletResponse response) {

		CmsNews cmsNews1 = cmsNewsService.get(cmsNews);
		this.resultSuccessData(request,response, "获取数据成功", cmsNews1);


	}

	/**
	 * 强推资讯
	 * @param cmsNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsNews/getRecommend",method = RequestMethod.GET)
	public void getRecommend(CmsNews cmsNews, HttpServletRequest request,HttpServletResponse response) {

        Page<CmsNews> cmsNewsList = cmsNewsService.getRecommend(new Page<CmsNews>(request, response),cmsNews);
		this.resultSuccessData(request,response, "获取数据成功", cmsNewsList);


	}

	/**
	 * 前台获得资讯分页列表
	 * @param cmsNews
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsNews/portallist",method = RequestMethod.GET)
	public void portallist(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsNews> page = cmsNewsService.findPage(new Page<CmsNews>(request, response), cmsNews);
		this.resultSuccessData(request,response, "获取数据成功", page);
	}


	/**
	 * 资讯排行
	 * @param cmsNews
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsNews/getRank",method = RequestMethod.GET)
	public void getRank(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Page<CmsNews> page = cmsNewsService.getRank(new Page<CmsNews>(request, response), cmsNews);
			this.resultSuccessData(request,response, "获取数据成功",  page);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 前台-保存
	 * @param cmsNews
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsNews/saveData",method = RequestMethod.POST)
	public void  saveData(CmsNews cmsNews, Model model, HttpServletRequest request,HttpServletResponse response) {
//		if (!beanValidator(model, cmsNews)){
//			return form(cmsNews, model);
//		}
		try {
			cmsNewsService.save(cmsNews);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultSuccessData(request,response, "保存数据成功", null);
		}
		this.resultSuccessData(request,response, "保存数据失败", null);

	}



}