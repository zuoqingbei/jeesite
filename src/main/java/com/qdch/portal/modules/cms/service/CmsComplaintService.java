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
import com.qdch.portal.modules.cms.entity.CmsComplaint;
import com.qdch.portal.modules.cms.dao.CmsComplaintDao;

/**
 * 投诉Service
 * @author zuoqb
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CmsComplaintService extends CrudService<CmsComplaintDao, CmsComplaint> {

	public CmsComplaint get(String id) {
		return super.get(id);
	}
	
	public List<CmsComplaint> findList(CmsComplaint cmsComplaint) {
		return super.findList(cmsComplaint);
	}
	
	public Page<CmsComplaint> findPage(Page<CmsComplaint> page, CmsComplaint cmsComplaint) {
		return super.findPage(page, cmsComplaint);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsComplaint cmsComplaint) {
		if(StringUtils.isNotBlank(cmsComplaint.getImage())){
			cmsComplaint.setImage(cmsComplaint.getImage().replaceAll("\\|", ","));
		}
		super.save(cmsComplaint);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsComplaint cmsComplaint) {
		super.delete(cmsComplaint);
	}
	
}