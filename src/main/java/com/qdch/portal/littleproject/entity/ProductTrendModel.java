package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class ProductTrendModel extends DataEntity<ProductTrendModel>{
private String vday;
private String cplb;
private double cpsl;
public String getVday() {
	return vday;
}
public void setVday(String vday) {
	this.vday = vday;
}
public String getCplb() {
	return cplb;
}
public void setCplb(String cplb) {
	this.cplb = cplb;
}
public double getCpsl() {
	return cpsl;
}
public void setCpsl(double cpsl) {
	this.cpsl = cpsl;
}

}
