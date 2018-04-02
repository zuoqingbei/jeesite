/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.util.HashMap;
import java.util.Map;

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
import com.qdch.portal.modules.cms.dao.CmsShareDao;
import com.qdch.portal.modules.cms.entity.CmsShare;
import com.qdch.portal.modules.cms.service.CmsShareService;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 用户分享记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsShareController extends BaseController {

	@Autowired
	private CmsShareService cmsShareService;

	@Autowired
	private CmsShareDao cmsShareDao;
	
	@ModelAttribute
	public CmsShare get(@RequestParam(required=false) String id) {
		CmsShare entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsShareService.get(id);
		}
		if (entity == null){
			entity = new CmsShare();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsShare:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsShare/list"})
	public String list(CmsShare cmsShare, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsShare> page = cmsShareService.findPage(new Page<CmsShare>(request, response), cmsShare); 
		model.addAttribute("page", page);
		return "modules/cms/cmsShareList";
	}

	@RequiresPermissions("cms:cmsShare:view")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/form")
	public String form(CmsShare cmsShare, Model model) {
		model.addAttribute("cmsShare", cmsShare);
		return "modules/cms/cmsShareForm";
	}

	@RequiresPermissions("cms:cmsShare:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/save")
	public String save(CmsShare cmsShare, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsShare)){
			return form(cmsShare, model);
		}
		cmsShareService.save(cmsShare);
		addMessage(redirectAttributes, "保存用户分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsShare/list?repage";
	}
	
	@RequiresPermissions("cms:cmsShare:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsShare/delete")
	public String delete(CmsShare cmsShare, RedirectAttributes redirectAttributes) {
		cmsShareService.delete(cmsShare);
		addMessage(redirectAttributes, "删除用户分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsShare/list?repage";
	}

	/**
	 * 保存分享内容
	 * @param cmsShare
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsShare/saveShare")
	@ResponseBody
	public String saveShare(CmsShare cmsShare,Model model, HttpServletRequest request,HttpServletResponse response){
		try {
			User user = UserUtils.getUser();
			if(StringUtils.isBlank(user.getId())){
				return this.resultFaliureData(request,response, "请先登录", null);
			}

			if(StringUtils.isBlank(cmsShare.getSourceTable())||StringUtils.isBlank(cmsShare.getSourceId())){
				return this.resultFaliureData(request,response, "请先输入sourceId和sourceTable的值", null);
			}

			if(StringUtils.isBlank(cmsShare.getPlatform())||StringUtils.isBlank(cmsShare.getUrl())){
				return this.resultFaliureData(request,response, "请先输入分享的平台和地址", null);
			}
			cmsShare.setUser(UserUtils.getUser());
			cmsShareService.save(cmsShare);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "保存数据失败", null);
		}
		return this.resultSuccessData(request,response, "保存数据成功", null);

	}

	/**
	 * 得到某条资讯的分享量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsShare/getCount")
	@ResponseBody
	public String  getCount(CmsShare cmsShare,HttpServletRequest request,HttpServletResponse response){
		try {
			String sourceTable = cmsShare.getSourceTable();
			String sourceId = cmsShare.getSourceId();
			if(StringUtils.isBlank(sourceTable)){
				return this.resultFaliureData(request,response, "请先输入sourceTable", "");
			}
			if(StringUtils.isBlank(sourceId)){
				return this.resultFaliureData(request,response, "请先输入sourceId", "");
			}
			cmsShare  = cmsShareDao.getShareCount(cmsShare);
			if(cmsShare != null && !cmsShare.equals("")){
				return this.resultSuccessData(request,response, "操作成功", cmsShare.getCount());
			}else{
				return this.resultSuccessData(request,response, "操作成功", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", "");
		}

	}

	/**
	  * 用户是否操作过
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsShare/isOperate")
	@ResponseBody
	public String  isOperate(CmsShare cmsShare,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		try {
			String sourceTable = cmsShare.getSourceTable();
			String sourceId = cmsShare.getSourceId();
			String userid = cmsShare.getUserId();
			Map<String,Object> res = new HashMap<String, Object>();
			if(StringUtils.isBlank(sourceTable)){
				return this.resultFaliureData(request,response, "请先输入sourceTable", "");
			}
			if(StringUtils.isBlank(sourceId)){
				return this.resultFaliureData(request,response, "请先输入sourceId", "");
			}
			if(StringUtils.isBlank(userid)){
				return this.resultFaliureData(request,response, "请先输入userid", "");
			}
			flag = cmsShareService.getDynamicSelf(cmsShare);
//			res.put("result",flag);
			return this.resultSuccessData(request,response, "操作成功", flag);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", flag);
		}

	}

}