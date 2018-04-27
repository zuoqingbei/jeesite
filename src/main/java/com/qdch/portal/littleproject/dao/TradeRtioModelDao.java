package com.qdch.portal.littleproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.TradeRtioModel;

@MyBatisDao
public interface TradeRtioModelDao extends CrudDao<TradeRtioModelDao>{
public List<TradeRtioModel> getTradeRtioModelDao();//查询全部
public List<TradeRtioModel> getTradeRtioModelDao2(@Param("cpdlinfo")String cpdlinfo);//有条件查询
}
