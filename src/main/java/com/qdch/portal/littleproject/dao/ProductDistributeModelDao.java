package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.ProductDistributeModel;

@MyBatisDao
public interface ProductDistributeModelDao extends CrudDao<ProductDistributeModelDao> {
public List<ProductDistributeModel> getProductDistributeModelDao();
}
