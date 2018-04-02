/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private AccountAttentionDao accountAttentionDao;
	
	/**用户添加关注
	 * @author lianjiming
	 * @version 2018-03-13
	 * @param accountAttention 用户关注实体
	 */
	@Transactional(readOnly = false)
	public void saveAttention(AccountAttention accountAttention){
		accountAttentionDao.saveAttention(accountAttention);
	}
	
	/**查询是否关注
	 * @author lianjiming
	 * @version 2018-03-14
	 * @param accountAttention 	用户关注实体
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<AccountAttention> findAccountAttention(AccountAttention accountAttention){
		List<AccountAttention> list = accountAttentionDao.findAccountAttention(accountAttention);
		return list;
	}
	//取消关注
	@Transactional(readOnly = false)
	public void delete(AccountAttention accountAttention) {
		super.delete(accountAttention);
	}
	//分页，列表查询
	public Page<AccountAttention> findPage(Page<AccountAttention> page, AccountAttention accountAttention) {
		return super.findPage(page, accountAttention);
	}
	
	//-------------------------------------------------------------
	
	public AccountAttention get(String id) {
		return super.get(id);
	}
	
	public List<AccountAttention> findList(AccountAttention accountAttention) {
		return super.findList(accountAttention);
	}
	

	
	@Transactional(readOnly = false)
	public void save(AccountAttention accountAttention) {
		super.save(accountAttention);
	}
	
	public AccountAttentionDao getAccountAttentionDao() {
		return accountAttentionDao;
	}
	
	public void setAccountAttentionDao(AccountAttentionDao accountAttentionDao) {
		this.accountAttentionDao = accountAttentionDao;
	}

	public Page<AccountAttention> getAttentionList(Page<AccountAttention> page,AccountAttention accountAttention){
		accountAttention.setPage(page);
		page.setList(dao.getAttentionList(accountAttention));
		return  page;
	}

	public Page<AccountAttention> getBeAttentionList(Page<AccountAttention> page,AccountAttention accountAttention){
		accountAttention.setPage(page);
		page.setList(dao.getBeAttentionList(accountAttention));
		return  page;
	}
}