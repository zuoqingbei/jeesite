package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.DtoModelDao;
import com.qdch.portal.littleproject.entity.*;

import org.dozer.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 总况
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


				lists = PostgreUtils.getInstance().excuteQuery(sql.tradeDay(),
						null);
			} else if ("week".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(sql.tradeWeek(),
						null);
			} else if ("month".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(
						sql.tradeMonth(), null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			/*lists = PostgreUtils.getInstance().excuteQuery(sql.tradeDay(),
			 null);*/
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


			List<Object> jiaoyiList = PostgreUtils.getInstance().excuteQuery(
					sql.jiaoyi(), null);
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
			if (jiaoyiList == null && jiaoyiList.size() < 0) {
				
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
	 * 总况——总量——用户 客户数
	 * 
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/yongHuShu" })
	@ResponseBody
	public String yongHuShu(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			List<Object> lists = null;
			if ("day".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(sql.yongHuDay(),
						null);
			} else if ("week".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(
						sql.yongHuWeek(), null);
			} else if ("month".equals(type)) {
				lists = PostgreUtils.getInstance().excuteQuery(
						sql.yongHuMonth(), null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			// List<Object> lists =
			// PostgreUtils.getInstance().excuteQuery(sql.yongHuDay(), null);
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
	 * 总况——总量——用户——金融资产类-客户分类
	 *
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/keHuFenLei" })
	@ResponseBody
	public String keHuFenLei(HttpServletRequest request,
			HttpServletResponse response) {
		try {


			KeHuFenLei kh = new KeHuFenLei();
			List<Object> khfl = PostgreUtils.getInstance().excuteQuery(
					sql.keHuFenLei(), null);
			if (khfl != null && khfl.size() > 0) {
				for (Object o : khfl) {
					Map m = (Map) o;
					kh.setGrs(m.get("grkhs") + "");
					kh.setJgs(m.get("jgkhs") + "");
				}
			}
			if (khfl == null && khfl.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", kh);
			}



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
	public String keHuTongJi(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			LittleProjectDto dto = new LittleProjectDto();
			List<Object> tongji = PostgreUtils.getInstance().excuteQuery(
					sql.keHuTongJi(), null);
			List<Object> tradelist = PostgreUtils.getInstance().excuteQuery(
					sql.shichan(), null);
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				for (Object t : tradelist) {
					Map m = (Map) t;
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(m.get("jysinfo") + "");

					res.add(aa);
				}

			}
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {
					List<String> shichan = new ArrayList<String>();
					if (tongji != null && tongji.size() > 0) {
						for (Object o : tongji) {
							Map m = (Map) o;
							if (m.get("jysinfo").equals(s.getName())) {
								shichan.add(m.get("rzrkhs") + "");
								shichan.add(m.get("tzrkhs") + "");
								shichan.add(m.get("count") + "");
							}
						}
					}
					s.setLists(shichan);
				}
			}
			dto.setEntities(res);
			if (tongji == null && tongji.size() < 0) {
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


			List<Object> ages = PostgreUtils.getInstance().excuteQuery(
					sql.keHuAge(), null);

			List<String> age = new ArrayList<String>();
			List<String> sum = new ArrayList<String>();
			if (ages != null && ages.size() > 0) {
				for (Object o : ages) {
					Map m = (Map) o;

					res.setName(m.get("jysinfo") + "");

				}

			}
			if (ages != null && ages.size() > 0) {
				for (Object o : ages) {
					Map m = (Map) o;
					age.add(m.get("coalesce") + "");
					sum.add(m.get("sum") + "");

				}

			}

			res.setAge(age);
			res.setSum(sum);
			if (ages == null && ages.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", res);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 总况——金融资产类-产品分布
	 * 
	 * @author gaozhao
	 * @time 2018年4月17日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/chanpinfenbu" })
	@ResponseBody
	public String chanpinfenbu(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Object> lists = PostgreUtils.getInstance().excuteQuery(
					sql.chanpinfenbu(), null);
			List<Object> sy=PostgreUtils.getInstance().excuteQuery(sql.suoyouchanpin(),null);
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if(sy!=null&&sy.size()>0){
				for(Object s:sy){
					List<String> jihe = new ArrayList<String>();
					LittleProjectEntity re = new LittleProjectEntity();
					Map w = (Map) s;
					if (lists != null && lists.size() > 0) {
						for (Object o : lists) {
							Map m = (Map) o;
							if(m.get("cplb").equals(w.get("cplb"))){
								re.setName(m.get("cplb") + "");
								jihe.add(m.get("cpsl") + "");
								jihe.add(m.get("jys") + "");
							}
							
							
						}
					}
					re.setLists(jihe);
					res.add(re);
				}
			}
			
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", res);
			}

		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}
	/**
	 * 总况——金融资产类-产品趋势
	 * 
	 * @author gaozhao
	 * @time 2018年4月17日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/chanpinqushi"})
	@ResponseBody
	public String chanpinqushi(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Object> lists=null;
			lists=PostgreUtils.getInstance().excuteQuery(sql.chanpinqushi(),null);
			List<Object> sy=PostgreUtils.getInstance().excuteQuery(sql.suoyouchanpin(),null);
			List<String> times = new ArrayList<String>();
			LittleProjectDto dto=new LittleProjectDto();
			List<LittleProjectEntity> res=new ArrayList<LittleProjectEntity>();
			int b=1;
			if(sy!=null&&sy.size()>0){
				for(Object o:sy){
					Map m=(Map)o;
					LittleProjectEntity re=new LittleProjectEntity();
					re.setName(m.get("cplb")+"");
					res.add(re);
				}
			}
			if(res!=null&&res.size()>0){
				for(LittleProjectEntity s:res){
					List<String> jihe=new ArrayList<String>();
					if(lists!=null&&lists.size()>0){
						for(Object o:lists){
							Map m=(Map)o;
							if(m.get("cplb").equals(s.getName())){
								jihe.add(m.get("cpsl")+"");
							}
						}
					}
					s.setLists(jihe);
				}
			}
			if(res!=null&&res.size()>0){
				for(LittleProjectEntity s:res){
					if(lists!=null&&lists.size()>0){
						for(Object o:lists){
							Map m=(Map)o;
							if(m.get("cplb").equals(s.getName())&&b==1){
								times.add(m.get("vday")+"");
							}
						}
					}
					b=2;
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
	 * 总况——金融资产类-平均年化利率
	 * 
	 * @author gaozhao
	 * @time 2018年4月17日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/nianhualilv"})
	@ResponseBody
	public String nianhualilv(HttpServletRequest request,HttpServletResponse response){
		
		try {


			List<Object> lists=null;
			lists=PostgreUtils.getInstance().excuteQuery(sql.nianhualilv(),null);
			List<Object> sy=PostgreUtils.getInstance().excuteQuery(sql.suoyouchanpin(),null);
			List<KeHuFenLei> res=new  ArrayList<KeHuFenLei>();
			if(sy!=null&&sy.size()>0){
			for(Object s:sy){
					Map w=(Map)s;
					if(lists!=null&&lists.size()>0){
						for(Object o:lists){
							Map m=(Map)o;
							KeHuFenLei re=new KeHuFenLei();
							if(m.get("cplb").equals(w.get("cplb"))){
								re.setGrs(m.get("cplb")+"");
								re.setJgs(m.get("cpsl")+"");
							}
							if(re.getGrs()!=null&&re.getGrs().length()>0){
								res.add(re);
							}
							
						}
					}
				}
			}
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}
	/**
	 * 总况——金融资产类-产品统计
	 * 
	 * @author gaozhao
	 * @time 2018年4月17日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/chanpintongji"})
	@ResponseBody
	public String chanpintongji(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Object> lists=null;
			lists=PostgreUtils.getInstance().excuteQuery(sql.chanpintongji(),null);
			List<Object> sy=PostgreUtils.getInstance().excuteQuery(sql.suoyouchanpin(),null);
			List<LittleProjectEntity> res=new  ArrayList<LittleProjectEntity>();
			if(sy!=null&&sy.size()>0){
				for(Object s:sy){
					LittleProjectEntity re=new LittleProjectEntity();
					List<String> jihe=new ArrayList<String>();
						Map w=(Map)s;
						if(lists!=null&&lists.size()>0){
							for(Object o:lists){
								Map m=(Map)o;
								
								if(m.get("cplb").equals(w.get("cplb"))){
									re.setName(m.get("cplb")+"");
									jihe.add(m.get("pjll")+"");
									jihe.add(m.get("jsyzz")+"");
								}
								
								
							}
						}
						re.setLists(jihe);
						res.add(re);
					}
				}
		
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", res);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}
	
	/**
	 * 总况——商品类-资金-沉淀资金
	 * 
	 * @author gaozhao
	 * @time 2018年4月18日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/chendianzijin" })
	@ResponseBody
	public String chendianzijin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Object type = request.getParameter("type");
			Object[] t=new Object[]{type};
			List<Object> lists = null;
			if (t!=null&&t.length>0) {

				lists = PostgreUtils.getInstance().excuteQuery(sql.chendianzijin(),
						t);
			} 
			ZiJin z=new ZiJin();
			List<String> jihe1=new ArrayList<String>();
			List<String> jihe2=new ArrayList<String>();
			if(lists!=null&&lists.size()>0){
				for(Object o:lists){
					Map m=(Map) o;
					jihe1.add(m.get("date")+"");
					jihe2.add(m.get("fvalue")+"");
					
				}
			}
			z.setA(jihe1);
			z.setB(jihe2);
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", z);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}
	/**
	 * 总况——商品类-资金-出入金
	 * 
	 * @author gaozhao
	 * @time 2018年4月18日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/churujin" })
	@ResponseBody
	public String churujin(HttpServletRequest request,HttpServletResponse response){
		try {
			Object type = request.getParameter("type");
			Object[] t=new Object[]{type};
			List<Object> lists = null;
			if (t!=null&&t.length>0) {

				lists = PostgreUtils.getInstance().excuteQuery(sql.churujinDay(),
						t);
			} 
			List<Object> alljinlist=PostgreUtils.getInstance().excuteQuery(sql.allchurujin(),null);
			LittleProjectDto dto=new LittleProjectDto();
			List<LittleProjectEntity> res=new ArrayList<LittleProjectEntity>();
			List<String> times=new ArrayList<String>();
			int a=1;
			if(alljinlist!=null&&alljinlist.size()>0){
				for(Object s:alljinlist){
					Map m=(Map)s;
					LittleProjectEntity re=new LittleProjectEntity();
					re.setName(m.get("xm")+"");
					res.add(re);
				}
			}
			if(res!=null&&res.size()>0){
				for(LittleProjectEntity s:res){
					List<String> jihe=new ArrayList<String>();
					if(lists!=null&&lists.size()>0){
						for(Object o:lists){
							Map m=(Map)o;
							if(m.get("xm").equals(s.getName())){
								jihe.add(m.get("fvalue")+"");
							}
						}
					}
					s.setLists(jihe);
				}
			}
			if(res!=null&&res.size()>0){
				for(LittleProjectEntity s:res){
					List<String> jihe=new ArrayList<String>();
					if(lists!=null&&lists.size()>0){
						for(Object o:lists){
							Map m=(Map)o;
							if(m.get("xm").equals(s.getName())&&a==1){
								times.add(m.get("date")+"");
							}
						}
					}
					a=2;
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
	 * 总况——商品类-行情-各指数行情
	 * 
	 * @author gaozhao
	 * @time 2018年4月19日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/zhishuhangqing" })
	@ResponseBody
	public String zhishuhangqing(HttpServletRequest request,HttpServletResponse response){
		try {
			FenLei dto=new FenLei();
			DecimalFormat dt=new DecimalFormat("0.00%");
			List<Object> lists=null;
			lists=PostgreUtils.getInstance().excuteQuery(sql.zhishuhangqing(),null);
			List<String> jihe1=new ArrayList<String>();
			List<String> jihe2=new ArrayList<String>();
			List<String> jihe3=new ArrayList<String>();
			if(lists!=null&&lists.size()>0){
				for(Object o:lists){
					Map m=(Map)o;
					jihe1.add(m.get("cpmc")+"");
					jihe2.add(m.get("zxjg")+"");
					
					jihe3.add(dt.format(m.get("bh"))+"");
				}
			}
			dto.setX(jihe1);
			dto.setY(jihe2);
			dto.setZ(jihe3);
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