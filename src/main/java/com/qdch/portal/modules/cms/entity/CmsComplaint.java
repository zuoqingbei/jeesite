/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import com.qdch.portal.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 投诉Entity
 * @author zuoqb
 * @version 2018-03-15
 */
public class CmsComplaint extends DataEntity<CmsComplaint> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 投诉人
	private String title;		// 投诉主题
	private String image;		// 图片 多张 用逗号分开
	private String source;		// 投诉来源 公众号 PC APP
	private String companyName;		// 被投诉方名称
	private String companyAddress;		// 被投诉方地址
	private String content;		// 投诉内容描述
	
	public CmsComplaint() {
		super();
	}

	public CmsComplaint(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=500, message="投诉主题长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1000, message="图片 多张 用逗号分开长度必须介于 0 和 1000 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=64, message="投诉来源 公众号 PC APP长度必须介于 0 和 64 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=64, message="被投诉方名称长度必须介于 0 和 64 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Length(min=0, max=500, message="被投诉方地址长度必须介于 0 和 500 之间")
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}