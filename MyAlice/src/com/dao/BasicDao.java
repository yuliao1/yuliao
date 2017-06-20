package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bean.MusicInfo;
import com.bean.User;
import com.constant.ISQL;

/**
 * 数据库基本操作：原子级 增删改查<br>
 * 1.批量插入数据<br>
 * 2.生成sql语句<br>
 * 3.查询生成结果集<br>
 * 4.自动生成sql语句<br>
 * 5.相关表、序列的创建<br>
 * 6.关闭数据库<br>
 * 
 * @author Administrator
 * 
 */
public class BasicDao {
	/**
	 * 通用方法 
	 * 
	 * @param connection
	 *            数据库连接
	 * @param sql
	 *            需要执行的sql语句
	 * @param values
	 *            数组中包含sql中需要存放的数据
	 * @return
	 */
	protected static boolean executetData(Connection connection,
			String sql, Object... values) {
		try {
			// 将该连接设置为不自动提交
			// 获取一个预处理对象
			PreparedStatement statement = connection.prepareStatement(sql);
			int counts = 0;
			// 遍历集合
			// 遍历数组，将数组中的数据放入sql中
			for (int i = 0; i < values.length; i++) {
				statement.setObject(i + 1, values[i]);
			}
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally{
			//closeDB(connection);
		}

		return false;
	}
	/**
	 * 通过批处理批量添加数据 insert into emp values(?,?,?,?)
	 * 
	 * @param connection
	 *            数据库连接
	 * @param sql
	 *            需要执行的sql语句
	 * @param values
	 *            数组中包含sql中需要存放的数据
	 * @return
	 */
	protected static boolean insertSQLByBatch(Connection connection,
			String sql, List<MusicInfo> listValues) {
		try {
			// 将该连接设置为不自动提交
			connection.setAutoCommit(false);
			// 获取一个预处理对象
			PreparedStatement statement = connection.prepareStatement(sql);
			int counts = 0;
			//遍历集合，将每一条数据添加到批处理中
			for (MusicInfo musicInfo : listValues) {
				// 遍历集合
				// 遍历数组，将数组中的数据放入sql中
				// 添加到批处理中
				statement.setObject(1, musicInfo.getMusicName());
				statement.setObject(2, musicInfo.getWordCreator());
				statement.setObject(3, musicInfo.getSongCreator());
				statement.setObject(4, musicInfo.getSinger());
				statement.setObject(5, musicInfo.getSex());
				statement.setObject(6, musicInfo.getAlbum());
				statement.setObject(7, new java.sql.Date(musicInfo.getCreateDate().getTime()));
				statement.setObject(8, musicInfo.getClasses());
				statement.addBatch();
				// 如果批处理中的数据达到30w条，那么执行批处理
				if (++counts % (100 * 10000) == 0) {
					// 执行批处理
					statement.executeBatch();
					// 提交数据
					connection.commit();
					statement.clearBatch();
					System.out.println(counts / 10000 + "w条数据已经保存");
				}
			}
			// 如果总数据不是30w条数据的整数倍，那么有剩余数据没有提交，所以，再执行和提交
			// 执行批处理
			statement.executeBatch();
			// 提交数据
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
		return false;
	}
	/**
	 * 通过批处理批量添加数据 insert into emp values(?,?,?,?)
	 * 
	 * @param connection
	 *            数据库连接
	 * @param sql
	 *            需要执行的sql语句
	 * @param values
	 *            数组中包含sql中需要存放的数据
	 * @return
	 */
	protected static boolean updateSQLByBatch(Connection connection,
			String sql, List<MusicInfo> listValues) {
		try {
			// 将该连接设置为不自动提交
			connection.setAutoCommit(false);
			// 获取一个预处理对象
			PreparedStatement statement = connection.prepareStatement(sql);
			int counts = 0;
			//遍历集合，将每一条数据添加到批处理中
			for (MusicInfo musicInfo : listValues) {
				// 遍历集合
				// 遍历数组，将数组中的数据放入sql中
				statement.setObject(1, musicInfo.getMusicName());
				statement.setObject(2, musicInfo.getWordCreator());
				statement.setObject(3, musicInfo.getSongCreator());
				statement.setObject(4, musicInfo.getSinger());
				statement.setObject(5, musicInfo.getSex());
				statement.setObject(6, musicInfo.getAlbum());
				statement.setObject(7, new java.sql.Date(musicInfo.getCreateDate().getTime()));
				statement.setObject(8, musicInfo.getClasses());
				statement.setObject(9, musicInfo.getId());
				// 添加到批处理中
				statement.addBatch();
				// 如果批处理中的数据达到30w条，那么执行批处理
				if (++counts % (100 * 10000) == 0) {
					// 执行批处理
					statement.executeBatch();
					// 提交数据
					connection.commit();
					statement.clearBatch();
					System.out.println(counts / 10000 + "w条数据已经保存");
				}
			}
			// 如果总数据不是30w条数据的整数倍，那么有剩余数据没有提交，所以，再执行和提交
			// 执行批处理
			statement.executeBatch();
			// 提交数据
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally{
			//closeDB(connection);
		}
		return false;
	}

	/**
	 * 查询原始数据中的结果集 eg：select * from emp where empname=? and empno=?
	 * 
	 * @param sql
	 *            需要执行的sql
	 * @param c
	 *            数据库连接
	 * @param values
	 *            需要传入的值
	 * @return 查询到的结果集
	 */
	public static ResultSet getResultSet(String sql, String... values) {
		Connection connection = null;
		try {
			 connection = DBFacotry.getConnectionByC3P0();
			// 通过数据库连接调用方法，获取一个预处理累的对象
			PreparedStatement statement = connection.prepareStatement(sql);
			// 如果需要向sql中设置参数
			if (values != null && values.length > 0) {
				// 设置sql语句中的参数
				for (int i = 0; i < values.length; i++) {
					statement.setObject(i + 1, values[i]);
				}
			}
			// 执行sql查询语句并返回结果集
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
		// 出现异常，返回一个null
		return null;
	}

	/**
	 * 通过条件自动生成查询语句sql语句
	 * 
	 * @param tableName
	 *            查询的表名 "musicInfo"
	 * @param rsFieldNames
	 *            需要查询的字段
	 *            {"id","musicName","wordCreator","songCreator","singer",
	 *            "sex","album","createDate","classes"}
	 * 
	 * @param where
	 *            sql语句中where后面跟查询的条件
	 * @return
	 */
	public static String getSQL(String tableName, String[] rsFieldNames,
			String where) {
		String sql = "select ";
		for (String rsFieldName : rsFieldNames) {
			sql += rsFieldName + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " where ";
		sql += where;

		return sql;
	}

	/**
	 * 数据库歌曲表相关表、序列等初始化-是否创建
	 * 
	 * @param connection
	 */
	public static void initMusicInfoTable(Connection connection) {
		try {
			PreparedStatement pre = connection
					.prepareStatement(ISQL.hasMusicInfoTable);
			ResultSet rs = pre.executeQuery();
			if (!rs.next()) {
				// 创建表
				pre.execute(ISQL.createMusicInfoTable);
				System.out.println("数据库歌曲表创建完毕");
			}
			ResultSet rs2 = pre.executeQuery(ISQL.hasSequenceForMusicInfo);
			if (!rs2.next()) {
				// 创建序列
				pre.execute(ISQL.createMusicInfoSequence);
				System.out.println("数据库歌曲表序列创建完毕");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}

	}

	/**
	 * 数据库登录表相关表、序列等初始化-是否创建
	 * 
	 * @param connection
	 */
	public static void initLoginInfoTable(Connection connection) {
		try {
			PreparedStatement pre = connection
					.prepareStatement(ISQL.hasLoginInfoTable);
			ResultSet rs = pre.executeQuery();
			if (!rs.next()) {
				// 创建表
				pre.execute(ISQL.createLoginInfoTable);
				System.out.println("用户登录表创建完毕");
			}
			ResultSet rs2 = pre.executeQuery(ISQL.hasSequenceForLoginInfo);
			if (!rs2.next()) {
				// 创建序列
				pre.execute(ISQL.createLoginInfoSequence);
				System.out.println("用户登录表序列创建完毕");
				//登录表中插入登录用户admin、123、1
				pre.execute(ISQL.insertLoginInfoAdmin);
				System.out.println("登录表插入管理员数据："+ISQL.insertLoginInfoAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
	}
	/**
	 * 数据库记录相关表、序列等初始化-是否创建
	 * 
	 * @param connection
	 */
	public static void initRecordTable(Connection connection) {
		try {
			PreparedStatement pre = connection
					.prepareStatement(ISQL.hasRecordTable);
			ResultSet rs = pre.executeQuery();
			if (!rs.next()) {
				// 创建表
				pre.execute(ISQL.createRecordTable);
				System.out.println("用户问答记录表创建完毕："+ISQL.createRecordTable);
			}
			ResultSet rs2 = pre.executeQuery(ISQL.hasSequenceForRecord);
			if (!rs2.next()) {
				// 创建序列
				pre.execute(ISQL.createRecordSequence);
				System.out.println("用户问答记录序列创建完毕："+ISQL.createRecordSequence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
	}
	
	/**
	 * 数据库订阅相关表、序列等初始化-是否创建
	 * 
	 * @param connection
	 */
	public static void initSubscribeTable(Connection connection) {
		try {
			PreparedStatement pre = connection
					.prepareStatement(ISQL.hasSubscribeTable);
			ResultSet rs = pre.executeQuery();
			if (!rs.next()) {
				// 创建表
				pre.execute(ISQL.createSubscribeTable);
				System.out.println("用户订阅表创建完毕");
			}
			ResultSet rs2 = pre.executeQuery(ISQL.hasSequenceForSubscribe);
			if (!rs2.next()) {
				// 创建序列
				pre.execute(ISQL.createSubscribeSequence);
				System.out.println("用户记录表序列创建完毕"+ISQL.createSubscribeSequence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
	}
	
	

	/**
	 * 如果数据库账户没有这个账户，那么创建这个账户------适用于11g及其以下
	 * 
	 * @param connection
	 *            以数据库管理员身份连接数据库的连接
	 * @param username
	 *            需要检测或创建的用户名
	 * @param password
	 *            用户名的密码
	 * @return
	 */
	public static void initUser(Connection connection, String username,
			String password) {
		PreparedStatement pre = null;
		System.out.println("数据库用户检查...");
		try {
			/** 1.检查是否存在该用户 */

			String sql0 = "select username from dba_users where username = '"
					+ username.toUpperCase() + "'";
			/** 2.如果不存在该用户，那么创建该用户 */
			System.out.println(username.toUpperCase() + "," + password);
			String sql1 = "create user " + username + " identified by "
					+ password;
			/** 3创建用户后给该用户权限 */
			String sql2 = "alter user " + username
					+ " quota unlimited on users";
			String sql3 = "grant connect,resource to " + username;

			pre = connection.prepareStatement(sql0);
			ResultSet rs1 = pre.executeQuery();
			if (!rs1.next()) {
				System.out.println("用户不存在，那么创建");
				pre.execute(sql1);
				System.out.println(sql1 + "-OK");
				pre.execute(sql2);
				System.out.println(sql2 + "-OK");
				pre.execute(sql3);
				System.out.println(sql3 + "-OK");
			}else{
				System.out.println("用户已经存在，所以不进行用户创建");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param connection
	 */
	public static void closeDB(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeDB(ResultSet resultSet){
		try {
			if(resultSet!=null&&!resultSet.isClosed()){
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeDB(Statement statement){
		try {
			if(statement!=null&&!statement.isClosed()){
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
