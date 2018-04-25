package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class QuotationModel extends DataEntity<QuotationModel>{
private String cpmc;
private double zxjg;
private double bh;
public String getCpmc() {
	return cpmc;
}
public void setCpmc(String cpmc) {
	this.cpmc = cpmc;
}
public double getZxjg() {
	return zxjg;
}
public void setZxjg(double zxjg) {
	this.zxjg = zxjg;
}
public double getBh() {
	return bh;
}
public void setBh(double bh) {
	this.bh = bh;
}

}
