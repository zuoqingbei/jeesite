/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsActivity;

/**
 * 活动DAO接口
 * @author wangfeng
 * @version 2018-03-21
 */
@MyBatisDao
public interface CmsActivityDao extends CrudDao<CmsActivity> {
	
}