/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.utils;

import com.google.common.collect.Lists;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.CacheUtils;
import com.qdch.portal.common.utils.SpringContextHolder;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.cms.entity.Article;
import com.qdch.portal.modules.cms.entity.Category;
import com.qdch.portal.modules.cms.entity.Link;
import com.qdch.portal.modules.cms.entity.Site;
import com.qdch.portal.modules.cms.service.ArticleService;
import com.qdch.portal.modules.cms.service.CategoryService;
import com.qdch.portal.modules.cms.service.LinkService;
import com.qdch.portal.modules.cms.service.SiteService;
import org.springframework.ui.Model;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author wangfeng
 * @version 11点36分
 */
public class RegUtils {
//	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
//	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
//	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//	private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
//	private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签

	//定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
	private static final String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	//定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
	private static final String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
	// 定义HTML标签的正则表达式
	private static final String regEx_html = "<[^>]+>";
	// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	private static final String regEx_special = "\\&[a-zA-Z]{1,10};";
	/**
	 * @param htmlStr
	 * @return 删除Html标签
	 * @author LongJin
	 */
//	public static String delHTMLTag(String htmlStr) {
//		Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
//		Matcher m_w = p_w.matcher(htmlStr);
//		htmlStr = m_w.replaceAll(""); // 过滤script标签
//		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//		Matcher m_script = p_script.matcher(htmlStr);
//		htmlStr = m_script.replaceAll(""); // 过滤script标签
//		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//		Matcher m_style = p_style.matcher(htmlStr);
//		htmlStr = m_style.replaceAll(""); // 过滤style标签
//		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
//		Matcher m_html = p_html.matcher(htmlStr);
//		htmlStr = m_html.replaceAll(""); // 过滤html标签
//		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
//		Matcher m_space = p_space.matcher(htmlStr);
//		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
//		htmlStr = htmlStr.replaceAll(" ", ""); //过滤
//		return htmlStr.trim(); // 返回文本字符串
//	}

	public static  String delHTMLTag(String inputString){

		if (inputString == null)
			return null;
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		inputString = inputString.replaceAll("<span>","").replaceAll("</span>","").
				replaceAll("<p>","").replaceAll("</span>","");
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		Pattern p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
		Matcher m_special = p_special.matcher(htmlStr);
		htmlStr = m_special.replaceAll(""); // 过滤特殊标签
		textStr = htmlStr;
		return  textStr;
	}


	public static void main(String[] args) {
		String str = "&lt;p&gt;\n" +
				"\t&lt;span style=&quot;font-size:72px;&quot;&gt;周瑜踩踩踩&lt;/span&gt;&lt;/p&gt;";
		str  = RegUtils.delHTMLTag(str);
		System.out.println(str);

	}

}