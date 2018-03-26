/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.integration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.integration.entity.AccountIntegrationHistory;
import com.qdch.portal.modules.integration.dao.AccountIntegrationHistoryDao;

/**
 * 用户活跃度记录Service
 * @author lianjiming
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class AccountIntegrationHistoryService extends CrudService<AccountIntegrationHistoryDao, AccountIntegrationHistory> {
	@Autowired
	private AccountIntegrationHistoryDao accountIntegrationHistoryDao;
	
	public AccountIntegrationHistory get(String id) {
		return super.get(id);
	}
	
	public List<AccountIntegrationHistory> findList(AccountIntegrationHistory accountIntegrationHistory) {
		return super.findList(accountIntegrationHistory);
	}
	
	public Page<AccountIntegrationHistory> findPage(Page<AccountIntegrationHistory> page, AccountIntegrationHistory accountIntegrationHistory) {
		return super.findPage(page, accountIntegrationHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(AccountIntegrationHistory accountIntegrationHistory) {
		accountIntegrationHistoryDao.save(accountIntegrationHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccountIntegrationHistory accountIntegrationHistory) {
		super.delete(accountIntegrationHistory);
	}
	
}