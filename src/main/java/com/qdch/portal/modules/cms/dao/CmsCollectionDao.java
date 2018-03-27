/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.entity.CmsPraise;

/**
 * 用户收藏记录DAO接口
 * @author wangfeng
 * @version 2018-03-15
 */
@MyBatisDao
public interface CmsCollectionDao extends CrudDao<CmsCollection> {

    /**
     * 获收藏的数量
     * @param cmsCollection
     * @return
     */
    public CmsCollection getCollectionCount(CmsCollection cmsCollection);


    /**
     * 我是否有收藏
     * @param cmsCollection
     * @return
     */
    public CmsCollection getDynamicSelf(CmsCollection cmsCollection);


    public CmsCollection getBySource(CmsCollection collection);
	
}