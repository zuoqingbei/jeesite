/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.account.entity.AccountAttention;

/**
 * 用户关注DAO接口
 * @author lianjiming
 * @version 2018-03-12
 */
@MyBatisDao
public interface AccountAttentionDao extends CrudDao<AccountAttention> {
	
}