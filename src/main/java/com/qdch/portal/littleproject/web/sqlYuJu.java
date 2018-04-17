package com.qdch.portal.littleproject.web;

/**
 * 小程序接口数据库语句
 * 
 * @time 2018年4月13日
 * @author gaozhao
 * @param request
 * @param response
 * @return
 */
public class sqlYuJu {

	// 按天查询
	public String tradeDay() {
		String sql = "SELECT \n"
				+ "\thdp.\"PERIOD_DATE\" vday,\n"
				+ "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n"
				+ "\tHRJ.jysinfo,\n"
				+ "\tcoalesce((SELECT\n"
				+ "\t\tsum(IFA.fvalue) 成交金额\n"
				+ "\t\tFROM insight_transaction_amount IFA\n"
				+ "\t\twhere IFA.JYS = HDTJ.jys and to_date(vday,'yyyymmdd') = hdp.\"PERIOD_DATE\"\n"
				+ "\t),0) FVALUE\n"
				+ "FROM HUB_DD_TQS_JYS HDTJ\n"
				+ "LEFT JOIN HUB_REF_JYSINFO HRJ\n"
				+ "ON HDTJ.JYS = HRJ.JYS\n"
				+ "CROSS JOIN hub_d_period hdp\n"
				+ "where HDTJ.jys in ('0015') and hdp.\"PERIOD_DATE\" >= now() - interval'15 d' AND hdp.\"PERIOD_DATE\" < NOW()\n"
				+ "GROUP BY\n"
				+ "\t\thdp.\"PERIOD_DATE\",\n"
				+ "\t\tHDTJ.jys,\n"
				+ "\t\tHDTJ.jysmc,\n"
				+ "\t\tHRJ.jysinfo\n"
				+ "UNION ALL\n"
				+ "SELECT \n"
				+ "\thdp.\"PERIOD_DATE\" vday,\n"
				+ "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n"
				+ "\tHRJ.jysinfo,\n"
				+ "\tcoalesce((SELECT\n"
				+ "\t\tsum(IFA.fvalue/10000) 成交金额\n"
				+ "\t\tFROM insight_finance_amount IFA\n"
				+ "\t\twhere IFA.JYS = HDTJ.jys and to_date(vday,'yyyymmdd') = hdp.\"PERIOD_DATE\"\n"
				+ "\t),0) FVALUE\n"
				+ "FROM HUB_DD_TQS_JYS HDTJ\n"
				+ "LEFT JOIN HUB_REF_JYSINFO HRJ\n"
				+ "ON HDTJ.JYS = HRJ.JYS\n"
				+ "CROSS JOIN hub_d_period hdp\n"
				+ "where jysfl = '2' and hdp.\"PERIOD_DATE\" >= now() - interval'15 d' AND hdp.\"PERIOD_DATE\" < NOW()\n"
				+ "GROUP BY\n" + "\t\thdp.\"PERIOD_DATE\",\n"
				+ "\t\tHDTJ.jys,\n" + "\t\tHDTJ.jysmc,\n" + "\t\tHRJ.jysinfo"
				+ " order by vday";
		return sql;

	}

	// 按周查询
	public String tradeWeek() {
		String sql = "SELECT\n"
				+ "\tCASE WHEN extract(week from CURRENT_DATE) > hdp.\"WEEK_IN_YEAR\" THEN\n"
				+ "\t\t\tCURRENT_DATE-CAST((extract(week from CURRENT_DATE) - hdp.\"WEEK_IN_YEAR\")*7||' DAY' AS INTERVAL)\n"
				+ "\t\tELSE CURRENT_DATE-CAST((extract(week from CURRENT_DATE) - 0)*7||' DAY' AS INTERVAL)\n"
				+ "\t\t\t- CAST(extract(week from TO_DATE(EXTRACT(YEAR FROM CURRENT_DATE) - 1||'1231','YYYYMMDD')) - hdp.\"WEEK_IN_YEAR\"||' DAY' AS INTERVAL)\n"
				+ "\tEND vday,\n"
				+ "\thdp.\"WEEK_IN_YEAR\",\n"
				+ "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n"
				+ "\tHRJ.jysinfo,\n"
				+ "\tcoalesce((SELECT\n"
				+ "\t\tsum(IFA.fvalue) 成交金额\n"
				+ "\t\tFROM insight_transaction_amount IFA\n"
				+ "\t\twhere IFA.JYS = HDTJ.jys and hdp.\"WEEK_IN_YEAR\" = extract(week from to_date(ifa.vday,'yyyymmdd'))\n"
				+ "\t),0) FVALUE\n"
				+ "FROM HUB_DD_TQS_JYS HDTJ\n"
				+ "LEFT JOIN HUB_REF_JYSINFO HRJ\n"
				+ "ON HDTJ.JYS = HRJ.JYS\n"
				+ "CROSS JOIN hub_d_period hdp\n"
				+ "where HDTJ.jys IN ('0015') and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'75 day'\n"
				+ "\tAND hdp.\"PERIOD_DATE\" < CURRENT_DATE\n"
				+ "GROUP BY\n"
				+ "\thdp.\"WEEK_IN_YEAR\",\n"
				+ "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n"
				+ "\tHRJ.jysinfo\n"
				+ "UNION ALL\n"
				+ "SELECT\n"
				+ "\tCASE WHEN extract(week from CURRENT_DATE) > hdp.\"WEEK_IN_YEAR\" THEN\n"
				+ "\t\t\tCURRENT_DATE-CAST((extract(week from CURRENT_DATE) - hdp.\"WEEK_IN_YEAR\")*7||' DAY' AS INTERVAL)\n"
				+ "\t\tELSE CURRENT_DATE-CAST((extract(week from CURRENT_DATE) - 0)*7||' DAY' AS INTERVAL)\n"
				+ "\t\t\t- CAST(extract(week from TO_DATE(EXTRACT(YEAR FROM CURRENT_DATE) - 1||'1231','YYYYMMDD')) - hdp.\"WEEK_IN_YEAR\"||' DAY' AS INTERVAL)\n"
				+ "\tEND vday,\n"
				+ "\thdp.\"WEEK_IN_YEAR\",\n"
				+ "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n"
				+ "\tHRJ.jysinfo,\n"
				+ "\tcoalesce((SELECT\n"
				+ "\t\tsum(IFA.fvalue/10000) 成交金额\n"
				+ "\t\tFROM insight_finance_amount IFA\n"
				+ "\t\twhere IFA.JYS = HDTJ.jys and hdp.\"WEEK_IN_YEAR\" = extract(week from to_date(ifa.vday,'yyyymmdd'))\n"
				+ "\t),0) FVALUE\n"
				+ "FROM HUB_DD_TQS_JYS HDTJ\n"
				+ "LEFT JOIN HUB_REF_JYSINFO HRJ\n"
				+ "ON HDTJ.JYS = HRJ.JYS\n"
				+ "CROSS JOIN hub_d_period hdp\n"
				+ "where jysfl = '2' and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'75 day'\n"
				+ "\tAND hdp.\"PERIOD_DATE\" < CURRENT_DATE\n" + "GROUP BY\n"
				+ "\thdp.\"WEEK_IN_YEAR\",\n" + "\tHDTJ.jys,\n"
				+ "\tHDTJ.jysmc,\n" + "\tHRJ.jysinfo";
		return sql;

	}

	// 按月查询
	public String tradeMonth() {
		String sql = "select VMONTH,'总量' jys,'总量' jysmc,'总量' jysinfo,sum(fvalue) from"
				+ " (SELECT"
				+ " hdp.\"PERIOD_YEAR_MONTH\" VMONTH,"
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo,"
				+ " coalesce((SELECT"
				+ " sum(IFA.fvalue) 成交金额"
				+ " FROM insight_transaction_amount IFA"
				+ " where IFA.JYS = HDTJ.jys and SUBSTR(VDAY,1,6) = hdp.\"PERIOD_YEAR_MONTH\""
				+ "),0) FVALUE"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " CROSS JOIN hub_d_period hdp"
				+ " where HDTJ.jys in ('0015') and hdp.\"PERIOD_DATE\" >= TO_DATE(TO_NUMBER(TO_CHAR(now() - interval'1 Y','YYYYMM'),'999999')+1||'01','YYYYMMDD')"
				+ " AND hdp.\"PERIOD_DATE\" < NOW()"
				+ " GROUP BY"
				+ " hdp.\"PERIOD_YEAR_MONTH\","
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo"
				+ " UNION ALL"
				+ " SELECT"
				+ " hdp.\"PERIOD_YEAR_MONTH\" VMONTH,"
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo,"
				+ " coalesce((SELECT"
				+ " sum(IFA.fvalue)/10000 成交金额"
				+ " FROM insight_finance_amount IFA"
				+ " where IFA.JYS = HDTJ.jys and SUBSTR(VDAY,1,6) = hdp.\"PERIOD_YEAR_MONTH\""
				+ "),0) FVALUE"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " CROSS JOIN hub_d_period hdp"
				+ " where jysFL = '2' and hdp.\"PERIOD_DATE\" >= TO_DATE(TO_NUMBER(TO_CHAR(now() - interval'1 Y','YYYYMM'),'999999')+1||'01','YYYYMMDD')"
				+ " AND hdp.\"PERIOD_DATE\" < NOW()"
				+ " GROUP BY"
				+ " hdp.\"PERIOD_YEAR_MONTH\","
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo) ls"
				+ " group by vmonth"
				+ " union ALL"
				+ " SELECT"
				+ " hdp.\"PERIOD_YEAR_MONTH\" VMONTH,"
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo,"
				+ " coalesce((SELECT"
				+ " sum(IFA.fvalue) 成交金额"
				+ " FROM insight_transaction_amount IFA"
				+ " where IFA.JYS = HDTJ.jys and SUBSTR(VDAY,1,6) = hdp.\"PERIOD_YEAR_MONTH\""
				+ "),0) FVALUE"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " CROSS JOIN hub_d_period hdp"
				+ " where HDTJ.jys in ('0015') and hdp.\"PERIOD_DATE\" >= TO_DATE(TO_NUMBER(TO_CHAR(now() - interval'1 Y','YYYYMM'),'999999')+1||'01','YYYYMMDD')"
				+ " AND hdp.\"PERIOD_DATE\" < NOW()"
				+ " GROUP BY"
				+ " hdp.\"PERIOD_YEAR_MONTH\","
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo"
				+ " UNION ALL"
				+ " SELECT"
				+ " hdp.\"PERIOD_YEAR_MONTH\" VMONTH,"
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo,"
				+ " coalesce((SELECT"
				+ " sum(IFA.fvalue)/10000 成交金额"
				+ " FROM insight_finance_amount IFA"
				+ " where IFA.JYS = HDTJ.jys and SUBSTR(VDAY,1,6) = hdp.\"PERIOD_YEAR_MONTH\""
				+ "),0) FVALUE"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " CROSS JOIN hub_d_period hdp"
				+ " where jysFL = '2' and hdp.\"PERIOD_DATE\" >= TO_DATE(TO_NUMBER(TO_CHAR(now() - interval'1 Y','YYYYMM'),'999999')+1||'01','YYYYMMDD')"
				+ " AND hdp.\"PERIOD_DATE\" < NOW()"
				+ " GROUP BY"
				+ " hdp.\"PERIOD_YEAR_MONTH\","
				+ " HDTJ.jys,"
				+ " HDTJ.jysmc,"
				+ " HRJ.jysinfo";

		return sql;
	}

	// 查询交易市场
	public String shichan() {
		String sql = "select hdtj.jys,hdtj.jysmc,hrj.jysinfo from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj"
				+ " on hdtj.jys = hrj.jys"
				+ " where hdtj.jysfl = '2' or hrj.jys in ('0015')";

		return sql;
	}

	// 总交易额
	public String jiaoyi() {
		String sql = "select"
				+ " ita.jys,"
				+ " hdtj.jysmc,"
				+ " hrj.jysinfo,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')-1) ||' days' as interval)"
				+ " then fvalue/10000 else 0 end),2) bz,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyymm')||'01','yyyymmdd')"
				+ " then fvalue/10000 else 0 end),2) by,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyy')||'0101','yyyymmdd')"
				+ " then fvalue/10000 else 0 end),2) bn,"
				+ " ROUND(sum(fvalue/10000),2) lj"
				+ " from insight_transaction_amount ita"
				+ " left join hub_dd_tqs_jys hdtj"
				+ " on ita.jys = hdtj.jys"
				+ " left join hub_ref_jysinfo hrj"
				+ " on ita.jys = hrj.jys"
				+ " left join hub_tqs_khxx htk"
				+ " on ita.cid = htk.cid"
				+ " where ita.jys = '0015'"
				+ " group by ita.jys,hdtj.jysmc,hrj.jysinfo"
				+ " union all"
				+ " select"
				+ " hdtj.jys,"
				+ " hdtj.jysmc,"
				+ " hrj.jysinfo,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')-1) ||' days' as interval)"
				+ " then fvalue/10000 else 0 end)) bz,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyymm')||'01','yyyymmdd')"
				+ " then fvalue/10000 else 0 end)) by,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyy')||'0101','yyyymmdd')"
				+ " then fvalue/10000 else 0 end)) bn,"
				+ " ROUND(sum(COALESCE(fvalue,0)/10000)) lj"
				+ " from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj"
				+ " on hdtj.jys = hrj.jys"
				+ " LEFT JOIN insight_finance_amount ita"
				+ " ON ita.jys = hdtj.jys"
				+ " left join hub_tqs_khxx htk"
				+ " on ita.cid = htk.cid"
				+ " where hdtj.jysfl = '2'"
				+ " group by hdtj.jys,hdtj.jysmc,hrj.jysinfo"
				+ " UNION ALL"
				+ " SELECT '总计' JYS,'总计' JYSMC,'总计' JYSINFO,ROUND(sum(BZ)),ROUND(sum(BY)),ROUND(sum(BN)),ROUND(sum(LJ)) FROM"
				+ " (select"
				+ " ita.jys,"
				+ " hdtj.jysmc,"
				+ " hrj.jysinfo,"
				+ " sum(case when to_date(ita.vday,'yyyymmdd') >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')-1) ||' days' as interval)"
				+ " then fvalue/10000 else 0 end) bz,"
				+ " sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyymm')||'01','yyyymmdd')"
				+ " then fvalue/10000 else 0 end) by,"
				+ " sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyy')||'0101','yyyymmdd')"
				+ " then fvalue/10000 else 0 end) bn,"
				+ " sum(fvalue/10000) lj"
				+ " from insight_transaction_amount ita"
				+ " left join hub_dd_tqs_jys hdtj"
				+ " on ita.jys = hdtj.jys"
				+ " left join hub_ref_jysinfo hrj"
				+ " on ita.jys = hrj.jys"
				+ " left join hub_tqs_khxx htk"
				+ " on ita.cid = htk.cid"
				+ " where ita.jys = '0015'"
				+ " group by ita.jys,hdtj.jysmc,hrj.jysinfo"
				+ " union all"
				+ " select"
				+ " hdtj.jys,"
				+ " hdtj.jysmc,"
				+ " hrj.jysinfo,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')-1) ||' days' as interval)"
				+ " then fvalue/10000 else 0 end),2) bz,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyymm')||'01','yyyymmdd')"
				+ " then fvalue/10000 else 0 end),2) by,"
				+ " ROUND(sum(case when to_date(ita.vday,'yyyymmdd') >= to_date(to_char(current_date,'yyyy')||'0101','yyyymmdd')"
				+ " then fvalue/10000 else 0 end),2) bn,"
				+ " ROUND(sum(fvalue/10000),2) lj"
				+ " from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj" + " on hdtj.jys = hrj.jys"
				+ " LEFT JOIN insight_finance_amount ita"
				+ " ON ita.jys = hdtj.jys" + " left join hub_tqs_khxx htk"
				+ " on ita.cid = htk.cid" + " where hdtj.jysfl = '2'"
				+ " group by hdtj.jys,hdtj.jysmc,hrj.jysinfo) LS";

		return sql;

	}

	public String yongHuDay() {
		String sql = "SELECT VDAY,'总量' JYS,'总量' JYSMC,'总量' JYSINFO,SUM(FVALUE) FVALUE FROM"
				+ " (SELECT"
				+ " TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " COALESCE(T.JYS,'0015') JYS,"
				+ " COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"
				+ " COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"
				+ " COALESCE(SUM(T.FVALUE),0) FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " LEFT JOIN INSIGHT_USER_QTY T"
				+ " ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') AND T.JYS IN('0015')"
				+ " WHERE HDP.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND HDP.\"PERIOD_DATE\" >= CURRENT_DATE - INTERVAL'15 DAY'"
				+ " GROUP BY HDP.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " HDTJ.JYS,"
				+ " HDTJ.JYSMC,"
				+ " HTJ.JYSINFO,"
				+ " (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS AND TO_DATE(HTK.DJRQ,'YYYYMMDD') <= HDP.\"PERIOD_DATE\") FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " CROSS JOIN HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HTJ"
				+ " ON HDTJ.JYS = HTJ.JYS"
				+ " WHERE HDTJ.JYSFL = '2'"
				+ " AND HDP.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND HDP.\"PERIOD_DATE\" >= CURRENT_DATE - INTERVAL'15 DAY'"
				+ " GROUP BY HDP.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO) LS"
				+ " GROUP BY VDAY"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " COALESCE(T.JYS,'0015') JYS,"
				+ " COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"
				+ " COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"
				+ " COALESCE(SUM(T.FVALUE),0) FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " LEFT JOIN INSIGHT_USER_QTY T"
				+ " ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') AND T.JYS IN('0015')"
				+ " WHERE HDP.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND HDP.\"PERIOD_DATE\" >= CURRENT_DATE - INTERVAL'15 DAY'"
				+ " GROUP BY HDP.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " HDTJ.JYS,"
				+ " HDTJ.JYSMC,"
				+ " HTJ.JYSINFO,"
				+ " (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS AND TO_DATE(HTK.DJRQ,'YYYYMMDD') <= HDP.\"PERIOD_DATE\") FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " CROSS JOIN HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HTJ"
				+ " ON HDTJ.JYS = HTJ.JYS"
				+ " WHERE HDTJ.JYSFL = '2'"
				+ " AND HDP.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND HDP.\"PERIOD_DATE\" >= CURRENT_DATE - INTERVAL'15 DAY'"
				+ " GROUP BY HDP.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO order by vday";

		return sql;
	}

	public String yongHuWeek() {
		String sql = "SELECT VDAY,'总量' JYS,'总量' JYSMC,'总量' JYSINFO,SUM(FVALUE) FVALUE FROM"
				+ " (SELECT"
				+ " TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " COALESCE(T.JYS,'0015') JYS,"
				+ " COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"
				+ " COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"
				+ " COALESCE(SUM(T.FVALUE),0) FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " LEFT JOIN INSIGHT_USER_QTY T"
				+ " ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') and T.jys IN ('0015')"
				+ " WHERE hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'76 day'"
				+ " AND hdp.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND hdp.\"PERIOD_DATE\" = hdp.\"PERIOD_DATE\" - CAST(to_number(to_char(hdp.\"PERIOD_DATE\",'d'),'9') - 1||' DAY' AS INTERVAL)"
				+ " GROUP BY hdp.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " HDTJ.JYS,"
				+ " HDTJ.JYSMC,"
				+ " HTJ.JYSINFO,"
				+ " (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS"
				+ " and to_date(HTK.djrq,'yyyymmdd') <= hdp.\"PERIOD_DATE\") FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " CROSS JOIN HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HTJ"
				+ " ON HDTJ.JYS = HTJ.JYS"
				+ " WHERE HDTJ.JYSFL = '2'"
				+ " and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'76 day'"
				+ " AND hdp.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND hdp.\"PERIOD_DATE\" = hdp.\"PERIOD_DATE\" - CAST(to_number(to_char(hdp.\"PERIOD_DATE\",'d'),'9') - 1||' DAY' AS INTERVAL)"
				+ " GROUP BY hdp.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO) LS"
				+ " GROUP BY VDAY"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " COALESCE(T.JYS,'0015') JYS,"
				+ " COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"
				+ " COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"
				+ " COALESCE(SUM(T.FVALUE),0) FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " LEFT JOIN INSIGHT_USER_QTY T"
				+ " ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') and T.jys IN ('0015')"
				+ " WHERE hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'76 day'"
				+ " AND hdp.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND hdp.\"PERIOD_DATE\" = hdp.\"PERIOD_DATE\" - CAST(to_number(to_char(hdp.\"PERIOD_DATE\",'d'),'9') - 1||' DAY' AS INTERVAL)"
				+ " GROUP BY hdp.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"
				+ " UNION ALL"
				+ " SELECT"
				+ " TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " HDTJ.JYS,"
				+ " HDTJ.JYSMC,"
				+ " HTJ.JYSINFO,"
				+ " (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS"
				+ " and to_date(HTK.djrq,'yyyymmdd') <= hdp.\"PERIOD_DATE\") FVALUE"
				+ " FROM HUB_D_PERIOD HDP"
				+ " CROSS JOIN HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HTJ"
				+ " ON HDTJ.JYS = HTJ.JYS"
				+ " WHERE HDTJ.JYSFL = '2'"
				+ " and hdp.\"PERIOD_DATE\" >= CURRENT_DATE - cast((TO_NUMBER(to_char(CURRENT_DATE,'D'),'99')) ||' days' as interval) - interval'76 day'"
				+ " AND hdp.\"PERIOD_DATE\" < CURRENT_DATE"
				+ " AND hdp.\"PERIOD_DATE\" = hdp.\"PERIOD_DATE\" - CAST(to_number(to_char(hdp.\"PERIOD_DATE\",'d'),'9') - 1||' DAY' AS INTERVAL)"
				+ " GROUP BY hdp.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO order by vday";
		return sql;
	}
	public String yongHuMonth(){
		String sql="SELECT VDAY,'总量' JYS,'总量' JYSMC,'总量' JYSINFO,SUM(FVALUE) FVALUE FROM"+
" (SELECT"+
	" TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"+
	" COALESCE(T.JYS,'0015') JYS,"+
	" COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"+
	" COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"+
	" coalesce(SUM(T.FVALUE),0) FVALUE"+
" FROM HUB_D_PERIOD HDP"+
" LEFT JOIN INSIGHT_USER_QTY T"+
" ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') and T.jys IN ('0015')"+
" WHERE HDP.\"PERIOD_DATE\" > CURRENT_DATE - INTERVAL'1 YEAR' AND HDP.\"PERIOD_DATE\" <= CURRENT_DATE"+
	" AND HDP.\"PERIOD_DATE\" = TO_DATE(TO_CHAR(HDP.\"PERIOD_DATE\" + INTERVAL'1 MONTH','YYYYMM')||'01','YYYYMMDD') - INTERVAL'1 DAY'"+
" GROUP BY HDP.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"+
" UNION ALL"+
" SELECT"+
	" TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"+
	" HDTJ.JYS,"+
	" HDTJ.JYSMC,"+
	" HTJ.JYSINFO,"+
	" (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS"+
		" and to_date(HTK.djrq,'yyyymmdd') <= HDP.\"PERIOD_DATE\") FVALUE"+
" FROM HUB_D_PERIOD HDP"+
" CROSS JOIN HUB_DD_TQS_JYS HDTJ"+
" LEFT JOIN HUB_REF_JYSINFO HTJ"+
" ON HDTJ.JYS = HTJ.JYS"+
" WHERE HDTJ.JYSFL = '2'"+
	" AND HDP.\"PERIOD_DATE\" > CURRENT_DATE - INTERVAL'1 YEAR'"+
	" AND HDP.\"PERIOD_DATE\" <= CURRENT_DATE"+
	" AND HDP.\"PERIOD_DATE\" = TO_DATE(TO_CHAR(HDP.\"PERIOD_DATE\" + INTERVAL'1 MONTH','YYYYMM')||'01','YYYYMMDD') - INTERVAL'1 DAY'"+
" GROUP BY hdp.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO) LS"+
" GROUP BY VDAY"+
" UNION ALL"+
" SELECT"+
	" TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"+
	" COALESCE(T.JYS,'0015') JYS,"+
	" COALESCE(T.JYSMC,'青岛文化产权交易中心有限公司') JYSMC,"+
	" COALESCE(T.JYSINFO,'青岛文化产权') JYSINFO,"+
	" coalesce(SUM(T.FVALUE),0) FVALUE"+
" FROM HUB_D_PERIOD HDP"+
" LEFT JOIN INSIGHT_USER_QTY T"+
" ON HDP.\"PERIOD_DATE\" = TO_DATE(T.VDAY,'YYYYMMDD') and T.jys IN ('0015')"+
" WHERE HDP.\"PERIOD_DATE\" > CURRENT_DATE - INTERVAL'1 YEAR' AND HDP.\"PERIOD_DATE\" <= CURRENT_DATE"+
	" AND HDP.\"PERIOD_DATE\" = TO_DATE(TO_CHAR(HDP.\"PERIOD_DATE\" + INTERVAL'1 MONTH','YYYYMM')||'01','YYYYMMDD') - INTERVAL'1 DAY'"+
" GROUP BY HDP.\"PERIOD_DATE\",T.JYS,T.JYSMC,T.JYSINFO"+
" UNION ALL"+
" SELECT"+
	" TO_CHAR(hdp.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"+
	" HDTJ.JYS,"+
	" HDTJ.JYSMC,"+
	" HTJ.JYSINFO,"+
	" (SELECT COUNT(HTK.CID) FROM HUB_TQS_KHXX HTK WHERE HTK.JYS = HDTJ.JYS"+
		" and to_date(HTK.djrq,'yyyymmdd') <= HDP.\"PERIOD_DATE\") FVALUE"+
" FROM HUB_D_PERIOD HDP"+
" CROSS JOIN HUB_DD_TQS_JYS HDTJ"+
" LEFT JOIN HUB_REF_JYSINFO HTJ"+
" ON HDTJ.JYS = HTJ.JYS"+
" WHERE HDTJ.JYSFL = '2'"+
" 	AND HDP.\"PERIOD_DATE\" > CURRENT_DATE - INTERVAL'1 YEAR'"+
" 	AND HDP.\"PERIOD_DATE\" <= CURRENT_DATE"+
" 	AND HDP.\"PERIOD_DATE\" = TO_DATE(TO_CHAR(HDP.\"PERIOD_DATE\" + INTERVAL'1 MONTH','YYYYMM')||'01','YYYYMMDD') - INTERVAL'1 DAY' "+
" GROUP BY hdp.\"PERIOD_DATE\",HDTJ.JYS,HDTJ.JYSMC,HTJ.JYSINFO order by vaday";
		return sql;
	}

	// 客户分类
	public String keHuFenLei() {
		String sql = "SELECT"
				+ " SUM(CASE WHEN JGBZ = '0' THEN 1 ELSE 0 END) GRKHS,"
				+ " SUM(CASE WHEN JGBZ = '1' THEN 1 ELSE 0 END) JGKHS"
				+ " FROM HUB_TQS_KHXX HTK" + " LEFT JOIN HUB_DD_TQS_JYS HDTJ"
				+ " ON HTK.JYS = HDTJ.JYS";
		return sql;
	}

	// 客户统计
	public String keHuTongJi() {
		String sql = "SELECT"
				+ " HDTJ.JYS,"
				+ " HDTJ.JYSMC,"
				+ " HRJ.JYSINFO,"
				+ " (SELECT COUNT(DISTINCT(HTYC.KHH)) FROM HUB_TQS_YQ_CPHY HTYC WHERE HTYC.JYS = HDTJ.JYS) RZRKHS,"
				+ " (SELECT COUNT(DISTINCT(LDTYR.CID)) FROM LAKE_DD_TQS_YQ_RGHY LDTYR WHERE LDTYR.JYS = HDTJ.JYS) TZRKHS,"
				+ " COUNT(HTK.KHH)" + " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ" + " ON HDTJ.JYS = HRJ.JYS"
				+ " LEFT JOIN HUB_TQS_KHXX HTK" + " ON HTK.JYS = HDTJ.JYS"
				+ " WHERE HDTJ.JYSFL = '2'"
				+ " GROUP BY HDTJ.JYS,HDTJ.JYSMC,HRJ.JYSINFO";

		return sql;
	}
	//客户年龄
	public String keHuAge() {
		String sql = "SELECT"
				+ " HDTJ.JYS,"
				+ " HRJ.JYSINFO,"
				+ " HDTJ.JYSMC,"
				+ " SUM(CASE WHEN IUQ.VDAY = TO_CHAR(CURRENT_DATE -INTERVAL'1 DAY','YYYYMMDD')"
				+ " AND (IUQ.AGERANGE = LS.AGERANGE OR (LS.AGERANGE IS NULL AND IUQ.AGERANGE IS NULL)) THEN FVALUE ELSE 0 END),"
				+ " COALESCE(LS.AGERANGE,'未知')"
				+ " FROM (SELECT '小于18岁' AGERANGE"
				+ " UNION ALL	SELECT '18-30岁' AGERANGE"
				+ " UNION ALL	SELECT '31-50岁' AGERANGE"
				+ " UNION ALL	SELECT '51-65岁' AGERANGE"
				+ " UNION ALL	SELECT '大于65岁' AGERANGE"
				+ " UNION ALL	SELECT NULL AGERANGE) LS"
				+ " CROSS JOIN HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " LEFT JOIN INSIGHT_USER_QTY IUQ"
				+ " ON (IUQ.AGERANGE = LS.AGERANGE OR (LS.AGERANGE IS NULL AND IUQ.AGERANGE IS NULL))"
				+ " AND IUQ.JYS = HDTJ.JYS" + " WHERE HDTJ.JYS = '0015'"
				+ " GROUP BY HDTJ.JYS,HRJ.JYSINFO,HDTJ.JYSMC,LS.AGERANGE";

		return sql;
	}
	//产品分布
	public String chanpinfenbu(){
		String sql="";
		return sql;
	}
	//产品趋势
	public String chanpinqushi() {
		String sql = "SELECT"
				+ " TO_CHAR(HDP.\"PERIOD_DATE\",'YYYYMMDD') VDAY,"
				+ " HQC1.CPLB,"
				+ " COALESCE((SELECT COUNT(1) FROM HUB_QY_CPLB HQC2"
				+ " LEFT JOIN INSIGHT_FINANCE_AMOUNT IFA"
				+ " ON IFA.CPMC = HQC2.CPMC"
				+ " WHERE HQC1.CPLB = HQC2.CPLB AND TO_DATE(IFA.VDAY,'YYYYMMDD') <= HDP.\"PERIOD_DATE\"),0) CPSL"
				+ " FROM HUB_QY_CPFL HQC1"
				+ " CROSS JOIN HUB_D_PERIOD HDP"
				+ " LEFT JOIN HUB_QY_CPLB HQC2"
				+ " ON HQC1.CPLB = HQC2.CPLB"
				+ " LEFT JOIN INSIGHT_FINANCE_AMOUNT IFA"
				+ " ON IFA.CPMC = HQC2.CPMC AND HDP.\"PERIOD_DATE\" = TO_DATE(IFA.VDAY,'YYYYMMDD')"
				+ " WHERE HDP.\"PERIOD_DATE\" > CURRENT_DATE - INTERVAL'1 DAY' -INTERVAL'1 YEAR'"
				+ " AND HDP.\"PERIOD_DATE\" <= CURRENT_DATE - INTERVAL'1 DAY'"
				+ " AND (HDP.\"PERIOD_DATE\" = CURRENT_DATE - INTERVAL'1 DAY'"
				+ " OR HDP.\"PERIOD_DATE\" = TO_DATE(TO_CHAR(HDP.\"PERIOD_DATE\" + INTERVAL'1 MONTH','YYYYMM')||'01','YYYYMMDD') - INTERVAL'1 DAY')"
				+ " GROUP BY HDP.\"PERIOD_DATE\",HQC1.CPLB"
				+ " ORDER BY HDP.\"PERIOD_DATE\"";
		return sql;
	}
}
