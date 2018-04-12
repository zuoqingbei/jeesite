/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.service.TreeService;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.thinker.category.entity.ThinkerCategory;
import com.qdch.portal.thinker.category.dao.ThinkerCategoryDao;

/**
 * 分类Service
 * @author zuoqb
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class ThinkerCategoryService extends TreeService<ThinkerCategoryDao, ThinkerCategory> {

	public ThinkerCategory get(String id) {
		return super.get(id);
	}
	
	public List<ThinkerCategory> findList(ThinkerCategory thinkerCategory) {
		if (StringUtils.isNotBlank(thinkerCategory.getParentIds())){
			thinkerCategory.setParentIds(","+thinkerCategory.getParentIds()+",");
		}
		return super.findList(thinkerCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(ThinkerCategory thinkerCategory) {
		super.save(thinkerCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(ThinkerCategory thinkerCategory) {
		super.delete(thinkerCategory);
	}
	
}