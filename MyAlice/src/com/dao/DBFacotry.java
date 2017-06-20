package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.aiml.AliceBotMother;
import com.bean.MusicInfo;
import com.constant.ISQL;
import com.util.ikanalyzer.WPSFileUtil;

public class DBFacotry {
//ORA-01950: 对表空间 'USERS' 无权限:alter user c##RUBOT quota unlimited on users;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String user = "sys as sysdba  ";
	private static String password = "123";
	private static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	/**
	 * 初始化连接
	 */
	private static Connection connection ;
	/**
	 * 根据c3p0获取数据库连接
	 * @return
	 */
	public static Connection getConnectionByC3P0(){
		try {
			connection =  C3P0ConnentionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(connection==null||connection.isClosed()){
				connection = getConnectionByC3P0();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/***
	 * 获取数据库超级管理员连接
	 * @return
	 */
	private static Connection getSysConnection(){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection =  DriverManager.getConnection(url, ISQL.SYSTE_DBA_USER, ISQL.SYSTE_DBA_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 对数据库表、序列等进行初始化，如果相关表，序列不存在，那么创建,需要相关c3p0类（C3P0ConnentionProvider.java）的数据支持
	 * @param path 
	 * @param connection
	 */
	public static void init(String path){
		/*连接数据库管理员并且进行相关用户检测*/
		BasicDao.initUser(getSysConnection(),
				C3P0ConnentionProvider.c3p0Values.get(C3P0ConnentionProvider.JDBC_USERNAME),
				C3P0ConnentionProvider.c3p0Values.get(C3P0ConnentionProvider.JDBC_PASSWORD)
				);
		/*连接数据库管理员并且进行相关用户检测*/
		BasicDao.initMusicInfoTable(getConnectionByC3P0());
		BasicDao.initLoginInfoTable(getConnectionByC3P0());
		BasicDao.initRecordTable(getConnectionByC3P0());
		BasicDao.initSubscribeTable(getConnectionByC3P0());
		//读智能聊天的配置文件
		AliceBotMother.newInstance(path);
		//导入音乐文件
		List<MusicInfo> listValues = WPSFileUtil.getAllExcelData(path);
		//导入数据--获取数据表中的数据
		MusicInfoDao.initDBMusicInfoDatas(listValues );
	}
	public static void main(String[] args) {
		//通过c3p0获取数据库连接
		/*for (int i = 0; i < 100; i++) {
			System.out.println(i+":"+getConnectionByC3P0());
		}*/
//		System.out.println(getConnection());
		new DBFacotry();
		System.out.println(getConnectionByC3P0());
	}
}
