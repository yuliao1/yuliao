package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.bean.Subscribe;
import com.bean.User;
import com.server.ChatSubscribeServer;
import com.server.SubscribeServer;

/**
 * 这是一个类层次的注解，主要将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问的URL
 * websocket服务类
 * @author Administrator
 *
 */
@WebServlet(urlPatterns="/subscribe")
public class SubscribeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String content = req.getParameter("subscribe");
		String date = req.getParameter("date");
		System.out.println(content+","+date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		try {
			Date date2 = dateFormat.parse(date);
			String userId = String.valueOf(req.getSession().getAttribute("userId"));
			SubscribeServer.insertInfo(userId, content, date2);
			System.out.println("订阅成功："+userId+","+content+","+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
