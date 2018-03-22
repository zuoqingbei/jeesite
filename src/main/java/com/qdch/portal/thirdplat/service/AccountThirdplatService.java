/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thirdplat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.thirdplat.entity.AccountThirdplat;
import com.qdch.portal.thirdplat.dao.AccountThirdplatDao;

/**
 * 第三方账号Service
 * @author zuoqb
 * @version 2018-03-20
 */
@Service
@Transactional(readOnly = true)
public class AccountThirdplatService extends CrudService<AccountThirdplatDao, AccountThirdplat> {
	@Autowired
	protected AccountThirdplatDao dao;
	public AccountThirdplat get(String id) {
		return super.get(id);
	}
	
	public List<AccountThirdplat> findList(AccountThirdplat accountThirdplat) {
		return super.findList(accountThirdplat);
	}
	
	public Page<AccountThirdplat> findPage(Page<AccountThirdplat> page, AccountThirdplat accountThirdplat) {
		return super.findPage(page, accountThirdplat);
	}
	
	@Transactional(readOnly = false)
	public void save(AccountThirdplat accountThirdplat) {
		super.save(accountThirdplat);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccountThirdplat accountThirdplat) {
		super.delete(accountThirdplat);
	}
	public AccountThirdplat getByPlatKey(AccountThirdplat accountThirdplat) {
		return dao.getByPlatKey(accountThirdplat);
	}
}