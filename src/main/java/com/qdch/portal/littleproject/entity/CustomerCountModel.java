package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class CustomerCountModel extends DataEntity<CustomerCountModel>{
private String jys;
private String jysmc;
private String jysinfo;
private String rzrkhs;
private String tzrkhs;
private double count;


public String getJys() {
	return jys;
}
public void setJys(String jys) {
	this.jys = jys;
}
public String getJysmc() {
	return jysmc;
}
public void setJysmc(String jysmc) {
	this.jysmc = jysmc;
}
public String getJysinfo() {
	return jysinfo;
}
public void setJysinfo(String jysinfo) {
	this.jysinfo = jysinfo;
}
public String getRzrkhs() {
	return rzrkhs;
}
public void setRzrkhs(String rzrkhs) {
	this.rzrkhs = rzrkhs;
}
public String getTzrkhs() {
	return tzrkhs;
}
public void setTzrkhs(String tzrkhs) {
	this.tzrkhs = tzrkhs;
}
public double getCount() {
	return count;
}
public void setCount(double count) {
	this.count = count;
}


}
