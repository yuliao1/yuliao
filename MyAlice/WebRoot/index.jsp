<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	我是机器人sdfg This is my JSP page.
	<br>
	<form action="loginServer" method="post">
		<table>
			<tr>
				<td>用户名：<input type="text" name="username" placeholder="请输入用户名" /></td>
			</tr>
			<tr>
				<td>密码：<input type="password" name="password"
					placeholder="请输入密码" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
