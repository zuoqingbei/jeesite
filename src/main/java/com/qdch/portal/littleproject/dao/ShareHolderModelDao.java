package com.qdch.portal.littleproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.ShareHolderModel;

@MyBatisDao
public interface ShareHolderModelDao extends CrudDao<ShareHolderModelDao>{
public List<ShareHolderModel> getShareHolderModelDao(@Param("jys")String jys);

}
