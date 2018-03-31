/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

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
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsProduct;
import com.qdch.portal.modules.cms.service.CmsProductService;
import com.qdch.portal.modules.sys.entity.Dict;
import com.qdch.portal.modules.sys.service.DictService;

/**
 * 产品管理Controller
 * @author zuoqb
 * @version 2018-03-31
 */
@Controller
public class CmsProductController extends BaseController {

	@Autowired
	private CmsProductService cmsProductService;
	@Autowired
	private DictService dictService;
	@ModelAttribute
	public CmsProduct get(@RequestParam(required=false) String id) {
		CmsProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsProductService.get(id);
		}
		if (entity == null){
			entity = new CmsProduct();
		}
		return entity;
	}
	
	/*@RequiresPermissions("cms:cmsProduct:view")*/
	@RequestMapping(value = {"${adminPath}/cms/cmsProduct/list"})
	public String list(CmsProduct cmsProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsProduct> page = cmsProductService.findPage(new Page<CmsProduct>(request, response), cmsProduct); 
		model.addAttribute("page", page);
		return "modules/cms/cmsProductList";
	}

	@RequiresPermissions("cms:cmsProduct:view")
	@RequestMapping(value = "${adminPath}/cms/cmsProduct/form")
	public String form(CmsProduct cmsProduct, Model model) {
		model.addAttribute("cmsProduct", cmsProduct);
		return "modules/cms/cmsProductForm";
	}

	@RequiresPermissions("cms:cmsProduct:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsProduct/save")
	public String save(CmsProduct cmsProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsProduct)){
			return form(cmsProduct, model);
		}
		cmsProductService.save(cmsProduct);
		addMessage(redirectAttributes, "保存产品管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsProduct/list?repage";
	}
	
	@RequiresPermissions("cms:cmsProduct:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsProduct/delete")
	public String delete(CmsProduct cmsProduct, RedirectAttributes redirectAttributes) {
		cmsProductService.delete(cmsProduct);
		addMessage(redirectAttributes, "删除产品管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsProduct/list?repage";
	}
	
	/**
	 * 
	 * @todo   获取产品数据列表
	 * @time   2018年3月31日 下午4:50:20
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsProduct/getList")
	@ResponseBody
	public String listData(CmsProduct cmsProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsProduct> page = cmsProductService.findPage(new Page<CmsProduct>(request, response), cmsProduct); 
		return this.resultSuccessData(request,response, "获取数据成功",
				mapJson(page,"success","获取数据成功"));
	}
	
	/**
	 * 
	 * @todo   获取产品明细
	 * @time   2018年3月31日 下午4:50:20
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = "${portalPath}/cms/cmsProduct/detail")
	@ResponseBody
	public String detail(CmsProduct cmsProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		return this.resultSuccessData(request, response, "获取明细数据成功", cmsProduct);
	}
	
	/**
	 * 
	 * @todo   获取客户案例类型 产品类型
	 * @time   2018年3月31日 下午4:55:37
	 * @author zuoqb
	 * @return_type   product_type customer_type
	 */
	@ResponseBody
	@RequestMapping(value = "${portalPath}/cms/system/dict")
	public String dict(@RequestParam(required=false) String type,HttpServletRequest request, HttpServletResponse response) {
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list= dictService.findList(dict);
		return this.resultSuccessData(request, response, "获取字典数据成功", list);
	}
}