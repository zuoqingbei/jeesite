/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;

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
import com.qdch.portal.modules.cms.dao.CmsNewsDao;
import com.qdch.portal.modules.cms.dao.CmsNewsDataDao;
import com.qdch.portal.modules.cms.entity.CmsComplaint;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsNewsData;
import com.qdch.portal.modules.cms.service.CmsNewsDataService;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import com.qdch.portal.modules.cms.utils.RegUtils;
import com.qdch.portal.modules.sys.service.DictService;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 资讯Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
//
//@RequestMapping(value = "${adminPath}/cms/cmsNews")
public class CmsNewsController extends BaseController {
	public CmsNewsController(){
		DynamicDataSource.setDataSource();
	}

	@Autowired
	private CmsNewsService cmsNewsService;
	@Autowired
	private DictService dictService;

	@Autowired
	private CmsNewsDataService cmsNewsDataService;

	@Autowired
	private CmsNewsDao cmsNewsDao;


	@Autowired
	private CmsNewsDataDao cmsNewsDataDao;
	
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
//
//	@ModelAttribute
//
//	public Dict getDict(@RequestParam(required=false) String id) {
//		Dict entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = dictService.get(id);
//		}
//		if (entity == null){
//			entity = new Dict();
//		}
//		return entity;
//	}
	
	@RequiresPermissions("cms:cmsNews:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsNews/list", ""})
	public String list(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsNews> page = cmsNewsService.findPage(new Page<CmsNews>(request, response), cmsNews); 
		model.addAttribute("page", page);
		return "modules/cms/cmsNewsList";
	}

//	@RequiresPermissions("cms:cmsNews:view")
	@RequestMapping(value = "${adminPath}/cms/cmsNews/form")
	public String form(CmsNews cmsNews, Model model) {
		try {
//			Dict dict = new Dict();
//			dict.setType("tags_type");
//			cmsNews  = cmsNewsService.getContent(cmsNews);
//			if(cmsNews == null){
//				cmsNews = new CmsNews();
//			}
//			List<Dict> dicts = dictService.findByType(dict);
//			cmsNews.setTypeDict(dicts);
			CmsNewsData newsData = cmsNewsDataDao.getByNewId(cmsNews.getId());
			cmsNews.setContentHtml(newsData.getContentHtml());
			model.addAttribute("cmsNews", cmsNews);
//			model.addAttribute("table","CmsNews");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/cms/cmsNewsForm";
	}

	@RequiresPermissions("cms:cmsNews:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsNews/save")
	public String save(CmsNews cmsNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsNews)){
			return form(cmsNews, model);
		}
		try {
			cmsNews.setDataType("2"); //管理人员发布
			if(StringUtils.isBlank(cmsNews.getWeight())){
				cmsNews.setWeight("0");
			}

			if(StringUtils.isBlank(cmsNews.getRecommend())){
				cmsNews.setRecommend("0"); //不推荐
			}
			if(StringUtils.isBlank(cmsNews.getAllowComment())){
				cmsNews.setAllowComment("0"); //允许评论
			}
			if(StringUtils.isBlank(cmsNews.getUndercarriage())){
				cmsNews.setUndercarriage("0"); //未下架
			}
			if(StringUtils.isBlank(cmsNews.getCommentAudit())){
				cmsNews.setCommentAudit("1");//评论不需要审核
			}
			if(StringUtils.isBlank(cmsNews.getAllowReport())){
				cmsNews.setAllowReport("0"); //允许举报
			}
			cmsNews.setUser(UserUtils.getUser());
			cmsNewsService.save(cmsNews);
			CmsNewsData cmsNewsData = cmsNewsDataDao.getByNewId(cmsNews.getId());
			if(cmsNewsData == null){
				cmsNewsData = new CmsNewsData();
				cmsNewsData.setNewsId(cmsNews.getId());
			}
			cmsNewsData.setContentHtml(cmsNews.getContentHtml());
			cmsNewsData.setContent(RegUtils.delHTMLTag(cmsNews.getContentHtml()));
			cmsNewsDataService.save(cmsNewsData);
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
//	@RequestMapping(value = "${portalPath}/cms/cmsNews/getNewsContent")
//	public void getNewsContent(CmsNews cmsNews, HttpServletRequest request,HttpServletResponse response) {
//
//		try {
//			CmsNews cmsNews1 = cmsNewsService.getContent(cmsNews);
//			this.resultSuccessData(request,response, "获取数据成功", cmsNews1);
//			return ;
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.resultSuccessData(request,response, "获取数据失败", "false");
//			return ;
//		}
//
//
//	}

	/**
	 * 强推资讯
	 * @param cmsNews
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "${portalPath}/cms/cmsNews/getRecommend")
//	public void getRecommend(CmsNews cmsNews, HttpServletRequest request,HttpServletResponse response) {
//
//        Page<CmsNews> cmsNewsList = cmsNewsService.getRecommend(new Page<CmsNews>(request, response),cmsNews);
////		mapJson(page,"success","获取数据成功")
//		this.resultSuccessData(request,response, "",mapJson(cmsNewsList,"success","获取数据成功"));
//
//
//	}

	/**
	 * 前台获得资讯分页列表
	 * @param cmsNews
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "${portalPath}/cms/cmsNews/portallist")
//	public void portallist(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<CmsNews> page = cmsNewsService.findPage(new Page<CmsNews>(request, response), cmsNews);
//		this.resultSuccessData(request,response, "", mapJson(page,"success","获取数据成功"));
//	}


	/**
	 * 资讯排行
	 * @param cmsNews
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsNews/getRank")
	@ResponseBody
	public String getRank(CmsNews cmsNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			DynamicDataSource.setDataSource();
			String tags = cmsNews.getTags();
			if(tags !=null  && !tags.equals("")){
				tags = StringUtils.delFrontAndEndSymbol(tags);
				cmsNews.setTags(tags);
			}
			Page<CmsNews> page = cmsNewsService.getRank(new Page<CmsNews>(request, response), cmsNews);
			//return JsonMapper.toJsonString(page);
			return this.resultSuccessData(request,response, "获取数据成功",
					mapJson(page,"success","获取数据成功"));
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "获取数据失败",
					null);
			return null;
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

	@RequestMapping(value = "${portalPath}/cms/cmsNews/saveData")
	@ResponseBody
	public String  saveData(CmsNews cmsNews, Model model, HttpServletRequest request,HttpServletResponse response) {
//		if (!beanValidator(model, cmsNews)){
//			return form(cmsNews, model);
//		}
		try {
			if(cmsNews.getTitle() == null ||cmsNews.getTitle().equals("")){
				return this.resultFaliureData(request,response, "请先输入信息", "false");
			}
			cmsNews.setUser(UserUtils.getUser());
			cmsNewsService.save(cmsNews);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.resultFaliureData(request,response, "保存数据失败", "false");
		}
		return this.resultSuccessData(request,response, "保存数据成功", "true");

	}

	@RequestMapping(value = "${portalPath}/cms/cmsNews/getSimilarByTags")
	@ResponseBody
	public String getSimilarByTags(HttpServletRequest request, HttpServletResponse response){

		try {
			String id = request.getParameter("id"); //资讯id
			String tags = request.getParameter("tags") ; //该条资讯的标签
			if(StringUtils.isBlank(id)&&StringUtils.isBlank(tags)){
				return this.resultFaliureData(request,response, "请先输入资讯的id或者标签tags", null);
			}
			List<CmsNews> results = new ArrayList<CmsNews>();
			CmsNews cmsNews  = null;
			if(StringUtils.isNotBlank(id)){
				cmsNews= cmsNewsService.get(id);
				tags = cmsNews.getTags();
			}else {
				cmsNews = new CmsNews();
			}
			String []  tagList = null;
			if(StringUtils.isNotBlank(tags)){
				tagList = tags.split(",");
				cmsNews.setTagsvalue(tagList);

			}
			results = cmsNewsDao.getSimilarByTags(cmsNews);
			return this.resultSuccessData(request,response, "操作成功", results);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", null);
		}

	}
	
	
	/**
	 * @todo  微信公众号资讯列表
	 * @time   2018年3月29日 下午1:57:58
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/cms/cmsNews/list"})
	public String cmsComplaintList(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		request.setAttribute("userId", request.getParameter("userId"));
		return "portal/wechat/newsList";
	}

	@RequestMapping(value = {"${portalPath}/littleproject/aa"})
	@ResponseBody
	public String  tradeAmount(HttpServletRequest request, HttpServletResponse response){

		Map<String,Object> results = new HashMap<String,Object>();

		List<Object> lists = PostgreUtils.getInstance().excuteQuery("select * from insight_transaction_amount limit 1",null);
		Set<String> times = new HashSet<String>();
		List<String> lianhe = new ArrayList<String>();
		List<String> qingjin = new ArrayList<String>();
		List<String> wenhua = new ArrayList<String>();

		results.put("times",times);
		results.put("lianhe",lianhe);
		results.put("qingjin",qingjin);
		results.put("wenhua",wenhua);

		for(Object o:lists){
			Map m = (Map) o;
			System.out.println("222");



		}

		return this.resultSuccessData(request,response, "", results);
	}








}