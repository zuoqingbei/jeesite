/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 每日一览详情Entity
 * @author wangfeng
 * @version 2018-03-22
 */
public class CmsDailyListContent extends DataEntity<CmsDailyListContent> {
	
	private static final long serialVersionUID = 1L;
	private String tableName;		// 表名
	private String cmsId;		// id

	private String dailyId;

	private CmsNews cmsNews;
	private  CmsEducation cmsEducation;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDailyId() {
		return dailyId;
	}

	public void setDailyId(String dailyId) {
		this.dailyId = dailyId;
	}

	public CmsDailyListContent() {
		super();
	}

	public CmsDailyListContent(String id){
		super(id);
	}

	@Length(min=0, max=255, message="表名长度必须介于 0 和 255 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Length(min=0, max=64, message="id长度必须介于 0 和 64 之间")
	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}
	
}