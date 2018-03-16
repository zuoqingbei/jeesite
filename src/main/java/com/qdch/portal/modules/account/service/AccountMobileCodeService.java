/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.account.entity.AccountMobileCode;
import com.qdch.portal.modules.account.dao.AccountMobileCodeDao;

/**
 * 短信验证码Service
 * @author wangfeng
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class AccountMobileCodeService extends CrudService<AccountMobileCodeDao, AccountMobileCode> {

	public AccountMobileCode get(String id) {
		return super.get(id);
	}
	
	public List<AccountMobileCode> findList(AccountMobileCode accountMobileCode) {
		return super.findList(accountMobileCode);
	}
	
	public Page<AccountMobileCode> findPage(Page<AccountMobileCode> page, AccountMobileCode accountMobileCode) {
		return super.findPage(page, accountMobileCode);
	}
	
	@Transactional(readOnly = false)
	public void save(AccountMobileCode accountMobileCode) {
		super.save(accountMobileCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccountMobileCode accountMobileCode) {
		super.delete(accountMobileCode);
	}
	
}