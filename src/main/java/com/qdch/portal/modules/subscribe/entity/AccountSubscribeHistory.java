/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.subscribe.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户订阅历史Entity
 * @author lianjiming
 * @version 2018-03-19
 */
public class AccountSubscribeHistory extends DataEntity<AccountSubscribeHistory> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String labId;		// 来自字典表 标签id
	
	public AccountSubscribeHistory() {
		super();
	}

	public AccountSubscribeHistory(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	@Length(min=1, max=64, message="来自字典表 标签id长度必须介于 1 和 64 之间")
	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}
	
}