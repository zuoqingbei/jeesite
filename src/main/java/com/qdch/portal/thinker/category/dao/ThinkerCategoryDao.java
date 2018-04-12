/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.category.dao;

import com.qdch.portal.common.persistence.TreeDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.thinker.category.entity.ThinkerCategory;

/**
 * 分类DAO接口
 * @author zuoqb
 * @version 2018-04-12
 */
@MyBatisDao
public interface ThinkerCategoryDao extends TreeDao<ThinkerCategory> {
	
}