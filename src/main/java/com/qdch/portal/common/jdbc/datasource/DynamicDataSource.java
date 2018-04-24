package com.qdch.portal.common.jdbc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.w3c.dom.views.AbstractView;

public class DynamicDataSource  extends AbstractRoutingDataSource {
	
	 private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();    
     
	 private static final String DATASOURCE = "dataSource";
	 private static final String INSIGHTDATASOURCE = "slaveDataSource";
	private static final String HUBDATASOURCE = "hubDataSource";
	 
	 public static String getCurrentLookupKey() {    
//	    	System.out.println((String) contextHolder.get());   //测试使用
	        return (String) contextHolder.get();    
	    }    
	 
	  public static void setDataSource() {
	    	contextHolder.set(DATASOURCE);    
		}
	  public static void setInsightDataSource() {
	    	contextHolder.set(INSIGHTDATASOURCE);
		}

	public static void setHubDataSource() {
		contextHolder.set(HUBDATASOURCE);
	}



	@Override
	protected Object determineCurrentLookupKey() {
		 return getCurrentLookupKey();    
	}


	public static void removeDataSourceKey(){
		contextHolder.remove();
	}
}
