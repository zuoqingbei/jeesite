/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.littleproject.user.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.user.entity.HubTestappInfo;

/**
 * 基础人员电话信息维护DAO接口
 * @author wangsw
 * @version 2018-05-03
 */
@MyBatisDao
public interface HubTestappInfoDao extends CrudDao<HubTestappInfo> {

	public int insertCustom(HubTestappInfo hubTestappInfo);
	
	public int deleteByPhone(String phoneNo);
	
	public HubTestappInfo getDetail(String phoneNo);
}