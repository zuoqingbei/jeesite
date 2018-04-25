package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.MarketDynamicModel;
@MyBatisDao
public interface MarketDynamicModelDao extends CrudDao<MarketDynamicModelDao>{
public List<MarketDynamicModel> getMarketDynamicModelDao(int limit);
}
