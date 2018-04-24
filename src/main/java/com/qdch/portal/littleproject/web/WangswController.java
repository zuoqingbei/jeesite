package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;
import com.qdch.portal.littleproject.service.DangerService;

/**
 * 风险
 * 
 * @author wangsw
 * @time 2018年4月24日
 */
@Controller
public class WangswController extends BaseController {

	@Autowired
	public DangerService dangerService;

	/**
	 * 风险——未然指数-未然指数趋势
	 * 
	 * @author wangsw
	 * @time 2018年4月24日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/wangsw1" })
	@ResponseBody
	public String weiranqushi(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			List<Map<String,Object>> lists = dangerService.getWeiRanTrend();
			DynamicDataSource.removeDataSourceKey();
			LittleProjectDto dto = new LittleProjectDto();
			// 时间集合
			HashSet<String> times = new LinkedHashSet<String>();
			List<String> names = new ArrayList<String>();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			
			if(lists == null || lists.size() < 0){
				return this.resultFaliureData(request, response, "", null);
			}
			String date = "";
			String jys = "";
			LittleProjectEntity aa = null;
			for(Map<String,Object> map : lists){
				date = map.get("date") == null ? "":map.get("date").toString();
				jys = map.get("jys") == null ? "":map.get("jys").toString();
				aa = new LittleProjectEntity();
				if(!names.contains(jys)){
					names.add(jys);
					aa.setName(jys);
					res.add(aa);
				}
				times.add(date);
			}
			// 获取市场
			if (res != null && res.size() > 0) {
				List<String> shiChang = null;
				for (LittleProjectEntity s : res) {
					shiChang = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {
						for (Map<String,Object> o : lists) {
							jys = o.get("jys") == null ? "":o.get("jys").toString();
							if (jys.equals(s.getName())) {
								shiChang.add(o.get("fvalue") + "");
							}
						}
					}
					s.setLists(shiChang);
				}
			}
			dto.setTimes(times.toArray());
			dto.setEntities(res);
			if (lists == null || lists.size() < 0) {
				return this.resultFaliureData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 风险-未然指数-风险统计
	 *
	 * @time 2018年4月24日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/wangsw2" })
	@ResponseBody
	public String fengxiantongji(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			LittleProjectDto dto = new LittleProjectDto();
			DynamicDataSource.setInsightDataSource();
			List<Map<String,Object>> lists = dangerService.getFengxiantongji();
			List<Map<String,Object>> tradelist = dangerService.getFinanceMarket();
			DynamicDataSource.removeDataSourceKey();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				LittleProjectEntity aa = null;
				for (Map<String,Object> o : tradelist) {
					aa = new LittleProjectEntity();
					aa.setName(o.get("jysinfo") + "");
					res.add(aa);
				}
			}
			if (res != null && res.size() > 0) {
				List<String> shiChan = null;
				for (LittleProjectEntity s : res) {
					shiChan = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {
						for (Map<String,Object> o : lists) {
							if(o.get("jysinfo") == null){
								continue;
							}
							if (o.get("jysinfo").toString().equals(s.getName())) {
								shiChan.add(o.get("bzfxz") + "");
								shiChan.add(o.get("byfxz") + "");
								shiChan.add(o.get("bnfxz") + "");
								shiChan.add(o.get("ljfxz") + "");
							}
						}

					}
					s.setLists(shiChan);
				}
			}
			dto.setEntities(res);
			if (lists == null || lists.size() < 0) {
				return this.resultFaliureData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

}
