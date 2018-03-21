/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.qdch.portal.common.config.Global;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import redis.clients.jedis.Jedis;

/**
 * 发送短信工具类
 * @author wangfeng
 * @version 10点42分
 */
public class MessageUtils {
	
//	public static Jedis jedis = JedisUtils.getResource();
	public static void main(String[] args) {
//		new MessageUtils().editMessage("17611570335");
		new MessageUtils().sendMessage("17611570335");
		
	}
	
	
	/**
	 * 避免重复发送逻辑
	 * @param tel
	 * @return
	 */
	public String  sendMessage(String tel){
		String returnmsg = "";
//		CacheUtils.put("MessageCache", System.currentTimeMillis() );
//		Object obj = CacheUtils.get("MessageCache");
//		if(obj != null){
//			long current = System.currentTimeMillis();
//			if(current-Integer.parseInt(obj.toString())<60000){
//				returnmsg =   "发送过于频繁";
//			}else{
//				CacheUtils.put("MessageCache", System.currentTimeMillis() );
//				editMessage(tel);
//			}
//				
//		}else{
//			CacheUtils.put("MessageCache", System.currentTimeMillis() );
//			editMessage(tel);
//		}
//		return returnmsg;
//		if(JedisUtils.exists("MessageCache")==true){
//			if(null != JedisUtils.get("MessageCache")){ 
//				returnmsg = "发送过于频繁";
//				return returnmsg;
//			}
//		}
//		String MessageCache = jedis.get("MessageCache");
//		if(MessageCache != null){
//			returnmsg = "发送过于频繁";
//		}
		editMessage(tel);
		return returnmsg;
	}
	/**
	 * 发送短信逻辑
	 * @param tel
	 */
	
	public String  editMessage(String tel){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.api.smschinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
		String uid = Global.getConfig("messageuid")==null?"portalmsg":Global.getConfig("messageuid").toString();
		String key = Global.getConfig("messagekey")==null?"e9e591c35bbaa5f9a29b":Global.getConfig("messagekey").toString();
		String random = getRandom();
	
		NameValuePair[] data ={ new NameValuePair("Uid", uid),new NameValuePair("Key", key),
				new NameValuePair("smsMob",tel),
				new NameValuePair("smsText","验证码："+random+"【青岛清算】")};  //加后缀是为了防止被屏蔽
		post.setRequestBody(data);
	
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:"+statusCode); 
		for(Header h : headers)
		{
		System.out.println(h.toString());
		}
		String result = "";
		try {
			result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(result); //打印返回消息状态
		if(result.equals("1")){
			//JedisUtils.setObject("MessageCache", random, 60); 
			//jedis.set("MessageCache", random, "NX", "EX", 60);  //过期时间为60秒
		}
		post.releaseConnection();
		return result;
	}
	/**
	 * 生成四位随机数字
	 * @return
	 */
	public String getRandom(){
		String[] beforeShuffle = new String[] {"0","1", "2", "3", "4", "5", "6", "7",  
	             "8", "9"};  
	     List list = Arrays.asList(beforeShuffle);  
	     Collections.shuffle(list);  
	     StringBuilder sb = new StringBuilder();  
	     for (int i = 0; i < list.size(); i++) {  
	         sb.append(list.get(i));  
	     }  
	     String afterShuffle = sb.toString();  
	     String result = afterShuffle.substring(5, 9);  
	     System.out .print(result) ;
	     return result;

	}
	/**
	 * 校验验证码是否正确
	 */
	public String  checkIndentifyCode(String code ){
		String returnmsg = "";
//		String sysCode =  jedis.get("MessageCache");
//		if(sysCode == null){
//			returnmsg = "请先点击发送验证码";
//			return returnmsg;
//		}
//		if(code.equals(sysCode)){
//			returnmsg = "true";
//		}else{
//			returnmsg = "false";
//		}
		return returnmsg;
		
	}
}
