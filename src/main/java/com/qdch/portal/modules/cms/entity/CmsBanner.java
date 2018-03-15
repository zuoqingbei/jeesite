/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;
import com.qdch.portal.common.utils.Json;

/**
 * 轮播图管理Entity
 * @author zuoqb
 * @version 2018-03-13
 */
public class CmsBanner extends DataEntity<CmsBanner> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 文字标题/描述
	private String content;
	@Json
	private String image;		// 图片
	private String linkUrl;		// 打开地址
	private String target;		// 打开方式 _blank-在新窗口中打开 _self-当前页面
	private String isTop;		// 是否置顶（推荐）0-不推荐 1-推荐
	private String orderNo;		// 排序
	private Date beginCreateDate;		// 开始 公告发布日期
	private Date endCreateDate;		// 结束 公告发布日期
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CmsBanner() {
		super();
	}

	public CmsBanner(String id){
		super(id);
	}

	@Length(min=0, max=255, message="文字标题/描述长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=500, message="打开地址长度必须介于 0 和 500 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(min=0, max=1, message="是否置顶（推荐）0-不推荐 1-推荐长度必须介于 0 和 1 之间")
	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
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

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
}