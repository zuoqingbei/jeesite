package com.qdch.portal.common.config;

/**
 * 
 * @className Constant.java
 * @time   2018年3月3日 上午11:26:32
 * @author zuoqb
 * @todo   清算所监管平台常量
 */
public interface Constant {
	public final static String MOBILE_VIEW_PLACEHOLDER = "${pla}";//placeholder占位符
	public final static String MOBILE_VIEW_FOLDER = "/mobile/";
	public final static String PC_VIEW_FOLDER = "/pc/";
	//controller返回码
	public final static String STATUS = "status";
	public final static String MSG = "msg";
	public final static String DEFAULT_PWD = "123456";
	public final static String MSG_USER = "qdqszx";

	public final static String MSG_USER_PWD = "b0ecbc94863ba01db82b67a3a4b01c7f";

	public final static String MSG_USER_SIGN = "监管预警平台";

	//测试的url地址
	//public final static String MSG_API_URL = "http://manager.wxtxsms.cn/smsport/feePost.aspx";

	//正式的url地址
	public final static String MSG_API_URL = "http://manager.wxtxsms.cn/smsport/sendPost.aspx";
	public final static String[] MSG_MODEL = {
		"尊敬的用户您好，您的短信验证码为NUM（5分钟内有效），请尽快完成注册。" };
}
