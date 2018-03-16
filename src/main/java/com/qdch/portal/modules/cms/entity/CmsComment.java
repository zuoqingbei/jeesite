/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 评论表Entity
 * @author wangfeng
 * @version 2018-03-15
 */
public class CmsComment extends DataEntity<CmsComment> {
	
	private static final long serialVersionUID = 1L;
	private String categoryId;		// 栏目编号
	private String contentId;		// 栏目内容的编号
	private String title;		// 栏目内容的标题
	private String content;		// 评论内容
	private String name;		// 评论姓名
	private String ip;		// 评论IP
	private String auditUserId;		// 审核人
	private Date auditDate;		// 审核时间
	
	public CmsComment() {
		super();
	}

	public CmsComment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="栏目编号长度必须介于 1 和 64 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=1, max=64, message="栏目内容的编号长度必须介于 1 和 64 之间")
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	@Length(min=0, max=255, message="栏目内容的标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="评论内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=100, message="评论姓名长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="评论IP长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=64, message="审核人长度必须介于 0 和 64 之间")
	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}