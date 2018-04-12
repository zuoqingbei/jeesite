package com.qdch.portal.thinker.core;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.thinker.api.entity.ThinkerApi;
import com.qdch.portal.thinker.api.service.ThinkerApiService;
import com.qdch.portal.thinker.category.entity.ThinkerCategory;
import com.qdch.portal.thinker.category.service.ThinkerCategoryService;
import com.qdch.portal.thinker.indexs.entity.ThinkerIndex;
import com.qdch.portal.thinker.indexs.service.ThinkerIndexService;
import com.qdch.portal.thinker.reports.entity.ThinkerReports;
import com.qdch.portal.thinker.reports.service.ThinkerReportsService;
import com.qdch.portal.thinker.view.entity.ViewThinker;
import com.qdch.portal.thinker.view.service.ViewThinkerService;

/**
 * 
 * @todo   1169前台接口
 * @time   2018年4月12日 下午9:27:55
 * @author zuoqb
 */
@Controller
public class ThinkerController extends BaseController {
	@Autowired
	private ThinkerCategoryService thinkerCategoryService;//分类
	@Autowired
	private ThinkerIndexService thinkerIndexService;//指标
	@Autowired
	private ThinkerApiService thinkerApiService;//API
	@Autowired
	private ThinkerReportsService thinkerReportsService;//报表
	
	@Autowired
	private ViewThinkerService viewThinkerService;//视图
	
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
	@RequestMapping(value = {"${portalPath}/thinker/home"})
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		return render(request, "thinker/home");
	}
	
	/**
	 * 
	 * @todo   分类
	 * @time   2018年4月12日 下午9:46:32
	 * @author zuoqb
	 * @return_type   List<Map<String,Object>>
	 */
	@ResponseBody
	@RequestMapping(value = "${portalPath}/thinkerCategory/listData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ThinkerCategory> list = thinkerCategoryService.findList(new ThinkerCategory());
		for (int i=0; i<list.size(); i++){
			ThinkerCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	@RequestMapping(value = {"${portalPath}/view/viewThinker/list"})
	@ResponseBody
	public Page<ViewThinker> list(ViewThinker viewThinker, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ViewThinker> page = viewThinkerService.findPage(new Page<ViewThinker>(request, response), viewThinker); 
		return page;
	}
	/**
	 * 
	 * @todo   指标明细
	 * @time   2018年4月12日 下午9:43:46
	 * @author zuoqb
	 * @return_type   ThinkerIndex
	 */
	
	@RequestMapping(value = {"${portalPath}/indexs/detail"})
	@ResponseBody
	public ThinkerIndex indexDetail(@RequestParam(required=false) String id,HttpServletRequest request, HttpServletResponse response) {
		ThinkerIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerIndexService.get(id);
		}
		if (entity == null){
			entity = new ThinkerIndex();
		}
		return entity;
	}
	/**
	 * 
	 * @todo   API明细
	 * @time   2018年4月12日 下午9:46:16
	 * @author zuoqb
	 * @return_type   ThinkerApi
	 */
	@RequestMapping(value = {"${portalPath}/api/detail"})
	@ResponseBody
	public ThinkerApi apiDetail(@RequestParam(required=false) String id,HttpServletRequest request, HttpServletResponse response) {
		ThinkerApi entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerApiService.get(id);
		}
		if (entity == null){
			entity = new ThinkerApi();
		}
		return entity;
	}
	/**
	 * 
	 * @todo   报表明细
	 * @time   2018年4月12日 下午9:46:16
	 * @author zuoqb
	 * @return_type   ThinkerApi
	 */
	@RequestMapping(value = {"${portalPath}/reports/detail"})
	@ResponseBody
	public ThinkerReports reportsDetail(@RequestParam(required=false) String id,HttpServletRequest request, HttpServletResponse response) {
		ThinkerReports entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = thinkerReportsService.get(id);
		}
		if (entity == null){
			entity = new ThinkerReports();
		}
		return entity;
	}
}
