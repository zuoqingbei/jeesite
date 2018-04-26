package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.ProductCountModel;
@MyBatisDao
public interface ProductCountModelDao extends CrudDao<ProductCountModelDao>{
public List<ProductCountModel> getProductCountModelDao();
}
