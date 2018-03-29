/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.dao.*;
import com.qdch.portal.modules.cms.entity.*;
import com.qdch.portal.modules.cms.service.*;
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

import java.util.List;

/**
 * 每日一览Controller
 * @author wangfeng
 * @version 2018-03-22
 */
@Controller
public class CmsDailyListController extends BaseController {

	@Autowired
	private CmsDailyListService cmsDailyListService;

	private CmsDailyListDao cmsDailyListDao;
	@Autowired
	private CmsNewsService cmsNewsService;
	@Autowired
	private CmsActivityService cmsActivityService;

	@Autowired
	private CmsEducationDao cmsEducationDao;
	@Autowired
	private CmsEducationService cmsEducationService;

	@Autowired
	private CmsDailyListContentService cmsDailyListContentService;

	@Autowired
	private CmsDailyListContentDao cmsDailyListContentDao;

	@Autowired
	private CmsNewsDao cmsNewsDao;
	@Autowired
	private CmsActivityDao cmsActivityDao;
	@ModelAttribute
	public CmsDailyList get(@RequestParam(required=false) String id) {
		CmsDailyList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsDailyListService.get(id);
		}
		if (entity == null){
			entity = new CmsDailyList();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsDailyList:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsDailyList/list"})
	public String list(CmsDailyList cmsDailyList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsDailyList> page = cmsDailyListService.findPage(new Page<CmsDailyList>(request, response), cmsDailyList); 
		model.addAttribute("page", page);
		return "modules/cms/cmsDailyListList";
	}

	@RequiresPermissions("cms:cmsDailyList:view")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/form")
	public String form(CmsDailyList cmsDailyList, Model model,HttpServletRequest request,HttpServletResponse response) {

//		Page<CmsNews> cmsNewsPage =  cmsNewsService.findPage(new Page<CmsNews>(request,response),new CmsNews());
//		Page<CmsActivity> cmsActivityPage =  cmsActivityService.findPage(new Page<CmsActivity>(request,response),new CmsActivity());
        try {
            List<CmsNews> cmsNewsList = cmsNewsDao.findList(new CmsNews());
            List<CmsActivity> cmsActivityList  = cmsActivityDao.findList(new CmsActivity());
            List<CmsEducation> educationList = cmsEducationDao.findList(new CmsEducation());
            CmsDailyListContent cmsDailyListContent = new CmsDailyListContent();
            cmsDailyListContent.setDailyId(cmsDailyList.getId());
            model.addAttribute("cmsNewsList",cmsNewsList);
            model.addAttribute("cmsActivityList",cmsActivityList);
			model.addAttribute("educationList",educationList);
            model.addAttribute("news",cmsDailyListContentDao.getNewsList(cmsDailyListContent)); //编辑进入 的时候的资讯列表
			model.addAttribute("educations",cmsDailyListContentDao.getEductionList(cmsDailyListContent)); //编辑进入 的时候的案例列表

            model.addAttribute("cmsDailyList", cmsDailyList);
            return "modules/cms/cmsDailyListForm";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

	@RequiresPermissions("cms:cmsDailyList:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/save")
	public String save(CmsDailyList cmsDailyList, Model model,
					   RedirectAttributes redirectAttributes,HttpServletRequest request,
					   HttpServletResponse response) {
		if (!beanValidator(model, cmsDailyList)){
			return form(cmsDailyList, model,request,response);
		}
        cmsDailyListService.save(cmsDailyList);
		String newsid = cmsDailyList.getNewids();
		String educationids = cmsDailyList.getEducationids();

		if(StringUtils.isNotBlank(cmsDailyList.getId())){
			CmsDailyListContent content = new CmsDailyListContent();
			content.setDailyId(cmsDailyList.getId());
			cmsDailyListContentDao.delByDaily(content); //编辑状态保存的时候先删除之前保存的 资讯和案例
		}
		if(StringUtils.isNotBlank(newsid)){
			String [] newsids = newsid.split(",");
			for(String s:newsids){
				CmsDailyListContent cmsDailyListContent = new CmsDailyListContent();
				cmsDailyListContent.setDailyId(cmsDailyList.getId());
                cmsDailyListContent.setTableName("cms_news");
                cmsDailyListContent.setCmsId(s);
				cmsDailyListContentService.save(cmsDailyListContent);
			}
		}

		if(StringUtils.isNotBlank(educationids)){
			String [] educationsids = educationids.split(",");
			for(String s:educationsids){
				CmsDailyListContent cmsDailyListContent = new CmsDailyListContent();
				cmsDailyListContent.setDailyId(cmsDailyList.getId());
				cmsDailyListContent.setTableName("cms_education");
				cmsDailyListContent.setCmsId(s);
				cmsDailyListContentService.save(cmsDailyListContent);
			}
		}
		addMessage(redirectAttributes, "保存每日一览成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyList/list?repage";
	}
	
	@RequiresPermissions("cms:cmsDailyList:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsDailyList/delete")
	public String delete(CmsDailyList cmsDailyList, RedirectAttributes redirectAttributes) {
		cmsDailyListService.delete(cmsDailyList);
		addMessage(redirectAttributes, "删除每日一览成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsDailyList/list?repage";
	}

	/**
	 * 每天的每日一览
	 * @param cmsDailyList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsDailyList/getDailyListByDay")
	public void getDailyListByDay(CmsDailyList cmsDailyList,HttpServletRequest request,HttpServletResponse response){
		try {
			if(StringUtils.isBlank(request.getParameter("updateDate"))){
				this.resultFaliureData(request,response, "请先选择时间", null);
				return ;
			}
			CmsDailyListDto result = new CmsDailyListDto();
			CmsDailyList dailyList = cmsDailyListDao.getDailyByDay(cmsDailyList);
			result.setCmsDailyList(dailyList);

			List<CmsNews> cmsNewsList =  cmsNewsDao.getDailyNews(cmsDailyList.getId());
			result.setCmsNewsList(cmsNewsList);
			result.setCmsEducationList(cmsEducationDao.getDailyEducation(cmsDailyList.getId()));
			this.resultSuccessData(request,response, "操作成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作成功", null);
			return ;
		}


	}


	/**
	 * 每月的每日一览
	 * @param cmsDailyList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsDailyList/getDailyListByMonth")
	public void getDailyListByMonth(CmsDailyList cmsDailyList,HttpServletRequest request,HttpServletResponse response){
		try {
			if(StringUtils.isBlank(request.getParameter("updateDate"))){
				this.resultFaliureData(request,response, "请先选择月份", null);
				return ;
			}
			List<CmsDailyList> cmsDailyLists = cmsDailyListDao.getDailyByMonth(cmsDailyList);

			this.resultSuccessData(request,response, "操作成功", cmsDailyLists);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作成功", null);
			return ;
		}


	}

}