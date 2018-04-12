/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.view.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.thinker.view.entity.ViewThinker;
import com.qdch.portal.thinker.view.dao.ViewThinkerDao;

/**
 * api报表指标视图数据Service
 * @author zuoqb
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class ViewThinkerService extends CrudService<ViewThinkerDao, ViewThinker> {

	public ViewThinker get(String id) {
		return super.get(id);
	}
	
	public List<ViewThinker> findList(ViewThinker viewThinker) {
		return super.findList(viewThinker);
	}
	
	public Page<ViewThinker> findPage(Page<ViewThinker> page, ViewThinker viewThinker) {
		return super.findPage(page, viewThinker);
	}
	
	@Transactional(readOnly = false)
	public void save(ViewThinker viewThinker) {
		super.save(viewThinker);
	}
	
	@Transactional(readOnly = false)
	public void delete(ViewThinker viewThinker) {
		super.delete(viewThinker);
	}
	
}