package com.qdch.portal.littleproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.EvaluateScoreModel;
/**
 *画像—— 市场评价得分
 * @author gaozhao
 *
 */
@MyBatisDao
public interface EvaluateScoreModelDao extends CrudDao<EvaluateScoreModelDao>{
public List<EvaluateScoreModel> evaluateScore(@Param("jys")String jys);


}
