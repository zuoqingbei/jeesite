/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.ViewRecommend;

import javax.swing.text.View;
import java.util.List;

/**
 * 推荐DAO接口
 * @author wangfeng
 * @version 2018-03-24
 */
@MyBatisDao
public interface ViewRecommendDao extends CrudDao<ViewRecommend> {

    public List<ViewRecommend> getRecommend(ViewRecommend recommend);
	
}