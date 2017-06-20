package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiml.AliceBotMother;
import com.dao.DBFacotry;
import com.server.ChatServer;

public class BeforLogin implements Filter{
	String[] uris={"login","regist","erro.html"};
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Object hasLogin = request.getSession().getAttribute("hashLogin");
		
		if(hasLogin!=null||canAcciss(request.getRequestURI())){
			chain.doFilter(req, resp);
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}
	private boolean canAcciss(String requestURI){
		System.out.println(requestURI);
		if(requestURI==null){
			return false;
		}
		for (String  uri: uris) {
			if(requestURI.contains(uri)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("加载");
		String path = config.getServletContext().getRealPath("/WEB-INF\\");
		DBFacotry.init(path );
		ChatServer.searchTemple();//通过调用方法，达到加载其他静态代码
	}

}
