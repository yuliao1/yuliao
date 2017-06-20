package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.LoginDao;
import com.server.LoginServer;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String status = req.getParameter("status");
		try {
			User user = new User(username, password, status);
			if(LoginServer.canLogin(user)){
				req.getSession().setAttribute("hashLogin", true);
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("chat.jsp");//重定向
				//向session中添加用户的id
				user = LoginServer.getUser(username, password, status);
				req.getSession().setAttribute("userId", user.getId());
			}else{
				resp.sendRedirect("login.jsp");//重定向
			}
		} catch (Exception e) {
			resp.sendRedirect("erro.html");//重定向
		}
		
	}
	
	
}
