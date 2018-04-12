/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thinker.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.thinker.api.entity.ThinkerApi;
import com.qdch.portal.thinker.api.dao.ThinkerApiDao;
import com.qdch.portal.thinker.api.entity.ThinkerApiErrorcode;
import com.qdch.portal.thinker.api.dao.ThinkerApiErrorcodeDao;
import com.qdch.portal.thinker.api.entity.ThinkerApiParam;
import com.qdch.portal.thinker.api.dao.ThinkerApiParamDao;
import com.qdch.portal.thinker.api.entity.ThinkerApiSuccess;
import com.qdch.portal.thinker.api.dao.ThinkerApiSuccessDao;

/**
 * api管理Service
 * @author zuoqb
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class ThinkerApiService extends CrudService<ThinkerApiDao, ThinkerApi> {

	@Autowired
	private ThinkerApiErrorcodeDao thinkerApiErrorcodeDao;
	@Autowired
	private ThinkerApiParamDao thinkerApiParamDao;
	@Autowired
	private ThinkerApiSuccessDao thinkerApiSuccessDao;
	
	public ThinkerApi get(String id) {
		ThinkerApi thinkerApi = super.get(id);
		thinkerApi.setThinkerApiErrorcodeList(thinkerApiErrorcodeDao.findList(new ThinkerApiErrorcode(thinkerApi.getId(),null)));
		thinkerApi.setThinkerApiParamList(thinkerApiParamDao.findList(new ThinkerApiParam(thinkerApi.getId(),null)));
		thinkerApi.setThinkerApiSuccessList(thinkerApiSuccessDao.findList(new ThinkerApiSuccess(thinkerApi.getId(),null)));
		return thinkerApi;
	}
	
	public List<ThinkerApi> findList(ThinkerApi thinkerApi) {
		return super.findList(thinkerApi);
	}
	
	public Page<ThinkerApi> findPage(Page<ThinkerApi> page, ThinkerApi thinkerApi) {
		return super.findPage(page, thinkerApi);
	}
	
	@Transactional(readOnly = false)
	public void save(ThinkerApi thinkerApi) {
		super.save(thinkerApi);
		try {
			for (ThinkerApiErrorcode thinkerApiErrorcode : thinkerApi.getThinkerApiErrorcodeList()){
				if (thinkerApiErrorcode.getId() == null){
					continue;
				}
				if (ThinkerApiErrorcode.DEL_FLAG_NORMAL.equals(thinkerApiErrorcode.getDelFlag())){
					if (StringUtils.isBlank(thinkerApiErrorcode.getId())){
						thinkerApiErrorcode.setApiId(thinkerApi.getId());
						thinkerApiErrorcode.preInsert();
						thinkerApiErrorcodeDao.insert(thinkerApiErrorcode);
					}else{
						thinkerApiErrorcode.preUpdate();
						thinkerApiErrorcodeDao.update(thinkerApiErrorcode);
					}
				}else{
					thinkerApiErrorcodeDao.delete(thinkerApiErrorcode);
				}
			}
			for (ThinkerApiParam thinkerApiParam : thinkerApi.getThinkerApiParamList()){
				if (thinkerApiParam.getId() == null){
					continue;
				}
				if (ThinkerApiParam.DEL_FLAG_NORMAL.equals(thinkerApiParam.getDelFlag())){
					if (StringUtils.isBlank(thinkerApiParam.getId())){
						thinkerApiParam.setApiId(thinkerApi);
						thinkerApiParam.preInsert();
						thinkerApiParamDao.insert(thinkerApiParam);
					}else{
						thinkerApiParam.preUpdate();
						thinkerApiParamDao.update(thinkerApiParam);
					}
				}else{
					thinkerApiParamDao.delete(thinkerApiParam);
				}
			}
			for (ThinkerApiSuccess thinkerApiSuccess : thinkerApi.getThinkerApiSuccessList()){
				if (thinkerApiSuccess.getId() == null){
					continue;
				}
				if (ThinkerApiSuccess.DEL_FLAG_NORMAL.equals(thinkerApiSuccess.getDelFlag())){
					if (StringUtils.isBlank(thinkerApiSuccess.getId())){
						thinkerApiSuccess.setApiId(thinkerApi.getId());
						thinkerApiSuccess.preInsert();
						thinkerApiSuccessDao.insert(thinkerApiSuccess);
					}else{
						thinkerApiSuccess.preUpdate();
						thinkerApiSuccessDao.update(thinkerApiSuccess);
					}
				}else{
					thinkerApiSuccessDao.delete(thinkerApiSuccess);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ThinkerApi thinkerApi) {
		super.delete(thinkerApi);
		thinkerApiErrorcodeDao.delete(new ThinkerApiErrorcode(thinkerApi.getId(),null));
		thinkerApiParamDao.delete(new ThinkerApiParam(thinkerApi.getId(),null));
		thinkerApiSuccessDao.delete(new ThinkerApiSuccess(thinkerApi.getId(),null));
	}
	
}