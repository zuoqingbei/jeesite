/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsNewsData;
import com.qdch.portal.modules.cms.dao.CmsNewsDataDao;

/**
 * 资讯详表Service
 * @author wangfeng
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CmsNewsDataService extends CrudService<CmsNewsDataDao, CmsNewsData> {

	public CmsNewsData get(String id) {
		return super.get(id);
	}
	
	public List<CmsNewsData> findList(CmsNewsData cmsNewsData) {
		return super.findList(cmsNewsData);
	}
	
	public Page<CmsNewsData> findPage(Page<CmsNewsData> page, CmsNewsData cmsNewsData) {
		return super.findPage(page, cmsNewsData);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsNewsData cmsNewsData) {
		super.save(cmsNewsData);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsNewsData cmsNewsData) {
		super.delete(cmsNewsData);
	}
	
}