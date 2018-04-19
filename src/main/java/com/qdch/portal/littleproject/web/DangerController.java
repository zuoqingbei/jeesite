package com.qdch.portal.littleproject.web;

import java.text.DecimalFormat;
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
import com.qdch.portal.littleproject.entity.FenLei;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;
import com.qdch.portal.littleproject.entity.Risks;
import com.qdch.portal.littleproject.entity.Single;
import com.qdch.portal.littleproject.entity.UnknownIndex;

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

			List<Object> lists = null;

			lists = PostgreUtils.getInstance().excuteQuery(sql.weiranDay(),
					null);

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
					aa.setName(m.get("jysinfo") + "");
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
	public String fengxiantongji(HttpServletRequest request,
			HttpServletResponse response) {
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
	/**
	 * 风险-金融资产类风险监测
	 *
	 * @time 2018年4月18日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/riskMonitoring" })
	@ResponseBody
	public String riskMonitoring(HttpServletRequest request,HttpServletResponse response){
		try {
			UnknownIndex dto = new UnknownIndex();
			DecimalFormat dt=new DecimalFormat("0.00%");
			List<Object> lists = null;
			lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring(),null);
			List<Object> tradelist = PostgreUtils.getInstance().excuteQuery(sql.shichan(), null);
			List<Object> unknownIndex=PostgreUtils.getInstance().excuteQuery(sql.unknownIndex(), null);
			List<KeHuFenLei> aggregate1=new ArrayList<KeHuFenLei>();
			List<Risks> aggregate2=new ArrayList<Risks>();
			//List<Single> aggregate=new ArrayList<Single>();
			List<Object> risks= PostgreUtils.getInstance().excuteQuery(sql.risks(),null);
			//List<Object> riskAll= PostgreUtils.getInstance().excuteQuery(sql.riskAll(),null);
			int n=lists.size();
			
			if(tradelist!=null&&tradelist.size()>0){
				for(Object  o:tradelist){
					Map m=(Map) o;
					KeHuFenLei k=new KeHuFenLei();
					if(unknownIndex!=null&&unknownIndex.size()>0){
						for(Object x:unknownIndex){
							Map y=(Map) x;
						if(y.get("jysinfo").equals(m.get("jysinfo"))){
							k.setGrs(y.get("jysinfo")+"");
							k.setJgs(y.get("wrzs")+"");
							aggregate1.add(k);
						}
						}
					}
					
				}
			}
			/*if(lists!=null&&lists.size()>0){
				for(Object o:lists){
					Map m=(Map) o;
					List<String> aggregate3=new ArrayList<String>();
					Single s=new Single();
					aggregate3.add(m.get("fxzb")+"");
					aggregate3.add(m.get("fxzbz")+"");
					aggregate3.add(dt.format(m.get("yz"))+"");
					aggregate3.add(m.get("cee")+"");
					aggregate3.add(m.get("yjsj")+"");
					s.setS(aggregate3);
					aggregate.add(s);
				}
			}*/
			
		if(risks!=null&&risks.size()>0){
				for(Object o:risks){
					Map m=(Map) o;
					Risks r=new Risks();
					Object z=m.get("fxdl");
					int b1=0;
					for(Object x:lists){
						Map y=(Map) x;
						if(y.get("fxdl").equals(m.get("fxdl"))){
							b1++;
						}
					}
					r.setName(m.get("fxdl")+"");
					r.setCreditRisks(b1);
					r.setPercentage(dt.format((double)b1/n));
				    aggregate2.add(r);
				}
			}
			dto.setA(aggregate1);
			dto.setB(aggregate2);
			//dto.setC(aggregate);
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
