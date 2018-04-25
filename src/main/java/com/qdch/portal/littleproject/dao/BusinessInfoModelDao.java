package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.BusinessInfoModel;
@MyBatisDao
public interface BusinessInfoModelDao extends CrudDao<BusinessInfoModelDao>{
public List<BusinessInfoModel> getBusinessInfoModelDao();
public List<BusinessInfoModel> getBusinessInfoModelDao2();
public List<BusinessInfoModel> getBusinessInfoModelDao3();
}
