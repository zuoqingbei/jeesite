/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsDailyListContent;

import java.util.List;

/**
 * 每日一览详情DAO接口
 * @author wangfeng
 * @version 2018-03-22
 */
@MyBatisDao
public interface CmsDailyListContentDao extends CrudDao<CmsDailyListContent> {
    public List<CmsDailyListContent>  getNewsList(CmsDailyListContent cmsDailyListContent);

    public List<CmsDailyListContent>  getEductionList(CmsDailyListContent cmsDailyListContent);

    public int delByDaily(CmsDailyListContent cmsDailyListContent);
	
}