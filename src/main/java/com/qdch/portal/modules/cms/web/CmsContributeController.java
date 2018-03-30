/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsContribute;
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsNewsData;
import com.qdch.portal.modules.cms.entity.CmsQuestionAnswer;
import com.qdch.portal.modules.cms.service.CmsContributeService;
import com.qdch.portal.modules.cms.service.CmsEducationService;
import com.qdch.portal.modules.cms.service.CmsNewsDataService;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import com.qdch.portal.modules.cms.service.CmsQuestionAnswerService;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 用户投稿Controller
 * @author wangfeng
 * @version 2018-03-13
 */
@Controller
//@RequestMapping(value = "${adminPath}/cms/cmsContribute")
public class CmsContributeController extends BaseController {

	@Autowired
	private CmsContributeService cmsContributeService;

	@Autowired
	private CmsNewsService cmsNewsService;

	@Autowired
	private CmsNewsDataService cmsNewsDataService;

	@Autowired
	private CmsEducationService cmsEducationService;

	@Autowired
	private CmsQuestionAnswerService cmsQuestionAnswerService;

	
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
	
	@RequiresPermissions("cms:cmsContribute:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsContribute/list", ""})
	public String list(CmsContribute cmsContribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsContribute> page = cmsContributeService.findPage(new Page<CmsContribute>(request, response), cmsContribute); 
		model.addAttribute("page", page);
		return "modules/cms/cmsContributeList";
	}

	@RequiresPermissions("cms:cmsContribute:view")
	@RequestMapping(value = "${adminPath}/cms/cmsContribute/form")
	public String form(CmsContribute cmsContribute, Model model) {


		model.addAttribute("cmsContribute", cmsContribute);
		return "modules/cms/cmsContributeForm";
	}

	@RequiresPermissions("cms:cmsContribute:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsContribute/save")
	public String save(CmsContribute cmsContribute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsContribute)){
			return form(cmsContribute, model);
		}
		cmsContributeService.save(cmsContribute);
		addMessage(redirectAttributes, "保存用户投稿成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsContribute/list?repage";
	}
	
	@RequiresPermissions("cms:cmsContribute:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsContribute/delete")
	public String delete(CmsContribute cmsContribute, RedirectAttributes redirectAttributes) {
		cmsContributeService.delete(cmsContribute);
		addMessage(redirectAttributes, "删除用户投稿成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsContribute/list?repage";
	}
	
	
	/**
	 * 改变投稿状态
	 * @param cmsContribute
	 * @param
	 * @return
	 */

//	@RequiresPermissions("cms:cmsContribute:edit")
    @Transactional(readOnly = false)
	@RequestMapping(value = "${adminPath}/cms/cmsContribute/changeState")
	public void  changeState(CmsContribute cmsContribute, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(StringUtils.isBlank(request.getParameter("id"))){
				this.resultFaliureData(request,response, "请输入要修改的投稿的id", null);
				return;
			}
			CmsContribute cmsContribute1 = cmsContributeService.get(cmsContribute.getId());
            if(!cmsContribute1.getStatus().equals("1")){
                this.resultFaliureData(request,response, "只有已投稿未审核状态的才可以进行审核", null);
                return;
            }
//			if(cmsContribute1.getStatus().equals("2")){
//				this.resultFaliureData(request,response, "该投稿已经审核通过了，不能再审核", null);
//				return;
//			}
			if(StringUtils.isBlank(request.getParameter("status"))){
				this.resultFaliureData(request,response, "请输入要修改的投稿的状态status", null);
				return;
			}

			cmsContributeService.changeState(cmsContribute);
			//如果是审核通过，则加到news表和news_data表中
			if(cmsContribute.getStatus().equals("2")){
				if(cmsContribute.getDataType().equals("0")){ // 投稿类型 0-资讯 1-案例 2-投资教育 3-问答
					CmsNews cmsNews = new CmsNews();
					CmsNewsData cmsNewsData = new CmsNewsData();
					cmsNews.setLink(cmsContribute.getId());
					cmsNews.setDataType("1");//用户投稿
					cmsNews.setUser(cmsContribute.getUser());
					cmsNews.setImage(cmsContribute.getImage());
					cmsNews.setKeywords(cmsContribute.getKeywords());
					cmsNews.setTags(cmsContribute.getTags());
					cmsNews.setDescription(cmsContribute.getDescription());
					cmsNews.setCreateDate(cmsContribute.getCreateDate());
					cmsNews.setUpdateBy(cmsContribute.getUpdateBy());
					cmsNews.setUpdateDate(cmsContribute.getUpdateDate());
					cmsNews.setCreateBy(cmsContribute.getCreateBy());
					cmsNews.setRemarks(cmsContribute.getRemarks());
					cmsNews.setCategory1("");
					cmsNews.setTitle(cmsContribute.getTitle());
					cmsNewsService.save(cmsNews);

					//保存cmsData表
					String newsid = "";
					cmsNews = cmsNewsService.getByLinkId(cmsNews);
					newsid = cmsNews.getId();
					CmsNewsData cmsNewsData1 = new CmsNewsData();
					cmsNewsData1.setNewsId(newsid);
					cmsNewsData1.setContent(cmsContribute.getContent());
					cmsNewsData1.setContentHtml(cmsContribute.getContentHtml());
					cmsNewsDataService.save(cmsNewsData1);
				}else if(cmsContribute.getDataType().equals("1")){ //案例
					saveSame(cmsContribute,"1");
				}else if(cmsContribute.getDataType().equals("2")){  //-投资教育
					saveSame(cmsContribute,"0");
				}else if(cmsContribute.getDataType().equals("3")){ //问答
                    saveQuestoinAndAnswer(cmsContribute,"");
				}
			}

			this.resultSuccessData(request,response, "修改成功", null);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultFaliureData(request,response, "修改失败", null);
			return;
		}
	}

    /**
     * 获得用户投稿
     * @param cmsContribute
     * @param
     * @return
     */
    @RequestMapping(value = "${portalPath}/cms/cmsContribute/getUserContribute",method = RequestMethod.GET)
    @ResponseBody
    public String  getUserContribute(CmsContribute cmsContribute, HttpServletRequest request,  HttpServletResponse response) {
        try {
        	String userId = request.getParameter("userId");
        	if(userId == null || userId.equals("")){
        		return this.resultFaliureData(request,response, "请输入userId", null);
			}
			cmsContribute.setUser(UserUtils.get(userId));
            Page<CmsContribute> cmsContribute1 = cmsContributeService.getUserContribute(new Page<CmsContribute>(request, response),cmsContribute);
            return this.resultSuccessData(request,response, "获取数据成功", cmsContribute1);
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response, "操作失败", null);
        }

    }

	/**
	 * 前台-保存
	 * @param
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsContribute/saveData")
	@ResponseBody
	public String  saveData(CmsContribute cmsContribute, Model model, HttpServletRequest request,HttpServletResponse response) {
		try {
			if(StringUtils.isBlank(cmsContribute.getTitle())){
				return this.resultFaliureData(request,response, "请先输入标题", null);
			}
			if(StringUtils.isBlank(cmsContribute.getDataType())){
				return this.resultFaliureData(request,response, "请先选择投稿类型", null);
			}
			cmsContribute.setContent(cmsContribute.getContentHtml());
			cmsContribute.setStatus("0"); //保存后默认是草稿状态
			cmsContributeService.save(cmsContribute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.resultFaliureData(request,response, "保存数据失败", null);
		}
		return this.resultSuccessData(request,response, "保存数据成功", null);

	}


	/**
	 * 前台-投稿
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsContribute/contribute")
	@ResponseBody
	public String contribute(CmsContribute contribute,HttpServletRequest request,HttpServletResponse response){
//		String status = request.getParameter("status");
		try {
			if(contribute.getId()==null ||contribute.getId().equals("")){
				return this.resultFaliureData(request,response, "请输入要修改的投稿的id", null);
            }
			CmsContribute cmsContribute = cmsContributeService.get(contribute.getId());
			if(!cmsContribute.getStatus().equals("0")){
				return this.resultFaliureData(request,response, "只有草稿状态才可以投稿", null);
            }
            cmsContribute.setStatus("1");
			cmsContributeService.changeState(cmsContribute);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request,response, "操作失败", null);
		}
		return this.resultSuccessData(request,response, "操作成功", null);

	}

	public void saveSame(CmsContribute cmsContribute, String catalog){
		CmsEducation cmsEducation =new CmsEducation();
		CmsNewsData cmsNewsData = new CmsNewsData();
		cmsEducation.setLink(cmsContribute.getId());
		cmsEducation.setDataType("1");//用户投稿
		cmsEducation.setUser(cmsContribute.getUser());
		cmsEducation.setImage(cmsContribute.getImage());
		cmsEducation.setKeywords(cmsContribute.getKeywords());
		cmsEducation.setTags(cmsContribute.getTags());
		cmsEducation.setDescription(cmsContribute.getDescription());
		cmsEducation.setCreateDate(cmsContribute.getCreateDate());
		cmsEducation.setUpdateBy(cmsContribute.getUpdateBy());
		cmsEducation.setUpdateDate(cmsContribute.getUpdateDate());
		cmsEducation.setCreateBy(cmsContribute.getCreateBy());
		cmsEducation.setRemarks(cmsContribute.getRemarks());
		cmsEducation.setCategory1(catalog);  //一级分类0-投资教育 1-案例 2-政策解读 3-攻略
		cmsEducation.setTitle(cmsContribute.getTitle());
		cmsEducation.setContentHtml(cmsContribute.getContentHtml());
		cmsEducation.setContent(StringUtils.replaceMobileHtml(cmsContribute.getContentHtml()));
		cmsEducationService.save(cmsEducation);
	}

    public void saveQuestoinAndAnswer(CmsContribute cmsContribute, String catalog){
        CmsQuestionAnswer questionAnswer = new CmsQuestionAnswer();
        questionAnswer.setLink(cmsContribute.getId());
        questionAnswer.setDataType("1");//用户投稿
        questionAnswer.setUser(cmsContribute.getUser());
        questionAnswer.setImage(cmsContribute.getImage());
        questionAnswer.setKeywords(cmsContribute.getKeywords());
        questionAnswer.setTags(cmsContribute.getTags());
        questionAnswer.setDescription(cmsContribute.getDescription());
        questionAnswer.setCreateDate(cmsContribute.getCreateDate());
        questionAnswer.setUpdateBy(cmsContribute.getUpdateBy());
        questionAnswer.setUpdateDate(cmsContribute.getUpdateDate());
        questionAnswer.setCreateBy(cmsContribute.getCreateBy());
        questionAnswer.setRemarks(cmsContribute.getRemarks());
        questionAnswer.setCategory1(catalog);  //一级分类0-投资教育 1-案例 2-政策解读 3-攻略
        questionAnswer.setTitle(cmsContribute.getTitle());
        questionAnswer.setContentHtml(cmsContribute.getContentHtml());
        questionAnswer.setContent(StringUtils.replaceMobileHtml(cmsContribute.getContentHtml()));
        cmsQuestionAnswerService.save(questionAnswer);
    }



}
