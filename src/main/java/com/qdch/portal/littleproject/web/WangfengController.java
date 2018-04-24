package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.DtoModelDao;
import com.qdch.portal.littleproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 总况
 * 
 * @author gaozhao
 * @time 2018年4月13日
 */
@Controller
public class WangfengController extends BaseController {

	@Autowired
	public DtoModelDao dtoModelDao;


	@RequestMapping(value = { "${portalPath}/littleproject/testPostgre" })
	@ResponseBody
	public String testPostgre(HttpServletRequest request,HttpServletResponse response){

//		DynamicDataSource.setDataSource();
//		String aaa =  DynamicDataSource.getCurrentLookupKey();
		DynamicDataSource.setInsightDataSource();
		List<DtoModel> aa = dtoModelDao.getTest();
		DynamicDataSource.removeDataSourceKey();
		return this.resultSuccessData(request,response,"",aa);
	}


}
