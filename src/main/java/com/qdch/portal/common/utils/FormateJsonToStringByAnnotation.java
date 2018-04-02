package com.qdch.portal.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.qdch.portal.modules.sys.entity.User;

/**
 * @todo    把object转化为json格式的字符串,并使用注解
 * @time   2018年3月15日 下午3:57:56
 * @author zuoqb
 */
public class FormateJsonToStringByAnnotation {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String jsonFromObject(Object status,Object msg,Object list) {
		if (list == null || "".equals(list)||"[]".equals(list.toString())) {
			return "{\"status\":\""+status+"\",\"msg\":\""+msg+"\"}";
		}
		StringBuffer json = new StringBuffer("{");
		json.append("{\"status\":\""+status+"\",\"msg\":\""+msg+"\",data:");
		json.append("[");
		List<Object> objList = null;
		if (list instanceof List) {
			objList = (List<Object>) list;

			Class cls = objList.get(0).getClass();
			Field[] fi = cls.getDeclaredFields();

			List<Field> arr = new ArrayList<Field>();
			for (Field ed : fi) {
				if (ed.isAnnotationPresent(Json.class)) {
					arr.add(ed);
				}
			}
			Field[] fi22 = new Field[arr.size()];
			for (int x = 0; x < arr.size(); x++) {
				fi22[x] = arr.get(x);
			}
			Method[] meth = cls.getDeclaredMethods();
			for (Object obj : objList) {
				try {
					json.append("{");
					for (int i = 0; i < fi22.length; i++) {
						Field ff = fi22[i];
						String name = ff.getName();
						for (Method m : meth) {
							// 判断obj是否用了Json的注解
							if (ff.isAnnotationPresent(Json.class)) {
								// 返回Json类型的注解
								// Json annotation =
								// ff.getAnnotation(Json.class);
								if (m.getName().toUpperCase()
										.equals("GET" + name.toUpperCase())) {
									json.append("\"" + ff.getName() + "\":");
									if (i < fi22.length - 1) {
										if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
											json.append("\"" + jsonFromObjectNoStatus(m.invoke(obj))
													+ "\",");
										}else{
											json.append("\"" + m.invoke(obj)
													+ "\",");
										}
									} else {
										if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
											json.append("\"" + jsonFromObjectNoStatus(m.invoke(obj)) + "\"");
										}else{
											json.append("\"" + m.invoke(obj) + "\"");
										}
										
									}
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
					return "{\"status\":\"failure\",\"msg\":\""+e.getMessage()+"\"}";
					// throw new RuntimeException();
				}
			}
		} else {
			json.append("{");
			Class cls1 = list.getClass();
			Field[] fi1 = cls1.getDeclaredFields();

			List<Field> arr = new ArrayList<Field>();
			for (Field ed : fi1) {
				if (ed.isAnnotationPresent(Json.class)) {
					arr.add(ed);
				}
			}
			Field[] fi22 = new Field[arr.size()];
			for (int x = 0; x < arr.size(); x++) {
				fi22[x] = arr.get(x);
			}

			Method[] meth1 = cls1.getDeclaredMethods();
			for (int j = 0; j < fi22.length; j++) {
				Field ff = fi22[j];
				String name1 = ff.getName();
				for (Method m1 : meth1) {
					if (ff.isAnnotationPresent(Json.class)) {
						if (m1.getName().toUpperCase()
								.equals("GET" + name1.toUpperCase())) {
							json.append("\"" + ff.getName() + "\":");
							try {
								if (j < fi22.length - 1) {
									if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
										json.append("\"" + jsonFromObjectNoStatus(m1.invoke(list))
												+ "\",");
									}else{
										json.append("\"" + m1.invoke(list)
												+ "\",");
									}
								} else {
									if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
										json.append("\"" + jsonFromObjectNoStatus(m1.invoke(list)) + "\"");
									}else{
										json.append("\"" + m1.invoke(list) + "\"");
									}
									
								}
							} catch (Exception e1) {
								// throw new RuntimeException();
								return "{\"status\":\"failure\",\"msg\":\""+e1.getMessage()+"\"}";
							}
						}
					}
				}
			}
			json.append("}");
		}
		json.append("]}");
		return json.toString();
	}
	
	public String jsonFromObjectNoStatus(Object list) {
		if (list == null || "".equals(list)||"[]".equals(list.toString())) {
			return "[]";
		}
		StringBuffer json = new StringBuffer("[");
		List<Object> objList = null;
		if (list instanceof List) {
			objList = (List<Object>) list;

			Class cls = objList.get(0).getClass();
			Field[] fi = cls.getDeclaredFields();

			List<Field> arr = new ArrayList<Field>();
			for (Field ed : fi) {
				if (ed.isAnnotationPresent(Json.class)) {
					arr.add(ed);
				}
			}
			Field[] fi22 = new Field[arr.size()];
			for (int x = 0; x < arr.size(); x++) {
				fi22[x] = arr.get(x);
			}
			Method[] meth = cls.getDeclaredMethods();
			for (Object obj : objList) {
				try {
					json.append("{");
					for (int i = 0; i < fi22.length; i++) {
						Field ff = fi22[i];
						String name = ff.getName();
						for (Method m : meth) {
							// 判断obj是否用了Json的注解
							if (ff.isAnnotationPresent(Json.class)) {
								// 返回Json类型的注解
								// Json annotation =
								// ff.getAnnotation(Json.class);
								if (m.getName().toUpperCase()
										.equals("GET" + name.toUpperCase())) {
									json.append("\"" + ff.getName() + "\":");
									if (i < fi22.length - 1) {
										if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
											json.append("\"" + jsonFromObjectNoStatus(m.invoke(obj))
													+ "\",");
										}else{
											json.append("\"" + m.invoke(obj)
													+ "\",");
										}
									} else {
										if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
											json.append("\"" + jsonFromObjectNoStatus(m.invoke(obj)) + "\"");
										}else{
											json.append("\"" + m.invoke(obj) + "\"");
										}
										
									}
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
					return "{\"status\":\"failure\",\"msg\":\""+e.getMessage()+"\"}";
					// throw new RuntimeException();
				}
			}
		} else {
			json.append("{");
			Class cls1 = list.getClass();
			Field[] fi1 = cls1.getDeclaredFields();

			List<Field> arr = new ArrayList<Field>();
			for (Field ed : fi1) {
				if (ed.isAnnotationPresent(Json.class)) {
					arr.add(ed);
				}
			}
			Field[] fi22 = new Field[arr.size()];
			for (int x = 0; x < arr.size(); x++) {
				fi22[x] = arr.get(x);
			}

			Method[] meth1 = cls1.getDeclaredMethods();
			for (int j = 0; j < fi22.length; j++) {
				Field ff = fi22[j];
				String name1 = ff.getName();
				for (Method m1 : meth1) {
					if (ff.isAnnotationPresent(Json.class)) {
						if (m1.getName().toUpperCase()
								.equals("GET" + name1.toUpperCase())) {
							json.append("\"" + ff.getName() + "\":");
							try {
								if (j < fi22.length - 1) {
									if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
										json.append("\"" + jsonFromObjectNoStatus(m1.invoke(list))
												+ "\",");
									}else{
										json.append("\"" + m1.invoke(list)
												+ "\",");
									}
								} else {
									if(ff.getGenericType().toString().startsWith("class com.qdch.portal")){
										json.append("\"" + jsonFromObjectNoStatus(m1.invoke(list)) + "\"");
									}else{
										json.append("\"" + m1.invoke(list) + "\"");
									}
									
								}
							} catch (Exception e1) {
								// throw new RuntimeException();
								return "{\"status\":\"failure\",\"msg\":\""+e1.getMessage()+"\"}";
							}
						}
					}
				}
			}
			json.append("}");
		}
		json.append("]");
		return json.toString();
	}
	public static void main(String[] args) {
		System.out.println( new FormateJsonToStringByAnnotation().jsonFromObject("200", "msg",new User()));
	}
}
