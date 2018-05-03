package com.qdch.portal.littleproject.entity;

import java.util.List;

public class Portrait {
private String name;
private LittleProjectEntity otherInfo;
private Single info;
private List<ZiJin> shareholder;
private List<KeHuFenLei> radar;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public LittleProjectEntity getOtherInfo() {
	return otherInfo;
}
public void setOtherInfo(LittleProjectEntity otherInfo) {
	this.otherInfo = otherInfo;
}

public Single getInfo() {
	return info;
}
public void setInfo(Single info) {
	this.info = info;
}

public List<ZiJin> getShareholder() {
	return shareholder;
}
public void setShareholder(List<ZiJin> shareholder) {
	this.shareholder = shareholder;
}
public List<KeHuFenLei> getRadar() {
	return radar;
}
public void setRadar(List<KeHuFenLei> radar) {
	this.radar = radar;
}




}
