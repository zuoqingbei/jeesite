/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 短信验证码Entity
 * @author wangfeng
 * @version 2018-03-15
 */
public class AccountMobileCode extends DataEntity<AccountMobileCode> {
	
	private static final long serialVersionUID = 1L;
	private String mobile;		// 手机号
	private String codes;		// 验证码
	private String used;		// 是否使用过 0-未使用 1-已使用
	private String uasge;		// 短信用途 0-注册 1-找回密码 2-重置密码 3-设定支付密码 4-提现
	
	public AccountMobileCode() {
		super();
	}

	public AccountMobileCode(String id){
		super(id);
	}

	@Length(min=0, max=100, message="手机号长度必须介于 0 和 100 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=64, message="验证码长度必须介于 1 和 64 之间")
	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}
	
	@Length(min=0, max=1, message="是否使用过 0-未使用 1-已使用长度必须介于 0 和 1 之间")
	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}
	
	@Length(min=0, max=1, message="短信用途 0-注册 1-找回密码 2-重置密码 3-设定支付密码 4-提现长度必须介于 0 和 1 之间")
	public String getUasge() {
		return uasge;
	}

	public void setUasge(String uasge) {
		this.uasge = uasge;
	}
	
}