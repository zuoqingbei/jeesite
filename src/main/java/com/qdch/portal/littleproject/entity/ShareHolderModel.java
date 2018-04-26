package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class ShareHolderModel extends DataEntity<ShareHolderModel>{
private String name;
private double pay;
private String pay_date;
private double scale;
private String jysinfo;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPay() {
	return pay;
}
public void setPay(double pay) {
	this.pay = pay;
}
public String getPay_date() {
	return pay_date;
}
public void setPay_date(String pay_date) {
	this.pay_date = pay_date;
}
public double getScale() {
	return scale;
}
public void setScale(double scale) {
	this.scale = scale;
}
public String getJysinfo() {
	return jysinfo;
}
public void setJysinfo(String jysinfo) {
	this.jysinfo = jysinfo;
}

}
