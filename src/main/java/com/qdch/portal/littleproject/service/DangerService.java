package com.qdch.portal.littleproject.service;

import java.util.List;
import java.util.Map;

import com.qdch.portal.littleproject.dao.DangerModelDao;
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
	public DangerModelDao dangerModelDao;

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


}