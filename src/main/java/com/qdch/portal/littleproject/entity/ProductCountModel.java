package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;

public class ProductCountModel extends DataEntity<ProductCountModel>{
private String cplb;
private double pjll;
private double jsyzz;
public String getCplb() {
	return cplb;
}
public void setCplb(String cplb) {
	this.cplb = cplb;
}
public double getPjll() {
	return pjll;
}
public void setPjll(double pjll) {
	this.pjll = pjll;
}
public double getJsyzz() {
	return jsyzz;
}
public void setJsyzz(double jsyzz) {
	this.jsyzz = jsyzz;
}

}
