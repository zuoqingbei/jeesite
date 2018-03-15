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
import com.qdch.portal.modules.cms.entity.CmsBanner;
import com.qdch.portal.modules.cms.dao.CmsBannerDao;

/**
 * 轮播图管理Service
 * @author zuoqb
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CmsBannerService extends CrudService<CmsBannerDao, CmsBanner> {
	@Autowired
	protected CmsBannerDao dao;
	public CmsBanner get(String id) {
		return super.get(id);
	}
	
	public List<CmsBanner> findList(CmsBanner cmsBanner) {
		return super.findList(cmsBanner);
	}
	
	public Page<CmsBanner> findPage(Page<CmsBanner> page, CmsBanner cmsBanner) {
		return super.findPage(page, cmsBanner);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsBanner cmsBanner) {
		if(StringUtils.isNotBlank(cmsBanner.getImage())&&cmsBanner.getImage().startsWith("|")){
			cmsBanner.setImage(cmsBanner.getImage().substring(1));
		}
		super.save(cmsBanner);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsBanner cmsBanner) {
		super.delete(cmsBanner);
	}
	public List<CmsBanner> findBannerList(int offset,int limit) {
		return dao.findBannerList(offset,limit);
	}
	
}