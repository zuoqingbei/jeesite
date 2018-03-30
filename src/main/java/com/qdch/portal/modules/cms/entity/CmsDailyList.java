/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 每日一览Entity
 * @author wangfeng
 * @version 2018-03-22
 */
public class CmsDailyList extends DataEntity<CmsDailyList> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String image;		// 图片
	private String newids; //资讯
	private String activityids;
	private String educationids; //案例

	public String getEducationids() {
		return educationids;
	}

	public void setEducationids(String educationids) {
		this.educationids = educationids;
	}

	public String getNewids() {
		return newids;
	}

	public void setNewids(String newids) {
		this.newids = newids;
	}

	public String getActivityids() {
		return activityids;
	}

	public void setActivityids(String activityids) {
		this.activityids = activityids;
	}

	public CmsDailyList() {
		super();
	}

	public CmsDailyList(String id){
		super(id);
	}

	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}