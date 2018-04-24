package com.qdch.portal.littleproject.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.EvaluateScoreModel;

@MyBatisDao
public interface EvaluateScoreModelDao extends CrudDao<EvaluateScoreModelDao>{
public List<EvaluateScoreModel> evaluateScore();
public List<EvaluateScoreModel> evaluateScore2();
public List<EvaluateScoreModel> evaluateScore3();

}
