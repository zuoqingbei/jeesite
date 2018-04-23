package com.qdch.portal.littleproject.web;

/**
 * 小程序接口(风险模块)数据库语句
 * 
 * @time 2018年4月18日
 * @author gaozhao
 * 
 */
public class sqlDanger {
	// 查询金融资产类交易市场
	public String financeMarket() {
		String sql = "select hdtj.jys,hdtj.jysmc,hrj.jysinfo from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj"
				+ " on hdtj.jys = hrj.jys"
				+ " where hdtj.jysfl = '2' order by jys";

		return sql;
	}

	// 查询商品类交易市场
	public String tradeMraket() {
		String sql = "select hdtj.jys,hdtj.jysmc,hrj.jysinfo from hub_dd_tqs_jys hdtj"
				+ " left join hub_ref_jysinfo hrj"
				+ " on hdtj.jys = hrj.jys"
				+ " where hdtj.jysfl = '1' and jys in ('0015') order by jys";

		return sql;
	}

	public String weiranDay() {
		String sql = "select * from  INSIGHT_XCX_WRZSQX  order by date";
		return sql;
	}

	// 风险统计
	public String fengxiantongji() {
		String sql = "SELECT"
				+ " HRJ.JYSINFO,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') > CURRENT_DATE - CAST(TO_CHAR(CURRENT_DATE,'D')||' DAY' AS INTERVAL)"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认')) BZFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYYMM')||'01','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认')) BYFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYY')||'0101','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认')) BNFXZ,"
				+ " (SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认')) LJFXZ"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN HUB_REF_JYSINFO HRJ"
				+ " ON HDTJ.JYS = HRJ.JYS"
				+ " WHERE HDTJ.JYS IN ('0014','0012','0015')"
				+ " UNION ALL"
				+ " SELECT"
				+ " '总量' JYSINFO,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') > CURRENT_DATE - CAST(TO_CHAR(CURRENT_DATE,'D')||' DAY' AS INTERVAL)"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认'))) BZFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYYMM')||'01','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认'))) BYFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND TO_DATE(HF.BJSJ,'YYYYMMDD') >= TO_DATE(TO_CHAR(CURRENT_DATE,'YYYY')||'0101','YYYYMMDD')"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认'))) BNFXZ,"
				+ " SUM((SELECT COUNT(1) FROM HUB_FXSJ HF WHERE TO_DATE(HF.BJSJ,'YYYYMMDD') <= CURRENT_DATE"
				+ " AND HF.JGDM = HDTJ.JYS AND HF.CLZT IN ('已上报','已查阅','已确认'))) LJFXZ"
				+ " FROM HUB_DD_TQS_JYS HDTJ"
				+ " LEFT JOIN  HUB_REF_JYSINFO HRJ" + " ON HDTJ.JYS = HRJ.JYS"
				+ " WHERE HDTJ.JYS IN ('0014','0012','0015')";

		return sql;
	}

	/**
	 * 金融资产类
	 * 
	 * @return
	 */

	// 风险监测(2是金融资产类)
	public String riskMonitoring() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where jysfl='2' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 风险监测(有一个参数的时候)
	public String riskMonitoring2() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where ixf.fxdl=? and jysfl='2' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 风险监测(有两个参数的时候)
	public String riskMonitoring3() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where ixf.fxdl=? and ixf.jys=? and jysfl='2' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 获取风险预测表有的风险
	public String risks() {
		String sql = "select DISTINCT(FXDL) from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys  where jysfl='2' order by ixf.fxdl";
		return sql;
	}

	// 未然指数
	public String unknownIndex() {
		String sql = "select * from insight_welcome_sheet ";
		return sql;
	}

	/**
	 * 这是商品类
	 * 
	 * @return
	 */
	// 风险监测(1是商品类)
	public String riskMonitoringB() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where jysfl='1' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 风险监测(有一个参数的时候)
	public String riskMonitoring2B() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where ixf.fxdl=? and jysfl='1' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 风险监测(有两个参数的时候)
	public String riskMonitoring3B() {
		String sql = "select * from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys where ixf.fxdl=? and ixf.jys=? and jysfl='1' order by fxdl,ixf.jys,fxzb,yjsj";
		return sql;
	}

	// 获取风险预测表有的风险
	public String risksB() {
		String sql = "select DISTINCT(FXDL) from INSIGHT_XCX_FXSJS ixf left join  hub_dd_tqs_jys hdtj on ixf.jys = hdtj.jys order by ixf.fxdl where jysfl='1'";
		return sql;
	}

	/**
	 * 画像(大宗)
	 */
	public String dazong() {
		String sql = "select * from (select fxlb from hub_fxlb where jysfl = '1' group by fxlb"
				+ " ) fxlb"
				+ " cross join hub_ref_jysinfo hrj"
				+ " left join insight_fxsj_fxlb iff"
				+ " on fxlb.fxlb = iff.fxlb and hrj.jys = iff.jys"
				+ " where hrj.jysinfo=? order by fxlb.fxlb"
				+ " select fxlb from hub_fxlb where jysfl = '1' group by fxlb";
		return sql;
	}

	// 画像(权益)
	public String quanyi() {
		String sql = "select"
				+ " jt.jysinfo,"
				+ " jt3.fxlb,"
				+ " (SELECT"
				+ " count(jys)"
				+ " from (select fxlb from public.hub_fxlb where jysfl = 2 group by fxlb) t2"
				+ " left join public.\"hub_fxsj\" t1"
				+ " on t2.fxlb=t1.fxlb  and t1.jgdm = jt.jys and t1.fxlb = jt3.fxlb"
				+ " left join public.hub_ref_jysinfo t"
				+ " on t.jys=t1.jgdm"
				+ " where t2.fxlb = jt3.fxlb"
				+ " group by t.jysinfo,t.jys,t2.fxlb,t1.jysfl,t1.jgdm) fvalue"
				+ " from hub_ref_jysinfo jt"
				+ " left join hub_dd_tqs_jys jt2"
				+ " on jt.jys = jt2.jys"
				+ " left join (select jysfl,fxlb from public.hub_fxlb group by fxlb,jysfl) jt3"
				+ " on jt2.jysfl = jt3.jysfl" + " where jt2.jysfl = '2' and jt.jysinfo=? ORDER BY jt3.fxlb";
		return sql;
	}
	//工商信息
	public String businesss() {
		String sql = "select * from hub_commerce_enterprise m "
				+ " where m.name in (select n.company_name from hub_commerce_ref_jys n"
				+ " where n.jysinfo=?)";
		return sql;
	}

	// 股东信息
	public String shareHolder() {
		String sql = "SELECT" + " T.name,T.pay,T.pay_date,T.scale,jysinfo" + " FROM"
				+ " hub_commerce_co_shareholder T"
				+ " LEFT JOIN hub_commerce_enterprise n"
				+ " ON T .company_name = n. NAME"
				+ " left join hub_dd_tqs_jys h1"
				+ " on h1.jysmc = t.company_name"
				+ " left join hub_ref_jysinfo h2" + " on h1.jys = h2.jys"
				+ " where jysinfo = ? and T.type='1' order by name";

		return sql;
	}
	//企业关系
	public String enterprise(){
		String sql="";
		return sql;
	}

}
