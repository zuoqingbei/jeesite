package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class EntryAndExitCapitalModel extends DataEntity<EntryAndExitCapitalModel>{
private String date;
private double fvalue;
private String xm;
private String wd;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public double getFvalue() {
	return fvalue;
}
public void setFvalue(double fvalue) {
	this.fvalue = fvalue;
}
public String getXm() {
	return xm;
}
public void setXm(String xm) {
	this.xm = xm;
}
public String getWd() {
	return wd;
}
public void setWd(String wd) {
	this.wd = wd;
}

}
