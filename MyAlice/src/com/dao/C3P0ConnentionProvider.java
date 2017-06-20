package com.dao;  
  
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;
  
/** 
 * c3p0连接池管理类 
 * @author ICE 
 * 
 */  
public class C3P0ConnentionProvider {  
	/**
	 * c3p0相关的key，根据这些key取出相关的value
	 */
	public static final String JDBC_DRIVER = "driver";  
	public static final String JDBC_USERNAME = "user";  
	public static final String JDBC_PASSWORD = "password";  
	public static final String JDBC_URL = "url";  
    /**c3p0配置文档中的相关参数*/
    public static Map<String,String> c3p0Values=new HashMap<String, String>();
    private static DataSource ds;  
    /** 
     * 初始化连接池代码块 
     */  
    static{  
    	initC3P0XMLValues();
        initDBSource();  
    }  
     public static void initC3P0XMLValues(){
    	 Properties c3p0Pro = new Properties();  
         try {  
             //加载配置文件  
             c3p0Pro.load(C3P0ConnentionProvider.class.getResourceAsStream("/c3p0.properties"));  
         } catch (Exception e) {  
             e.printStackTrace();  
         }   
         for(Object key:c3p0Pro.keySet()){  
             String skey = (String)key;  
             if(!skey.startsWith("c3p0.")){  
            	 c3p0Values.put(skey, c3p0Pro.getProperty(skey));  
             }  
         }  
     }
    /** 
     * 初始化c3p0连接池 
     */  
    private static final void initDBSource(){  
        Properties c3p0Pro = new Properties();  
        try {  
            //加载配置文件  
            c3p0Pro.load(C3P0ConnentionProvider.class.getResourceAsStream("/c3p0.properties"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
          
        String driverClass = c3p0Pro.getProperty(JDBC_DRIVER); 
        System.out.println(driverClass);
        if(driverClass != null){  
            try {  
                //加载驱动类  
                Class.forName(driverClass);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }  
              
        }  
          
        Properties jdbcpropes = new Properties();  
        Properties c3propes = new Properties();  
        for(Object key:c3p0Pro.keySet()){  
            String skey = (String)key;  
            if(skey.startsWith("c3p0.")){  
                c3propes.put(skey, c3p0Pro.getProperty(skey));  
            }else{  
                jdbcpropes.put(skey, c3p0Pro.getProperty(skey));  
            }  
        }  
          
        try {  
            //建立连接池  
            DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL),jdbcpropes);  
            ds = DataSources.pooledDataSource(unPooled,c3propes); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 获取数据库连接对象 
     * @return 数据连接对象 
     * @throws SQLException  
     */  
    public static synchronized Connection getConnection() throws SQLException{  
        final Connection conn = ds.getConnection();  
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); 
        
        return conn;  
    }  
}  
