package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.server.LoginServer;
import com.server.RegistServer;

public class RegistServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String status = req.getParameter("status");
			try {
				User user = new User(username, password, status);
				if(RegistServer.regist(username, password, status)){
					resp.sendRedirect("chat.jsp");//重定向
				}else{
					resp.sendRedirect("regist.jsp");//重定向
				}
			} catch (Exception e) {
				resp.sendRedirect("erro.html");//重定向
			}
	}
}
