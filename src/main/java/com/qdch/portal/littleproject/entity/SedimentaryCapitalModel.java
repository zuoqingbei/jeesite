package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class SedimentaryCapitalModel extends DataEntity<SedimentaryCapitalModel>{
private String date;
private String week_date;
private double fvalue;
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
public String getWd() {
	return wd;
}
public void setWd(String wd) {
	this.wd = wd;
}
public String getWeek_date() {
	return week_date;
}
public void setWeek_date(String week_date) {
	this.week_date = week_date;
}

}
