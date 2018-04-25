package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.ShareHolderModel;

@MyBatisDao
public interface ShareHolderModelDao extends CrudDao<ShareHolderModelDao>{
public List<ShareHolderModel> getShareHolderModelDao();
public List<ShareHolderModel> getShareHolderModelDao2();
public List<ShareHolderModel> getShareHolderModelDao3();
}
