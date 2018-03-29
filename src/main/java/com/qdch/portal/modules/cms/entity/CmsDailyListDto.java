/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.entity;

import com.qdch.portal.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 每日一览Entity
 * @author wangfeng
 * @version 2018-03-22
 */
public class CmsDailyListDto extends DataEntity<CmsDailyListDto> {

	private CmsDailyList cmsDailyList;

	private List<CmsNews>  cmsNewsList;
	private List<CmsEducation> cmsEducationList;


	public List<CmsNews> getCmsNewsList() {
		return cmsNewsList;
	}

	public void setCmsNewsList(List<CmsNews> cmsNewsList) {
		this.cmsNewsList = cmsNewsList;
	}

	public List<CmsEducation> getCmsEducationList() {
		return cmsEducationList;
	}

	public void setCmsEducationList(List<CmsEducation> cmsEducationList) {
		this.cmsEducationList = cmsEducationList;
	}

	public CmsDailyList getCmsDailyList() {
		return cmsDailyList;
	}

	public void setCmsDailyList(CmsDailyList cmsDailyList) {
		this.cmsDailyList = cmsDailyList;
	}
}