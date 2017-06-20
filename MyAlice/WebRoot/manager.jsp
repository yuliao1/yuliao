<%@ page language="java" import="java.util.*" import="com.bean.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'manager.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/manager.js"></script>
</head>

<body>
	<table>

		<c:forEach items="${list}" var="item" varStatus="status">
			<tr >
				<td>id:</td>
				<td name="id">${item.id}</td>
				<td>姓名:</td>
				<td name="username">${item.username}</td>
				<td>权限:</td>
				<td><select id="${item.id}_status" name="status">
						<c:if test="${item.status==1}">
							<option value="1" selected="selected">管理员</option>
							<option value="2">普通用户</option>
							<option value="3">高级用户</option>
						</c:if>
						<c:if test="${item.status==2}">
							<option value="1">管理员</option>
							<option value="2" selected="selected">普通用户</option>
							<option value="3">高级用户</option>
						</c:if>
						<c:if test="${item.status==3}">
							<option value="1">管理员</option>
							<option value="2">普通用户</option>
							<option value="3" selected="selected">高级用户</option>
						</c:if>
				</select></td>
				<td><button  onclick="modifyStatus(${item.id})";>修改</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
