package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class TradeAmountModel extends DataEntity<TradeAmountModel> {
private String vday;
private String jys;
private String jysmc;
private String jysinfo;
private double fvalue;
private double sum;
private String vmonth;
public double getSum() {
	return sum;
}
public void setSum(double sum) {
	this.sum = sum;
}
public String getVday() {
	return vday;
}
public void setVday(String vday) {
	this.vday = vday;
}
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
public double getFvalue() {
	return fvalue;
}
public void setFvalue(double fvalue) {
	this.fvalue = fvalue;
}
public String getVmonth() {
	return vmonth;
}
public void setVmonth(String vmonth) {
	this.vmonth = vmonth;
}







}
