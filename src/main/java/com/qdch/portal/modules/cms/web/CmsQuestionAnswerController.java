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
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsQuestionAnswer;
import com.qdch.portal.modules.cms.service.CmsQuestionAnswerService;

/**
 * 问答表Controller
 * @author wangfeng
 * @version 2018-03-27
 */
@Controller
public class CmsQuestionAnswerController extends BaseController {

	@Autowired
	private CmsQuestionAnswerService cmsQuestionAnswerService;
	
	@ModelAttribute
	public CmsQuestionAnswer get(@RequestParam(required=false) String id) {
		CmsQuestionAnswer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsQuestionAnswerService.get(id);
		}
		if (entity == null){
			entity = new CmsQuestionAnswer();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsQuestionAnswer:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsQuestionAnswer/list"})
	public String list(CmsQuestionAnswer cmsQuestionAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsQuestionAnswer> page = cmsQuestionAnswerService.findPage(new Page<CmsQuestionAnswer>(request, response), cmsQuestionAnswer); 
		model.addAttribute("page", page);
		return "modules/cms/cmsQuestionAnswerList";
	}

	@RequiresPermissions("cms:cmsQuestionAnswer:view")
	@RequestMapping(value = "${adminPath}/cms/cmsQuestionAnswer/form")
	public String form(CmsQuestionAnswer cmsQuestionAnswer, Model model) {
		model.addAttribute("cmsQuestionAnswer", cmsQuestionAnswer);
		return "modules/cms/cmsQuestionAnswerForm";
	}

	@RequiresPermissions("cms:cmsQuestionAnswer:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsQuestionAnswer/save")
	public String save(CmsQuestionAnswer cmsQuestionAnswer, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, cmsQuestionAnswer)){
                return form(cmsQuestionAnswer, model);
            }
			cmsQuestionAnswer.setDataType("2"); //管理人员发布
			if(StringUtils.isBlank(cmsQuestionAnswer.getWeight())){
                cmsQuestionAnswer.setWeight("0");
            }

			if(StringUtils.isBlank(cmsQuestionAnswer.getRecommend())){
				cmsQuestionAnswer.setRecommend("0"); //不推荐
			}
			if(StringUtils.isBlank(cmsQuestionAnswer.getAllowComment())){
				cmsQuestionAnswer.setAllowComment("0"); //允许评论
			}
			if(StringUtils.isBlank(cmsQuestionAnswer.getUndercarriage())){
				cmsQuestionAnswer.setUndercarriage("0"); //未下架
			}
			if(StringUtils.isBlank(cmsQuestionAnswer.getCommentAudit())){
				cmsQuestionAnswer.setCommentAudit("1");//评论不需要审核
			}
			if(StringUtils.isBlank(cmsQuestionAnswer.getAllowReport())){
				cmsQuestionAnswer.setAllowReport("0"); //允许举报
			}
            cmsQuestionAnswer.setContent(StringUtils.replaceMobileHtml(cmsQuestionAnswer.getContentHtml()));
			cmsQuestionAnswerService.save(cmsQuestionAnswer);
			addMessage(redirectAttributes, "保存问答表成功");
			return "redirect:"+Global.getAdminPath()+"/cms/cmsQuestionAnswer/list?repage";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequiresPermissions("cms:cmsQuestionAnswer:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsQuestionAnswer/delete")
	public String delete(CmsQuestionAnswer cmsQuestionAnswer, RedirectAttributes redirectAttributes) {
		cmsQuestionAnswerService.delete(cmsQuestionAnswer);
		addMessage(redirectAttributes, "删除问答表成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsQuestionAnswer/list?repage";
	}


	/**
	 * 问答列表 标签-排行
	 * @param questionAnswer
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsQuestionAnswer/getRank")
	@ResponseBody
	public String getRank(CmsQuestionAnswer questionAnswer,HttpServletRequest request,HttpServletResponse response){
		try {
			Page<CmsQuestionAnswer> page = cmsQuestionAnswerService.getRank(new Page<CmsQuestionAnswer>(request, response), questionAnswer);
			return this.resultSuccessData(request,response, "获取数据成功",
                    mapJson(page,"success","获取数据成功"));

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", null);
		}

	}

}