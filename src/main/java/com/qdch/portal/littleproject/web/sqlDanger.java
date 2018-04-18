package com.qdch.portal.littleproject.web;

/**
 * 小程序接口(风险模块)数据库语句
 * 
 * @time 2018年4月18日
 * @author gaozhao
 * 
 */
public class sqlDanger {
	// 查询交易市场
	public String shichan() {
		String sql = "select hdtj.jys,hdtj.jysmc,hrj.jysinfo from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj"
				+ " on hdtj.jys = hrj.jys"
				+ " where hdtj.jysfl = '2' or hrj.jys in ('0015') order by jys";

		return sql;
	}

	public String weiranDay() {
		String sql = "";
		return sql;
	}

	public String weiranWeek() {
		String sql = "";
		return sql;
	}

	public String weiranMonth() {
		String sql = "";
		return sql;
	}

	// 风险统计
	public String fengxiantongji() {
		String sql = "SELECT"
				+ " HRJ.JYSINFO,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') > CURRENT_DATE - CAST(TO_CHAR(CURRENT_DATE,'D')||' DAY' AS INTERVAL)"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除') BZFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYYMM')||'01','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除') BYFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYY')||'0101','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除') BNFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除') LJFXZ"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " WHERE HDTJ.JYS IN ('0014','0012','0015')"
				+ " UNION ALL"
				+ " SELECT"
				+ " '总量' JYSINFO,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') > CURRENT_DATE - CAST(TO_CHAR(CURRENT_DATE,'D')||' DAY' AS INTERVAL)"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除')) BZFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYYMM')||'01','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除')) BYFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYY')||'0101','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除')) BNFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT <> '已排除')) LJFXZ"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ" + " ON HDTJ.JYS = HRJ.JYS"
				+ " WHERE HDTJ.JYS IN ('0014','0012','0015')";
		return sql;
	}
}
