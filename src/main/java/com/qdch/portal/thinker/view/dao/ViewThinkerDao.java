/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.view.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.thinker.view.entity.ViewThinker;

/**
 * api报表指标视图数据DAO接口
 * @author zuoqb
 * @version 2018-04-12
 */
@MyBatisDao
public interface ViewThinkerDao extends CrudDao<ViewThinker> {
	
}