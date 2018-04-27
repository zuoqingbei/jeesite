package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.service.SummaryProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 总况-商品类-产品
 * 
 * @author wangsw
 * @time 2018年4月26日
 */
@Controller
public class SummaryProductController extends BaseController {
	@Autowired
	private SummaryProductService summaryProductService;
	
	/**
	 * 产品分类及占比
	 *
	 * @time 2018年4月26日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/productClassRatio" })
	@ResponseBody
	public String getProductClassRadtio(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> classList = summaryProductService.getProductClassRatio();
			DynamicDataSource.removeDataSourceKey();
			if (classList == null || classList.size() < 1) {
				return this.resultFaliureData(request, response, "", null);
			}
			List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> product = null;
			for (Map<String, Object> map : classList) {
				product = new HashMap<String, Object>();
				product.put("name", formatterString(map.get("cpdlinfo")));
				product.put("qty", formatterString(map.get("cpsl")));
				dataList.add(product);
			}
			return this.resultSuccessData(request, response, "", dataList);
		} catch (Exception e) {
			logger.warn("总况-商品类-产品-产品分类及占比",e);
			return this.resultFaliureData(request, response, "", null);
		}

	}

	private String formatterString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	
	
}
