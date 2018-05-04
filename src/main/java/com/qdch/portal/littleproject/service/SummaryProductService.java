package com.qdch.portal.littleproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.service.BaseService;
import com.qdch.portal.littleproject.dao.SummaryProductModelDao;

/**
 * 风险 Service
 * @author wangsw
 * @version 2018-04-26
 */
@Service
@Transactional(readOnly = true)
public class SummaryProductService extends BaseService{
	@Autowired
	private SummaryProductModelDao summaryProductModelDao;

	public List<Map<String,Object>> getProductClassRatio(){
		return  summaryProductModelDao.getProductClassRatio();
	}
	
	public List<Map<String,Object>> getProductClassAndName(){
		return  summaryProductModelDao.getProductClassAndName();
	}
	
	public List<Map<String,Object>> getProductDetail(String productClassNo,String productCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("productClassNo", productClassNo);
		param.put("productCode", productCode);
		return  summaryProductModelDao.getProductDetail(param);
	}
	
	public List<Map<String,Object>> getProductPriceTrend(String productClassNo,String productCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("productClassNo", productClassNo);
		param.put("productCode", productCode);
		return  summaryProductModelDao.getProductPriceTrend(param);
	}
	
}