package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.server.LoginServer;

@WebServlet(urlPatterns = "/modify")
/**
 * 用户修改页面-修改用户权限
 */
public class ModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取是否有权限修改
		Object statusObj = req.getParameter("status");
		if (statusObj != null) {
			//如果有权限提交
			String status = String.valueOf(statusObj);
			//获取需要修改的人的用户id
			String id = String.valueOf(req.getParameter("id"));
			//从数据库获取用户对象
			User user = LoginServer.getUser(id);
			//修改用户权限
			LoginServer.modify(user.getUsername(), user.getPassword(), status,
					id);
		}
		// 从数据库获取所有用户
		List<User> list = LoginServer.getUsers();
		//将数据放入请求中
		req.setAttribute("list", list);
		//转发到管理页面
		req.getRequestDispatcher("manager.jsp").forward(req, resp);
	}
}
