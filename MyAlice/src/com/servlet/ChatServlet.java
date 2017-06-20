package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.server.ChatServer;
import com.server.RecordServer;

/**
 * 聊天
 * 
 * @author Administrator
 * 
 */
public class ChatServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//聊天问答信息获取
		String question = req.getParameter("input");
		User user = (User) req.getSession().getAttribute("user");
		String answer = ChatServer.respond(question,Integer.valueOf(user.getStatus()));
		resp.getWriter().println(answer);// 重定向
		// 将问答记录在数据库中
		try {
			String userId = String.valueOf(req.getSession().getAttribute(
					"userId"));
			RecordServer.insertData(userId, question, answer);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("用户id没有？");
		}
	}
}
