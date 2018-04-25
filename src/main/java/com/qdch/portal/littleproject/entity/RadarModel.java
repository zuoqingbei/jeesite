package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class RadarModel extends DataEntity<RadarModel>{
private String jysinfo;
private String fxlb;
private double fvalue;
public String getJysinfo() {
	return jysinfo;
}
public void setJysinfo(String jysinfo) {
	this.jysinfo = jysinfo;
}
public String getFxlb() {
	return fxlb;
}
public void setFxlb(String fxlb) {
	this.fxlb = fxlb;
}
public double getFvalue() {
	return fvalue;
}
public void setFvalue(double fvalue) {
	this.fvalue = fvalue;
}

}
