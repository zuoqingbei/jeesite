/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;

/**
 * 资讯Service
 * @author wangfeng
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CmsNewsService extends CrudService<CmsNewsDao, CmsNews> {

	public CmsNews get(String id) {
		return super.get(id);
	}
	
	public List<CmsNews> findList(CmsNews cmsNews) {
		return super.findList(cmsNews);
	}
	
	public Page<CmsNews> findPage(Page<CmsNews> page, CmsNews cmsNews) {
		return super.findPage(page, cmsNews);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsNews cmsNews) {
		super.save(cmsNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsNews cmsNews) {
		super.delete(cmsNews);
	}
	
}