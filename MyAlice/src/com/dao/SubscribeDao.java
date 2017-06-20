package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bean.Subscribe;
import com.constant.ISQL;
import com.util.ikanalyzer.DBUtil;

/**
 * 订阅db类
 * 
 * @author Administrator
 * 
 */
public class SubscribeDao extends BasicDao {
	/**
	 * 插入：向订阅表中插入订阅信息
	 */
	public static boolean insertSubsribe(String userId, String content,Date date) {
		return executetData(DBFacotry.getConnectionByC3P0(),
				ISQL.insertIntoSubscribeTable, userId, content,new java.sql.Date(date.getTime()));
	}

	/**
	 * 查询：根据某人的id查询订阅表中的信息
	 */
	public static List<Subscribe> getDatas(String userId) {
		ResultSet rs = getResultSet(ISQL.selectFromSubscribeTableById, userId);
		return DBUtil.getObjectsByResult(rs, Subscribe.class);
	}
	/**
	 * 获取所有人的订阅信息
	 * @return
	 */
	public static List<Subscribe> getDatas(){
		ResultSet rs = getResultSet(ISQL.selectFromSubscribeTable);
		return DBUtil.getObjectsByResult(rs, Subscribe.class);
	}
	

}
