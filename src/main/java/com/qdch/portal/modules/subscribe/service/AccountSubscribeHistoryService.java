/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.subscribe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.subscribe.entity.AccountSubscribeHistory;
import com.qdch.portal.modules.subscribe.dao.AccountSubscribeHistoryDao;

/**
 * 用户订阅历史Service
 * @author lianjiming
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class AccountSubscribeHistoryService extends CrudService<AccountSubscribeHistoryDao, AccountSubscribeHistory> {

	public AccountSubscribeHistory get(String id) {
		return super.get(id);
	}
	
	public List<AccountSubscribeHistory> findList(AccountSubscribeHistory accountSubscribeHistory) {
		return super.findList(accountSubscribeHistory);
	}
	
	public Page<AccountSubscribeHistory> findPage(Page<AccountSubscribeHistory> page, AccountSubscribeHistory accountSubscribeHistory) {
		return super.findPage(page, accountSubscribeHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(AccountSubscribeHistory accountSubscribeHistory) {
		super.save(accountSubscribeHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccountSubscribeHistory accountSubscribeHistory) {
		super.delete(accountSubscribeHistory);
	}
	
}