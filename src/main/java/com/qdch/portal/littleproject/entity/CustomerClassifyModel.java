package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;
/**
 * 总况——客户分类
 * @author gaozhao 20180424
 *
 */
public class CustomerClassifyModel extends DataEntity<CustomerClassifyModel> {
private double grkhs;
private double jgkhs;
public double getGrkhs() {
	return grkhs;
}
public void setGrkhs(double grkhs) {
	this.grkhs = grkhs;
}
public double getJgkhs() {
	return jgkhs;
}
public void setJgkhs(double jgkhs) {
	this.jgkhs = jgkhs;
}


}
