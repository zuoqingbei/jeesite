/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.littleproject.user.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;
import com.qdch.portal.modules.sys.entity.User;

/**
 * 基础人员电话信息维护Entity
 * @author wangsw
 * @version 2018-05-03
 */
public class HubTestappInfo extends DataEntity<HubTestappInfo> {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// 姓名
	private String ftel;		// 电话
	private String status;		// 状态
	private String createTime;	// 创建日期
	private String updateTime;	// 更新日期
	
	public HubTestappInfo() {
		super();
	}

	public HubTestappInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="姓名长度必须介于 0 和 60 之间")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Length(min=0, max=20, message="电话长度必须介于 0 和 20 之间")
	public String getFtel() {
		return ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}