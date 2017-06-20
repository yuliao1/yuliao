package com.server;

import java.util.List;

import com.bean.User;
import com.dao.LoginDao;

public class LoginServer {
	/**
	 * 测试用户是否能够登录
	 * @param user	需要有username，password，status
	 * @return
	 */
	public static  boolean canLogin(User user){
		return LoginDao.canLogin(user);
	}
	/**
	 * 从数据库获取完整的user对象
	 * @param userName
	 * @param password
	 * @param status
	 * @return
	 */
	public static User getUser(String username,String password,String status){
		return LoginDao.getUser(username, password, status);
	}
	/**
	 * 从数据库获取完整的user对象
	 * @param id
	 * @return
	 */
	public static User getUser(String id){
		return LoginDao.getUser(id);
	}
	/**
	 * 修改数据
	 * @param newUsername
	 * @param newPssword
	 * @param newStatus
	 * @param id
	 */
	public static void modify(String newUsername,String newPssword,String newStatus,String id){
		LoginDao.modify(newUsername, newPssword, newStatus, id);
	}
	/**
	 * 获取所有user用户
	 * @return 
	 */
	public static List<User> getUsers() {
		return LoginDao.getUsers();
	}
}
