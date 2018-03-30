/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.text.SimpleDateFormat;
import java.util.List;

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

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.dao.CmsActivityDao;
import com.qdch.portal.modules.cms.dao.CmsDailyListContentDao;
import com.qdch.portal.modules.cms.dao.CmsDailyListDao;
import com.qdch.portal.modules.cms.dao.CmsEducationDao;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;
import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.cms.entity.CmsDailyList;
import com.qdch.portal.modules.cms.entity.CmsDailyListContent;
import com.qdch.portal.modules.cms.entity.CmsDailyListDto;
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsActivityService;
import com.qdch.portal.modules.cms.service.CmsDailyListContentService;
import com.qdch.portal.modules.cms.service.CmsDailyListService;
import com.qdch.portal.modules.cms.service.CmsEducationService;
import com.qdch.portal.modules.cms.service.CmsNewsService;

/**
 * 每日一览Controller
 * @author wangfeng
 * @version 2018-03-22
 */
@Controller
public class CmsDailyListController extends BaseController {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private CmsDailyListService cmsDailyListService;

	@Autowired
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
			CmsEducation education = new CmsEducation();
			education.setCategory1("1");//案例
            List<CmsEducation> educationList = cmsEducationDao.findList(education);
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
	@ResponseBody
	public String getDailyListByDay(CmsDailyList cmsDailyList,HttpServletRequest request,HttpServletResponse response){
		try {
			String updateDate = request.getParameter("updateDate");
//			String updateDate = "2018-03-30";
			if(StringUtils.isBlank(updateDate)){
				return this.resultFaliureData(request,response, "请先选择时间", null);
			}
			CmsDailyListDto result = new CmsDailyListDto();
			CmsDailyList dailyList = cmsDailyListDao.getDailyByDay(cmsDailyList);
			result.setCmsDailyList(dailyList);
			if(StringUtils.isNotBlank(dailyList.getId())){
				List<CmsNews> cmsNewsList =  cmsNewsDao.getDailyNews(dailyList.getId());
				result.setCmsNewsList(cmsNewsList);
				result.setCmsEducationList(cmsEducationDao.getDailyEducation(dailyList.getId()));
			}

			return this.resultSuccessData(request,response, "操作成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作成功", null);
		}


	}


	/**
	 * 每月的每日一览
	 * @param cmsDailyList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsDailyList/getDailyListByMonth")
	@ResponseBody
	public String getDailyListByMonth(CmsDailyList cmsDailyList,HttpServletRequest request,HttpServletResponse response){
		try {
			String updateDate = request.getParameter("updateDate");
			if(StringUtils.isBlank(updateDate)){
				return this.resultFaliureData(request,response, "请先选择月份", null);
			}
			CmsDailyListDto result = new CmsDailyListDto();
			List<CmsDailyList> dailyList = cmsDailyListDao.getDailyByMonth(cmsDailyList);
			return this.resultSuccessData(request,response, "操作成功", dailyList);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作成功", null);
		}


	}

}