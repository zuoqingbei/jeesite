/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsActivityEnter;
import com.qdch.portal.modules.cms.dao.CmsActivityEnterDao;

/**
 * 活动报名记录Service
 * @author lianjiming
 * @version 2018-03-28
 */
@Service
@Transactional(readOnly = true)
public class CmsActivityEnterService extends CrudService<CmsActivityEnterDao, CmsActivityEnter> {

	public CmsActivityEnter get(String id) {
		return super.get(id);
	}
	
	public List<CmsActivityEnter> findList(CmsActivityEnter cmsActivityEnter) {
		return super.findList(cmsActivityEnter);
	}
	
	public Page<CmsActivityEnter> findPage(Page<CmsActivityEnter> page, CmsActivityEnter cmsActivityEnter) {
		return super.findPage(page, cmsActivityEnter);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsActivityEnter cmsActivityEnter) {
		super.save(cmsActivityEnter);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsActivityEnter cmsActivityEnter) {
		super.delete(cmsActivityEnter);
	}
	
}