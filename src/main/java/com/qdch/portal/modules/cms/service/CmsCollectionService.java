/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import com.qdch.portal.modules.cms.entity.CmsPraise;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.dao.CmsCollectionDao;

/**
 * 用户收藏记录Service
 * @author wangfeng
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CmsCollectionService extends CrudService<CmsCollectionDao, CmsCollection> {

	public CmsCollection get(String id) {
		return super.get(id);
	}
	
	public List<CmsCollection> findList(CmsCollection cmsCollection) {
		return super.findList(cmsCollection);
	}
	
	public Page<CmsCollection> findPage(Page<CmsCollection> page, CmsCollection cmsCollection) {
		return super.findPage(page, cmsCollection);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsCollection cmsCollection) {
		super.save(cmsCollection);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsCollection cmsCollection) {
		super.delete(cmsCollection);
	}


	@Transactional(readOnly = false)
	public boolean getDynamicSelf(CmsCollection cmsCollection) {
		return  Integer.parseInt(dao.getDynamicSelf(cmsCollection).getCount())>0?true:false;
	}
	
}