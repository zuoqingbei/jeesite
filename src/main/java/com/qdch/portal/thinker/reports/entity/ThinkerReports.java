/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.reports.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 报表管理Entity
 * @author zuoqb
 * @version 2018-04-12
 */
public class ThinkerReports extends DataEntity<ThinkerReports> {
	
	private static final long serialVersionUID = 1L;
	private String systemName;		// 显示系统
	private String link;		// 显示系统连接地址
	private String openType;		// 打开类型 _balnk _target
	private String name;		// 名称
	private String types;		// 报表类型 字典表
	private String path;		// 报表路径
	private String url;		// 报表地址
	private String category1;		// category1
	private String category2;		// category2
	private String category3;		// category3
	private String dimension;		// 维度
	private String tags;		// 标签
	
	public ThinkerReports() {
		super();
	}

	public ThinkerReports(String id){
		super(id);
	}

	@Length(min=1, max=64, message="显示系统长度必须介于 1 和 64 之间")
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	@Length(min=1, max=400, message="显示系统连接地址长度必须介于 1 和 400 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=64, message="打开类型 _balnk _target长度必须介于 0 和 64 之间")
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="报表类型 字典表长度必须介于 1 和 64 之间")
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
	@Length(min=0, max=400, message="报表路径长度必须介于 0 和 400 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=400, message="报表地址长度必须介于 0 和 400 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=400, message="category1长度必须介于 0 和 400 之间")
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
	
	@Length(min=0, max=400, message="维度长度必须介于 0 和 400 之间")
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	@Length(min=0, max=400, message="标签长度必须介于 0 和 400 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}