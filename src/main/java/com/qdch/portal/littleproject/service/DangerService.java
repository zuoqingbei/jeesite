package com.qdch.portal.littleproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qdch.portal.littleproject.dao.DangerModelDao;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 风险 Service
 * @author wangsw
 * @version 2018-04-24
 */
@Service
@Transactional(readOnly = true)
public class DangerService {
	@Autowired
	private DangerModelDao dangerModelDao;

	public List<Map<String,Object>> getWeiranDay(){
		return  dangerModelDao.getWeiranDay();
	}

	public List<Map<String,Object>> getFinanceMarket(){
		return  dangerModelDao.getFinanceMarket();
	}

	public List<Map<String,Object>> getFengxiantongji(){
		return  dangerModelDao.getFengxiantongji();
	}
	
	public List<Map<String,Object>> getWeiRanTrend(){
		return  dangerModelDao.getWeiRanTrend();
	}
	
	public List<Map<String,Object>> getMarketRiskIndex(){
		return  dangerModelDao.getMarketRiskIndex();
	}
	
	public List<Map<String,Object>> getMarketRiskQtyAndRatio(String type){
		return  dangerModelDao.getMarketRiskQtyAndRatio(type);
	}
	
	public List<Map<String,Object>> getMarketRiskEventList(String type,String risk,String market){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("riskType", type);
		map.put("riskName", risk);
		map.put("jys", market);
		return  dangerModelDao.getMarketRiskEventList(map);
	}
	
	public List<Map<String,Object>> getMarketByType(String type){
		return  dangerModelDao.getMarketByType(type);
	}
	


}