/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsActivityEnter;

/**
 * 活动报名记录DAO接口
 * @author lianjiming
 * @version 2018-03-28
 */
@MyBatisDao
public interface CmsActivityEnterDao extends CrudDao<CmsActivityEnter> {
	
}