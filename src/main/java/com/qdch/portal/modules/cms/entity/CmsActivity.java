/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import com.qdch.portal.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 活动Entity
 * @author wangfeng
 * @version 2018-03-21
 */
public class CmsActivity extends DataEntity<CmsActivity> {
	
	private static final long serialVersionUID = 1L;
	private String dataType;		// 类型 0-线下活动 1-线上活动
	private User user;		// 活动创建人
	private String title;		// 标题
	private String image;		// 文章图片
	private String keywords;		// 关键字
	private String tags;		// 标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,
	private String description;		// 描述、摘要
	private String content;		// 活动内容 不含HTML
	private String contentHtml;		// 活动内容 含HTML
	private String weight;		// 权重，越大越靠前
	private String hits;		// 点击数、阅读数
	private String transmit;		// 转发数 分享数
	private String discess;		// 评论数  回复数
	private String praise;		// 赞数量
	private String tread;		// 踩数量
	private String collection;		// 收藏量
	private String evaluate;		// 评价数量
	private String enter;		// 报名人数
	private String view;		// 曝光量
	private String recommend;		// 是否推荐 0-普通 1-推荐
	private String allowComment;		// 是否允许评论 0-允许 1-不允许
	private String leader;		// 活动主办方
	private String address;		// 活动举办地点
	private Date startDate;		// 举办开始时间
	private Date endDate;		// 活动结束时间
	private Date enterStartDate;		// 报名开始时间
	private Date enterEndDate;		// 报名结束时间
	private String category1;		// 一级分类
	private String category2;		// 二级分类
	private String category3;		// 三级分类
	
	public CmsActivity() {
		super();
	}

	public CmsActivity(String id){
		super(id);
	}

	@Length(min=0, max=1, message="类型 0-线下活动 1-线上活动长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=11, message="评价数量长度必须介于 0 和 11 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Length(min=0, max=11, message="报名人数长度必须介于 0 和 11 之间")
	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
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
	
	@Length(min=0, max=255, message="活动主办方长度必须介于 0 和 255 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=255, message="活动举办地点长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnterStartDate() {
		return enterStartDate;
	}

	public void setEnterStartDate(Date enterStartDate) {
		this.enterStartDate = enterStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnterEndDate() {
		return enterEndDate;
	}

	public void setEnterEndDate(Date enterEndDate) {
		this.enterEndDate = enterEndDate;
	}
	
	@Length(min=1, max=64, message="一级分类长度必须介于 1 和 64 之间")
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
	
}