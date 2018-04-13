/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.search.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 热搜Entity
 * @author zuoqb
 * @version 2018-04-13
 */
public class ThinkerHotSearch extends DataEntity<ThinkerHotSearch> {
	
	private static final long serialVersionUID = 1L;
	private String keyword;		// 搜索关键字
	private Integer nums;		// 搜索次数
	
	public ThinkerHotSearch() {
		super();
	}

	public ThinkerHotSearch(String id){
		super(id);
	}

	@Length(min=1, max=100, message="搜索关键字长度必须介于 1 和 100 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@NotNull(message="搜索次数不能为空")
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
}