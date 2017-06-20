package com.dao;

import java.sql.ResultSet;
import java.util.List;

import com.bean.MusicInfo;
import com.util.ikanalyzer.DBUtil;

/**
 * 智能问答的资料搜索数据库类
 * 
 * @author Administrator
 *
 */
public class AIMLDao extends BasicDao{
	/**
	 * 执行sql语句，获取结果集
	 * @param sql
	 * @return
	 */
	public static List<MusicInfo> getListFromDB(String sql){
		ResultSet rs = getResultSet(sql);
		List<MusicInfo> list = DBUtil.getObjectsByResult(rs, MusicInfo.class);
		return list;
	}
}
