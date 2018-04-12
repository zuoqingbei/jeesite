/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.reports.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.thinker.reports.entity.ThinkerReports;
import com.qdch.portal.thinker.reports.dao.ThinkerReportsDao;

/**
 * 报表管理Service
 * @author zuoqb
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class ThinkerReportsService extends CrudService<ThinkerReportsDao, ThinkerReports> {

	public ThinkerReports get(String id) {
		return super.get(id);
	}
	
	public List<ThinkerReports> findList(ThinkerReports thinkerReports) {
		return super.findList(thinkerReports);
	}
	
	public Page<ThinkerReports> findPage(Page<ThinkerReports> page, ThinkerReports thinkerReports) {
		return super.findPage(page, thinkerReports);
	}
	
	@Transactional(readOnly = false)
	public void save(ThinkerReports thinkerReports) {
		super.save(thinkerReports);
	}
	
	@Transactional(readOnly = false)
	public void delete(ThinkerReports thinkerReports) {
		super.delete(thinkerReports);
	}
	
}