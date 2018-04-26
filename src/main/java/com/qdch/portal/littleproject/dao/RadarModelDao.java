package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.RadarModel;
@MyBatisDao
public interface RadarModelDao extends CrudDao<RadarModelDao>{
public List<RadarModel> getRadarModelDao();
public List<RadarModel> getRadarModelDao2();
public List<RadarModel> getRadarModelDao3();
}
