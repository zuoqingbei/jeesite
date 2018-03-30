/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 活动报名记录Entity
 * @author lianjiming
 * @version 2018-03-28
 */
public class CmsActivityEnter extends DataEntity<CmsActivityEnter> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动ID
	private User user;		// 报名人员id
	private String sign;		// 活动开始后签到情况 0-未签到 1-已签到
	
	public CmsActivityEnter() {
		super();
	}

	public CmsActivityEnter(String id){
		super(id);
	}

	@Length(min=1, max=64, message="活动ID长度必须介于 1 和 64 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="活动开始后签到情况 0-未签到 1-已签到长度必须介于 0 和 1 之间")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}