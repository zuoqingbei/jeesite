package com.qdch.portal.littleproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.RadarModel;
@MyBatisDao
public interface RadarModelDao extends CrudDao<RadarModelDao>{
public List<RadarModel> getRadarModelDao(@Param("jys")String jys);

}
