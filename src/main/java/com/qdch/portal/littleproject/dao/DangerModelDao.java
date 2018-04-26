package com.qdch.portal.littleproject.dao;

import java.util.List;
import java.util.Map;

import com.qdch.portal.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface DangerModelDao {

    public List<Map<String,Object>> getWeiranDay();//已停用
    
    public List<Map<String,Object>> getFinanceMarket();//已停用
    
    public List<Map<String,Object>> getFengxiantongji();
    
    public List<Map<String,Object>> getWeiRanTrend();

}
