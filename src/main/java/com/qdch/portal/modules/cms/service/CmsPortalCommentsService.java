/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.ArrayList;
import java.util.List;

import com.qdch.portal.modules.cms.dao.CmsPraiseDao;
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsPraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsPortalComments;
import com.qdch.portal.modules.cms.dao.CmsPortalCommentsDao;

/**
 * 门户评论Service
 * @author wangfeng
 * @version 2018-03-20
 */
@Service
@Transactional(readOnly = true)
public class CmsPortalCommentsService extends CrudService<CmsPortalCommentsDao, CmsPortalComments> {

	@Autowired
	private CmsPraiseDao cmsPraiseDao;

	public CmsPortalComments get(String id) {
		return super.get(id);
	}
	
	public List<CmsPortalComments> findList(CmsPortalComments cmsPortalComments) {
		return super.findList(cmsPortalComments);
	}
	
	public Page<CmsPortalComments> findPage(Page<CmsPortalComments> page, CmsPortalComments cmsPortalComments) {
		return super.findPage(page, cmsPortalComments);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsPortalComments cmsPortalComments) {
		super.save(cmsPortalComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsPortalComments cmsPortalComments) {
		super.delete(cmsPortalComments);
	}

	@Transactional(readOnly = false)
	public boolean getDynamicSelf(CmsPortalComments cmsPortalComments) {
		return  dao.getDynamicSelf(cmsPortalComments)>0?true:false;
	}

	@Transactional(readOnly = false)
	public Page<CmsPortalComments> getCommentsBySource(Page<CmsPortalComments> page, CmsPortalComments cmsPortalComments) {
		cmsPortalComments.setPage(page);
		page.setList(dao.getCommentsBySource(cmsPortalComments));
		return page;
	}

	@Transactional(readOnly = false)
	public Page<CmsPortalComments> getCommentsAndPraise(Page<CmsPortalComments> page, CmsPortalComments cmsPortalComments) {
		cmsPortalComments.setPage(page);
//		List<CmsPortalComments> lists = dao.getCommentsAndPraise(cmsPortalComments);
		List<CmsPortalComments> lists = dao.getParentComments(cmsPortalComments);
		List<CmsPortalComments> result = new ArrayList<CmsPortalComments>();
		for(CmsPortalComments c:lists){
			c.setCommentsList(new ArrayList<CmsPortalComments>());
			digui(c);
			result.add(c);
		}
		page.setList(result);
		return page;
	}



	private void digui(CmsPortalComments parent){
		CmsPortalComments comments = new CmsPortalComments();
		comments.setSourceTable(parent.getSourceTable());
		comments.setSourceId(parent.getSourceId());
		comments.setParentId(parent.getId());
		List<CmsPortalComments> childlist = dao.getChildComments(comments);
		CmsPortalComments comments1 = dao.getCommentsCount(comments);
		if(comments1 == null || comments1.equals("")){
			parent.setCommentsCount("");
		}else{
			parent.setCommentsCount(comments1.getCommentsCount());
		}
		//
		CmsPraise cmsPraise = new CmsPraise();
		cmsPraise.setSourceTable("cms_portal_comments");
		cmsPraise.setOperateType("1"); //0-踩 1-赞
		cmsPraise.setSourceId(parent.getId());
		CmsPraise praise = cmsPraiseDao.getPraiseCount(cmsPraise);
		if(praise == null || praise.equals("")){
			parent.setPraiseCount("0");
		}else{
			parent.setPraiseCount(praise.getCount());
		}
		if(childlist==null || childlist.size()<1){ //终止递归

		}else{
			for(CmsPortalComments c:childlist){
				parent.getCommentsList().add(c);
				c.setCommentsList(new ArrayList<CmsPortalComments>());
				digui(c);
			}
		}

	}

	public  List<CmsPortalComments> getHotComments(CmsPortalComments cmsPortalComments){
		List<CmsPortalComments> list = dao.getHotComments(cmsPortalComments);
		List<CmsPortalComments> results = new ArrayList<CmsPortalComments>();
		for(CmsPortalComments c:list){
			CmsPortalComments comments = new CmsPortalComments();
			comments.setSourceTable(c.getSourceTable());
			comments.setSourceId(c.getSourceId());
			comments.setParentId(c.getId());
			comments	= dao.getCommentsCount(comments);
			if(comments == null || comments.equals("")){
				c.setCommentsCount("");
			}else{
				c.setCommentsCount(comments.getCommentsCount());
			}

			results.add(c);
		}
		return results;
	}
}