
package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.BusinessInfoModelDao;
import com.qdch.portal.littleproject.dao.BusinessRelationModelDao;
import com.qdch.portal.littleproject.dao.EvaluateScoreModelDao;
import com.qdch.portal.littleproject.dao.MarketDynamicModelDao;
import com.qdch.portal.littleproject.dao.RadarModelDao;
import com.qdch.portal.littleproject.dao.ShareHolderModelDao;
import com.qdch.portal.littleproject.entity.BusinessInfoModel;
import com.qdch.portal.littleproject.entity.BusinessRelationModel;
import com.qdch.portal.littleproject.entity.CompanyRelation;
import com.qdch.portal.littleproject.entity.EvaluateScoreModel;
import com.qdch.portal.littleproject.entity.FenLei;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.MarketDynamic;
import com.qdch.portal.littleproject.entity.MarketDynamicModel;
import com.qdch.portal.littleproject.entity.Portrait;
import com.qdch.portal.littleproject.entity.RadarModel;
import com.qdch.portal.littleproject.entity.ShareHolderModel;
import com.qdch.portal.littleproject.entity.Single;
import com.qdch.portal.littleproject.entity.ZiJin;

/**
 * 
 * 画像
 * 
 * @author gaozhao
 * @time 2018年4月18日
 */
@Controller
public class DangerController extends BaseController {
	
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
			
			List<KeHuFenLei> aggregate=new ArrayList<KeHuFenLei>();
			List<RadarModel> lists=null;	
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
				if(lists!=null&&lists.size()>0){
					for(RadarModel o:lists){
						
						KeHuFenLei k=new KeHuFenLei();
						k.setGrs(o.getFxlb());
						k.setJgs(o.getWrzs()+"");
						aggregate.add(k);
					}
				}
					dto.setRadar(aggregate);
					DynamicDataSource.removeDataSourceKey();
			if (lists == null || lists.isEmpty()) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "",dto );
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
					shareHolderLists= shareHolderModelDao.getShareHolderModelDao("0014");
					
				}else{
					
					dto.setName("联合信产");
					shareHolderLists= shareHolderModelDao.getShareHolderModelDao("0012");
					
				}
			 
			}else if("3".equals(type)){
				
				dto.setName("文化产权");
				shareHolderLists= shareHolderModelDao.getShareHolderModelDao("0015");
				
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
			if (shareHolderLists == null ||shareHolderLists.size() < 0) {
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
					lists= evaluateScoreDao.evaluateScore(t);
					
				}else{
					t="联合信产";
					dto.setName(t);
					lists= evaluateScoreDao.evaluateScore(t);
					
				}
			 
			}else if("3".equals(type)){
				t="文化产权";
				dto.setName(t);
				lists= evaluateScoreDao.evaluateScore(t);
				
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
			if (lists == null || lists.isEmpty()) {
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
			if (lists == null || lists.size() < 0) {
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
			if (lists == null ||lists.size() < 0) {
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
	 * 画像-企业关系
	 *
	 * @time 2018年4月27日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	public BusinessRelationModelDao businessRelationModelDao;
	@RequestMapping(value = { "${portalPath}/littleproject/BusinessRelation" })
	@ResponseBody
	public String BusinessRelation(HttpServletRequest request,HttpServletResponse response){
		try {
			DynamicDataSource.setInsightDataSource();
			String type="";
			type=request.getParameter("type");
			List<BusinessRelationModel> lists=null;
			lists=businessRelationModelDao.getBusinessRelationModelDao(type);
			CompanyRelation dto=new CompanyRelation();
			if(lists!=null&&lists.size()>0){
				for(BusinessRelationModel b:lists){
					dto.setCompanyName(b.getCompany_name());
					dto.setLegalPerson(b.getLegal_person());
					String x=b.getSenior_managers();
					String x0=x.substring(2,x.length()-2);
					String y=b.getShareholders();
					String y0=y.substring(2,y.length()-2);
					String[] x1=x0.split("\",\"");
					String[] y1=y0.split("\",\"");
					List<String> aggeratex=Arrays.asList(x1);
					List<String> aggeratey=Arrays.asList(y1);
					dto.setManagers(aggeratex);
					dto.setShareholders(aggeratey);
				}
			}
			DynamicDataSource.removeDataSourceKey();
			if (lists == null || lists.size() < 0) {
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

