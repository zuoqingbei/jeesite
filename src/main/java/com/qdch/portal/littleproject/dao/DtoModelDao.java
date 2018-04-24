package com.qdch.portal.littleproject.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.DtoModel;


import java.util.List;

@MyBatisDao
public interface DtoModelDao extends CrudDao<DtoModel>{

    public List<DtoModel> getTest();

}

