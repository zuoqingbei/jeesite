package com.qdch.portal.littleproject.entity;

import java.util.List;

public class CompanyRelation {
private String companyName;
private String legalPerson;
private List<String> managers;
private List<String> shareholders;
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getLegalPerson() {
	return legalPerson;
}
public void setLegalPerson(String legalPerson) {
	this.legalPerson = legalPerson;
}
public List<String> getManagers() {
	return managers;
}
public void setManagers(List<String> managers) {
	this.managers = managers;
}
public List<String> getShareholders() {
	return shareholders;
}
public void setShareholders(List<String> shareholders) {
	this.shareholders = shareholders;
}

}
