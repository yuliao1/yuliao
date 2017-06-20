package com.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import javax.servlet.http.HttpSession;

import com.bean.Subscribe;
import com.bean.User;
import com.dao.SubscribeDao;

/**
 * 用户订阅服务
 * @author Administrator
 *
 */
public class ChatSubscribeServer {
	
	/**
	 * 插入：向订阅表中插入订阅信息
	 */
	public static boolean insertSubsribe(String userId, String content,Date date) {
		return SubscribeDao.insertSubsribe(userId, content, date);
	}

	/**
	 * 查询：根据某人的id查询订阅表中的信息
	 */
	public static List<Subscribe> getDatas(String userId) {
		return SubscribeDao.getDatas(userId);
	}
	/**
	 * 查看用户是否已经到达发送订阅信息的时候,将没有达到要求的删除
	 * 对比时间为：hh:mm
	 * @return 
	 */
	public static List<Subscribe> getSendMessgeSubscribe(Date date,List<Subscribe> subscribes){
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		String nowDate = dateFormat.format(date);
		for (int i = subscribes.size()-1; i >=0; i--) {
			Date temp =subscribes.get(i).getSendOfDate();
			String tempStr = dateFormat.format(temp);
			if(!nowDate.equals(tempStr)){
				subscribes.remove(i);
			}
		}
		return subscribes;
	}
	
	/**
	 * 根据用户的订阅需求，从数据库获取
	 */
	public static String getSubscribeContent(String question){
		String answer = ChatServer.respond(question, 1);
		return answer;
	}
	
}
