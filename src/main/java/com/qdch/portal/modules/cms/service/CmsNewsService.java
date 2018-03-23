/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.ArrayList;
import java.util.List;

import com.qdch.portal.modules.cms.entity.CmsActivity;
import com.qdch.portal.modules.sys.dao.DictDao;
import com.qdch.portal.modules.sys.entity.Dict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;

/**
 * 资讯Service
 * @author wangfeng
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CmsNewsService extends CrudService<CmsNewsDao, CmsNews> {
	@Autowired
	public DictDao dictDao;

	public CmsNews get(String id) {
		return super.get(id);
	}
	
	public List<CmsNews> findList(CmsNews cmsNews) {
		return super.findList(cmsNews);
	}
	
	public Page<CmsNews> findPage(Page<CmsNews> page, CmsNews cmsNews) {
		return super.findPage(page, cmsNews);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsNews cmsNews) {
		if(StringUtils.isNotBlank(cmsNews.getImage())&&cmsNews.getImage().startsWith("|")){
			cmsNews.setImage(cmsNews.getImage().substring(1));
		}
		super.save(cmsNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsNews cmsNews) {
		super.delete(cmsNews);
	}

	@Transactional(readOnly = false)
	public Page<CmsNews> getRecommend(Page<CmsNews> page,CmsNews cmsNews) {
		cmsNews.setPage(page);
		page.setList(dao.getRecommend(cmsNews));
		return page;
	}


	@Transactional(readOnly = false)
	public String getByLinkId(CmsNews cmsNews) {
		return dao.getByLinkId(cmsNews);
	}

	@Transactional(readOnly = false)
	public CmsNews getContent(CmsNews cmsNews) {
		return dao.getContent(cmsNews);
	}


//	@Transactional(readOnly = false)
//	public Page<CmsNews> getRank(Page<CmsNews> page,CmsNews cmsNews) {
//		cmsNews.setPage(page);
//		page.setList(dao.getRank(cmsNews));
//		return page;
//	}

	@Transactional(readOnly = false)
	public Page<CmsNews> getRank(Page<CmsNews> page,CmsNews cmsNews) {
		cmsNews.setPage(page);
		List<CmsNews> cmsNewsList = dao.getRank(cmsNews);
		List<CmsNews> resultlist = new ArrayList<CmsNews>();
		for(CmsNews news:cmsNewsList){
			Dict dict = new Dict();
			dict.setType("tags_type");
			String tags = news.getTags();
			if(tags !=null  && !tags.equals("")){
				if(tags.startsWith(",")){
					tags = tags.substring(1);
				}
				if(tags.endsWith(",")){
					tags = tags.substring(0,tags.length()-1);
				}
				dict.setValue(tags);
				Dict dict1 =  dictDao.getLabelByIds(dict);
				if(dict1 !=null && !dict1.equals("")){
					news.setTagslabel(dict1.getLabel());
				}

			}
			news.setTagslabel("");
			resultlist.add(news);
		}

		page.setList(resultlist);
		return page;
	}


}