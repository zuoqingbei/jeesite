/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsDailyListContent;
import com.qdch.portal.modules.cms.dao.CmsDailyListContentDao;

/**
 * 每日一览详情Service
 * @author wangfeng
 * @version 2018-03-22
 */
@Service
@Transactional(readOnly = true)
public class CmsDailyListContentService extends CrudService<CmsDailyListContentDao, CmsDailyListContent> {

	public CmsDailyListContent get(String id) {
		return super.get(id);
	}
	
	public List<CmsDailyListContent> findList(CmsDailyListContent cmsDailyListContent) {
		return super.findList(cmsDailyListContent);
	}
	
	public Page<CmsDailyListContent> findPage(Page<CmsDailyListContent> page, CmsDailyListContent cmsDailyListContent) {
		return super.findPage(page, cmsDailyListContent);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsDailyListContent cmsDailyListContent) {
		super.save(cmsDailyListContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsDailyListContent cmsDailyListContent) {
		super.delete(cmsDailyListContent);
	}
	
}