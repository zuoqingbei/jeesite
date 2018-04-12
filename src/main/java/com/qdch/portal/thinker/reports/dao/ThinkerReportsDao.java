/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.reports.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.thinker.reports.entity.ThinkerReports;

/**
 * 报表管理DAO接口
 * @author zuoqb
 * @version 2018-04-12
 */
@MyBatisDao
public interface ThinkerReportsDao extends CrudDao<ThinkerReports> {
	
}