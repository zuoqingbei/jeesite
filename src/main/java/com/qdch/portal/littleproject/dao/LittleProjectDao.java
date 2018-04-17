package com.qdch.portal.littleproject.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.sys.entity.User;

import java.util.List;

@MyBatisDao
public interface LittleProjectDao extends CrudDao<TradeModel> {

    public TradeModel getaa ();


}
