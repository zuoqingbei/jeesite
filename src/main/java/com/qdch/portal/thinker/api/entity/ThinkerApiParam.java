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
public class ThinkerApiParam extends DataEntity<ThinkerApiParam> {
	
	private static final long serialVersionUID = 1L;
	private String apiId;		// api主键 父类
	private String name;		// 参数名称
	private String dataType;		// 参数类型
	private String required;		// 是否必填 0-非必填 1-必填
	
	public ThinkerApiParam() {
		super();
	}

	public ThinkerApiParam(String id){
		super(id);
	}
	public ThinkerApiParam(String apiId,String name){
		this.apiId=apiId;
	}
	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public void setApiId(ThinkerApi apiId) {
		this.apiId = apiId.getId();
	}
	
	@Length(min=1, max=100, message="参数名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="参数类型长度必须介于 1 和 64 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=1, max=1, message="是否必填 0-非必填 1-必填长度必须介于 1 和 1 之间")
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
	
}