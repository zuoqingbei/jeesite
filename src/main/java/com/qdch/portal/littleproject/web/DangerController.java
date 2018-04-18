package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;

/**
 * 
 * 风险
 * 
 * @author gaozhao
 * @time 2018年4月18日
 */
@Controller
public class DangerController extends BaseController {
	sqlDanger sql = new sqlDanger();

	/**
	 * 风险——未然指数-未然指数趋势 
	 * 
	 * @author gaozhao
	 * @time 2018年4月18日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/weiranqushi" })
	@ResponseBody
	public String weiranqushi(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			List<Object> lists = null;
			if ("day".equals(type)) {

				lists = PostgreUtils.getInstance().excuteQuery(sql.weiranDay(),
						null);
			} else if ("week".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(
						sql.weiranWeek(), null);
			} else if ("month".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(
						sql.weiranMonth(), null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			List<Object> tradelist = PostgreUtils.getInstance().excuteQuery(
					sql.shichan(), null);

			// 时间集合
			List<String> times = new ArrayList<String>();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			int a = 1;

			if (tradelist != null && tradelist.size() > 0) {
				for (Object o : tradelist) {
					Map m = (Map) o;
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(m.get("jys") + "");
					res.add(aa);
				}
			}

			// 获取市场

			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					List<String> shiChan = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {

						for (Object o : lists) {

							Map m = (Map) o;

							if (m.get("jys").equals(s.getName())) {

								shiChan.add(m.get("fvalue") + "");

							}
						}

					}
					s.setLists(shiChan);
				}
			}

			// 获取时间
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					if (lists != null && lists.size() > 0) {

						for (Object o : lists) {
							Map m = (Map) o;

							if (m.get("jys").equals(s.getName()) && a == 1) {
								times.add(m.get("date") + "");

							}

						}
					}
					a = 2;
				}
			}
			dto.setTimes(times.toArray());

			dto.setEntities(res);
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
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
	 * @time 2018年4月18日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/fengxiantongji" })
	@ResponseBody
	public String fengxiantongji(HttpServletRequest request,HttpServletResponse response){
		try {
			LittleProjectDto dto = new LittleProjectDto();


			List<Object> lists = PostgreUtils.getInstance().excuteQuery(
					sql.fengxiantongji(), null);
			List<Object> tradelist = PostgreUtils.getInstance().excuteQuery(
					sql.shichan(), null);

			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				for (Object o : tradelist) {
					Map m = (Map) o;
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(m.get("jysinfo") + "");
					res.add(aa);
				}
			}
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					List<String> shiChan = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {

						for (Object o : lists) {

							Map m = (Map) o;

							if (m.get("jysinfo").equals(s.getName())) {

								shiChan.add(m.get("bzfxz") + "");
								shiChan.add(m.get("byfxz") + "");
								shiChan.add(m.get("bnfxz") + "");
								shiChan.add(m.get("ljfxz") + "");

							}
						}

					}
					s.setLists(shiChan);
				}
			}

			dto.setEntities(res);
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}

		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}
	
}
