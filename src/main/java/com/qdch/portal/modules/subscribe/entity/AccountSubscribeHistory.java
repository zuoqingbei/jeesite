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
	private String user;		// 用户id
	private String labId;		// 来自字典表 标签id
	
	private String lgoinName;	//用于显示登录名
	private String label;		//用于显示订阅标签

	
	public String getLgoinName() {
		return lgoinName;
	}

	public void setLgoinName(String lgoinName) {
		this.lgoinName = lgoinName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AccountSubscribeHistory() {
		super();
	}

	public AccountSubscribeHistory(String id){
		super(id);
	}

	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setLabId(String labId) {
		this.labId = labId;
	}

	@Length(min=1, max=64, message="来自字典表 标签id长度必须介于 1 和 64 之间")
	public String getLabId() {
		return labId;
	}




	
}