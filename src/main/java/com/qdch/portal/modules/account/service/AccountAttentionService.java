/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.account.dao.AccountAttentionDao;

/**
 * 用户关注Service
 * @author lianjiming
 * @version 2018-03-12
 */
@Service
@Transactional(readOnly = true)
public class AccountAttentionService extends CrudService<AccountAttentionDao, AccountAttention> {

	public AccountAttention get(String id) {
		return super.get(id);
	}
	
	public List<AccountAttention> findList(AccountAttention accountAttention) {
		return super.findList(accountAttention);
	}
	
	public Page<AccountAttention> findPage(Page<AccountAttention> page, AccountAttention accountAttention) {
		return super.findPage(page, accountAttention);
	}
	
	@Transactional(readOnly = false)
	public void save(AccountAttention accountAttention) {
		super.save(accountAttention);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccountAttention accountAttention) {
		super.delete(accountAttention);
	}
	
}