/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsNews;

import java.util.List;

/**
 * 资讯DAO接口
 * @author wangfeng
 * @version 2018-03-13
 */
@MyBatisDao
public interface CmsNewsDao extends CrudDao<CmsNews> {

    /**
     * 获得强推资讯
     * @param cmsNews
     * @return
     */
    public List<CmsNews>  getRecommend(CmsNews cmsNews);

    public CmsNews getByLinkId(CmsNews cmsNews);

    public CmsNews getContent(CmsNews cmsNews);


    public List<CmsNews> getRank(CmsNews cmsNews);

    public List<CmsNews> getSimilarByTags(CmsNews cmsNews);

    public List<CmsNews> getDailyNews(String dailyId);



}