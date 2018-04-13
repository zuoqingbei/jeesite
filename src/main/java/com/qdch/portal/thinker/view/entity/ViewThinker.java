/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.view.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * api报表指标视图数据Entity
 * @author zuoqb
 * @version 2018-04-12
 */
public class ViewThinker extends DataEntity<ViewThinker> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String descs;		// 描述
	private String category1;		// 分类
	private String category2;		// category2
	private String category3;		// category3
	private String tags;		// 标签
	private String url;		// 接口地址
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Integer viewNum;		// 浏览量
	private Integer zanNum;		// 点赞量
	private Integer shareNum;		// 分享数
	public ViewThinker() {
		super();
	}

	public ViewThinker(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="描述长度必须介于 0 和 1000 之间")
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
	@Length(min=0, max=64, message="分类长度必须介于 0 和 64 之间")
	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	
	@Length(min=0, max=64, message="category2长度必须介于 0 和 64 之间")
	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
	@Length(min=0, max=64, message="category3长度必须介于 0 和 64 之间")
	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	
	@Length(min=0, max=400, message="标签长度必须介于 0 和 400 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Length(min=1, max=400, message="接口地址长度必须介于 1 和 400 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getZanNum() {
		return zanNum;
	}

	public void setZanNum(Integer zanNum) {
		this.zanNum = zanNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
		
}