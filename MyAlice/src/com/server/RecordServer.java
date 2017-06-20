package com.server;

import java.util.Date;
import java.util.List;

import com.bean.Record;
import com.dao.RecordDao;

/**
 * 问答记录服务
 * @author Administrator
 *
 */
public class RecordServer {
	/**
	 * 向记录表中插入问答信息
	 * @param userId
	 * @param question
	 * @param answer
	 */
	public static void insertData(String userId,String question,String answer){
		RecordDao.insertData(userId, question, answer,new Date());
	}
	/**
	 * 获取聊天记录
	 * @return 
	 */
	public static String getData(String userId){
		StringBuffer buffer = new StringBuffer("");
		List<Record> list = RecordDao.getDatas(userId);
		for (Record record : list) {
			buffer.append(record.toString());
		}
		return buffer.toString();
	}
}
