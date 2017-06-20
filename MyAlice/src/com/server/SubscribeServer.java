package com.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.bean.Subscribe;
import com.bean.User;
import com.dao.SubscribeDao;
import com.server.ChatSubscribeServer;

/**
 * 这是一个类层次的注解，主要将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问的URL
 * websocket服务类
 * @author Administrator
 *
 */
@ServerEndpoint("/websocket")
public class SubscribeServer {
	public static Map<String, Session> clients = new HashMap<String, Session>();
	@OnMessage
	public void onMessage(String userId, final Session session)
			throws IOException {
		System.out.println("收到消息："+userId+","+session);
		final String id = new String(userId);
//		if(clients.get(id)==null){
//			clients.put(id, session);
			new java.lang.Thread() {
				public void run() {
					while (true) {
						
						// 向客户端发送消息
						try {
							System.out.println(id+"用户的订阅线程启动");
							Date nowDate = new Date();
							List<Subscribe> subscribes = ChatSubscribeServer
									.getDatas(String.valueOf(id));
							// 需要向用户发送
							List<Subscribe> toSendList = ChatSubscribeServer
									.getSendMessgeSubscribe(nowDate, subscribes);
							StringBuffer buffer = new StringBuffer("");
							for (Subscribe subscribe : toSendList) {
								String sendMSG = ChatSubscribeServer
										.getSubscribeContent(subscribe.getContent());
								// 向客户端发送
								buffer.append(sendMSG + "<br>");
							}
							String sendMSG = buffer.toString();
							if(sendMSG!=null&&!sendMSG.equals("")){
								System.out.println(sendMSG);
								session.getBasicRemote().sendText("您好，您的订阅已经送达：<br>"+sendMSG);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							sleep(60*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}.start();
//		}
		
		
	}

	@OnOpen
	public void onOpen( final javax.websocket.Session session) {
		System.out.println("client connected");
	}

	@OnClose
	public void onClose(Session session) {
		for (int i = clients.size()-1; i >=0; i--) {
			Set<String> keys = clients.keySet();
			Iterator<String> iter = keys.iterator();
			while(iter.hasNext()){
				String key = iter.next();
				if(clients.get(key).equals(session)){
					clients.remove(key);
					break;
				}
			}
		}
		System.out.println("Connected closed");
	}

	public static void sendMSG() {
		// 新建一个线程，遍历循环用户是否需要发送订阅信息
		System.out.println("sendMSG");
	}
	public static void insertInfo(String userId, String content, Date date){
		SubscribeDao.insertSubsribe(userId, content, date);
	}
}
