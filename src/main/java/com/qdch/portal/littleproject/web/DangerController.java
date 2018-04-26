
package com.qdch.portal.littleproject.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.BusinessInfoModelDao;
import com.qdch.portal.littleproject.dao.EvaluateScoreModelDao;
import com.qdch.portal.littleproject.dao.MarketDynamicModelDao;
import com.qdch.portal.littleproject.dao.RadarModelDao;
import com.qdch.portal.littleproject.dao.ShareHolderModelDao;
import com.qdch.portal.littleproject.entity.BusinessInfoModel;
import com.qdch.portal.littleproject.entity.EvaluateScoreModel;
import com.qdch.portal.littleproject.entity.FenLei;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;
import com.qdch.portal.littleproject.entity.MarketDynamic;
import com.qdch.portal.littleproject.entity.MarketDynamicModel;
import com.qdch.portal.littleproject.entity.Portrait;
import com.qdch.portal.littleproject.entity.RadarModel;
import com.qdch.portal.littleproject.entity.Risks;
import com.qdch.portal.littleproject.entity.ShareHolderModel;
import com.qdch.portal.littleproject.entity.Single;
import com.qdch.portal.littleproject.entity.UnknownIndex;
import com.qdch.portal.littleproject.entity.ZiJin;

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
					sql.financeMarket(), null);

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
					sql.financeMarket(), null);

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
	 * @time 2018年4月19日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/riskMonitoring" })
	@ResponseBody
	public String riskMonitoring(HttpServletRequest request,HttpServletResponse response){
		try {
			String type=request.getParameter("type");
			UnknownIndex dto = new UnknownIndex();
			DecimalFormat dt=new DecimalFormat("0.00%");
			List<Object> lists = null;
			List<Object> tradelist=null;
			List<Object> unknownIndex=null;
			List<Object> risks=null;
			//2是金融资产类，1是商品类
			if("2".equals(type)){
				lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring(),null);
				tradelist = PostgreUtils.getInstance().excuteQuery(sql.financeMarket(), null);
				unknownIndex=PostgreUtils.getInstance().excuteQuery(sql.unknownIndex(), null);
				risks= PostgreUtils.getInstance().excuteQuery(sql.risks(),null);
			}else if("1".equals(type)){
				lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoringB(),null);
				tradelist = PostgreUtils.getInstance().excuteQuery(sql.tradeMraket(), null);
				unknownIndex=PostgreUtils.getInstance().excuteQuery(sql.unknownIndex(), null);
				risks= PostgreUtils.getInstance().excuteQuery(sql.risksB(),null);
			}
			
			List<KeHuFenLei> aggregate1=new ArrayList<KeHuFenLei>();
			List<Risks> aggregate2=new ArrayList<Risks>();
			
			
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
	/**
	 * 风险-金融资产类风险监测-风险事件
	 *
	 * @time 2018年4月20日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/jysRisk" })
	@ResponseBody
	public String jysRisk(HttpServletRequest request,HttpServletResponse response){
		try {
			UnknownIndex dto = new UnknownIndex();
			DecimalFormat dt=new DecimalFormat("0.00%");
			String type=request.getParameter("type");
			Object risk = request.getParameter("risk");
			Object market=request.getParameter("market");
			Object[] t=null;
			if(market==null){
				 t=new Object[]{risk};
			}else{
				 t=new Object[]{risk,market};
			}
			
			List<Object> lists = null;
			if("2".equals(type)){
				if (t!=null&&t.length>1) {

					lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring3(),
							t);
				}else if(t!=null&&t.length>0){
					lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring2(),
							t);
				}
			}else if("1".equals(type)){
				if (t!=null&&t.length>1) {

					lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring3B(),
							t);
				}else if(t!=null&&t.length>0){
					lists = PostgreUtils.getInstance().excuteQuery(sql.riskMonitoring2B(),
							t);
				}
			}
			
			List<Single> aggregate=new ArrayList<Single>();
			if(lists!=null&&lists.size()>0){
			for(Object o:lists){
				Map m=(Map) o;
				List<String> aggregate3=new ArrayList<String>();
				Single s=new Single();
				aggregate3.add(m.get("jys")+"");
				aggregate3.add(m.get("jysmc")+"");
				aggregate3.add(m.get("jysinfo")+"");
				aggregate3.add(m.get("fxzb")+"");
				aggregate3.add(m.get("fxzbz")+"");
				aggregate3.add(dt.format(m.get("yz"))+"");
				aggregate3.add(m.get("cee")+"");
				aggregate3.add(m.get("yjsj")+"");
				s.setS(aggregate3);
				aggregate.add(s);
			}
		}
		dto.setC(aggregate);
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
	 * 画像-风险雷达图
	 *
	 * @time 2018年4月20日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public RadarModelDao radarModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/portraitRadar" })
	@ResponseBody
	public String portraitRadar(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			Portrait dto = new Portrait();
			String type=request.getParameter("type");
			List<RadarModel> lists=null;	
			LittleProjectEntity re=new LittleProjectEntity();
			List<String> aggregate=new ArrayList<String>();
				if("1".equals(type)||"2".equals(type)){
				
				if("1".equals(type)){
					
					dto.setName("青金中心");
					lists= radarModelDao.getRadarModelDao("0014");
					
				}else{
					
					dto.setName("联合信产");
					lists=  radarModelDao.getRadarModelDao("0012");
					
				}
			 
			}else if("3".equals(type)){
				
				dto.setName("文化产权");
				lists=  radarModelDao.getRadarModelDao("0015");
				
			}
			/*//风险雷达图
			if(lists!=null&&lists.size()>0){
				for(RadarModel o:lists){
					aggregate.add(o.getWrzs()+"");
				}
				re.setLists(aggregate);
			}
		
			dto.setOtherInfo(re);*/
			DynamicDataSource.removeDataSourceKey();
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", lists);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.resultFaliureData(request, response, "", null);
		}

	}
	/**
	 * 画像-股东信息
	 *
	 * @time 2018年4月20日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public ShareHolderModelDao shareHolderModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/Shareholder" })
	@ResponseBody
	public String Shareholder(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			String type=request.getParameter("type");
			Portrait dto = new Portrait();
			List<ShareHolderModel> shareHolderLists=null;
			List<ZiJin> zijiAggregate=new ArrayList<ZiJin>();
			if("1".equals(type)||"2".equals(type)){
				
				if("1".equals(type)){
					
					dto.setName("青金中心");
					shareHolderLists= shareHolderModelDao.getShareHolderModelDao();
					
				}else{
					
					dto.setName("联合信产");
					shareHolderLists= shareHolderModelDao.getShareHolderModelDao2();
					
				}
			 
			}else if("3".equals(type)){
				
				dto.setName("文化产权");
				shareHolderLists= shareHolderModelDao.getShareHolderModelDao3();
				
			}
			//股东信息
			if(shareHolderLists!=null&&shareHolderLists.size()>0){
				for(ShareHolderModel o:shareHolderLists){
					ZiJin z=new ZiJin();
					List<String> aggregate3=new ArrayList<String>();
					aggregate3.add(o.getName());
					aggregate3.add(o.getPay()+"");
					aggregate3.add(o.getPay_date());
					aggregate3.add(o.getScale()+"%");
					z.setA(aggregate3);
					zijiAggregate.add(z);
				}
				
			}
			dto.setShareholder(zijiAggregate);
			DynamicDataSource.removeDataSourceKey();
			if (shareHolderLists == null && shareHolderLists.size() < 0) {
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
	 * 画像-市场评价分数
	 *
	 * @time 2018年4月24日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public EvaluateScoreModelDao evaluateScoreDao;
	@RequestMapping(value = { "${portalPath}/littleproject/evaluateScore" })
	@ResponseBody
	public String evaluateScore(HttpServletRequest request,HttpServletResponse response){
		try {
			
			DynamicDataSource.setInsightDataSource();
			String type=request.getParameter("type");
			String t="";
			FenLei dto=new FenLei();
			List<KeHuFenLei> aggregate=new ArrayList<KeHuFenLei>();
			List<EvaluateScoreModel> lists=null;
			
			
			if("1".equals(type)||"2".equals(type)){
			
				if("1".equals(type)){
					t="青金中心";
					dto.setName(t);
					lists= evaluateScoreDao.evaluateScore();
					
				}else{
					t="联合信产";
					dto.setName(t);
					lists= evaluateScoreDao.evaluateScore2();
					
				}
			 
			}else if("3".equals(type)){
				t="文化产权";
				dto.setName(t);
				lists= evaluateScoreDao.evaluateScore3();
				
			}
			double s=0;
			if(lists!=null&&lists.size()>0){
				for(EvaluateScoreModel o:lists){
					s=s+o.getDf();
					KeHuFenLei k=new KeHuFenLei();
					k.setGrs(o.getPjfj());
					k.setJgs(o.getDf()+"");
					aggregate.add(k);
				}
			}
				dto.setSum(s);
				dto.setAbility(aggregate);
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
	 * 画像-市场动态
	 *
	 * @time 2018年4月25日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public MarketDynamicModelDao marketDynamicModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/marketDynamic" })
	@ResponseBody
	public String marketDynamic(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			String page=request.getParameter("type");//前台传入第一页以1开始
			List<MarketDynamicModel> lists=marketDynamicModelDao.getMarketDynamicModelDao((Integer.parseInt(page)-1)*4);
			List<MarketDynamic> marketList=new ArrayList<MarketDynamic>();
			if(lists!=null&&lists.size()>0){
				for(MarketDynamicModel m:lists){
					MarketDynamic mc=new MarketDynamic();
					mc.setTitle(m.getTitle());
					mc.setData_source(m.getData_source());
					mc.setPublish_date(m.getPublish_date());
					marketList.add(mc);
				}
			}
			DynamicDataSource.removeDataSourceKey();
			if (lists == null && lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", marketList);
			}
		} catch (Exception e) {
			
				e.printStackTrace();
				return this.resultFaliureData(request, response, "", null);
			
		}
	}
	/**
	 * 画像-工商信息
	 *
	 * @time 2018年4月25日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public BusinessInfoModelDao businessInfoModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/businessInfo" })
	@ResponseBody
	public String businessInfo(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			String type="";
			type=request.getParameter("type");
			String t="";
			Portrait dto = new Portrait();
			Single s=new Single();
			List<String> aggregate=new ArrayList<String>();
			List<BusinessInfoModel> lists=null;
			
			List<String> aggregate2=new ArrayList<String>();
			if("1".equals(type)||"2".equals(type)){
			
				if("1".equals(type)){
					t="青金中心";
					dto.setName(t);
					lists= businessInfoModelDao.getBusinessInfoModelDao();
					
				}else{
					t="联合信产";
					dto.setName(t);
					lists= businessInfoModelDao.getBusinessInfoModelDao2();
					
				}
			 
			}else if("3".equals(type)){
				t="文化产权";
				dto.setName(t);
				lists= businessInfoModelDao.getBusinessInfoModelDao3();
				
			}
			if(lists!=null&&lists.size()>0){
				for(BusinessInfoModel o:lists){
					
					aggregate2.add(o.getLegal_person());//法定代表人
					aggregate2.add(o.getCreate_date());//建立日期
					aggregate2.add(o.getRegister_money()+"");//注册资本
					aggregate2.add(o.getRegister_code());//工商注册号
					aggregate2.add(o.getOrganizition_code());//组织机构代码
					aggregate2.add(o.getCredit_code());//统一信用代码
					aggregate2.add(o.getTaxpayer_num());//纳税人识别号
					aggregate2.add(o.getEnglish_name());//英文名
					aggregate2.add(o.getBusiness_status());//经营状态
					aggregate2.add(o.getCompany_type());//企业类型
					aggregate2.add(o.getIndustry());//行业
					aggregate2.add(o.getBusiness_limit());//营业期限
					aggregate2.add(o.getPublish_date());//核准日期
					aggregate2.add(o.getRegister_address());//企业地址
					aggregate2.add(o.getBusiness_scope());//经营范围
					
				}
				s.setS(aggregate2);
			}	
				dto.setInfo(s);
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
}

