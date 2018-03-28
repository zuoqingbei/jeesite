/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 投资教育Entity
 * @author wangfeng
 * @version 2018-03-21
 */
public class CmsEducation extends DataEntity<CmsEducation> {
	
	private static final long serialVersionUID = 1L;
	private String link;		// 源链接 如果是用户投稿 该字段保存投稿主键
	private String dataType;		// 数据来源类型 0-采集 1-用户投稿 2-管理人员发布
	private User user;		// 创建者 用户发布id  采集源也可以当做用户
	private String title;		// 标题
	private String image;		// 文章图片
	private String keywords;		// 关键字
	private String tags;		// 标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,
	private String description;		// 描述、摘要
	private String content;		// 内容 不包含HTML
	private String contentHtml;		// 内容 包含HTML
	private String videoUrl;		// 视频地址
	private String teacherId;		// 讲师 对应用户表中id
	private String weight;		// 权重，越大越靠前
	private String hits;		// 点击数、阅读数
	private String transmit;		// 转发数 分享数
	private String discess;		// 评论数  回复数
	private String praise;		// 赞数量
	private String tread;		// 踩数量
	private String collection;		// 收藏量
	private String report;		// 举报数量
	private String evaluate;		// 评价数量
	private String tip;		// 打赏次数
	private String view;		// 曝光量
	private String recommend;		// 是否推荐 0-普通 1-推荐
	private String allowComment;		// 是否允许评论 0-允许 1-不允许
	private String commentAudit;		// 评论是否需要审核 0-需要 1-不需要
	private String allowReport;		// 是否允许举报  0-允许 1-不允许
	private String undercarriage;		// 内容是否下架 0-未下架 1-已下架
	private String reason;		// 下架原因
	private String category1;		// 一级分类0-投资教育 1-案例 2-政策解读 3-攻略
	private String category2;		// 二级分类 0-视频 1-图书 2-讲座
	private String category3;		// 三级分类
	
	public CmsEducation() {
		super();
	}

	public CmsEducation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="源链接 如果是用户投稿 该字段保存投稿主键长度必须介于 0 和 255 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=1, message="数据来源类型 0-采集 1-用户投稿 2-管理人员发布长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=255, message="文章图片长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=255, message="视频地址长度必须介于 0 和 255 之间")
	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	@Length(min=0, max=64, message="讲师 对应用户表中id长度必须介于 0 和 64 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=11, message="权重，越大越靠前长度必须介于 0 和 11 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=11, message="点击数、阅读数长度必须介于 0 和 11 之间")
	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}
	
	@Length(min=0, max=11, message="转发数 分享数长度必须介于 0 和 11 之间")
	public String getTransmit() {
		return transmit;
	}

	public void setTransmit(String transmit) {
		this.transmit = transmit;
	}
	
	@Length(min=0, max=11, message="评论数  回复数长度必须介于 0 和 11 之间")
	public String getDiscess() {
		return discess;
	}

	public void setDiscess(String discess) {
		this.discess = discess;
	}
	
	@Length(min=0, max=11, message="赞数量长度必须介于 0 和 11 之间")
	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}
	
	@Length(min=0, max=11, message="踩数量长度必须介于 0 和 11 之间")
	public String getTread() {
		return tread;
	}

	public void setTread(String tread) {
		this.tread = tread;
	}
	
	@Length(min=0, max=11, message="收藏量长度必须介于 0 和 11 之间")
	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	@Length(min=0, max=11, message="举报数量长度必须介于 0 和 11 之间")
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	@Length(min=0, max=11, message="评价数量长度必须介于 0 和 11 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Length(min=0, max=11, message="打赏次数长度必须介于 0 和 11 之间")
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Length(min=0, max=11, message="曝光量长度必须介于 0 和 11 之间")
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	@Length(min=0, max=1, message="是否推荐 0-普通 1-推荐长度必须介于 0 和 1 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	@Length(min=0, max=1, message="是否允许评论 0-允许 1-不允许长度必须介于 0 和 1 之间")
	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}
	
	@Length(min=0, max=1, message="评论是否需要审核 0-需要 1-不需要长度必须介于 0 和 1 之间")
	public String getCommentAudit() {
		return commentAudit;
	}

	public void setCommentAudit(String commentAudit) {
		this.commentAudit = commentAudit;
	}
	
	@Length(min=0, max=1, message="是否允许举报  0-允许 1-不允许长度必须介于 0 和 1 之间")
	public String getAllowReport() {
		return allowReport;
	}

	public void setAllowReport(String allowReport) {
		this.allowReport = allowReport;
	}
	
	@Length(min=0, max=1, message="内容是否下架 0-未下架 1-已下架长度必须介于 0 和 1 之间")
	public String getUndercarriage() {
		return undercarriage;
	}

	public void setUndercarriage(String undercarriage) {
		this.undercarriage = undercarriage;
	}
	
	@Length(min=0, max=255, message="下架原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
//	@Length(min=1, max=64, message="一级分类0-投资教育 1-案例 2-政策解读 3-攻略长度必须介于 1 和 64 之间")
	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	
	@Length(min=0, max=64, message="二级分类 0-视频 1-图书 2-讲座长度必须介于 0 和 64 之间")
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
	
}