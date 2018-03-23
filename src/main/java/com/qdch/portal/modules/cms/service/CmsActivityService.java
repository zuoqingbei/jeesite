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
import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.cms.dao.CmsActivityDao;

/**
 * 活动Service
 * @author wangfeng
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class CmsActivityService extends CrudService<CmsActivityDao, CmsActivity> {

	public CmsActivity get(String id) {
		return super.get(id);
	}
	
	public List<CmsActivity> findList(CmsActivity cmsActivity) {
		return super.findList(cmsActivity);
	}
	
	public Page<CmsActivity> findPage(Page<CmsActivity> page, CmsActivity cmsActivity) {
		return super.findPage(page, cmsActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsActivity cmsActivity) {
		if(StringUtils.isNotBlank(cmsActivity.getImage())&&cmsActivity.getImage().startsWith("|")){
			cmsActivity.setImage(cmsActivity.getImage().substring(1));
		}
		super.save(cmsActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsActivity cmsActivity) {
		super.delete(cmsActivity);
	}
	
}