package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class CustomerCountModel extends DataEntity<CustomerCountModel>{
private String jys;
private String jysmc;
private String jysinfo;
private double grrzrkhs;//个人融资客户数
private double grtzrkhs;//个人投资人
private double jgrzrkhs;//机构融资人
private double jgrtzrkhs;//机构投资人
private double jgkhs;//机构客户数总数
private double grkhs;//个人总数
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
public double getGrrzrkhs() {
	return grrzrkhs;
}
public void setGrrzrkhs(double grrzrkhs) {
	this.grrzrkhs = grrzrkhs;
}
public double getGrtzrkhs() {
	return grtzrkhs;
}
public void setGrtzrkhs(double grtzrkhs) {
	this.grtzrkhs = grtzrkhs;
}
public double getJgrzrkhs() {
	return jgrzrkhs;
}
public void setJgrzrkhs(double jgrzrkhs) {
	this.jgrzrkhs = jgrzrkhs;
}
public double getJgrtzrkhs() {
	return jgrtzrkhs;
}
public void setJgrtzrkhs(double jgrtzrkhs) {
	this.jgrtzrkhs = jgrtzrkhs;
}
public double getJgkhs() {
	return jgkhs;
}
public void setJgkhs(double jgkhs) {
	this.jgkhs = jgkhs;
}
public double getGrkhs() {
	return grkhs;
}
public void setGrkhs(double grkhs) {
	this.grkhs = grkhs;
}





}
