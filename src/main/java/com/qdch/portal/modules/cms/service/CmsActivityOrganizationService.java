/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsActivityOrganization;
import com.qdch.portal.modules.cms.dao.CmsActivityOrganizationDao;

/**
 * 活动组织结构Service
 * @author wangfeng
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class CmsActivityOrganizationService extends CrudService<CmsActivityOrganizationDao, CmsActivityOrganization> {

	public CmsActivityOrganization get(String id) {
		return super.get(id);
	}
	
	public List<CmsActivityOrganization> findList(CmsActivityOrganization cmsActivityOrganization) {
		return super.findList(cmsActivityOrganization);
	}
	
	public Page<CmsActivityOrganization> findPage(Page<CmsActivityOrganization> page, CmsActivityOrganization cmsActivityOrganization) {
		return super.findPage(page, cmsActivityOrganization);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsActivityOrganization cmsActivityOrganization) {
		super.save(cmsActivityOrganization);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsActivityOrganization cmsActivityOrganization) {
		super.delete(cmsActivityOrganization);
	}
	
}