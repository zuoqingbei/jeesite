/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsCollection;
import com.qdch.portal.modules.cms.entity.CmsPortalComments;

import java.util.List;

/**
 * 门户评论DAO接口
 * @author wangfeng
 * @version 2018-03-20
 */
@MyBatisDao
public interface CmsPortalCommentsDao extends CrudDao<CmsPortalComments> {


    /**
     * 获得评论的数量
     * @param cmsPortalComments
     * @return
     */
    public int getPortalCommentsCount(CmsPortalComments cmsPortalComments);


    /**
     * 我是否有评论
     * @param cmsPortalComments
     * @return
     */
    public int getDynamicSelf(CmsPortalComments cmsPortalComments);

    /**
     * 获得某条文章的全部评论
     * @param cmsPortalComments
     * @return
     */
    public List<CmsPortalComments> getCommentsBySource(CmsPortalComments cmsPortalComments);

    /**
     * 获得文章的 评论和赞、踩的个数
     * @param cmsPortalComments
     * @return
     */

    public  List<CmsPortalComments> getCommentsAndPraise(CmsPortalComments cmsPortalComments);


    public void changeState(CmsPortalComments cmsPortalComments);

    //该条评论下的回复数
    public CmsPortalComments getCommentsCount(CmsPortalComments portalComments);

    //第一层评论
    public List<CmsPortalComments> getParentComments(CmsPortalComments cmsPortalComments);

    //子评论
    public List<CmsPortalComments> getChildComments(CmsPortalComments cmsPortalComments);

    public List<CmsPortalComments> getHotComments(CmsPortalComments cmsPortalComments);



}