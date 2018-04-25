package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class BusinessInfoModel extends DataEntity<BusinessInfoModel> {
	private String legal_person;// 法定代表人
	private String create_date;// 创建日期
	private double register_money;// 注册资本
	private String register_code;// 工商注册编号
	private String organizition_code;// 组织机构代码
	private String credit_code;// 统一信用代码
	private String taxpayer_num;// 纳税人识别号
	private String english_name;// 英文名
	private String business_status;// 经营状态
	private String company_type;// 企业类型

	private String industry; // 行业

	private String business_limit;// 营业期限

	private String publish_date;// 核准日期

	private String register_address;// 企业地址
	private String business_scope;// 经营范围

	public String getLegal_person() {
		return legal_person;
	}

	public void setLegal_person(String legal_person) {
		this.legal_person = legal_person;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public double getRegister_money() {
		return register_money;
	}

	public void setRegister_money(double register_money) {
		this.register_money = register_money;
	}

	public String getRegister_code() {
		return register_code;
	}

	public void setRegister_code(String register_code) {
		this.register_code = register_code;
	}

	public String getOrganizition_code() {
		return organizition_code;
	}

	public void setOrganizition_code(String organizition_code) {
		this.organizition_code = organizition_code;
	}

	public String getCredit_code() {
		return credit_code;
	}

	public void setCredit_code(String credit_code) {
		this.credit_code = credit_code;
	}

	public String getTaxpayer_num() {
		return taxpayer_num;
	}

	public void setTaxpayer_num(String taxpayer_num) {
		this.taxpayer_num = taxpayer_num;
	}

	public String getEnglish_name() {
		return english_name;
	}

	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusiness_limit() {
		return business_limit;
	}

	public void setBusiness_limit(String business_limit) {
		this.business_limit = business_limit;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public String getRegister_address() {
		return register_address;
	}

	public void setRegister_address(String register_address) {
		this.register_address = register_address;
	}

	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	public String getBusiness_status() {
		return business_status;
	}

	public void setBusiness_status(String business_status) {
		this.business_status = business_status;
	}

}
