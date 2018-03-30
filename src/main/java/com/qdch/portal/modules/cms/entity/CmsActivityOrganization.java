/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 活动Entity
 * @author lianjiming
 * @version 2018-03-29
 */
public class CmsActivityOrganization extends DataEntity<CmsActivityOrganization> {
	
	private static final long serialVersionUID = 1L;
	private CmsActivity activityId;		// 活动ID 父类
	private String type;		// 类型 0-主办方 1-承办方 2-技术支持/讲师
	private String logo;		// 图标
	private Date webUrl;		// 企业网站
	private Date name;		// 企业单位
	private String address;		// 企业地址
	private String descs;		// 企业简介
	private String contact;		// 联系人姓名
	private String contactMobile;		// 联系人手机号
	private Integer orderNum;		// 企业排序
	
	public CmsActivityOrganization() {
		super();
	}

	public CmsActivityOrganization(String id){
		super(id);
	}

	public CmsActivityOrganization(CmsActivity activityId){
		this.activityId = activityId;
	}

	@Length(min=1, max=64, message="活动ID长度必须介于 1 和 64 之间")
	public CmsActivity getActivityId() {
		return activityId;
	}

	public void setActivityId(CmsActivity activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=1, message="类型 0-主办方 1-承办方 2-技术支持/讲师长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(Date webUrl) {
		this.webUrl = webUrl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getName() {
		return name;
	}

	public void setName(Date name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="企业地址长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=255, message="联系人姓名长度必须介于 0 和 255 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=255, message="联系人手机号长度必须介于 0 和 255 之间")
	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}