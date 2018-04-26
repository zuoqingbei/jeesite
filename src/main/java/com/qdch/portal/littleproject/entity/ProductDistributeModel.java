package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class ProductDistributeModel extends DataEntity<ProductDistributeModel>{
private String cplb;
private double jys;
private double cpsl;
public String getCplb() {
	return cplb;
}
public void setCplb(String cplb) {
	this.cplb = cplb;
}
public double getJys() {
	return jys;
}
public void setJys(double jys) {
	this.jys = jys;
}
public double getCpsl() {
	return cpsl;
}
public void setCpsl(double cpsl) {
	this.cpsl = cpsl;
}

}
