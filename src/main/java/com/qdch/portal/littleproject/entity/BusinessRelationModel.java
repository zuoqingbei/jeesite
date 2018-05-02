package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class BusinessRelationModel extends DataEntity<BusinessRelationModel>{
private String company_name;
private String legal_person;
private String senior_managers;
private String shareholders;
public String getCompany_name() {
	return company_name;
}
public void setCompany_name(String company_name) {
	this.company_name = company_name;
}
public String getLegal_person() {
	return legal_person;
}
public void setLegal_person(String legal_person) {
	this.legal_person = legal_person;
}
public String getSenior_managers() {
	return senior_managers;
}
public void setSenior_managers(String senior_managers) {
	this.senior_managers = senior_managers;
}
public String getShareholders() {
	return shareholders;
}
public void setShareholders(String shareholders) {
	this.shareholders = shareholders;
}

}
