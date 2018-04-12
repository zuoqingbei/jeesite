/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.api.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * api管理Entity
 * @author zuoqb
 * @version 2018-04-12
 */
public class ThinkerApiErrorcode extends DataEntity<ThinkerApiErrorcode> {
	
	private static final long serialVersionUID = 1L;
	private String apiId;		// api主键 父类
	private String types;		// 错误分类 0-系统错误 1-参数错误 2-服务错误
	private String errorCode;		// 状态码
	private String descs;		// 错误描述
	private String dealMethod;		// 解决办法 建议
	
	public ThinkerApiErrorcode() {
		super();
	}

	public ThinkerApiErrorcode(String id){
		super(id);
	}

	public ThinkerApiErrorcode(String apiId,String types){
		this.apiId=apiId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	@Length(min=0, max=64, message="错误分类 0-系统错误 1-参数错误 2-服务错误长度必须介于 0 和 64 之间")
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
	@Length(min=1, max=20, message="状态码长度必须介于 1 和 20 之间")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@Length(min=0, max=200, message="错误描述长度必须介于 0 和 200 之间")
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
	@Length(min=0, max=200, message="解决办法 建议长度必须介于 0 和 200 之间")
	public String getDealMethod() {
		return dealMethod;
	}

	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}
	
}