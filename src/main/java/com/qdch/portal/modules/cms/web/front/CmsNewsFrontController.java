/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web.front;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 资讯Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
//
@RequestMapping(value = "${portalPath}/cms/cmsNews")
public class CmsNewsFrontController extends BaseController {

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

	/**
	 * 得到某条资讯详情
	 * @param cmsNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getNewsContent")
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
	@RequestMapping(value = "getRecommend")
	public void getRecommend(CmsNews cmsNews, HttpServletRequest request,HttpServletResponse response) {
		List<CmsNews> cmsNewsList = cmsNewsService.getRecommend(new Page<CmsNews>(request, response),cmsNews);
		this.resultSuccessData(request,response, "获取数据成功", cmsNewsList);

	}




}