/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 活动日程Entity
 * @author wangfeng
 * @version 2018-03-21
 */
public class CmsActivityFlow extends DataEntity<CmsActivityFlow> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动ID
	private String title;		// 标题
	private String images;		// 图片
	private Date flowStartTime;		// 当前流程开始时间
	private Date flowEndTime;		// 流程结束时间
	private String orderNum;		// 流程排序
	private String address;		// 地址
	private String descs;		// 当前流程内容介绍 支持HTML
	
	public CmsActivityFlow() {
		super();
	}

	public CmsActivityFlow(String id){
		super(id);
	}

	@Length(min=1, max=64, message="活动ID长度必须介于 1 和 64 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFlowStartTime() {
		return flowStartTime;
	}

	public void setFlowStartTime(Date flowStartTime) {
		this.flowStartTime = flowStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFlowEndTime() {
		return flowEndTime;
	}

	public void setFlowEndTime(Date flowEndTime) {
		this.flowEndTime = flowEndTime;
	}
	
	@Length(min=0, max=11, message="流程排序长度必须介于 0 和 11 之间")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
}