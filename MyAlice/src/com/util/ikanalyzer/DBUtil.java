package com.util.ikanalyzer;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DBUtil {
	/**
	 * 通过反射获取对象集合
	 * @param <T>
	 */
	public static <T> List<T> getObjectsByResult(ResultSet resultSet,Class<T> clazz){
		List<T> list = new ArrayList<T>();
		
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				T t = getObjectByResult(resultSet,metaData , clazz);
				if(t!=null){
					list.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 通过反射映射一个对象返回：可以识别字符串、数字、时间
	 * @param resultSet
	 * @param resultSetMetaData
	 * @param clazz
	 * @return
	 */
	public static <T> T getObjectByResult(ResultSet resultSet,ResultSetMetaData resultSetMetaData,Class<T> clazz){
		T t = null;
		try {
			t = clazz.newInstance();
			int counts = resultSetMetaData.getColumnCount();
			String[] columnNames = new String[counts];
			for (int i = 1; i <= counts; i++) {
				columnNames[i-1] = resultSetMetaData.getColumnName(i);
			}
			Field[] fields = clazz.getDeclaredFields();
			String[] fieldsNames = new String[fields.length];
			for (int i = 0; i < fieldsNames.length; i++) {
				fieldsNames[i] = fields[i].getName();
			}
			for (int i = 0; i < counts; i++) {
				Object temp = resultSet.getObject(i+1);
				if(temp==null){
					continue;
				}
				if(temp.getClass().getName().contains("Big")||temp.getClass().getName().contains("Integer")){
					BigDecimal bigDecimal = new BigDecimal(String.valueOf(temp));
					double a1 = bigDecimal.doubleValue();
					temp = a1;
					long a2 = (long) a1;
					if(a2==a1){
						temp = a2;
					}
					int a3 = (int) a2;
					if(a3==a2){
						temp = a3;
					}
				}
				if(temp.getClass().getName().contains("Timestamp")){
					temp =new java.util.Date(((java.sql.Timestamp) temp).getTime());
				}else if(temp.getClass().getName().contains("Date")){
					temp=new java.util.Date(((java.sql.Date) temp).getTime());
				}
				for (int j = 0; j < fields.length; j++) {
					if(fieldsNames[j].equalsIgnoreCase(columnNames[i])){
						fields[j].setAccessible(true);
						//名称相同
//						System.out.println(fields[j].getType().getName()+","+columnNames[i]);
						fields[j].set(t, temp);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
}
