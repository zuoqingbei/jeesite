/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.search.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.thinker.search.entity.ThinkerHotSearch;
import com.qdch.portal.thinker.search.dao.ThinkerHotSearchDao;

/**
 * 热搜Service
 * @author zuoqb
 * @version 2018-04-13
 */
@Service
@Transactional(readOnly = true)
public class ThinkerHotSearchService extends CrudService<ThinkerHotSearchDao, ThinkerHotSearch> {

	public ThinkerHotSearch get(String id) {
		return super.get(id);
	}
	
	public List<ThinkerHotSearch> findList(ThinkerHotSearch thinkerHotSearch) {
		return super.findList(thinkerHotSearch);
	}
	
	public Page<ThinkerHotSearch> findPage(Page<ThinkerHotSearch> page, ThinkerHotSearch thinkerHotSearch) {
		return super.findPage(page, thinkerHotSearch);
	}
	
	@Transactional(readOnly = false)
	public void save(ThinkerHotSearch thinkerHotSearch) {
		super.save(thinkerHotSearch);
	}
	
	@Transactional(readOnly = false)
	public void delete(ThinkerHotSearch thinkerHotSearch) {
		super.delete(thinkerHotSearch);
	}
	
}