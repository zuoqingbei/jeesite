/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsActivityFlow;
import com.qdch.portal.modules.cms.dao.CmsActivityFlowDao;

/**
 * 活动日程Service
 * @author wangfeng
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class CmsActivityFlowService extends CrudService<CmsActivityFlowDao, CmsActivityFlow> {

	public CmsActivityFlow get(String id) {
		return super.get(id);
	}
	
	public List<CmsActivityFlow> findList(CmsActivityFlow cmsActivityFlow) {
		return super.findList(cmsActivityFlow);
	}
	
	public Page<CmsActivityFlow> findPage(Page<CmsActivityFlow> page, CmsActivityFlow cmsActivityFlow) {
		return super.findPage(page, cmsActivityFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsActivityFlow cmsActivityFlow) {
		super.save(cmsActivityFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsActivityFlow cmsActivityFlow) {
		super.delete(cmsActivityFlow);
	}
	
}