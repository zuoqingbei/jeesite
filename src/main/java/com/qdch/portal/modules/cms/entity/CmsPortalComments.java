/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qdch.portal.common.utils.Json;
import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 门户评论Entity
 * @author wangfeng
 * @version 2018-03-20
 */
public class CmsPortalComments extends DataEntity<CmsPortalComments> {
	
	private static final long serialVersionUID = 1L;
	private CmsPortalComments parent;		// 评论父级ID，如果直接回复作者为-1
	private String parentIds;		// 所有父级id用逗号拼接，并且以逗号开头结尾 比如 ,1,2,
	private String sourceId;		// 评论内容源id
	private String sourceTable;		// 内容来源表 比如 资讯 政策解读 攻略为portal_news
	@Json
	private String content;		// 评论内容
	@Json
	private User user;		// 评论姓名
	private String ip;		// 评论IP
	private String auditUserId;		// 审核人
	private Date auditDate;		// 审核时间

	@Json
	private String tradeCount; //踩的数量

	@Json
	private String praiseCount; //赞的数量

	@Json
	private String  commentsCount; //评论的数量


	public String getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(String commentsCount) {
		this.commentsCount = commentsCount;
	}

	@Json
	private List<CmsPortalComments>  commentsList; //子评论

	public List<CmsPortalComments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<CmsPortalComments> commentsList) {
		this.commentsList = commentsList;
	}



	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	private String parentId;

	private  String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(String praiseCount) {
		this.praiseCount = praiseCount;
	}

	public CmsPortalComments() {
		super();
	}

	public CmsPortalComments(String id){
		super(id);
	}

	@JsonBackReference
	public CmsPortalComments getParent() {
		return parent;
	}

	public void setParent(CmsPortalComments parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=255, message="所有父级id用逗号拼接，并且以逗号开头结尾 比如 ,1,2,长度必须介于 0 和 255 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=64, message="评论内容源id长度必须介于 1 和 64 之间")
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
	
	@Length(min=0, max=255, message="评论内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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