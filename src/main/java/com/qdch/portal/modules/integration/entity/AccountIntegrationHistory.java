/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.integration.entity;

import com.qdch.portal.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户活跃度记录Entity
 * @author lianjiming
 * @version 2018-03-23
 */
public class AccountIntegrationHistory extends DataEntity<AccountIntegrationHistory> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String allIntegration;		// 变更后积分余额
	private String nums;		// 变更分值
	private String reason;		// 变更原因
	
	private String loginName;	//用户登录名
	
	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public AccountIntegrationHistory() {
		super();
	}

	public AccountIntegrationHistory(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=11, message="变更后积分余额长度必须介于 0 和 11 之间")
	public String getAllIntegration() {
		return allIntegration;
	}

	public void setAllIntegration(String allIntegration) {
		this.allIntegration = allIntegration;
	}
	
	@Length(min=1, max=11, message="变更分值长度必须介于 1 和 11 之间")
	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}
	
	@Length(min=0, max=255, message="变更原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}