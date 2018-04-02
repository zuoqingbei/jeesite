/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.account.entity.AccountAttention;

/**
 * 用户关注DAO接口
 * @author lianjiming
 * @version 2018-03-12
 */
@MyBatisDao
public interface AccountAttentionDao extends CrudDao<AccountAttention> {
	
	
	/**用户添加关注
	 * @author lianjiming
	 * @version 2018-03-13
	 * @param accountAttention 用户关注实体
	 */
	public void saveAttention(AccountAttention accountAttention);
	
	
	
	/**查询是否关注
	 * @author lianjiming
	 * @version 2018-03-14
	 * @param accountAttention 	用户关注实体
	 * @return
	 */
	public List<AccountAttention> findAccountAttention(AccountAttention accountAttention);

	public AccountAttention getAttention(AccountAttention accountAttention);

	public int quitAttention(AccountAttention accountAttention);

	public List<AccountAttention> getAttentionList(AccountAttention accountAttention);

	public List<AccountAttention> getBeAttentionList(AccountAttention accountAttention);

	
}