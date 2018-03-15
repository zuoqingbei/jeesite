/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsContribute;
import com.qdch.portal.modules.cms.dao.CmsContributeDao;

/**
 * 用户投稿Service
 * @author wangfeng
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CmsContributeService extends CrudService<CmsContributeDao, CmsContribute> {
	
	@Autowired
	public CmsContributeDao dao;

	public CmsContribute get(String id) {
		return super.get(id);
	}
	
	public List<CmsContribute> findList(CmsContribute cmsContribute) {
		return super.findList(cmsContribute);
	}
	
	public Page<CmsContribute> findPage(Page<CmsContribute> page, CmsContribute cmsContribute) {
		return super.findPage(page, cmsContribute);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsContribute cmsContribute) {
		super.save(cmsContribute);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsContribute cmsContribute) {
		super.delete(cmsContribute);
	}
	
	@Transactional(readOnly = false)
	public void changeState(CmsContribute cmsContribute) {
		dao.changeState(cmsContribute);
	}

	@Transactional(readOnly = false)
	public CmsContribute getUserContribute(CmsContribute cmsContribute) {
		return dao.getUserContribute(cmsContribute);
	}
	
}