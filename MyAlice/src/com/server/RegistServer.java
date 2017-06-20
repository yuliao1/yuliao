package com.server;

import com.bean.User;
import com.dao.RegistDao;

public class RegistServer {
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public static boolean  regist(User user){
		return RegistDao.regist(user);
	}
	/**
	 * 注册
	 * @param username
	 * @param password
	 * @param status
	 * @return
	 */
	public static boolean  regist(String username,String password,String status){
		return RegistDao.regist(new User(username, password, status));
	}
	
}
