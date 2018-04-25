package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.CustomerNumberModel;
@MyBatisDao
public interface CustomerNumberModelDao extends CrudDao<CustomerNumberModelDao> {
public List<CustomerNumberModel> getCustomerNumberModelDao();
public List<CustomerNumberModel> getCustomerNumberModelDao2();
public List<CustomerNumberModel> getCustomerNumberModelDao3();
}
