/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.api.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * api管理Entity
 * @author zuoqb
 * @version 2018-04-12
 */
public class ThinkerApi extends DataEntity<ThinkerApi> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String https;		// 是否支持https  0-不支持 1-支持
	private String descs;		// 描述
	private String dataId;		// 数据ID
	private String appNum;		// 应用数
	private String status;		// 接口状态 0-正常 1-故障
	private String category1;		// 分类
	private String category2;		// category2
	private String category3;		// category3
	private String tags;		// 标签
	private String url;		// 接口地址
	private String requestType;		// 请求类型
	private String dataType;		// 数据类型 json xml其实可以支持多种
	private String demoUrl;		// 样例地址
	private String jsonDemo;		// json返回样例 html格式
	private String xmlDemo;		// xml返回样例 html格式
	private List<ThinkerApiErrorcode> thinkerApiErrorcodeList = Lists.newArrayList();		// 子表列表
	private List<ThinkerApiParam> thinkerApiParamList = Lists.newArrayList();		// 子表列表
	private List<ThinkerApiSuccess> thinkerApiSuccessList = Lists.newArrayList();		// 子表列表
	
	public ThinkerApi() {
		super();
	}

	public ThinkerApi(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="是否支持https  0-不支持 1-支持长度必须介于 1 和 1 之间")
	public String getHttps() {
		return https;
	}

	public void setHttps(String https) {
		this.https = https;
	}
	
	@Length(min=0, max=1000, message="描述长度必须介于 0 和 1000 之间")
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
	@Length(min=1, max=64, message="数据ID长度必须介于 1 和 64 之间")
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	@Length(min=0, max=10, message="应用数长度必须介于 0 和 10 之间")
	public String getAppNum() {
		return appNum;
	}

	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	
	@Length(min=1, max=1, message="接口状态 0-正常 1-故障长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	@Length(min=0, max=20, message="请求类型长度必须介于 0 和 20 之间")
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	@Length(min=0, max=20, message="数据类型 json xml其实可以支持多种长度必须介于 0 和 20 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=1, max=400, message="样例地址长度必须介于 1 和 400 之间")
	public String getDemoUrl() {
		return demoUrl;
	}

	public void setDemoUrl(String demoUrl) {
		this.demoUrl = demoUrl;
	}
	
	public String getJsonDemo() {
		return jsonDemo;
	}

	public void setJsonDemo(String jsonDemo) {
		this.jsonDemo = jsonDemo;
	}
	
	public String getXmlDemo() {
		return xmlDemo;
	}

	public void setXmlDemo(String xmlDemo) {
		this.xmlDemo = xmlDemo;
	}
	
	public List<ThinkerApiErrorcode> getThinkerApiErrorcodeList() {
		return thinkerApiErrorcodeList;
	}

	public void setThinkerApiErrorcodeList(List<ThinkerApiErrorcode> thinkerApiErrorcodeList) {
		this.thinkerApiErrorcodeList = thinkerApiErrorcodeList;
	}
	public List<ThinkerApiParam> getThinkerApiParamList() {
		return thinkerApiParamList;
	}

	public void setThinkerApiParamList(List<ThinkerApiParam> thinkerApiParamList) {
		this.thinkerApiParamList = thinkerApiParamList;
	}
	public List<ThinkerApiSuccess> getThinkerApiSuccessList() {
		return thinkerApiSuccessList;
	}

	public void setThinkerApiSuccessList(List<ThinkerApiSuccess> thinkerApiSuccessList) {
		this.thinkerApiSuccessList = thinkerApiSuccessList;
	}
}