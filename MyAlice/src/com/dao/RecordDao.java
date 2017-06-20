package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bean.Record;
import com.bean.Subscribe;
import com.constant.ISQL;
import com.util.ikanalyzer.DBUtil;

/**
 * 用户问答记录
 * @author Administrator
 *
 */
public class RecordDao extends BasicDao{
	/**
	 * 添加数据，
	 * insert into record(id,userId,question,answer values(recordSeq.nextval,?,?,?
	 * @param userId
	 * @param question
	 * @param answer	不能超过2000汉字
	 * @return
	 */
	
	public static boolean insertData(String userId,String question,String answer,Date date) {
		Connection connection = null;
		try {
			connection = DBFacotry.getConnectionByC3P0();
			// 将该连接设置为不自动提交
			// 获取一个预处理对象
			PreparedStatement statement = connection.prepareStatement(ISQL.insertIntoRecordTable);
			statement.setObject(1, userId);
			statement.setObject(2, question);
			statement.setObject(3, answer);
			statement.setObject(4, new java.sql.Date(date.getTime()));
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}

		return false;
	}
	/**
	 * 根据id获取所有的记录值
	 * @param userId
	 * @return
	 */
	public static List<Record> getDatas(String userId){
		ResultSet rs = getResultSet(ISQL.selectFromRecordTable, userId);
		return DBUtil.getObjectsByResult(rs, Record.class);
	}
}
