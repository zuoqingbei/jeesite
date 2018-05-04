/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.littleproject.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.littleproject.user.entity.HubTestappInfo;
import com.qdch.portal.littleproject.user.dao.HubTestappInfoDao;

/**
 * 基础人员电话信息维护Service
 * @author wangsw
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class HubTestappInfoService extends CrudService<HubTestappInfoDao, HubTestappInfo> {

	@Autowired
	protected HubTestappInfoDao hubTestappInfoDao;
	
	public HubTestappInfo get(HubTestappInfo hubTestappInfo) {
		return hubTestappInfoDao.get(hubTestappInfo);
	}
	
	public List<HubTestappInfo> findList(HubTestappInfo hubTestappInfo) {
		return super.findList(hubTestappInfo);
	}
	
	public Page<HubTestappInfo> findPage(Page<HubTestappInfo> page, HubTestappInfo hubTestappInfo) {
		return super.findPage(page, hubTestappInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(HubTestappInfo hubTestappInfo) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = df.format(date);
		hubTestappInfo.setCreateTime(d);
		hubTestappInfo.setUpdateTime(d);
		hubTestappInfoDao.insert(hubTestappInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(HubTestappInfo hubTestappInfo) {
		hubTestappInfoDao.deleteByPhone(hubTestappInfo.getFtel());
	}
	
	public HubTestappInfo getDetail(String phoneNo) {
		return hubTestappInfoDao.getDetail(phoneNo);
	}
	
}