package com.qdch.portal.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @todo    把object转化为json格式的字符串
 * @time   2018年3月15日 下午3:58:08
 * @author zuoqb
 */
public class FormateJsonToString {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String jsonFromObject(Object status,Object msg,Object list) {
		if (list == null || "".equals(list)) {
			return "{\"status\":\""+status+"\",\"msg\":\""+msg+"\",data:[]}";
		}
		StringBuffer json = new StringBuffer("{");
		json.append("{\"status\":\""+status+"\",\"msg\":\""+msg+"\",data:");
		json.append("[");
		List<Object> objList = null;
		if (list instanceof List) {
			objList = (List<Object>) list;
			for (Object obj : objList) {
				Class cls = obj.getClass();
				Field[] fi = cls.getDeclaredFields();
				Method[] meth = cls.getDeclaredMethods();
//				try {
//			   Method me= cls.getMethod("getProduct");
//			   me.invoke(cls);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} 
				try {
					json.append("{");
					for (int i = 0; i < fi.length; i++) {
						Field ff = fi[i];
						String name = ff.getName();
						for (Method m : meth) {
							if (m.getName().toUpperCase()
									.equals(("GET" + name.toUpperCase()))) {
								json.append("\"" + ff.getName() + "\":");
								if (i < fi.length - 1) {
									json.append("\"" + m.invoke(obj) + "\",");
								} else {
									json.append("\"" + m.invoke(obj) + "\"");
								}
							}
						}
					}
					if (objList.indexOf(obj) == objList.size() - 1) {
						json.append("}");
					} else {
						json.append("},");
					}
				} catch (Exception e) {
					return "{\"status\":\"failure\",\"msg\":\""+e.getMessage()+"\",data:[]}";
					// throw new RuntimeException();
				}
			}
		} else {
			json.append("{");
			Class cls1 = list.getClass();
			Field[] fi1 = cls1.getDeclaredFields();
			Method[] meth1 = cls1.getDeclaredMethods();
			for (int j = 0; j < fi1.length; j++) {
				Field ff = fi1[j];
				String name1 = ff.getName();
				for (Method m1 : meth1) {
					if (m1.getName().toUpperCase()
							.equals("GET" + name1.toUpperCase())) {
						json.append("\"" + ff.getName() + "\":");
						try {
							if (j < fi1.length - 1) {
								json.append("\"" + m1.invoke(list) + "\",");
							} else {
								json.append("\"" + m1.invoke(list) + "\"");
							}
						} catch (Exception e1) {
							// throw new RuntimeException();
							return "{\"status\":\"failure\",\"msg\":\""+e1.getMessage()+"\",data:[]}";
						}
					}
				}
			}
			json.append("}");
		}
		json.append("]}");
		return json.toString();
	}
}
