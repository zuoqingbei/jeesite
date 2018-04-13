/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.indexs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.thinker.indexs.entity.ThinkerIndex;
import com.qdch.portal.thinker.indexs.dao.ThinkerIndexDao;

/**
 * 指标管理Service
 * @author zuoqb
 * @version 2018-04-13
 */
@Service
@Transactional(readOnly = true)
public class ThinkerIndexService extends CrudService<ThinkerIndexDao, ThinkerIndex> {

	public ThinkerIndex get(String id) {
		return super.get(id);
	}
	
	public List<ThinkerIndex> findList(ThinkerIndex thinkerIndex) {
		return super.findList(thinkerIndex);
	}
	
	public Page<ThinkerIndex> findPage(Page<ThinkerIndex> page, ThinkerIndex thinkerIndex) {
		return super.findPage(page, thinkerIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(ThinkerIndex thinkerIndex) {
		super.save(thinkerIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(ThinkerIndex thinkerIndex) {
		super.delete(thinkerIndex);
	}
	
}