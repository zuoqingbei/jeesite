package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.EntryAndExitCapitalModel;
@MyBatisDao
public interface EntryAndExitCapitalModelDao extends CrudDao<EntryAndExitCapitalModelDao>{
public List<EntryAndExitCapitalModel> getEntryAndExitCapitalModelDaoDay();
public List<EntryAndExitCapitalModel> getEntryAndExitCapitalModelDaoWeek();
public List<EntryAndExitCapitalModel> getEntryAndExitCapitalModelDaoMonth();
public List<EntryAndExitCapitalModel> getEntryAndExitCapitalModelDaoAll();
}
