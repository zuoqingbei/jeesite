/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.cms.entity.CmsPraise;
import com.qdch.portal.modules.cms.entity.CmsShare;

/**
 * 用户赞 踩记录DAO接口
 * @author wangfeng
 * @version 2018-03-15
 */
@MyBatisDao
public interface CmsPraiseDao extends CrudDao<CmsPraise> {

    /**
     * 获得踩的数量
     * @param cmsPraise
     * @return
     */
    public CmsPraise getTradeCount(CmsPraise cmsPraise);
    /**
     * 获得赞的数量
     * @param cmsPraise
     * @return
     */
    public CmsPraise getPraiseCount(CmsPraise cmsPraise);

    /**
     * 我是否有踩或赞过
     * @param cmsPraise
     * @return
     */
    public CmsPraise getDynamicSelf(CmsPraise cmsPraise);



}