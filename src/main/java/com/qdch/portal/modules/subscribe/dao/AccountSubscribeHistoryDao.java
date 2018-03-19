/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.subscribe.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.subscribe.entity.AccountSubscribeHistory;

/**
 * 用户订阅历史DAO接口
 * @author lianjiming
 * @version 2018-03-19
 */
@MyBatisDao
public interface AccountSubscribeHistoryDao extends CrudDao<AccountSubscribeHistory> {
	
}