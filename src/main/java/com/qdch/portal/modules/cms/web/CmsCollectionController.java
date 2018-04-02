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
import com.qdch.portal.modules.cms.dao.CmsCollectionDao;
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.service.CmsCollectionService;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 用户收藏记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsCollectionController extends BaseController {

	@Autowired
	private CmsCollectionService cmsCollectionService;


	@Autowired
	private CmsCollectionDao cmsCollectionDao;
	
	@ModelAttribute
	public CmsCollection get(@RequestParam(required=false) String id) {
		CmsCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsCollectionService.get(id);
		}
		if (entity == null){
			entity = new CmsCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsCollection:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsCollection/list"})
	public String list(CmsCollection cmsCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsCollection> page = cmsCollectionService.findPage(new Page<CmsCollection>(request, response), cmsCollection); 
		model.addAttribute("page", page);
		return "modules/cms/cmsCollectionList";
	}

	@RequiresPermissions("cms:cmsCollection:view")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/form")
	public String form(CmsCollection cmsCollection, Model model) {
		model.addAttribute("cmsCollection", cmsCollection);
		return "modules/cms/cmsCollectionForm";
	}

	@RequiresPermissions("cms:cmsCollection:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/save")
	public String save(CmsCollection cmsCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsCollection)){
			return form(cmsCollection, model);
		}
		cmsCollectionService.save(cmsCollection);
		addMessage(redirectAttributes, "保存用户收藏记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsCollection/list?repage";
	}
	
	@RequiresPermissions("cms:cmsCollection:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsCollection/delete")
	public String delete(CmsCollection cmsCollection, RedirectAttributes redirectAttributes) {
		cmsCollectionService.delete(cmsCollection);
		addMessage(redirectAttributes, "删除用户收藏记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsCollection/list?repage";
	}


	/**
	 * 得到某条资讯的分享量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsCollection/getCount")
	@ResponseBody
	public String  getCount(CmsCollection cmsCollection, HttpServletRequest request, HttpServletResponse response){
		try {
			String sourceTable = cmsCollection.getSourceTable();
			String sourceId = cmsCollection.getSourceId();
			if(StringUtils.isBlank(sourceTable)){
				return this.resultFaliureData(request,response, "请先输入sourceTable", "");
			}
			if(StringUtils.isBlank(sourceId)){
				return this.resultFaliureData(request,response, "请先输入sourceId", "");
			}
			cmsCollection  = cmsCollectionDao.getCollectionCount(cmsCollection);
			if(cmsCollection != null && !cmsCollection.equals("")){
				return this.resultSuccessData(request,response, "操作成功", cmsCollection.getCount());
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

	@RequestMapping(value = "${portalPath}/cms/cmsCollection/isOperate")
	@ResponseBody
	public String  isOperate(CmsCollection cmsCollection,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		try {
			String sourceTable = cmsCollection.getSourceTable();
			String sourceId = cmsCollection.getSourceId();
			String userid = cmsCollection.getUserId();
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
			flag = cmsCollectionService.getDynamicSelf(cmsCollection);
//			res.put("result",flag);
			return this.resultSuccessData(request,response, "操作成功", flag);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", flag);
		}

	}

	/**
	 * 点击收藏按钮
	 * @param cmsCollection
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsCollection/doCollection")
	@ResponseBody
	public String doCollection(CmsCollection cmsCollection,HttpServletRequest request,HttpServletResponse response){
		try {
			User user = UserUtils.getUser();
			String sourceId = cmsCollection.getSourceId();
			String sourceTable = cmsCollection.getSourceTable();
			if(StringUtils.isBlank(user.getId())){
				return this.resultFaliureData(request,response, "请先登录", null);
            }
			if(StringUtils.isBlank(sourceTable)){
				return this.resultFaliureData(request,response, "请先输入sourceTable", "");
            }
			if(StringUtils.isBlank(sourceId)){
				return this.resultFaliureData(request,response, "请先输入sourceId", "");
            }
            cmsCollection.setUser(user);
			CmsCollection collection = cmsCollectionDao.getBySource(cmsCollection);
			CmsCollection collection1 = new CmsCollection();
			collection1.setSourceId(sourceId);
			collection1.setSourceTable(sourceTable);
			collection1.setUser(user);
			if(collection == null){
                cmsCollectionService.save(collection1);
                return  this.resultSuccessData(request,response, "点赞成功", null);
            }else{
				collection1.setId(collection.getId());
                cmsCollectionService.delete(collection1);
                return this.resultSuccessData(request,response, "取消点赞成功", null);
            }
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", null);
		}


	}

}