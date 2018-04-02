/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.qdch.portal.modules.cms.dao.CmsPortalCommentsDao;
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsPortalComments;
import com.qdch.portal.modules.cms.entity.CmsQuestionAnswer;
import com.qdch.portal.modules.cms.service.CmsEducationService;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import com.qdch.portal.modules.cms.service.CmsPortalCommentsService;
import com.qdch.portal.modules.cms.service.CmsQuestionAnswerService;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 门户评论Controller
 * @author wangfeng
 * @version 2018-03-20
 */
@Controller
public class CmsPortalCommentsController extends BaseController {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	@Autowired
	private CmsPortalCommentsService cmsPortalCommentsService;

	@Autowired
	private CmsPortalCommentsDao cmsPortalCommentsDao;

	@Autowired
	private CmsNewsService cmsNewsService;

	@Autowired
	private CmsEducationService cmsEducationService;
	@Autowired
	private CmsQuestionAnswerService cmsQuestionAnswerService;

	@ModelAttribute
	public CmsPortalComments get(@RequestParam(required = false) String id) {
		CmsPortalComments entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cmsPortalCommentsService.get(id);
		}
		if (entity == null) {
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
		if (!beanValidator(model, cmsPortalComments)) {
			return form(cmsPortalComments, model);
		}
//		cmsPortalComments.setIp(getServerIp(re));
		addMessage(redirectAttributes, "保存门户评论成功");
		return "redirect:" + Global.getAdminPath() + "/cms/cmsPortalComments/list?repage";
	}

	@RequiresPermissions("cms:cmsPortalComments:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/delete")
	public String delete(CmsPortalComments cmsPortalComments, RedirectAttributes redirectAttributes) {
		cmsPortalCommentsService.delete(cmsPortalComments);
		addMessage(redirectAttributes, "删除门户评论成功");
		return "redirect:" + Global.getAdminPath() + "/cms/cmsPortalComments/list?repage";
	}


	/**
	 * 得到某条资讯的评论数量
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getCount")
	@ResponseBody
	public String getCount(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		try {
			count = cmsPortalCommentsDao.getPortalCommentsCount(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败", count);
		}
		return this.resultSuccessData(request, response, "操作成功", count);
	}

	/**
	 * 用户是否操作过
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/isOperate")
	@ResponseBody
	public String isOperate(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		try {
			flag = cmsPortalCommentsService.getDynamicSelf(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败", flag);
		}
		return this.resultSuccessData(request, response, "操作成功", flag);
	}

	/**
	 * 保存评论
	 *
	 * @param cmsPortalComments
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/saveData")
	@ResponseBody
	public String saveData(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		try {

			if (StringUtils.isBlank(cmsPortalComments.getSourceId()) ||
					StringUtils.isBlank(cmsPortalComments.getSourceTable()) ||
					StringUtils.isBlank(cmsPortalComments.getContent())) {
				return this.resultFaliureData(request, response, "请先输入信息sourceId、sourceTable、content", null);
			}
			boolean flag = IsAllowComments(cmsPortalComments);
			if (flag == false) {
				return this.resultFaliureData(request, response, "该条记录设置了不允许评论", null);
			}
			String parentID = cmsPortalComments.getParentId();
			CmsPortalComments parent = null;
			if (StringUtils.isBlank(parentID)) {
				parent = new CmsPortalComments();
				parent.setId("-1");
				cmsPortalComments.setParent(parent);
				cmsPortalComments.setParentIds("-1,");
			} else {
				parent = get(parentID);
				cmsPortalComments.setParent(parent);
				cmsPortalComments.setParentIds(parent.getParentIds() + parent.getId() + ",");
			}
			cmsPortalComments.setIp(getServerIp(request));
			cmsPortalComments.setUser(UserUtils.getUser());
			cmsPortalCommentsService.save(cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败", null);
		}
		return this.resultSuccessData(request, response, "操作成功", null);
	}

	/**
	 * 获得某个文章的 评论
	 *
	 * @param cmsPortalComments
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getCommentsBySource")
	@ResponseBody
	public String getCommentsBySource(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		Page<CmsPortalComments> page = null;
		try {
			page = cmsPortalCommentsService.getCommentsBySource(
					new Page<CmsPortalComments>(request, response), cmsPortalComments);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败",
					null);
		}
		return this.resultSuccessData(request, response, "操作成功",
				mapJson(page, "success", "获取数据成功"));
	}

	/**
	 * 获得某个文章的 评论和赞数
	 *
	 * @param cmsPortalComments
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getCommentsAndPraise")
	@ResponseBody
	public String getCommentsAndPraise(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		Page<CmsPortalComments> page = null;
		try {
			if (StringUtils.isBlank(cmsPortalComments.getSourceId())) {
				return this.resultSuccessData(request, response, "请输入资讯的id",
						"false");
			}
			if (StringUtils.isBlank(cmsPortalComments.getSourceTable())) {
				cmsPortalComments.setSourceTable("cms_news");
			}

			boolean flag = IsAllowComments(cmsPortalComments);
			if (flag == false) {
				return this.resultFaliureData(request, response, "该条记录设置了不允许评论", null);
			}
			page = cmsPortalCommentsService.getCommentsAndPraise(
					new Page<CmsPortalComments>(request, response), cmsPortalComments);
			return this.resultSuccessData(request, response, "操作成功",
					mapJson(page, "success", "获取数据成功"));
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败",
					null);
		}

	}

	/**
	 * 获得某个文章的 热门评论
	 *
	 * @param cmsPortalComments
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "${portalPath}/cms/cmsPortalComments/getHotComments")
	@ResponseBody
	public String getHotComments(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		List<CmsPortalComments> list = null;
		try {
			if (StringUtils.isBlank(cmsPortalComments.getSourceId())) {
				return this.resultSuccessData(request, response, "请输入资讯的id",
						"false");
			}
			if (StringUtils.isBlank(cmsPortalComments.getSourceTable())) {
				cmsPortalComments.setSourceTable("cms_news");
			}

			list = cmsPortalCommentsService.getHotComments(cmsPortalComments);
			return this.resultSuccessData(request, response, "操作成功",
					list);
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "操作失败",
					null);
		}

	}


	/**
	 * 获取服务器IP地址
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getServerIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				//根据网卡获取本机配置的IP地址
				InetAddress inetAddress = null;
				try {
					inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inetAddress.getHostAddress();
			}
		}

		//对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
		if (null != ipAddress && ipAddress.length() > 15) {
			//"***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;

	}

	@RequestMapping(value = "${adminPath}/cms/cmsPortalComments/changeState")
	public void changeState(CmsPortalComments cmsPortalComments, HttpServletRequest request, HttpServletResponse response) {
		try {

			cmsPortalComments.setAuditUserId(UserUtils.getUser().getUserId());
			cmsPortalComments.setAuditDate(new Date());
			cmsPortalCommentsDao.changeState(cmsPortalComments);
			this.resultSuccessData(request, response, "修改成功", true);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultSuccessData(request, response, "修改失败", false);
			return;
		}
	}

	/**
	 * 看是否设置了允许评论
	 *
	 * @param cmsPortalComments
	 * @return
	 */

	public boolean IsAllowComments(CmsPortalComments cmsPortalComments) {
		boolean flag = true;
		if (cmsPortalComments.getSourceTable().equals("cms_news")) {
			CmsNews cmsNews = cmsNewsService.get(cmsPortalComments.getSourceId());
			if (cmsNews.getAllowComment().equals("1")) {
				flag = false;
			}
		} else if (cmsPortalComments.getSourceTable().equals("cms_education")) {
			CmsEducation cmsEducation = cmsEducationService.get(cmsPortalComments.getSourceId());
			if (cmsEducation.getAllowComment().equals("1")) {
				flag = false;
			}

		} else if (cmsPortalComments.getSourceTable().equals("cms_question_answer")) {
			CmsQuestionAnswer cmsQuestionAnswer = cmsQuestionAnswerService.get(cmsPortalComments.getSourceId());
			if (cmsQuestionAnswer.getAllowComment().equals("1")) {
				flag = false;
			}

		}
		return flag;

	}
}