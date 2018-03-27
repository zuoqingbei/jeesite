/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsEducation;
import com.qdch.portal.modules.cms.dao.CmsEducationDao;

/**
 * 投资教育Service
 * @author wangfeng
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class CmsEducationService extends CrudService<CmsEducationDao, CmsEducation> {

	public CmsEducation get(String id) {
		return super.get(id);
	}
	
	public List<CmsEducation> findList(CmsEducation cmsEducation) {
		return super.findList(cmsEducation);
	}
	
	public Page<CmsEducation> findPage(Page<CmsEducation> page, CmsEducation cmsEducation) {
		return super.findPage(page, cmsEducation);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsEducation cmsEducation) {
		if(StringUtils.isNotBlank(cmsEducation.getImage())&&cmsEducation.getImage().startsWith("|")){
			cmsEducation.setImage(cmsEducation.getImage().substring(1));
		}
		super.save(cmsEducation);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsEducation cmsEducation) {
		super.delete(cmsEducation);
	}

	@Transactional(readOnly = false)
	public Page<CmsEducation> getList(Page<CmsEducation> page,CmsEducation cmsEducation) {
		cmsEducation.setPage(page);
		page.setList(dao.getList(cmsEducation));
		return  page;

	}
	
}