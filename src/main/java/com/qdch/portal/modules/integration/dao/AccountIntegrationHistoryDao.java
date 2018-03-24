/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.integration.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.integration.entity.AccountIntegrationHistory;

/**
 * 用户活跃度记录DAO接口
 * @author lianjiming
 * @version 2018-03-23
 */
@MyBatisDao
public interface AccountIntegrationHistoryDao extends CrudDao<AccountIntegrationHistory> {
	//添加
	public void save(AccountIntegrationHistory accountIntegrationHistory);
}