package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class CustomerAgeModel extends DataEntity<CustomerAgeModel> {
private String jys;
private String jysinfo;
private String jysmc;
private double sum;
private String coalesce;
public String getJys() {
	return jys;
}
public void setJys(String jys) {
	this.jys = jys;
}
public String getJysinfo() {
	return jysinfo;
}
public void setJysinfo(String jysinfo) {
	this.jysinfo = jysinfo;
}
public String getJysmc() {
	return jysmc;
}
public void setJysmc(String jysmc) {
	this.jysmc = jysmc;
}
public double getSum() {
	return sum;
}
public void setSum(double sum) {
	this.sum = sum;
}
public String getCoalesce() {
	return coalesce;
}
public void setCoalesce(String coalesce) {
	this.coalesce = coalesce;
}

}
