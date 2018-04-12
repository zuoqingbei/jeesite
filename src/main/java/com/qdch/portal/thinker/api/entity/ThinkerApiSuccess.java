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
public class ThinkerApiSuccess extends DataEntity<ThinkerApiSuccess> {
	
	private static final long serialVersionUID = 1L;
	private String apiId;		// api主键 父类
	private String types;		// 返回类型 json/xml
	private String name;		// 参数名称
	private String dataType;		// 结果类型 String int char
	private String descs;		// 错误描述
	
	public ThinkerApiSuccess() {
		super();
	}

	public ThinkerApiSuccess(String id){
		super(id);
	}
	public ThinkerApiSuccess(String apiId,String name){
		this.apiId = apiId;
	}
	
	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	@Length(min=0, max=64, message="返回类型 json/xml长度必须介于 0 和 64 之间")
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
	@Length(min=1, max=20, message="参数名称长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="结果类型 String int char长度必须介于 0 和 20 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=0, max=200, message="错误描述长度必须介于 0 和 200 之间")
	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}
	
}