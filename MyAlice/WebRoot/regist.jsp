<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>

  </head>
  
  <body>
    	<form action="regist" method="post">
    		<table align="center">
     			<tr align="left">
    				<td>用户名：<input type="text" name="username" placeholder="请输入用户名"/></td>
    			</tr>
     			<tr align="left">
     				<td>密&nbsp&nbsp码：<input type="password" name="password" placeholder="请输入密码"/></td>
    			</tr>
      			<tr align="left">
     				<td>权&nbsp&nbsp限：<input type="text" name="status" readonly="readonly" min="1" max="3" value="2"/></td>
    			</tr>
       			<tr align="center">
     				<td><input type="submit" value="注册"/><input type="reset" value="重置"/></td>
    			</tr>
    			
    		</table>
    	</form>
  </body>
</html>
