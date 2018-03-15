/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsBanner;

/**
 * 轮播图管理DAO接口
 * @author zuoqb
 * @version 2018-03-13
 */
@MyBatisDao
public interface CmsBannerDao extends CrudDao<CmsBanner> {
	public List<CmsBanner> findBannerList(@Param("offset") int offset, @Param("limit") int limit);
}