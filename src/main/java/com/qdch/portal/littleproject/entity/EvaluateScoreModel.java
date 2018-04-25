package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class EvaluateScoreModel extends DataEntity<EvaluateScoreModel>{
private String jys;
private String pjfj;
private double bzfs;
private double df;
public String getJys() {
	return jys;
}
public void setJys(String jys) {
	this.jys = jys;
}
public String getPjfj() {
	return pjfj;
}
public void setPjfj(String pjfj) {
	this.pjfj = pjfj;
}
public double getBzfs() {
	return bzfs;
}
public void setBzfs(double bzfs) {
	this.bzfs = bzfs;
}
public double getDf() {
	return df;
}
public void setDf(double df) {
	this.df = df;
}


}
