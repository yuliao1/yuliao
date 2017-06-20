package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bean.User;
import com.constant.ISQL;
import com.util.ikanalyzer.DBUtil;

public class LoginDao extends BasicDao {
	/**
	 * 测试用户是否能够登录
	 * @param user	需要有username，password，status
	 * @return
	 */
	public static boolean canLogin(User user){
		
		ResultSet rs = getResultSet(ISQL.selectUser  , user.getUsername(),user.getPassword(),user.getStatus());
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		}
		return false;
	}
	/**
	 * 测试用户是否能够登录
	 * @param username
	 * @param password
	 * @param status
	 * @return
	 */
	public static User getUser(String username,String password,String status){
		ResultSet rs = getResultSet(ISQL.selectUser, username, password, status);
		try {
			if(rs.next()){
				return DBUtil.getObjectByResult(rs, rs.getMetaData(), User.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		}
		return null;
	}
	/**
	 * 测试用户是否能够登录
	 * @param id
	 * @return
	 */
	public static User getUser(String id){
		ResultSet rs = getResultSet(ISQL.selectUserById, id);
		try {
			if(rs.next()){
				return DBUtil.getObjectByResult(rs, rs.getMetaData(), User.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		}
		return null;
	}
	
	/**
	 * 测试用户是否能够登录
	 * @param username
	 * @param password
	 * @param status
	 * @return
	 */
	public static boolean canLogin(String username,String password,String status){
		
		ResultSet rs = getResultSet(ISQL.selectUser, username,password,status);
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		}
		return false;
	}
	
	public static boolean  modify(String newUsername,String newPssword,String newStatus,String id){
		return  executetData(DBFacotry.getConnectionByC3P0(), ISQL.updateLoginInfoTable, newUsername,newPssword,newStatus,id);
	}
	public static List<User> getUsers() {
		ResultSet rs = getResultSet(ISQL.selectUsers);
		List<User> list = DBUtil.getObjectsByResult(rs, User.class);
		return list;
	}
}
