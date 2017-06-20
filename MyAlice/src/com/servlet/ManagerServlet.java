package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.server.LoginServer;

import oracle.net.aso.u;
/**
 * 用户管理页面相关
 */
@WebServlet(urlPatterns="/manager")
public class ManagerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean isContinue = isContinue(req);
		if(isContinue){
			//转发到用户管理页面
			req.getRequestDispatcher("modify").forward(req, resp);
		}else{
//			resp.getWriter().println("用户权限不足");
			//重定向会聊天页面
			resp.sendRedirect("chat.jsp");
		}
	}
	/**
	 * 能否跳转到用户管理页面
	 * @param req
	 * @return
	 */
	public boolean isContinue(HttpServletRequest req){
		try {
			//从数据库获取user对象，判断是否是管理员，如果是管理员，那么可以跳转到页面，如果不是，那么不跳转页面
			String userId = String.valueOf(req.getSession().getAttribute("userId"));
			User user = LoginServer.getUser(userId);
			if(Integer.valueOf(user.getStatus())==1){
				return true;
			}else{
				System.out.println("跳转失败，权限不足");
			}
		} catch (Exception e) {
			System.out.println("跳转失败，数据错误");
		}
		return false;
	}
}
