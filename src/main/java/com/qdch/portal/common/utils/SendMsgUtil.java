package com.qdch.portal.common.utils;

import com.qdch.portal.common.config.Constant;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;

/***
 * 
 * @todo   发送短信工具类
 * @time   2018年3月22日 上午10:45:07
 * @author zuoqb
 */
public class SendMsgUtil {
	
	/***
	 * 发送短信
	 * 发送号码。多个号码请用“,”分隔。最多1000个号码
	 */
	public static String sentMsg(int type,String phoneNum){
		String param = "";
		param += "uid="+Constant.MSG_USER;
		param += "&upsd="+Constant.MSG_USER_PWD;
		param += "&sendtele="+phoneNum;
		String msg = Constant.MSG_MODEL[type];
		msg = msg.replace("NUM", gen6MobileCode());
		System.out.println(msg);
		param += "&msg="+msg;
		param += "&sign="+Constant.MSG_USER_SIGN;
		return HttpClientUtil.sendPostRequest(Constant.MSG_API_URL, param,true);
	}
	/**
	 * @todo   生成6位随机数
	 * @time   2018年3月22日 上午9:57:50
	 * @author zuoqb
	 * @return_type   int
	 */
	public static String gen6MobileCode(){
		int code=(int)((Math.random()*9+1)*100000);
		return String.valueOf(code);
	}
	public static String gen4MobileCode(){
		int code=(int)((Math.random()*9+1)*1000);
		return String.valueOf(code);
	}
	public static void main(String[] args){
		String result = sentMsg(0,"15805422889");
		System.out.println(result);
	}

}
