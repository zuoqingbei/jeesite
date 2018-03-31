/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsProduct;
import com.qdch.portal.modules.cms.dao.CmsProductDao;

/**
 * 产品管理Service
 * @author zuoqb
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class CmsProductService extends CrudService<CmsProductDao, CmsProduct> {

	public CmsProduct get(String id) {
		return super.get(id);
	}
	
	public List<CmsProduct> findList(CmsProduct cmsProduct) {
		return super.findList(cmsProduct);
	}
	
	public Page<CmsProduct> findPage(Page<CmsProduct> page, CmsProduct cmsProduct) {
		return super.findPage(page, cmsProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsProduct cmsProduct) {
		if(StringUtils.isNotBlank(cmsProduct.getImage())&&cmsProduct.getImage().startsWith("|")){
			cmsProduct.setImage(cmsProduct.getImage().substring(1));
		}
		super.save(cmsProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsProduct cmsProduct) {
		super.delete(cmsProduct);
	}
	
}