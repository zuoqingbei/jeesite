/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsNews;

/**
 * 资讯DAO接口
 * @author wangfeng
 * @version 2018-03-13
 */
@MyBatisDao
public interface CmsNewsDao extends CrudDao<CmsNews> {
	
}