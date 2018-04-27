package com.qdch.portal.littleproject.entity;

import java.util.List;
import java.util.Map;

public class SummaryProductDto {
    private Map<String,Object> product ;
    private List<String> x;
    private List<String> y;
	public Map<String, Object> getProduct() {
		return product;
	}
	public void setProduct(Map<String, Object> product) {
		this.product = product;
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
    
    

}
