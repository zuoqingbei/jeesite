/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户投稿Entity
 * @author wangfeng
 * @version 2018-03-13
 */
public class CmsContribute extends DataEntity<CmsContribute> {
	
	private static final long serialVersionUID = 1L;
	private String link;		// 源链接
	private String dataType;		// 投稿类型 0-资讯 1-案例 2-投资教育 3-问答
	private User user;		// 投稿用户id
	private String title;		// 标题
	private String contentHtml;		// 内容 包含HTML标签
	private String content;		// 内容 不包含HTML标签
	private String image;		// 图片
	private String keywords;		// 关键字
	private String tags;		// 标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,
	private String description;		// 描述、摘要
	private String status;		// 状态 0-草稿 1-已投稿 2-审核中 3-审核通过，发布 4-驳回
	
	public CmsContribute() {
		super();
	}

	public CmsContribute(String id){
		super(id);
	}

	@Length(min=0, max=255, message="源链接长度必须介于 0 和 255 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=1, message="投稿类型 0-资讯 1-案例 2-投资教育 3-问答长度必须介于 0 和 1 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=0, max=255, message="标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,长度必须介于 0 和 255 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Length(min=0, max=255, message="描述、摘要长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1, message="状态 0-草稿 1-已投稿 2-审核中 3-审核通过，发布 4-驳回长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}