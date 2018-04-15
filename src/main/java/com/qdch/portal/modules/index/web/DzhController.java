package com.qdch.portal.modules.index.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.JdbcUtils;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.cms.entity.CmsBanner;
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsProduct;
import com.qdch.portal.modules.cms.service.CmsBannerService;
import com.qdch.portal.modules.cms.service.CmsProductService;
import com.qdch.portal.modules.sys.entity.Role.RoleTypeEnum;
import com.qdch.portal.modules.sys.entity.Dict;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.security.SecurityAuthorityAnnotation;
import com.qdch.portal.modules.sys.service.DictService;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 
 * @className PortalIndexController.java
 * @time   2018年3月2日 下午8:32:06
 * @author zuoqb
 * @todo   清算所门户PC首页模块
 */
@Controller
public class DzhController extends BaseController {
	@Autowired
	private CmsBannerService cmsBannerService;
	@Autowired
	private CmsProductService cmsProductService;
	@Autowired
	private DictService dictService;
	/*@ModelAttribute
	public CmsProduct get(@RequestParam(required=false) String id) {
		CmsProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsProductService.get(id);
			//entity.setContentHtml(dealHtmls(entity.getContentHtml()));
		}
		if (entity == null){
			entity = new CmsProduct();
		}
		return entity;
	}*/
	/**
	 * @time   2018年3月2日 下午8:34:10
	 * @author zuoqb
	 * @todo   首页
	 * @param  @param model
	 * @param  @param request
	 * @param  @param response
	 * @param  @return
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/company/home"})
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,0);
		request.setAttribute("pageNo", "1");
		request.setAttribute("pageSize", "8");
		Page<CmsProduct> products = cmsProductService.findPage(new Page<CmsProduct>(request, response), new CmsProduct()); 
		model.addAttribute("products", products);
		return render(request, "dzh/index");
	}
	
	@RequestMapping(value = {"${portalPath}/company/about"})
	public String about(Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,4);
		return render(request, "dzh/about");
	}
	@RequestMapping(value = {"${portalPath}/company/productList"})
	public String productList(Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,1);
		setDict(model, "product_type");
		return render(request, "dzh/productList");
	}
	@RequestMapping(value = {"${portalPath}/company/productDetail"})
	public String productDetail(CmsProduct cmsProduct,Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,1);
		model.addAttribute("detail", cmsProduct);
		return render(request, "dzh/productDetail");
	}
	
	@RequestMapping(value = {"${portalPath}/company/caseList"})
	public String caseList(Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,2);
		setDict(model, "customer_type");
		return render(request, "dzh/caseList");
	}
	@RequestMapping(value = {"${portalPath}/company/caseDetail"})
	public String caseDetail(CmsEducation cmsEducation,Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,2);
		model.addAttribute("detail", cmsEducation);
		return render(request, "dzh/caseDetail");
	}
	@RequestMapping(value = {"${portalPath}/company/newsList"})
	public String newsList(Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,3);
		setDict(model, "news_type");
		return render(request, "dzh/newsList");
	}
	@RequestMapping(value = {"${portalPath}/company/newsDetail"})
	public String newsDetail(CmsNews cmsNews,Model model,HttpServletRequest request, HttpServletResponse response){
		setBanner(model,3);
		model.addAttribute("detail", cmsNews);
		return render(request, "dzh/newsDetail");
	}
	/**
	 * type：product_type 表示产品大类
	type：customer_type表示客户案例类型
	type：news_type 表示资讯类型
	 */
	private void setDict(Model model,String type) {
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list= dictService.findList(dict);
		model.addAttribute("dicts", list);
	}
	private void setBanner(Model model,Object index) {
		List<CmsBanner> list = cmsBannerService.findBannerList(0,4);
		model.addAttribute("cmsBanner", list);
		model.addAttribute("index", index);
	}
	
	
	
}
