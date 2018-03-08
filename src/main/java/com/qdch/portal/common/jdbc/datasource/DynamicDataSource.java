package com.qdch.portal.common.jdbc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.w3c.dom.views.AbstractView;

public class DynamicDataSource  extends AbstractRoutingDataSource {
	
	 private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();    
     
	 private static final String DATASOURCE = "dataSource";
	 private static final String SLAVEDATASOURCE = "slaveDataSource";
	 
	 public static String getCurrentLookupKey() {    
//	    	System.out.println((String) contextHolder.get());   //测试使用
	        return (String) contextHolder.get();    
	    }    
	 
	  public static void setDataSource() {
	    	contextHolder.set(DATASOURCE);    
		}
	  public static void setSlaveDataSource() {
	    	contextHolder.set(SLAVEDATASOURCE);    
		}



	@Override
	protected Object determineCurrentLookupKey() {
		 return getCurrentLookupKey();    
	}

}
