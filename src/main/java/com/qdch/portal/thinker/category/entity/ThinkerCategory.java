/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.TreeEntity;

/**
 * 分类Entity
 * @author zuoqb
 * @version 2018-04-12
 */
public class ThinkerCategory extends TreeEntity<ThinkerCategory> {
	
	private static final long serialVersionUID = 1L;
	private ThinkerCategory parent;		// 父键
	private String parentIds;		// 全部父键
	private String name;		// 名称
	private Integer sort;		// 排序
	private List<ThinkerCategory> children;
	
	public List<ThinkerCategory> getChildren() {
		return children;
	}

	public void setChildren(List<ThinkerCategory> children) {
		this.children = children;
	}

	public ThinkerCategory() {
		super();
	}

	public ThinkerCategory(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父键不能为空")
	public ThinkerCategory getParent() {
		return parent;
	}

	public void setParent(ThinkerCategory parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="全部父键长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
	
	public static List<ThinkerCategory> getChildrenByPid(String pid,List<ThinkerCategory> list,boolean needNext){
		List<ThinkerCategory> children=new ArrayList<ThinkerCategory>();
		for(ThinkerCategory t:list){
			if(pid.equals(t.getParentId())){
				if(needNext){
					List<ThinkerCategory> l=getChildrenByPid(t.getId(), list,false);
					if(l!=null&&l.size()>0){
						t.setChildren(l);
					}
				}
				children.add(t);
			}
		}
		return children;
	}
}