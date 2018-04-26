package com.qdch.portal.littleproject.dao;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.persistence.annotation.MyBatisDao;
@MyBatisDao
public interface SummaryProductModelDao {
	public List<Map<String,Object>> getProductClassRatio();
}
