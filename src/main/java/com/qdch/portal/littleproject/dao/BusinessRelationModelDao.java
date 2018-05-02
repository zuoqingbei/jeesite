package com.qdch.portal.littleproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.BusinessRelationModel;
@MyBatisDao
public interface BusinessRelationModelDao extends CrudDao<BusinessRelationModelDao>{
public List<BusinessRelationModel> getBusinessRelationModelDao(@Param("jys")String jys);
}

