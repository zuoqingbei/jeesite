/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.entity.CmsNews;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsPortalComments;
import com.qdch.portal.modules.cms.dao.CmsPortalCommentsDao;

/**
 * 门户评论Service
 * @author wangfeng
 * @version 2018-03-20
 */
@Service
@Transactional(readOnly = true)
public class CmsPortalCommentsService extends CrudService<CmsPortalCommentsDao, CmsPortalComments> {

	public CmsPortalComments get(String id) {
		return super.get(id);
	}
	
	public List<CmsPortalComments> findList(CmsPortalComments cmsPortalComments) {
		return super.findList(cmsPortalComments);
	}
	
	public Page<CmsPortalComments> findPage(Page<CmsPortalComments> page, CmsPortalComments cmsPortalComments) {
		return super.findPage(page, cmsPortalComments);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsPortalComments cmsPortalComments) {
		super.save(cmsPortalComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsPortalComments cmsPortalComments) {
		super.delete(cmsPortalComments);
	}

	@Transactional(readOnly = false)
	public boolean getDynamicSelf(CmsPortalComments cmsPortalComments) {
		return  dao.getDynamicSelf(cmsPortalComments)>0?true:false;
	}

	@Transactional(readOnly = false)
	public Page<CmsPortalComments> getCommentsBySource(Page<CmsPortalComments> page, CmsPortalComments cmsPortalComments) {
		cmsPortalComments.setPage(page);
		page.setList(dao.getCommentsBySource(cmsPortalComments));
		return page;
	}
}