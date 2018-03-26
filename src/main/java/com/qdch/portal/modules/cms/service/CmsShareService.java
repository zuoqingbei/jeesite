/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import com.qdch.portal.modules.cms.entity.CmsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsShare;
import com.qdch.portal.modules.cms.dao.CmsShareDao;

/**
 * 用户分享记录Service
 * @author wangfeng
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CmsShareService extends CrudService<CmsShareDao, CmsShare> {

	public CmsShare get(String id) {
		return super.get(id);
	}
	
	public List<CmsShare> findList(CmsShare cmsShare) {
		return super.findList(cmsShare);
	}
	
	public Page<CmsShare> findPage(Page<CmsShare> page, CmsShare cmsShare) {
		return super.findPage(page, cmsShare);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsShare cmsShare) {
		super.save(cmsShare);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsShare cmsShare) {
		super.delete(cmsShare);
	}

	@Transactional(readOnly = false)
	public boolean getDynamicSelf(CmsShare cmsShare) {
		return  Integer.parseInt(dao.getDynamicSelf(cmsShare).getCount())>0?true:false;
	}
	
}