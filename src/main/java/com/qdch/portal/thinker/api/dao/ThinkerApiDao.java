/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.api.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.thinker.api.entity.ThinkerApi;

/**
 * api管理DAO接口
 * @author zuoqb
 * @version 2018-04-12
 */
@MyBatisDao
public interface ThinkerApiDao extends CrudDao<ThinkerApi> {
	
}