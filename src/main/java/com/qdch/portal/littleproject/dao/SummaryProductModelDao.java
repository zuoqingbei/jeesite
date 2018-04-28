package com.qdch.portal.littleproject.dao;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.persistence.annotation.MyBatisDao;
@MyBatisDao
public interface SummaryProductModelDao {
	public List<Map<String,Object>> getProductClassRatio();
	
	public List<Map<String,Object>> getProductDetail(Map<String,Object> param);
	
	public List<Map<String,Object>> getProductClassAndName();
	
	public List<Map<String,Object>> getProductPriceTrend(Map<String,Object> param);
}
