/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsComment;
import com.qdch.portal.modules.cms.dao.CmsCommentDao;

/**
 * 评论表Service
 * @author wangfeng
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CmsCommentService extends CrudService<CmsCommentDao, CmsComment> {

	public CmsComment get(String id) {
		return super.get(id);
	}
	
	public List<CmsComment> findList(CmsComment cmsComment) {
		return super.findList(cmsComment);
	}
	
	public Page<CmsComment> findPage(Page<CmsComment> page, CmsComment cmsComment) {
		return super.findPage(page, cmsComment);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsComment cmsComment) {
		super.save(cmsComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsComment cmsComment) {
		super.delete(cmsComment);
	}
	
}