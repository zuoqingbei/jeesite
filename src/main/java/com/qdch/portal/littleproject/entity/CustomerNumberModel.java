package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;
/**
 * 总况——客户数
 * @author gaozhao 20170424
 *
 */
public class CustomerNumberModel extends DataEntity<CustomerNumberModel>{
private String vday;
private String jys;
private String jysmc;
private String jysinfo;
private double fvalue;
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

}
