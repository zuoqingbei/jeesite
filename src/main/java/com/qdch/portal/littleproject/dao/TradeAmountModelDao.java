package com.qdch.portal.littleproject.dao;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.TradeAmountModel;


@MyBatisDao
public interface TradeAmountModelDao extends CrudDao<TradeAmountModelDao>{
	 public List<TradeAmountModel> tradeDay();
	 public List<TradeAmountModel> tradeWeek();
	 public List<TradeAmountModel> tradeMonth();
}
