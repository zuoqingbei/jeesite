/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.modules.cms.dao.CmsPraiseDao;
import com.qdch.portal.modules.cms.entity.CmsShare;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.utils.UserUtils;
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
import com.qdch.portal.modules.cms.entity.CmsPraise;
import com.qdch.portal.modules.cms.service.CmsPraiseService;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户赞 踩记录Controller
 * @author wangfeng
 * @version 2018-03-15
 */
@Controller
public class CmsPraiseController extends BaseController {

	@Autowired
	private CmsPraiseService cmsPraiseService;

	@Autowired
	private CmsPraiseDao cmsPraiseDao;
	@ModelAttribute
	public CmsPraise get(@RequestParam(required=false) String id) {
		CmsPraise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsPraiseService.get(id);
		}
		if (entity == null){
			entity = new CmsPraise();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsPraise:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsPraise/list"})
	public String list(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsPraise> page = cmsPraiseService.findPage(new Page<CmsPraise>(request, response), cmsPraise); 
		model.addAttribute("page", page);
		return "modules/cms/cmsPraiseList";
	}

	@RequiresPermissions("cms:cmsPraise:view")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/form")
	public String form(CmsPraise cmsPraise, Model model) {
		model.addAttribute("cmsPraise", cmsPraise);
		return "modules/cms/cmsPraiseForm";
	}

	@RequiresPermissions("cms:cmsPraise:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/save")
	public String save(CmsPraise cmsPraise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsPraise)){
			return form(cmsPraise, model);
		}
		cmsPraiseService.save(cmsPraise);
		addMessage(redirectAttributes, "保存用户赞 踩记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPraise/list?repage";
	}
	
	@RequiresPermissions("cms:cmsPraise:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPraise/delete")
	public String delete(CmsPraise cmsPraise, RedirectAttributes redirectAttributes) {
		cmsPraiseService.delete(cmsPraise);
		addMessage(redirectAttributes, "删除用户赞 踩记录成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsPraise/list?repage";
	}


	/**
	 * 得到某条资讯的踩的数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/getTradeCount")
	public void  getTradeCount(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response){
		try {
			String sourceTable = cmsPraise.getSourceTable();
			String sourceId = cmsPraise.getSourceId();
			if(sourceTable==null||sourceTable.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceTable", "");
				return;
			}
			if(sourceId==null||sourceId.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceId", "");
				return;
			}

			CmsPraise cmsPraise1 = cmsPraiseDao.getTradeCount(cmsPraise);
			this.resultSuccessData(request,response, "操作成功", cmsPraise1.getCount());
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作失败", null);
			return;
		}
	}

	/**
	 * 得到某条资讯的赞的数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/getPraiseCount")
	public void  getPraiseCount(CmsPraise cmsPraise, HttpServletRequest request, HttpServletResponse response){
		try {
			String sourceTable = cmsPraise.getSourceTable();
			String sourceId = cmsPraise.getSourceId();
			if(sourceTable==null||sourceTable.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceTable", "");
				return;
			}
			if(sourceId==null||sourceId.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceId", "");
				return;
			}

			cmsPraise.setOperateType("1");//赞
			CmsPraise cmsPraise1 = cmsPraiseDao.getPraiseCount(cmsPraise);
			this.resultSuccessData(request,response, "操作成功", cmsPraise1.getCount());
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作失败", null);
			return;
		}
	}

	/**
	 * 用户是否操作过
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPraise/isOperate")
	public void  isOperate(CmsPraise cmsPraise,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		try {
			String sourceTable = cmsPraise.getSourceTable();
			String sourceId = cmsPraise.getSourceId();
			String userid = cmsPraise.getUserId();
			String operatype = cmsPraise.getOperateType();
			Map<String,Object> res = new HashMap<String, Object>();
			if(sourceTable==null||sourceTable.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceTable", "");
				return;
			}
			if(sourceId==null||sourceId.equals("")){
				this.resultFaliureData(request,response, "请先输入sourceId", "");
				return;
			}
			if(userid==null||userid.equals("")){
				this.resultFaliureData(request,response, "请先输入userid", "");
				return;
			}
			if(operatype==null||operatype.equals("")){
				cmsPraise.setOperateType("1");
			}
			flag = cmsPraiseService.getDynamicSelf(cmsPraise);
			this.resultSuccessData(request,response, "操作成功", flag);
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作失败", null);
			return;
		}
	}

	/**
	 * 点赞按钮
	 * @param praise
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsPraise/addPraise")
	public void addPraise(CmsPraise praise,HttpServletRequest request,HttpServletResponse response){

		try {
			String sourceId =request.getParameter("sourceId");
			String sourceTable = request.getParameter("sourceTable");
			if(StringUtils.isBlank(sourceId)||
                    StringUtils.isBlank(sourceTable)){
                this.resultFaliureData(request,response, "请先输入sourceId和sourceTable", false);
                return ;
            }
			praise.setUser(UserUtils.getUser());
			cmsPraiseService.save(praise);

			this.resultSuccessData(request,response, "操作成功", true);
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultFaliureData(request,response, "操作失败", false);
			return ;
		}
	}

}