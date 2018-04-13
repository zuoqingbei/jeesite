package com.qdch.portal.littleproject.web;


import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.TradeModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class SummaryController extends BaseController {


    /**
     * 交易额
     * @param
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = {"${portalPath}/littleproject/summaryController_tradeAmount"})
    @ResponseBody
    public String  summaryController_tradeAmount(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> results = new HashMap<String,Object>();

        List<Object> lists = PostgreUtils.excuteQuery(tradeDay(),null);
        Set<String> times = new HashSet<String>();
        List<String> lianhe = new ArrayList<String>();
        List<String> qingjin = new ArrayList<String>();
        List<String> wenhua = new ArrayList<String>();
        for(Object o:lists){
            TradeModel tradeModel = (TradeModel)o;
            times.add(((TradeModel) o).getVday());
            if(((TradeModel) o).getJys().equals("0012")){
                lianhe.add(((TradeModel) o).getFvalue());
            }else if(((TradeModel) o).getJys().equals("0014")){
                qingjin.add(((TradeModel) o).getFvalue());
            }else if(((TradeModel) o).getJys().equals("0015")){
                wenhua.add(((TradeModel) o).getFvalue());
            }
        }
        results.put("times",times);
        results.put("lianhe",lianhe);
        results.put("qingjin",qingjin);
        results.put("wenhua",wenhua);

        return this.resultSuccessData(request,response, "", results);
    }

    public String tradeDay(){
        String sql = "SELECT \n" +
                "\thdp.\"PERIOD_DATE\" vday,\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo,\n" +
                "\tcoalesce((SELECT\n" +
                "\t\tsum(IFA.fvalue) 成交金额\n" +
                "\t\tFROM insight_transaction_amount IFA\n" +
                "\t\twhere IFA.JYS = HDTJ.jys and to_date(vday,'yyyymmdd') = hdp.\"PERIOD_DATE\"\n" +
                "\t),0) FVALUE\n" +
                "FROM HUB_DD_TQS_JYS HDTJ\n" +
                "LEFT JOIN HUB_REF_JYSINFO HRJ\n" +
                "ON HDTJ.JYS = HRJ.JYS\n" +
                "CROSS JOIN hub_d_period hdp\n" +
                "where HDTJ.jys in ('0015') and hdp.\"PERIOD_DATE\" >= now() - interval'15 d' AND hdp.\"PERIOD_DATE\" < NOW()\n" +
                "GROUP BY\n" +
                "\t\thdp.\"PERIOD_DATE\",\n" +
                "\t\tHDTJ.jys,\n" +
                "\t\tHDTJ.jysmc,\n" +
                "\t\tHRJ.jysinfo\n" +
                "UNION ALL\n" +
                "SELECT \n" +
                "\thdp.\"PERIOD_DATE\" vday,\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo,\n" +
                "\tcoalesce((SELECT\n" +
                "\t\tsum(IFA.fvalue/10000) 成交金额\n" +
                "\t\tFROM insight_finance_amount IFA\n" +
                "\t\twhere IFA.JYS = HDTJ.jys and to_date(vday,'yyyymmdd') = hdp.\"PERIOD_DATE\"\n" +
                "\t),0) FVALUE\n" +
                "FROM HUB_DD_TQS_JYS HDTJ\n" +
                "LEFT JOIN HUB_REF_JYSINFO HRJ\n" +
                "ON HDTJ.JYS = HRJ.JYS\n" +
                "CROSS JOIN hub_d_period hdp\n" +
                "where jysfl = '2' and hdp.\"PERIOD_DATE\" >= now() - interval'15 d' AND hdp.\"PERIOD_DATE\" < NOW()\n" +
                "GROUP BY\n" +
                "\t\thdp.\"PERIOD_DATE\",\n" +
                "\t\tHDTJ.jys,\n" +
                "\t\tHDTJ.jysmc,\n" +
                "\t\tHRJ.jysinfo" +
                " order by vday";
        return sql;

    }

    public String tradeWeek(){
        String sql = "SELECT\n" +
                "\tCASE WHEN extract(week from CURRENT_DATE) > hdp.\"WEEK_IN_YEAR\" THEN\n" +
                "\t\t\tCURRENT_DATE-CAST((extract(week from CURRENT_DATE) - hdp.\"WEEK_IN_YEAR\")*7||' DAY' AS INTERVAL)\n" +
                "\t\tELSE CURRENT_DATE-CAST((extract(week from CURRENT_DATE) - 0)*7||' DAY' AS INTERVAL)\n" +
                "\t\t\t- CAST(extract(week from TO_DATE(EXTRACT(YEAR FROM CURRENT_DATE) - 1||'1231','YYYYMMDD')) - hdp.\"WEEK_IN_YEAR\"||' DAY' AS INTERVAL)\n" +
                "\tEND vday,\n" +
                "\thdp.\"WEEK_IN_YEAR\",\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo,\n" +
                "\tcoalesce((SELECT\n" +
                "\t\tsum(IFA.fvalue) 成交金额\n" +
                "\t\tFROM insight_transaction_amount IFA\n" +
                "\t\twhere IFA.JYS = HDTJ.jys and hdp.\"WEEK_IN_YEAR\" = extract(week from to_date(ifa.vday,'yyyymmdd'))\n" +
                "\t),0) FVALUE\n" +
                "FROM HUB_DD_TQS_JYS HDTJ\n" +
                "LEFT JOIN HUB_REF_JYSINFO HRJ\n" +
                "ON HDTJ.JYS = HRJ.JYS\n" +
                "CROSS JOIN hub_d_period hdp\n" +
                "where HDTJ.jys IN ('0015') and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'75 day'\n" +
                "\tAND hdp.\"PERIOD_DATE\" < CURRENT_DATE\n" +
                "GROUP BY\n" +
                "\thdp.\"WEEK_IN_YEAR\",\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "\tCASE WHEN extract(week from CURRENT_DATE) > hdp.\"WEEK_IN_YEAR\" THEN\n" +
                "\t\t\tCURRENT_DATE-CAST((extract(week from CURRENT_DATE) - hdp.\"WEEK_IN_YEAR\")*7||' DAY' AS INTERVAL)\n" +
                "\t\tELSE CURRENT_DATE-CAST((extract(week from CURRENT_DATE) - 0)*7||' DAY' AS INTERVAL)\n" +
                "\t\t\t- CAST(extract(week from TO_DATE(EXTRACT(YEAR FROM CURRENT_DATE) - 1||'1231','YYYYMMDD')) - hdp.\"WEEK_IN_YEAR\"||' DAY' AS INTERVAL)\n" +
                "\tEND vday,\n" +
                "\thdp.\"WEEK_IN_YEAR\",\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo,\n" +
                "\tcoalesce((SELECT\n" +
                "\t\tsum(IFA.fvalue/10000) 成交金额\n" +
                "\t\tFROM insight_finance_amount IFA\n" +
                "\t\twhere IFA.JYS = HDTJ.jys and hdp.\"WEEK_IN_YEAR\" = extract(week from to_date(ifa.vday,'yyyymmdd'))\n" +
                "\t),0) FVALUE\n" +
                "FROM HUB_DD_TQS_JYS HDTJ\n" +
                "LEFT JOIN HUB_REF_JYSINFO HRJ\n" +
                "ON HDTJ.JYS = HRJ.JYS\n" +
                "CROSS JOIN hub_d_period hdp\n" +
                "where jysfl = '2' and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'75 day'\n" +
                "\tAND hdp.\"PERIOD_DATE\" < CURRENT_DATE\n" +
                "GROUP BY\n" +
                "\thdp.\"WEEK_IN_YEAR\",\n" +
                "\tHDTJ.jys,\n" +
                "\tHDTJ.jysmc,\n" +
                "\tHRJ.jysinfo";
        return sql;

    }
    /**
     * 交易额统计
     * @time   2018年4月13日
	 * @author 高照
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"${portalPath}/littleproject/tradeAmount"})
    @ResponseBody
    public String  summaryController_jiaoYiAmount(HttpServletRequest request, HttpServletResponse response){
    	 Map<String,Object> results = new HashMap<String,Object>();

         List<Object> lists = PostgreUtils.excuteQuery(jiaoyie(),null);
         
         List<String> lianhe = new ArrayList<String>();
         List<String> qingjin = new ArrayList<String>();
         List<String> wenhua = new ArrayList<String>();
         for(Object o:lists){
             
         }
        
         results.put("lianhe",lianhe);
         results.put("qingjin",qingjin);
         results.put("wenhua",wenhua);
         try {
        	 return this.resultSuccessData(request,response, "", results);
		} catch (Exception e) {
			 return this.resultFaliureData(request, response, "", results);
		}
   	
    }
    public String jiaoyie(){
    
    	return null;
    }

}
