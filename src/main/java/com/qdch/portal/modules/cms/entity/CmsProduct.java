/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 产品管理Entity
 * @author zuoqb
 * @version 2018-03-31
 */
public class CmsProduct extends DataEntity<CmsProduct> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 产品名称
	private String smalImage;		// 缩略图
	private String image;		// 文章图片
	private String keywords;		// 关键字
	private String tags;		// 产品分类
	private String description;		// 描述、摘要
	private String content;		// 内容 不包含HTML
	private String contentHtml;		// 内容 包含HTML
	private String recommend;		// 是否推荐 0-普通 1-推荐
	private Integer orderNo;		// 排序
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public CmsProduct() {
		super();
	}

	public CmsProduct(String id){
		super(id);
	}

	@Length(min=1, max=255, message="产品名称长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="缩略图长度必须介于 0 和 255 之间")
	public String getSmalImage() {
		return smalImage;
	}

	public void setSmalImage(String smalImage) {
		this.smalImage = smalImage;
	}
	
	@Length(min=0, max=1000, message="文章图片长度必须介于 0 和 1000 之间")
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
	
	@Length(min=0, max=255, message="产品分类长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=1, message="是否推荐 0-普通 1-推荐长度必须介于 0 和 1 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}