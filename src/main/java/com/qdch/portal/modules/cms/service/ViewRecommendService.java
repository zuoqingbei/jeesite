/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.service;

import java.util.ArrayList;
import java.util.List;

import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.sys.dao.DictDao;
import com.qdch.portal.modules.sys.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.cms.entity.ViewRecommend;
import com.qdch.portal.modules.cms.dao.ViewRecommendDao;

/**
 * 推荐Service
 * @author wangfeng
 * @version 2018-03-24
 */
@Service
@Transactional(readOnly = true)
public class ViewRecommendService extends CrudService<ViewRecommendDao, ViewRecommend> {

	@Autowired
	private DictDao dictDao;

	public ViewRecommend get(String id) {
		return super.get(id);
	}
	
	public List<ViewRecommend> findList(ViewRecommend viewRecommend) {
		return super.findList(viewRecommend);
	}
	
	public Page<ViewRecommend> findPage(Page<ViewRecommend> page, ViewRecommend viewRecommend) {
		return super.findPage(page, viewRecommend);
	}
	
	@Transactional(readOnly = false)
	public void save(ViewRecommend viewRecommend) {
		super.save(viewRecommend);
	}
	
	@Transactional(readOnly = false)
	public void delete(ViewRecommend viewRecommend) {
		super.delete(viewRecommend);
	}


	public  Page<ViewRecommend> getRecommend(Page<ViewRecommend> page, ViewRecommend viewRecommend) {
		viewRecommend.setPage(page);
		List<ViewRecommend> list = dao.getRecommend(viewRecommend);
//		List<ViewRecommend> results = new ArrayList<ViewRecommend>();
//		for(ViewRecommend news:list){
//			Dict dict = new Dict();
//			dict.setType("tags_type");
//			String tags = news.getTags();
//			if(tags !=null  && !tags.equals("")){
//				tags = StringUtils.delFrontAndEndSymbol(tags);
////				dict.setValue(tags);
//				dict.setTagsvalue(tags.split(","));
//				Dict dict1 =  dictDao.getLabelByIds(dict);
//				if(dict1 !=null && !dict1.equals("")){
//					news.setTagslabel(dict1.getLabel());
//				}
//
//			}else {
//				news.setTagslabel("");
//			}
//
//			results.add(news);
//		}
		page.setList(list);
		return page;
	}
	
}