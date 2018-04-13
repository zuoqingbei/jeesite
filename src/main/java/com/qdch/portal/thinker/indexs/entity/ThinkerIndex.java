/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.indexs.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 指标管理Entity
 * @author zuoqb
 * @version 2018-04-13
 */
public class ThinkerIndex extends DataEntity<ThinkerIndex> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String code;		// 指标编码
	private String screenNo;		// 屏幕编号
	private String screenName;		// 屏幕名称
	private String screenUrl;		// 屏幕地址
	private String showType;		// 展示形式
	private String descs;		// 指标描述
	private String showTable;		// 显示表
	private String useFiled;		// 使用字段
	private String expression;		// 计算公式
	private String category1;		// 一级分类
	private String category2;		// 二级分类
	private String category3;		// 三级分类
	private String tags;		// 标签
	private Integer viewNum;		// 浏览量
	private Integer zanNum;		// 点赞量
	private Integer shareNum;		// 分享数
	
	public ThinkerIndex() {
		super();
	}

	public ThinkerIndex(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="指标编码长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=64, message="屏幕编号长度必须介于 1 和 64 之间")
	public String getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	
	@Length(min=0, max=100, message="屏幕名称长度必须介于 0 和 100 之间")
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	@Length(min=1, max=400, message="屏幕地址长度必须介于 1 和 400 之间")
	public String getScreenUrl() {
		return screenUrl;
	}

	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
	}
	
	@Length(min=0, max=100, message="展示形式长度必须介于 0 和 100 之间")
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}
	
	@Length(min=0, max=1000, message="指标描述长度必须介于 0 和 1000 之间")
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
	@Length(min=0, max=400, message="显示表长度必须介于 0 和 400 之间")
	public String getShowTable() {
		return showTable;
	}

	public void setShowTable(String showTable) {
		this.showTable = showTable;
	}
	
	@Length(min=0, max=400, message="使用字段长度必须介于 0 和 400 之间")
	public String getUseFiled() {
		return useFiled;
	}

	public void setUseFiled(String useFiled) {
		this.useFiled = useFiled;
	}
	
	@Length(min=0, max=1000, message="计算公式长度必须介于 0 和 1000 之间")
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	@Length(min=0, max=64, message="一级分类长度必须介于 0 和 64 之间")
	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	
	@Length(min=0, max=64, message="二级分类长度必须介于 0 和 64 之间")
	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
	@Length(min=0, max=64, message="三级分类长度必须介于 0 和 64 之间")
	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	
	@Length(min=0, max=64, message="标签长度必须介于 0 和 64 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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