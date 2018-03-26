/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import com.qdch.portal.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 推荐Entity
 * @author wangfeng
 * @version 2018-03-24
 */
public class ViewRecommend extends DataEntity<ViewRecommend> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// user_id
	private String name;		// name
	private String photo;		// photo
	private String title;		// title
	private String image;		// image
	private String tags;		// tags
	private String description;		// description
	private String weight;		// weight
	private String hits;		// hits
	private String transmit;		// transmit
	private String discess;		// discess
	private String praise;		// praise
	private String tread;		// tread
	private String collection;		// collection
	private String report;		// report
	private String evaluate;		// evaluate
	private String answer;		// answer
	private String tip;		// tip
	private String category;		// category
	private String tagslabel;

	public String getTagslabel() {
		return tagslabel;
	}

	public void setTagslabel(String tagslabel) {
		this.tagslabel = tagslabel;
	}

	public ViewRecommend() {
		super();
	}

	public ViewRecommend(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=1, max=255, message="title长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="image长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="tags长度必须介于 0 和 255 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Length(min=0, max=255, message="description长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=11, message="weight长度必须介于 0 和 11 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=11, message="hits长度必须介于 0 和 11 之间")
	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}
	
	@Length(min=0, max=11, message="transmit长度必须介于 0 和 11 之间")
	public String getTransmit() {
		return transmit;
	}

	public void setTransmit(String transmit) {
		this.transmit = transmit;
	}
	
	@Length(min=0, max=11, message="discess长度必须介于 0 和 11 之间")
	public String getDiscess() {
		return discess;
	}

	public void setDiscess(String discess) {
		this.discess = discess;
	}
	
	@Length(min=0, max=11, message="praise长度必须介于 0 和 11 之间")
	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}
	
	@Length(min=0, max=11, message="tread长度必须介于 0 和 11 之间")
	public String getTread() {
		return tread;
	}

	public void setTread(String tread) {
		this.tread = tread;
	}
	
	@Length(min=0, max=11, message="collection长度必须介于 0 和 11 之间")
	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	@Length(min=0, max=11, message="report长度必须介于 0 和 11 之间")
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	@Length(min=0, max=11, message="evaluate长度必须介于 0 和 11 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Length(min=0, max=11, message="answer长度必须介于 0 和 11 之间")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Length(min=0, max=11, message="tip长度必须介于 0 和 11 之间")
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Length(min=0, max=4, message="category长度必须介于 0 和 4 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}