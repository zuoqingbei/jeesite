/**
a * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户关注Entity
 * @author lianjiming
 * @version 2018-03-12
 */
public class AccountAttention extends DataEntity<AccountAttention> {
	
	private static final long serialVersionUID = 1L;
	private String fromUser;		// 用户id 关注方
	private String toUser;			// 用户id 被关注方
	
	//后添加属性 与sys_user表关联查询时需要(显示登录名)
	private String fromUserName;	//关注方登录名
	private String toUserName;		//被关注方登录名
	
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public AccountAttention() {
		super();
	}

	public AccountAttention(String id){
		super(id);
	}

	@Length(min=1, max=100, message="用户id 关注方长度必须介于 1 和 100 之间")
	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	@Length(min=1, max=64, message="被关注方长度必须介于 1 和 64 之间")
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
}