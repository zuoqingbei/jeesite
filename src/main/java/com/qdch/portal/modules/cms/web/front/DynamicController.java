/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web.front;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.dao.CmsPortalCommentsDao;
import com.qdch.portal.modules.cms.entity.CmsPortalComments;
import com.qdch.portal.modules.cms.service.CmsPortalCommentsService;
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

/**
 * 用户动态
 * @author wangfeng
 * @version 2018-03-20
 */
@Controller
public class DynamicController extends BaseController {

	@Autowired
	private CmsPortalCommentsService cmsPortalCommentsService;

	@Autowired
	private CmsPortalCommentsDao cmsPortalCommentsDao;
	
	@ModelAttribute
	public CmsPortalComments get(@RequestParam(required=false) String id) {
		CmsPortalComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsPortalCommentsService.get(id);
		}
		if (entity == null){
			entity = new CmsPortalComments();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsPortalComments:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsPortalComments/list"})
	public String list(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsPortalComments> page = cmsPortalCommentsService.findPage(new Page<CmsPortalComments>(request, response), cmsPortalComments); 
		model.addAttribute("page", page);
		return "modules/cms/cmsPortalCommentsList";
	}

	@RequiresPermissions("cms:cmsPortalComments:view")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/form")
	public String form(CmsPortalComments cmsPortalComments, Model model) {
		model.addAttribute("cmsPortalComments", cmsPortalComments);
		return "modules/cms/cmsPortalCommentsForm";
	}

	@RequiresPermissions("cms:cmsPortalComments:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/save")
	public String save(CmsPortalComments cmsPortalComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsPortalComments)){
			return form(cmsPortalComments, model);
		}
		cmsPortalCommentsService.save(cmsPortalComments);
		addMessage(redirectAttributes, "保存门户评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPortalComments/list?repage";
	}
	
	@RequiresPermissions("cms:cmsPortalComments:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/delete")
	public String delete(CmsPortalComments cmsPortalComments, RedirectAttributes redirectAttributes) {
		cmsPortalCommentsService.delete(cmsPortalComments);
		addMessage(redirectAttributes, "删除门户评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPortalComments/list?repage";
	}


	/**
	 * 得到某条资讯的评论数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getCount")
	public void  getCount(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response){
		int count = 0;
		try {
			count = cmsPortalCommentsDao.getPortalCommentsCount(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", count);
			return;
		}
		this.resultSuccessData(request,response, "操作成功", count);
	}

	/**
	 * 用户是否操作过
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/isOperate")
	public void  isOperate(CmsPortalComments cmsPortalComments,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		try {
			flag = cmsPortalCommentsService.getDynamicSelf(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", flag);
			return;
		}
		this.resultSuccessData(request,response, "操作成功", flag);
	}

	/**
	 * 保存评论
	 * @param cmsPortalComments
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/saveData")
	public void saveData(CmsPortalComments cmsPortalComments,HttpServletRequest request,HttpServletResponse response){
		try {
			cmsPortalCommentsService.save(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败", null);
		}
		this.resultSuccessData(request,response, "操作成功", null);
	}

    /**
     * 获得某个文章的 评论
	 * @param cmsPortalComments
     * @param request
     * @param response
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getCommentsBySource")
	public void getCommentsBySource(CmsPortalComments cmsPortalComments,HttpServletRequest request,HttpServletResponse response){
		Page<CmsPortalComments> page = null;
		try {
			 page = cmsPortalCommentsService.getCommentsBySource(
                    new Page<CmsPortalComments>(request,response),cmsPortalComments);
			this.resultSuccessData(request,response, "操作成功",
                    mapJson(page,"success","获取数据成功"));
		} catch (Exception e) {
			e.printStackTrace();
			this.resultSuccessData(request,response, "操作失败",
					mapJson(null,"fail","操作失败"));
			return;
		}
		this.resultSuccessData(request,response, "操作成功",
				mapJson(page,"success","获取数据成功"));
	}

}