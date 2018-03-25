/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsPraise;
import com.qdch.portal.modules.cms.dao.CmsPraiseDao;

/**
 * 用户赞 踩记录Service
 * @author wangfeng
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CmsPraiseService extends CrudService<CmsPraiseDao, CmsPraise> {

	public CmsPraise get(String id) {
		return super.get(id);
	}
	
	public List<CmsPraise> findList(CmsPraise cmsPraise) {
		return super.findList(cmsPraise);
	}
	
	public Page<CmsPraise> findPage(Page<CmsPraise> page, CmsPraise cmsPraise) {
		return super.findPage(page, cmsPraise);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsPraise cmsPraise) {
		super.save(cmsPraise);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsPraise cmsPraise) {
		super.delete(cmsPraise);
	}

	@Transactional(readOnly = false)
	public boolean getDynamicSelf(CmsPraise cmsPraise) {
		return  Integer.parseInt(dao.getDynamicSelf(cmsPraise).getCount())>0?true:false;
	}
	
}