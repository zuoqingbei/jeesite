/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 资讯详表Entity
 * @author wangfeng
 * @version 2018-03-13
 */
public class CmsNewsData extends DataEntity<CmsNewsData> {
	
	private static final long serialVersionUID = 1L;
	private String newsId;		// 源数据ID
	private String content;		// 文章内容 不包含HTML
	private String contentHtml;		// 文章内容 包含HTML
	private String title;
	private String titleid;
	
	public CmsNewsData() {
		super();
	}

	public CmsNewsData(String id){
		super(id);
	}

	@Length(min=0, max=64, message="源数据ID长度必须介于 0 和 64 之间")
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleid() {
		return titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}
	
	
	
}