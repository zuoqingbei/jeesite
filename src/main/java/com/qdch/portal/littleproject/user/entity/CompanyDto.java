package com.qdch.portal.littleproject.user.entity;

import java.util.List;

public class CompanyDto {
	private String name;
	private String id;
	private List<TestDto> dtos;
	public List<TestDto> getDtos() {
		return dtos;
	}
	public void setDtos(List<TestDto> dtos) {
		this.dtos = dtos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
