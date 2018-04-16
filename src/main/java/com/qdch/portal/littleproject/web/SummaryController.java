package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.entity.KeHuAge;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * 总况——总量——交易额
 * 
 * @author gaozhao
 * @time 2018年4月13日
 */
@Controller
public class SummaryController extends BaseController {

	sqlYuJu sql = new sqlYuJu();

	/**
	 * 交易额
	 * 
	 * @time 2018年4月13日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = { "${portalPath}/littleproject/tradeAmount" })
	@ResponseBody
	public String tradeAmount(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			List<Object> lists = null;
			if ("day".equals(type)) {
				 lists = PostgreUtils.excuteQuery(sql.tradeDay(),
						null);
			} else if ("week".equals(type)) {
				 lists = PostgreUtils.excuteQuery(sql.tradeWeek(),
						null);
			} else if ("month".equals(type)) {
				 lists = PostgreUtils.excuteQuery(sql.tradeMonth(),
						null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			//List<Object> lists = PostgreUtils.excuteQuery(sql.tradeDay(), null);
			List<Object> tradelist = PostgreUtils.excuteQuery(sql.shichan(),
					null);
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

							if (m.get("jysinfo").equals(s.getName())) {
								
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

						if (m.get("jysinfo").equals(s.getName()) && a == 1) {
							times.add(m.get("vday") + "");

						}

					}
				}
				a = 2;
			}
			}
			dto.setTimes(times.toArray());

			dto.setEntities(res);
			if(lists==null){
				return this.resultSuccessData(request, response, "", null);
			}else{
				return this.resultSuccessData(request, response, "", dto);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 交易额统计
	 * 
	 * @time 2018年4月13日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/jiaoYiAmount" })
	@ResponseBody
	public String jiaoYiAmount(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			LittleProjectDto dto = new LittleProjectDto();
			List<Object> jiaoyiList = PostgreUtils.excuteQuery(sql.jiaoyi(),
					null);
			List<Object> tradelist = PostgreUtils.excuteQuery(sql.shichan(),
					null);
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
					if (jiaoyiList != null && jiaoyiList.size() > 0) {

						for (Object o : jiaoyiList) {

							Map m = (Map) o;

							if (m.get("jysinfo").equals(s.getName())) {
								
								shiChan.add(m.get("bz") + "");
								shiChan.add(m.get("by") + "");
								shiChan.add(m.get("bn") + "");
								shiChan.add(m.get("lj") + "");

							}
						}

					}
					s.setLists(shiChan);
				}
			}

			dto.setEntities(res);
			if(jiaoyiList==null){
				return this.resultSuccessData(request, response, "", null);
			}else{
				return this.resultSuccessData(request, response, "", dto);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

	
	/**
	 * 总况——总量——用户
	 * 客户数
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/yongHuShu" })
	@ResponseBody
	public String yongHuShu(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");

			if ("day".equals(type)) {
				List<Object> lists = PostgreUtils.excuteQuery(sql.yongHuDay(),
						null);
			} else if ("week".equals(type)) {
				List<Object> lists = PostgreUtils.excuteQuery(sql.yongHuWeek(),
						null);
			} else if ("month".equals(type)) {
				List<Object> lists = PostgreUtils.excuteQuery(sql.yongHuMonth(),
						null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			List<Object> lists = PostgreUtils.excuteQuery(sql.yongHuDay(), null);
			List<Object> tradelist = PostgreUtils.excuteQuery(sql.shichan(),
					null);
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

							if (m.get("jysinfo").equals(s.getName())) {
								
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

						if (m.get("jysinfo").equals(s.getName()) && a == 1) {
							times.add(m.get("vday") + "");

						}

					}
				}
				a = 2;
			}
			}
			dto.setTimes(times.toArray());

			dto.setEntities(res);

			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}
	/**
	 * 总况——总量——用户——金融资产类-客户分类
	 * 
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/keHuFenLei" })
	@ResponseBody
	public String keHuFenLei(HttpServletRequest request,HttpServletResponse response){
		try {
			KeHuFenLei kh=new KeHuFenLei();			
			List<Object>  khfl=PostgreUtils.excuteQuery(sql.keHuFenLei(),null);
			if(khfl!=null&&khfl.size()>0){
				for(Object o:khfl){
					Map m=(Map)o;
					kh.setGrs(m.get("grkhs")+"");
					kh.setJgs(m.get("jgkhs")+"");
				}
			}
			
			return this.resultSuccessData(request, response, "", kh);
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
		
	}
	/**
	 * 总况——总量——用户——客户统计
	 * 
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/keHuTongJi" })
	@ResponseBody
	public String keHuTongJi(HttpServletRequest request,HttpServletResponse response){
		try {
			LittleProjectDto dto=new LittleProjectDto();
			List<Object> tongji=PostgreUtils.excuteQuery(sql.keHuTongJi(),null);
			List<Object> tradelist = PostgreUtils.excuteQuery(sql.shichan(),null);
			List<LittleProjectEntity> res=new ArrayList<LittleProjectEntity>();
			if(tradelist!=null&&tradelist.size()>0){
				for(Object t:tradelist){
					Map m=(Map) t;
					LittleProjectEntity aa=new LittleProjectEntity();
					aa.setName(m.get("jysinfo")+"");
					res.add(aa);
				}
				
			}
			if(res!=null&&res.size()>0){
				for(LittleProjectEntity s:res){
					List<String> shichan=new ArrayList<String>();
					if(tongji!=null&&tongji.size()>0){
						for(Object o: tongji){
							Map m=(Map) o;
							if(m.get("jysinfo").equals(s.getName())){
								shichan.add(m.get("rzrkhs")+"");
								shichan.add(m.get("tzrkhs")+"");
								shichan.add(m.get("count")+"");
							}
						}
					}
					s.setLists(shichan);
				}
			}
			dto.setEntities(res);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}
	/**
	 * 总况——总量——用户——客户年龄
	 * 
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/kehuAge" })
	@ResponseBody
	public String kehuAge(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			KeHuAge res = new KeHuAge();
			List<Object> tongji = PostgreUtils.excuteQuery(sql.keHuAge(), null);
			List<String> age = new ArrayList<String>();
			List<String> sum = new ArrayList<String>();
			if (tongji != null && tongji.size() > 0) {
				for (Object o : tongji) {
					Map m = (Map) o;

					res.setName(m.get("jysinfo") + "");

				}

			}
			if (tongji != null && tongji.size() > 0) {
				for (Object o : tongji) {
					Map m = (Map) o;
					age.add(m.get("coalesce") + "");
					sum.add(m.get("sum") + "");

				}

			}

			res.setAge(age);
			res.setSum(sum);
			return this.resultSuccessData(request, response, "", res);
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

}
