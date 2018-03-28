/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

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
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.DateUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.utils.UploadUtils;
import com.qdch.portal.modules.cms.entity.CmsComplaint;
import com.qdch.portal.modules.cms.service.CmsComplaintService;
import com.qdch.portal.modules.sys.entity.User;

/**
 * 投诉Controller
 * @author zuoqb
 * @version 2018-03-15
 */
@Controller
public class CmsComplaintController extends BaseController {

	@Autowired
	private CmsComplaintService cmsComplaintService;
	
	@ModelAttribute
	public CmsComplaint get(@RequestParam(required=false) String id) {
		CmsComplaint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsComplaintService.get(id);
		}
		if (entity == null){
			entity = new CmsComplaint();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsComplaint:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsComplaint/list"})
	public String list(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsComplaint> page = cmsComplaintService.findPage(new Page<CmsComplaint>(request, response), cmsComplaint); 
		model.addAttribute("page", page);
		return "modules/cms/cmsComplaintList";
	}

	@RequiresPermissions("cms:cmsComplaint:view")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/form")
	public String form(CmsComplaint cmsComplaint, Model model) {
		model.addAttribute("cmsComplaint", cmsComplaint);
		return "modules/cms/cmsComplaintForm";
	}
	
	@RequiresPermissions("cms:cmsComplaint:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/save")
	public String save(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);
		addMessage(redirectAttributes, "保存投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}
	
	@RequiresPermissions("cms:cmsComplaint:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/delete")
	public String delete(CmsComplaint cmsComplaint, RedirectAttributes redirectAttributes) {
		cmsComplaintService.delete(cmsComplaint);
		addMessage(redirectAttributes, "删除投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}
	
	/**
	 * 
	 * @todo   微信举报
	 * @time   2018年3月27日 下午2:11:45
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/wx/report"})
	public String report(CmsComplaint cmsComplaint,Model model,HttpServletRequest request, HttpServletResponse response){
		String userId=request.getParameter("userId");
		model.addAttribute("cmsComplaint", cmsComplaint);
		model.addAttribute("userId", userId);
		return render(request, "wechat/report");
	}
	@RequestMapping(value = "${portalPath}/wx/saveReport")
	@ResponseBody
	public void saveReport(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		String userId=request.getParameter("userId");
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String images=request.getParameter("images");
		String target=request.getParameter("target");
		String address=request.getParameter("address");
		String date=request.getParameter("date");
		String source=request.getParameter("source");
		/*if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);*/
		cmsComplaint.setCompanyAddress(address);
		cmsComplaint.setCompanyName(target);
		UploadUtils util=new UploadUtils();
		images=util.GenerateImage(images,request);
		cmsComplaint.setImage(images);
		if(StringUtils.isNotBlank(date)){
			cmsComplaint.setFindDate(DateUtils.parseDate(date));
		}
		cmsComplaint.setCreateBy(new User(userId));
		cmsComplaint.setTitle(title);
		cmsComplaint.setContent(description);
		cmsComplaint.setSource(source);
		cmsComplaintService.save(cmsComplaint);
		this.resultSuccessData(request, response, "举报成功", null);
	}
	@RequestMapping(value = {"${portalPath}/cms/cmsComplaint/list"})
	@ResponseBody
	public void cmsComplaintList(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsComplaint> page = cmsComplaintService.findPage(new Page<CmsComplaint>(request, response), cmsComplaint); 
		this.resultSuccessData(request,response, "", mapJson(page,"success","获取数据成功"));
	}

}