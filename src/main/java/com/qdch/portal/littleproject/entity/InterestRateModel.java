package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class InterestRateModel extends DataEntity<InterestRateModel> {
private String cplb;
private double cpsl;
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
