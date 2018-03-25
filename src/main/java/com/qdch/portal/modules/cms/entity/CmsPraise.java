/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户赞 踩记录Entity
 * @author wangfeng
 * @version 2018-03-15
 */
public class CmsPraise extends DataEntity<CmsPraise> {
	
	private static final long serialVersionUID = 1L;
	private String sourceId;		// 赞踩内容源id
	private String sourceTable;		// 内容来源表 比如 资讯 政策解读 攻略为portal_news
	private User user;		// 用户id
	private String operateType;		// 操作类型 0-踩 1-赞

	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public CmsPraise() {
		super();
	}

	public CmsPraise(String id){
		super(id);
	}

	@Length(min=1, max=64, message="赞踩内容源id长度必须介于 1 和 64 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Length(min=1, max=64, message="内容来源表 比如 资讯 政策解读 攻略为portal_news长度必须介于 1 和 64 之间")
	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="操作类型 0-踩 1-赞长度必须介于 0 和 1 之间")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
}