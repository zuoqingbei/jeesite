package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.LittleProjectDao;
import com.qdch.portal.littleproject.entity.KeHuAge;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;
import com.qdch.portal.modules.cms.entity.CmsNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 总况——总量——交易额
 * 
 * @author gaozhao
 * @time 2018年4月13日
 */
@Controller
public class TestwfController extends BaseController {

	sqlYuJu sql = new sqlYuJu();
	@Autowired
	CmsNewsDao cmsNewsDao;

	@Autowired
	LittleProjectDao littleProjectDao;

	@RequestMapping(value = { "${portalPath}/littleproject/test/tradeAmount2" })
	@ResponseBody
	public String tradeAmount2(HttpServletRequest request,
							  HttpServletResponse response) {
		DynamicDataSource.setSlaveDataSource();


		try {
			return this.resultSuccessData(request, response, "", littleProjectDao.getaa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  this.resultSuccessData(request, response, "",null);

	}
//		try {
//			String type = request.getParameter("type");
//			List<Object> lists = null;
//			if ("day".equals(type)) {
//				lists = PostgreUtils.excuteQuery(sql.tradeDay(),
//						null);
//			} else if ("week".equals(type)) {
//				lists = PostgreUtils.excuteQuery(sql.tradeWeek(),
//						null);
//			} else if ("month".equals(type)) {
//				lists = PostgreUtils.excuteQuery(sql.tradeMonth(),
//						null);
//			}
//
//			LittleProjectDto dto = new LittleProjectDto();
//			//List<Object> lists = PostgreUtils.excuteQuery(sql.tradeDay(), null);
//			List<Object> tradelist = PostgreUtils.excuteQuery(sql.shichan(),
//					null);
//			// 时间集合
//			List<String> times = new ArrayList<String>();
//			// 交易市场集合
//			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
//			int a = 1;
//
//			if (tradelist != null && tradelist.size() > 0) {
//				for (Object o : tradelist) {
//					Map m = (Map) o;
//					LittleProjectEntity aa = new LittleProjectEntity();
//					aa.setName(m.get("jysinfo") + "");
//					res.add(aa);
//				}
//			}
//
//			// 获取市场
//
//			if (res != null && res.size() > 0) {
//				for (LittleProjectEntity s : res) {
//
//					List<String> shiChan = new ArrayList<String>();
//					if (lists != null && lists.size() > 0) {
//
//						for (Object o : lists) {
//
//							Map m = (Map) o;
//
//							if (m.get("jysinfo").equals(s.getName())) {
//
//								shiChan.add(m.get("fvalue") + "");
//
//
//							}
//						}
//
//					}
//					s.setLists(shiChan);
//				}
//			}
//
//			// 获取时间
//			if (res != null && res.size() > 0) {
//				for (LittleProjectEntity s : res) {
//
//					if (lists != null && lists.size() > 0) {
//
//						for (Object o : lists) {
//							Map m = (Map) o;
//
//							if (m.get("jysinfo").equals(s.getName()) && a == 1) {
//								times.add(m.get("vday") + "");
//
//							}
//
//						}
//					}
//					a = 2;
//				}
//			}
//			dto.setTimes(times.toArray());
//
//			dto.setEntities(res);
//			if(lists==null){
//				return this.resultSuccessData(request, response, "", null);
//			}else{
//				return this.resultSuccessData(request, response, "", dto);
//			}
//
//		} catch (Exception e) {
//			e.getStackTrace();
//			return this.resultFaliureData(request, response, "", null);
//		}
//
//	}


}
