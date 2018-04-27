package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 总况-产品——交易额占比
 * @author gaozhao
 *
 */
public class TradeRtioModel extends DataEntity<TradeRtioModel>{
private String cpdlbm;
private String cpdlmc;
private String cpdlinfo;
private String cpdm;
private String cpmc;
private double cpje;
private double sum;
public String getCpdlbm() {
	return cpdlbm;
}
public void setCpdlbm(String cpdlbm) {
	this.cpdlbm = cpdlbm;
}
public String getCpdlmc() {
	return cpdlmc;
}
public void setCpdlmc(String cpdlmc) {
	this.cpdlmc = cpdlmc;
}
public String getCpdlinfo() {
	return cpdlinfo;
}
public void setCpdlinfo(String cpdlinfo) {
	this.cpdlinfo = cpdlinfo;
}
public String getCpdm() {
	return cpdm;
}
public void setCpdm(String cpdm) {
	this.cpdm = cpdm;
}
public String getCpmc() {
	return cpmc;
}
public void setCpmc(String cpmc) {
	this.cpmc = cpmc;
}
public double getCpje() {
	return cpje;
}
public void setCpje(double cpje) {
	this.cpje = cpje;
}
public double getSum() {
	return sum;
}
public void setSum(double sum) {
	this.sum = sum;
}


}
