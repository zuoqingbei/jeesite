package com.qdch.portal.common.utils;

import com.qdch.portal.common.config.Constant;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.modules.account.dao.AccountMobileCodeDao;
import com.qdch.portal.modules.account.entity.AccountMobileCode;
import com.qdch.portal.modules.account.service.AccountMobileCodeService;
import com.qdch.portal.modules.sys.dao.UserDao;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;

import java.util.Calendar;
import java.util.Date;

/***
 * 
 * @todo   发送短信工具类
 * @time   2018年3月22日 上午10:45:07
 * @author zuoqb
 */
public class SendMsgUtil {

	private static AccountMobileCodeService service = SpringContextHolder.getBean(AccountMobileCodeService.class);
	private static AccountMobileCodeDao dao = SpringContextHolder.getBean(AccountMobileCodeDao.class);


	public static  String presend(String tel,String uasge){
		String returnmsg = "";
		String MessageCache = "";
		if(Global.getOpenRedis().equals("true")){
			MessageCache = JedisUtils.get("MessageCache"+tel);
			if(MessageCache != null){
				returnmsg = "发送过于频繁";
				return returnmsg;
			}
		}else{
			AccountMobileCode code  = new AccountMobileCode();
			code.setMobile(tel);
			code = dao.getByTel(code);
			if(code != null){
				long oldtime  = code.getCreateDate().getTime();
				long newtime = new Date().getTime();
				if(newtime-oldtime<5*60*1000) {	//5分钟
					returnmsg = "发送过于频繁";
					return returnmsg;
				}
			}

		}
		return sentMsg(0,tel,uasge);

	}
	
	/***
	 * 发送短信
	 * 发送号码。多个号码请用“,”分隔。最多1000个号码
	 */
	public static String sentMsg(int type,String phoneNum,String uasge){
		try {
			String param = "";
			param += "uid="+Constant.MSG_USER;
			param += "&upsd="+Constant.MSG_USER_PWD;
			param += "&sendtele="+phoneNum;
			String msg = Constant.MSG_MODEL[type];
			String code = gen6MobileCode();
			msg = msg.replace("NUM", code);
			System.out.println(msg);
			param += "&msg="+msg;
			param += "&sign="+Constant.MSG_USER_SIGN;
			String str = HttpClientUtil.sendPostRequest(Constant.MSG_API_URL, param,true);
			if(str.startsWith("success")){
				if(Global.getOpenRedis().equals("true")){
					JedisUtils.set("MessageCache"+phoneNum, code, 60*5);
				}
				AccountMobileCode accountMobileCode = new AccountMobileCode();
				accountMobileCode.setMobile(phoneNum);
				accountMobileCode.setCodes(code);
				accountMobileCode.setUsed("0"); //0--未使用 1--已使用
				accountMobileCode.setUasge(uasge);
				service.save(accountMobileCode);
                return "true";
            }else{
                return "false";
            }
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

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
//		String result = sentMsg(0,"15805422889");
//		System.out.println(result);
	}


	/**
	 * 校验验证码是否正确
	 */
	public static String  checkIndentifyCode(String tel,String code ){
		String returnmsg = "";
		if(Global.getOpenRedis().equals("true")){ //从redis取出来验证
			String sysCode =  JedisUtils.get("MessageCache"+tel);
			if(sysCode == null||StringUtils.isBlank(code)){
				returnmsg = "未发送验证码或者验证码已过期";
				return returnmsg;
			}
			if(code.equals(sysCode)){
				returnmsg = "true";
			}else{
				returnmsg = "false";
			}
			return returnmsg;
		}else{ //从数据库取出来验证
			AccountMobileCode mobileCode = new AccountMobileCode();
			mobileCode.setMobile(tel);
			AccountMobileCode code1 = dao.getByTel(mobileCode);
			if(code1 == null){
				returnmsg = "请先点击发送验证码";
				return returnmsg;
			}else{
				long oldtime  = code1.getCreateDate().getTime();
				long newtime = new Date().getTime();
				if(newtime-oldtime>5*60*1000) {	//5分钟
//					dao.setUsed(mobileCode);
					dao.deleteByTel(mobileCode);
					returnmsg = "该验证码已过期";
					return returnmsg;
				}
				mobileCode.setCodes(code);
				AccountMobileCode code2 = dao.getByTel(mobileCode);
				if(code2 == null){
					returnmsg = "验证码错误";
					return returnmsg;
				}else{
//					dao.setUsed(mobileCode);
					returnmsg = "true";
					return returnmsg;
				}

			}

		}


	}

}
