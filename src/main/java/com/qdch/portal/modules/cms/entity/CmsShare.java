/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 用户分享记录Entity
 * @author wangfeng
 * @version 2018-03-15
 */
public class CmsShare extends DataEntity<CmsShare> {
	
	private static final long serialVersionUID = 1L;
	private String sourceId;		// 分享内容源id
	private String sourceTable;		// 内容来源表 比如 资讯 政策解读 攻略为portal_news
	private User user;		// 分享用户id
	private String platform;		// 分享平台  比如QQ、微信
	private String title;		// 分享标题
	private String url;		// 分享地址

	private String count ;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public CmsShare() {
		super();
	}

	public CmsShare(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分享内容源id长度必须介于 1 和 64 之间")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Length(min=1, max=64, message="内容来源表 比如 资讯 政策解读 攻略为portal_news长度必须介于 1 和 64 之间")
	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="分享平台  比如QQ、微信长度必须介于 0 和 100 之间")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	@Length(min=0, max=255, message="分享标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="分享地址长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}