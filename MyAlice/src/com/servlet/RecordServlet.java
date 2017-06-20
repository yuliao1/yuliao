package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.RecordServer;


/**
 * 用户聊天记录
 * @author Administrator
 *
 */
@WebServlet(urlPatterns="/record")
public class RecordServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String userId = String.valueOf(req.getSession().getAttribute("userId"));
			String records = RecordServer.getData(userId);
			resp.getWriter().println(records);
			System.out.println(records);
			System.out.println("记录获取成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
}
