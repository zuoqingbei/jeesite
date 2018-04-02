/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.account.entity.AccountMobileCode;

/**
 * 短信验证码DAO接口
 * @author wangfeng
 * @version 2018-03-15
 */
@MyBatisDao
public interface AccountMobileCodeDao extends CrudDao<AccountMobileCode> {

    public AccountMobileCode getByTel(AccountMobileCode accountMobileCode);

    public int setUsed(AccountMobileCode code);

    public int deleteByTel(AccountMobileCode code);
	
}