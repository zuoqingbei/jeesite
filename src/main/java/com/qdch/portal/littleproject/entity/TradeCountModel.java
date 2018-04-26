package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;
/**
 * 交易额统计
 * @author gaozhao 20180424
 *
 */
public class TradeCountModel extends DataEntity<TradeCountModel> {
private String jys;
private String jysmc;
private String jysinfo;
private double bz;
private double by;
private double bn;
private double lj;
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
public double getBz() {
	return bz;
}
public void setBz(double bz) {
	this.bz = bz;
}
public double getBy() {
	return by;
}
public void setBy(double by) {
	this.by = by;
}
public double getBn() {
	return bn;
}
public void setBn(double bn) {
	this.bn = bn;
}
public double getLj() {
	return lj;
}
public void setLj(double lj) {
	this.lj = lj;
}


}
