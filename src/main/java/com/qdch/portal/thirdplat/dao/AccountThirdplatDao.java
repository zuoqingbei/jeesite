/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thirdplat.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.thirdplat.entity.AccountThirdplat;

/**
 * 第三方账号DAO接口
 * @author zuoqb
 * @version 2018-03-20
 */
@MyBatisDao
public interface AccountThirdplatDao extends CrudDao<AccountThirdplat> {
	public AccountThirdplat getByPlatKey(AccountThirdplat entity);
}