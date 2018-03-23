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
import com.qdch.portal.modules.cms.entity.CmsDailyList;
import com.qdch.portal.modules.cms.dao.CmsDailyListDao;

/**
 * 每日一览Service
 * @author wangfeng
 * @version 2018-03-22
 */
@Service
@Transactional(readOnly = true)
public class CmsDailyListService extends CrudService<CmsDailyListDao, CmsDailyList> {

	public CmsDailyList get(String id) {
		return super.get(id);
	}
	
	public List<CmsDailyList> findList(CmsDailyList cmsDailyList) {
		return super.findList(cmsDailyList);
	}
	
	public Page<CmsDailyList> findPage(Page<CmsDailyList> page, CmsDailyList cmsDailyList) {
		return super.findPage(page, cmsDailyList);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsDailyList cmsDailyList) {
		if(StringUtils.isNotBlank(cmsDailyList.getImage())&&cmsDailyList.getImage().startsWith("|")){
			cmsDailyList.setImage(cmsDailyList.getImage().substring(1));
		}
		super.save(cmsDailyList);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsDailyList cmsDailyList) {
		super.delete(cmsDailyList);
	}
	
}