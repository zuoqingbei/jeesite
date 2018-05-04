package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.entity.SummaryProductDto;
import com.qdch.portal.littleproject.service.SummaryProductService;

import org.apache.commons.lang3.StringUtils;
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
			List<Map<String, Object>> classList = summaryProductService
					.getProductClassRatio();
			DynamicDataSource.removeDataSourceKey();
			if (classList == null || classList.size() < 1) {
				return this.resultSuccessData(request, response, "", null);
			}
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> product = null;
			for (Map<String, Object> map : classList) {
				product = new HashMap<String, Object>();
				product.put("name", formatterString(map.get("cpdlinfo")));
				product.put("qty", formatterString(map.get("cpsl")));
				dataList.add(product);
			}
			return this.resultSuccessData(request, response, "", dataList);
		} catch (Exception e) {
			logger.warn("总况-商品类-产品-产品分类及占比", e);
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 产品分类与名称
	 *
	 * @time 2018年4月27日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/productClassAndName" })
	@ResponseBody
	public String getProductClassAndName(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> classList = summaryProductService
					.getProductClassAndName();
			DynamicDataSource.removeDataSourceKey();
			if (classList == null || classList.size() < 1) {
				return this.resultSuccessData(request, response, "", null);
			}
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, List<Map<String,Object>>> product = new LinkedHashMap<String, List<Map<String,Object>>>();
			String className = "";
			String productName = "";
			String classCode = "";
			String productCode = "";
			List<Map<String,Object>> tempList = null;
			Map<String,Object> proMap = null;
			for (Map<String, Object> map : classList) {
				className = formatterString(map.get("cpdlinfo"));
				classCode = formatterString(map.get("cplb"));
				productCode =  formatterString(map.get("cpdm"));
				productName = formatterString(map.get("cpmc"));
				if(StringUtils.isEmpty(className) || StringUtils.isEmpty(productName) || StringUtils.isEmpty(classCode) || StringUtils.isEmpty(productCode)){
					continue;
				}
				className = classCode+"|"+className;
				if (!product.containsKey(className)) {
					tempList = new ArrayList<Map<String,Object>>();
					product.put(className, tempList);
				}
				proMap = new LinkedHashMap<String, Object>();
				proMap.put("productCode", productCode);
				proMap.put("productName", productName);
				product.get(className).add(proMap);
			}
			Map<String, Object> classMap = null;
			String[] classSplit = null;
			for (Entry<String, List<Map<String, Object>>> en : product.entrySet()) {
				classMap = new LinkedHashMap<String, Object>();
				classSplit = en.getKey().split("\\|");
				classCode = classSplit[0];
				className = classSplit[1];
				classMap.put("classCode", classCode);
				classMap.put("className", className);
				classMap.put("productLists", en.getValue());
				dataList.add(classMap);
			}

			return this.resultSuccessData(request, response, "", dataList);
		} catch (Exception e) {
			logger.warn("总况-商品类-产品-产品分类与名称", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 产品详细及价格趋势
	 *
	 * @time 2018年4月27日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/productDetail" })
	@ResponseBody
	public String getProductDetail(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String productClassNo = request.getParameter("productClass");
			String productCode = request.getParameter("productCode");
			List<Map<String, Object>> product = summaryProductService
					.getProductDetail(productClassNo, productCode);
			List<Map<String, Object>> priceTrends = summaryProductService
					.getProductPriceTrend(productClassNo, productCode);
			DynamicDataSource.removeDataSourceKey();
			if (product == null || product.size() < 1) {
				return this.resultSuccessData(request, response, "", null);
			}
			Map<String, Object> prod = product.get(0);
			prod.put("cpmc", formatterString(prod.get("cpmc")));
			prod.put("cpdm", formatterString(prod.get("cpdm")));
			prod.put("ksjyr", formatterString(prod.get("ksjyr")));
			prod.put("csj", formatterString(prod.get("csj")));
			prod.put("rksl", formatterString(prod.get("rksl")));
			prod.put("jdjg", formatterString(prod.get("jdjg")));
			prod.put("ccjg", formatterString(prod.get("ccjg")));
			prod.put("bjfs", formatterString(prod.get("bjfs")));
			prod.put("msdydwl", formatterString(prod.get("msdydwl")));
			prod.put("jyms", formatterString(prod.get("jyms")));
			prod.put("srzdf", formatterString(prod.get("srzdf")));
			prod.put("srhzdf", formatterString(prod.get("srhzdf")));
			List<String> x = new ArrayList<String>();
			List<String> y = new ArrayList<String>();
			if (priceTrends == null || priceTrends.size() < 1) {
				x.add("");
				y.add("");
			} else {
				for (Map<String, Object> map : priceTrends) {
					x.add(formatterString(map.get("vday")));
					y.add(formatterString(map.get("fvalue")));
				}
			}
			SummaryProductDto dto = new SummaryProductDto();
			dto.setProduct(prod);
			dto.setX(x);
			dto.setY(y);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn("总况-商品类-产品-产品详细及价格趋势", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	private String formatterString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}
