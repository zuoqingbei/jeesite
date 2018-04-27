
package com.qdch.portal.littleproject.entity;

import java.util.List;

public class FenLei {
private String name;
private double sum;
private List<String> x;
private List<String> y;
private List<String> z;
private List<KeHuFenLei> ability;

public List<KeHuFenLei> getAbility() {
	return ability;
}
public void setAbility(List<KeHuFenLei> ability) {
	this.ability = ability;
}
public List<String> getX() {
	return x;
}
public void setX(List<String> x) {
	this.x = x;
}
public List<String> getY() {
	return y;
}
public void setY(List<String> y) {
	this.y = y;
}
public List<String> getZ() {
	return z;
}
public void setZ(List<String> z) {
	this.z = z;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getSum() {
	return sum;
}
public void setSum(double sum) {
	this.sum = sum;
}





}
