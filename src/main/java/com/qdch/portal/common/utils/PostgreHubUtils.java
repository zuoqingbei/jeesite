package com.qdch.portal.common.utils;

import com.qdch.portal.common.config.Global;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 连接postgre
 * @author wangfeng
 *
 */
public class PostgreHubUtils {

	private static final String driver = Global.getConfig("postgre.driver");
    private static final String url = Global.getConfig("postgre.url");
    private static final String username = Global.getConfig("postgre.username");
    private static final String password = Global.getConfig("postgre.password");

    private static final String hubusername = Global.getConfig("postgre.hub.username");
    private static final String hubpassword = Global.getConfig("postgre.hub.password");

    private static CallableStatement callableStatement = null;//创建CallableStatement对象

    private static Connection conn = null;
    private static PreparedStatement pst = null;
    private  ResultSet rst = null;
    private PostgreHubUtils() {
    
    	
    }

	/**
	 * 当前对象实例
	 */
	private static PostgreHubUtils postgreUtils = null;

	/**
	 * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
	 */

	public static synchronized PostgreHubUtils getInstance() {

		if (postgreUtils == null) {
			synchronized (PostgreHubUtils.class) {
				if (postgreUtils == null)
					postgreUtils = new PostgreHubUtils();
			}
		}
		return postgreUtils;
	}
    
   /* public   PostgreUtils()  {
    	driver = Global.getConfig("postgre.driver");
    	url = Global.getConfig("postgre.url");
    	username = Global.getConfig("postgre.username");
    	password = Global.getConfig("postgre.password");

	}*/
    
    
    /**   
     * 建立数据库连接   
     * @return 数据库连接   
     */      
    public static Connection getConnection() {
        try {      
             // 加载数据库驱动程序      
            try {  
                Class.forName(driver);  
            } catch (ClassNotFoundException e) {  
                System.out.println("加载驱动错误");      
                System.out.println(e.getMessage());    
                e.printStackTrace();  
            }   
            // 获取连接      
            conn = DriverManager.getConnection(url, hubusername,
                    hubpassword);
        } catch (SQLException e) {      
            System.out.println(e.getMessage());      
        }      
        return conn;      
    }   
    
     
      
    /**   
     * insert update delete SQL语句的执行的统一方法   
     * @param sql SQL语句   
     * @param params 参数数组，若没有参数则为null   
     * @return 受影响的行数   
     */      
    public  int executeUpdate(String sql, Object[] params) {
        // 受影响的行数      
        int affectedLine = 0;      
              
        try {      
            // 获得连接      
            conn = this.getConnection();      
            // 调用SQL       
            pst = conn.prepareStatement(sql);      
                  
            // 参数赋值      
            if (params != null) {      
                for (int i = 0; i < params.length; i++) {      
                    pst.setObject(i + 1, params[i]);      
                }      
            }      
            /*在此 PreparedStatement 对象中执行 SQL 语句， 
                                          该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，比如 INSERT、UPDATE 或 DELETE  
                                          语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。    */  
            // 执行      
            affectedLine = pst.executeUpdate();  
      
        } catch (SQLException e) {      
            System.out.println(e.getMessage());      
        } finally {      
            // 释放资源      
            closeAll();      
        }      
        return affectedLine;      
    } 
    
    /**   
     * SQL 查询将查询结果直接放入ResultSet中   
     * @param sql SQL语句   
     * @param params 参数数组，若没有参数则为null   
     * @return 结果集   
     */      

          
    /**   
     * SQL 查询将查询结果：一行一列   
     * @param sql SQL语句   
     * @param params 参数数组，若没有参数则为null   
     * @return 结果集   
     */      
    public Object executeQuerySingle(String sql, Object[] params) {
        Object object = null;      
        try {      
            // 获得连接      
            conn = this.getConnection();
                  
            // 调用SQL      
            pst = conn.prepareStatement(sql);      
                  
            // 参数赋值      
            if (params != null) {      
                for (int i = 0; i < params.length; i++) {      
                    pst.setObject(i + 1, params[i]);      
                }      
            }      
                  
            // 执行      
            rst = pst.executeQuery();      
      
            if(rst.next()) {      
                object = rst.getObject(1);      
            }      
                  
        } catch (SQLException e) {      
            System.out.println(e.getMessage());      
        } finally {      
            closeAll();      
        }      
      
        return object;      
    }      
    
    /**   
     * 获取结果集，并将结果放在List中   
     *    
     * @param sql  SQL语句   
     *         params  参数，没有则为null    
     * @return List   
     *                       结果集   
     */      
    public  List<Object> excuteQuery(String sql, Object[] params) {
        // 执行SQL获得结果集      
        ResultSet rs = executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();

            // 获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        // 创建List
        List<Object> list = new ArrayList<Object>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);//每一个map代表一条记录，把所有记录存在list中
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭所有资源
            closeAll();
        }

        return list;
    }

    private  ResultSet executeQueryRS(String sql, Object[] params) {
        try {
            // 获得连接
            conn = PostgreHubUtils.getConnection();

            // 调用SQL
            pst = conn.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            // 执行
            rst = pst.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return rst;
    }



    /**   
     * 关闭所有资源   
     */      
    private static   void closeAll() {
        // 关闭结果集对象      
////        if (rst != null) {
//            try {
//                rst.close();
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        // 关闭PreparedStatement对象      
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
      
        // 关闭Connection 对象      
        if (conn != null) {      
            try {      
                conn.close();      
            } catch (SQLException e) {
                System.out.println(e.getMessage());      
            }      
        }         
    }      
    

	
	public static void main(String args[])  {
		//conn();
		PostgreHubUtils postgreUtils  = PostgreHubUtils.getInstance();
	
		System.out.println(postgreUtils.executeQuerySingle("select * from test1", null));
		
	}

	public static Connection getconn(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        DataSource ds = ctx.getBean("dataSourcePostgre",DataSource.class);
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
