package com.qdch.portal.littleproject.dao;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface DangerModelDao {

    public List<Map<String,Object>> getWeiranDay();//已停用
    
    public List<Map<String,Object>> getFinanceMarket();
    
    public List<Map<String,Object>> getFengxiantongji();
    
    public List<Map<String,Object>> getWeiRanTrend();
    
    public List<Map<String,Object>> getMarketRiskIndex();
    
    public List<Map<String,Object>> getMarketRiskQtyAndRatio(String type);
    
    public List<Map<String,Object>> getMarketRiskEventList(Map<String,Object> map);
    
    public List<Map<String,Object>> getMarketByType(String type);

}
