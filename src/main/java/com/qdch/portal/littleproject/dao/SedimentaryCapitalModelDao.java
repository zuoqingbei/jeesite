package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.SedimentaryCapitalModel;
@MyBatisDao
public interface SedimentaryCapitalModelDao extends CrudDao<SedimentaryCapitalModelDao>{
public  List<SedimentaryCapitalModel> getSedimentaryCapitalModelDaoDay();
public  List<SedimentaryCapitalModel> getSedimentaryCapitalModelDaoWeek();
public  List<SedimentaryCapitalModel> getSedimentaryCapitalModelDaoMonth();
}
