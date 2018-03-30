/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.cms.dao.CmsActivityDao;
import com.qdch.portal.modules.cms.entity.CmsActivityFlow;
import com.qdch.portal.modules.cms.dao.CmsActivityFlowDao;
import com.qdch.portal.modules.cms.entity.CmsActivityOrganization;
import com.qdch.portal.modules.cms.dao.CmsActivityOrganizationDao;

/**
 * 活动Service
 * @author lianjiming
 * @version 2018-03-28
 */
@Service
@Transactional(readOnly = true)
public class CmsActivityService extends CrudService<CmsActivityDao, CmsActivity> {

	@Autowired
	private CmsActivityFlowDao cmsActivityFlowDao;
	@Autowired
	private CmsActivityOrganizationDao cmsActivityOrganizationDao;
	
	public CmsActivity get(String id) {
		CmsActivity cmsActivity = super.get(id);
		cmsActivity.setCmsActivityFlowList(cmsActivityFlowDao.findList(new CmsActivityFlow(cmsActivity)));
		cmsActivity.setCmsActivityOrganizationList(cmsActivityOrganizationDao.findList(new CmsActivityOrganization(cmsActivity)));
		return cmsActivity;
	}
	
	public List<CmsActivity> findList(CmsActivity cmsActivity) {
		return super.findList(cmsActivity);
	}
	
	public Page<CmsActivity> findPage(Page<CmsActivity> page, CmsActivity cmsActivity) {
		return super.findPage(page, cmsActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsActivity cmsActivity) {
		super.save(cmsActivity);
		for (CmsActivityFlow cmsActivityFlow : cmsActivity.getCmsActivityFlowList()){
			if (cmsActivityFlow.getId() == null){
				continue;
			}
			if (CmsActivityFlow.DEL_FLAG_NORMAL.equals(cmsActivityFlow.getDelFlag())){
				if (StringUtils.isBlank(cmsActivityFlow.getId())){
					cmsActivityFlow.setActivityId(cmsActivity);
					cmsActivityFlow.preInsert();
					cmsActivityFlowDao.insert(cmsActivityFlow);
				}else{
					cmsActivityFlow.preUpdate();
					cmsActivityFlowDao.update(cmsActivityFlow);
				}
			}else{
				cmsActivityFlowDao.delete(cmsActivityFlow);
			}
		}
		for (CmsActivityOrganization cmsActivityOrganization : cmsActivity.getCmsActivityOrganizationList()){
			if (cmsActivityOrganization.getId() == null){
				continue;
			}
			if (CmsActivityOrganization.DEL_FLAG_NORMAL.equals(cmsActivityOrganization.getDelFlag())){
				if (StringUtils.isBlank(cmsActivityOrganization.getId())){
					cmsActivityOrganization.setActivityId(cmsActivity);
					cmsActivityOrganization.preInsert();
					cmsActivityOrganizationDao.insert(cmsActivityOrganization);
				}else{
					cmsActivityOrganization.preUpdate();
					cmsActivityOrganizationDao.update(cmsActivityOrganization);
				}
			}else{
				cmsActivityOrganizationDao.delete(cmsActivityOrganization);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsActivity cmsActivity) {
		super.delete(cmsActivity);
		cmsActivityFlowDao.delete(new CmsActivityFlow(cmsActivity));
		cmsActivityOrganizationDao.delete(new CmsActivityOrganization(cmsActivity));
	}
	
}