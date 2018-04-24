package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.CustomerAgeModelDao;
import com.qdch.portal.littleproject.dao.CustomerClassifyModelDao;
import com.qdch.portal.littleproject.dao.CustomerCountModelDao;
import com.qdch.portal.littleproject.dao.CustomerNumberModelDao;
import com.qdch.portal.littleproject.dao.DtoModelDao;
import com.qdch.portal.littleproject.dao.TradeAmountModelDao;
import com.qdch.portal.littleproject.dao.TradeCountModelDao;
import com.qdch.portal.littleproject.dao.TradeMarketModelDao;
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
	@Autowired
	public TradeAmountModelDao tradeAmountModelDao;//交易额
	
	@Autowired
	public TradeMarketModelDao tradeMarketModelDao;//交易市场


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
			DynamicDataSource.setInsightDataSource();
			String type = request.getParameter("type");
			//List<Object> lists = null;
			List<TradeAmountModel> lists1=null;
			if ("day".equals(type)) {

			lists1=tradeAmountModelDao.tradeDay();//按天查询
		
			} else if ("week".equals(type)) {
				
				lists1=tradeAmountModelDao.tradeWeek();//按周查询
			} else if ("month".equals(type)) {
				
				lists1=tradeAmountModelDao.tradeMonth();//按月查询
			}

			LittleProjectDto dto = new LittleProjectDto();
			//交易市场
			List<TradeMarketModel> tradelist=tradeMarketModelDao.tradeMarket();

			// 时间集合
			List<String> times = new ArrayList<String>();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			int a = 1;
			
			//把查询出来的市场加到市场集合中
			if (tradelist != null && tradelist.size() > 0) {
				for (TradeMarketModel o : tradelist) {
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(o.getJysinfo());
					res.add(aa);
				}
			}

			
			//交易市场的交易额一些信息
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					List<String> shiChan = new ArrayList<String>();
					if (lists1 != null && lists1.size() > 0) {

						for (TradeAmountModel o : lists1) {

							if (o.getJysinfo().equals(s.getName())) {
								if("day".equals(type)){
									shiChan.add(o.getFvalue() + "");
								}else if("week".equals(type)||"month".equals(type)){
									shiChan.add(o.getSum() + "");
								}
							}
						}

					}
					s.setLists(shiChan);
				}
			}

			// 获取时间
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					if (lists1 != null && lists1.size() > 0) {

						for (TradeAmountModel o : lists1) {
							if("day".equals(type)||"week".equals(type)){
								if (o.getJysinfo().equals(s.getName()) && a == 1) {
									times.add(o.getVday());
								}
							}else if("month".equals(type)){
								if (o.getJysinfo().equals(s.getName()) && a == 1) {
									times.add(o.getVmonth());
								}
							}
							
							

						}
					}
					a = 2;
				}
			}
			
			dto.setTimes(times.toArray());//把时间加到对象dto中
			dto.setEntities(res);//把市场的信息加到对象dto中
			DynamicDataSource.removeDataSourceKey();
			if (lists1 == null && lists1.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	@Autowired
	public TradeCountModelDao tradeCountModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/jiaoYiAmount" })
	@ResponseBody
	public String jiaoYiAmount(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			LittleProjectDto dto = new LittleProjectDto();
			//交易额统计
			List<TradeCountModel> tradeCountList = tradeCountModelDao.getTradeCountModel();
			//交易市场
			List<TradeMarketModel> tradelist=tradeMarketModelDao.tradeMarket();

			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				for (TradeMarketModel o : tradelist) {
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(o.getJysinfo());
					res.add(aa);
				}
			}
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					List<String> shiChan = new ArrayList<String>();
					if (tradeCountList != null && tradeCountList.size() > 0) {

						for (TradeCountModel o : tradeCountList) {
							if (o.getJysinfo().equals(s.getName())) {
								shiChan.add(o.getBz()+"");
								shiChan.add(o.getBy()+"");
								shiChan.add(o.getBn()+"");
								shiChan.add(o.getLj()+"");

							}
						}

					}
					s.setLists(shiChan);
				}
			}

			dto.setEntities(res);
			DynamicDataSource.removeDataSourceKey();
			if (tradeCountList == null && tradeCountList.size() < 0) {
				
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}


	/**
	 * 总况——总量——用户 客户数
	 * 
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@Autowired
	public CustomerNumberModelDao customerNumberModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/yongHuShu" })
	@ResponseBody
	public String yongHuShu(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String type = request.getParameter("type");
			List<CustomerNumberModel> lists = null;
			if ("day".equals(type)) {
				lists = customerNumberModelDao.getCustomerNumberModelDao();
			} else if ("week".equals(type)) {
				lists = customerNumberModelDao.getCustomerNumberModelDao2();
			} else if ("month".equals(type)) {
				lists = customerNumberModelDao.getCustomerNumberModelDao3();
			}
			//要返回的对象
			LittleProjectDto dto = new LittleProjectDto();
			//交易市场
			List<TradeMarketModel> tradelist=tradeMarketModelDao.tradeMarket();
			// 时间集合
			List<String> times = new ArrayList<String>();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			int a = 1;
			//给交易市场集合加入市场对象
			if (tradelist != null && tradelist.size() > 0) {
				for (TradeMarketModel o : tradelist) {
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(o.getJysinfo());
					res.add(aa);
				}
			}

			// 获取市场

			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {

					List<String> shiChan = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {

						for (CustomerNumberModel o : lists) {

							if (o.getJysinfo().equals(s.getName())) {

								shiChan.add(o.getFvalue() + "");


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

						for (CustomerNumberModel o : lists) {
								if (o.getJysinfo().equals(s.getName()) && a == 1) {
								times.add(o.getVday());

							}

						}
					}
					a = 2;
				}
			}
			dto.setTimes(times.toArray());
			dto.setEntities(res);
			DynamicDataSource.removeDataSourceKey();
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 总况——总量——用户——金融资产类-客户分类
	 *
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@Autowired
	public CustomerClassifyModelDao customerClassifyModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/keHuFenLei" })
	@ResponseBody
	public String keHuFenLei(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();

			KeHuFenLei kh = new KeHuFenLei();
			List<CustomerClassifyModel> khfl = customerClassifyModelDao.getCustomerClassifyModelDao();
			if (khfl != null && khfl.size() > 0) {
				for (CustomerClassifyModel o : khfl) {
					kh.setGrs(o.getGrkhs()+"");
					kh.setJgs(o.getJgkhs()+"");
				}
			}
			DynamicDataSource.removeDataSourceKey();
			if (khfl == null && khfl.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", kh);
			}



		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 总况——总量——用户——客户统计
	 *
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@Autowired
	public CustomerCountModelDao customerCountModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/keHuTongJi" })
	@ResponseBody
	public String keHuTongJi(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			LittleProjectDto dto = new LittleProjectDto();
			//客户统计
			List<CustomerCountModel> tongji =customerCountModelDao.getCustomerCountModelDao(); 
			//交易市场
			List<TradeMarketModel> tradelist=tradeMarketModelDao.tradeMarket();
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				for (TradeMarketModel t : tradelist) {
					LittleProjectEntity aa = new LittleProjectEntity();
					aa.setName(t.getJysinfo());
					res.add(aa);
				}

			}
			if (res != null && res.size() > 0) {
				for (LittleProjectEntity s : res) {
					List<String> shichan = new ArrayList<String>();
					if (tongji != null && tongji.size() > 0) {
						for (CustomerCountModel o : tongji) {
							if (o.getJysinfo().equals(s.getName())) {
								shichan.add(o.getRzrkhs());
								shichan.add(o.getTzrkhs());
								shichan.add(o.getCount() + "");
								
							}
						}
					}
					s.setLists(shichan);
				}
			}
			dto.setEntities(res);
			DynamicDataSource.removeDataSourceKey();
			if (tongji == null && tongji.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 总况——总量——用户——客户年龄
	 *
	 * @author gaozhao
	 * @time 2018年4月16日
	 */
	@Autowired
	public CustomerAgeModelDao customerAgeModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/kehuAge" })
	@ResponseBody
	public String kehuAge(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			KeHuAge res = new KeHuAge();
			List<CustomerAgeModel> ages = customerAgeModelDao.getCustomerAgeModelDao();
			List<String> age = new ArrayList<String>();
			List<String> sum = new ArrayList<String>();
			if (ages != null && ages.size() > 0) {
				for (CustomerAgeModel o : ages) {

					res.setName(o.getJysinfo());

				}

			}
			if (ages != null && ages.size() > 0) {
				for (CustomerAgeModel o : ages) {
					
					age.add(o.getCoalesce());
					sum.add(o.getSum()+"");

				}

			}

			res.setAge(age);
			res.setSum(sum);
			DynamicDataSource.removeDataSourceKey();
			if (ages == null && ages.size() < 0) {
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

			DecimalFormat dt=new DecimalFormat("0.00%");
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
								re.setJgs(dt.format(m.get("cpsl")));
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
			DecimalFormat dt=new DecimalFormat("0.00%");
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
									jihe.add(dt.format(m.get("pjll")));
									jihe.add(dt.format(m.get("jsyzz")));
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
			
			DecimalFormat dt=new DecimalFormat("0.00%");
			List<Object> lists=null;
			lists=PostgreUtils.getInstance().excuteQuery(sql.zhishuhangqing(),null);
			List<LittleProjectEntity> res=new ArrayList<LittleProjectEntity>();
			
			if(lists!=null&&lists.size()>0){
				for(Object o:lists){
					Map m=(Map)o;
					LittleProjectEntity re=new LittleProjectEntity();
					List<String> aggregate=new ArrayList<String>();
					aggregate.add(m.get("cpmc")+"");
					aggregate.add(m.get("cmdm")+"");
					aggregate.add(m.get("jys")+"");
					aggregate.add(m.get("jysmc")+"");
					aggregate.add(m.get("jysinfo")+"");
					aggregate.add(m.get("zxjg")+"");
					aggregate.add(dt.format(m.get("bh")));
					re.setLists(aggregate);
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
}
