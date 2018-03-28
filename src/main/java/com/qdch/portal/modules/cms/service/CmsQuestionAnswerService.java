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
import com.qdch.portal.modules.cms.entity.CmsQuestionAnswer;
import com.qdch.portal.modules.cms.dao.CmsQuestionAnswerDao;

/**
 * 问答表Service
 * @author wangfeng
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class CmsQuestionAnswerService extends CrudService<CmsQuestionAnswerDao, CmsQuestionAnswer> {

	public CmsQuestionAnswer get(String id) {
		return super.get(id);
	}
	
	public List<CmsQuestionAnswer> findList(CmsQuestionAnswer cmsQuestionAnswer) {
		return super.findList(cmsQuestionAnswer);
	}
	
	public Page<CmsQuestionAnswer> findPage(Page<CmsQuestionAnswer> page, CmsQuestionAnswer cmsQuestionAnswer) {
		return super.findPage(page, cmsQuestionAnswer);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsQuestionAnswer cmsQuestionAnswer) {
		if(StringUtils.isNotBlank(cmsQuestionAnswer.getImage())&&cmsQuestionAnswer.getImage().startsWith("|")){
			cmsQuestionAnswer.setImage(cmsQuestionAnswer.getImage().substring(1));
		}
		super.save(cmsQuestionAnswer);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsQuestionAnswer cmsQuestionAnswer) {
		super.delete(cmsQuestionAnswer);
	}



	@Transactional(readOnly = false)
	public Page<CmsQuestionAnswer> getRank(Page<CmsQuestionAnswer> page,CmsQuestionAnswer cmsQuestionAnswer) {
		cmsQuestionAnswer.setPage(page);
		page.setList(dao.getRank(cmsQuestionAnswer));

		return  page;
	}
}