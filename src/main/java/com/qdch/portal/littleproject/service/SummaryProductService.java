package com.qdch.portal.littleproject.service;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.service.BaseService;
import com.qdch.portal.littleproject.dao.SummaryProductModelDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}