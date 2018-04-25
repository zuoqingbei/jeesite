package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.ProductTrendModel;
@MyBatisDao
public interface ProductTrendModelDao extends CrudDao<ProductTrendModelDao>{
public List<ProductTrendModel> getProductTrendModelDao();
public List<ProductTrendModel> getProduct();
}
