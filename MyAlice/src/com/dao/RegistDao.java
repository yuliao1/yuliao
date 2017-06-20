package com.dao;

import java.sql.Connection;

import com.bean.User;
import com.constant.ISQL;

/**
 * 用户注册
 * @author Administrator
 *
 */
public class RegistDao extends BasicDao{
	/**
	 * 用户注册注册
	 * @param user
	 * @return
	 */
	public static boolean regist(User user){
		return executetData(DBFacotry.getConnectionByC3P0(),ISQL.insertIntoLoginInfoTable, user.getUsername(),user.getPassword(),user.getStatus());
	}

	
}
