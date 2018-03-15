/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web.front;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsContribute;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsContributeService;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

/**
 * 用户投稿Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${portalPath}/cms/cmsContribute")
public class CmsContributeFrontController extends BaseController {

	@Autowired
	private CmsContributeService cmsContributeService;

	@Autowired
	private CmsNewsService cmsNewsService;
	
	@ModelAttribute
	
	public CmsContribute get(@RequestParam(required=false) String id) {
		CmsContribute entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsContributeService.get(id);
		}
		if (entity == null){
			entity = new CmsContribute();
		}
		return entity;
	}


	/**
	 * 获得用户投稿
	 * @param cmsContribute
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getUserContribute")
	public void  getUserContribute(HttpServletRequest request,CmsContribute cmsContribute, HttpServletResponse response) {
		try {
			CmsContribute cmsContribute1 = cmsContributeService.getUserContribute(cmsContribute);

			HashMap< String, Object> r=new HashMap<String, Object>();
			r.put("user", cmsContribute1);
			this.resultSuccessData(request,response, "获取数据成功", r);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}




}