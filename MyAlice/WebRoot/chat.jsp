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

<title>聊天室</title>
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
</script>
<script src="js/chat.js"></script>
<style type="text/css">
#chat {
	font-size: 12px;
	line-height: 24px;
	width: 700px;
	height: 200px;
	padding: 10px;
	background: #FFFF00;
	overflow-x: scroll;
}
#record {
	font-size: 12px;
	line-height: 24px;
	width: 700px;
	height: 200px;
	padding: 10px;
	background: #FFFF00;
	overflow-x: scroll;
}
#record
 {
 	margin-top:10px;
	font-size: 12px;
	line-height: 24px;
	width: 700px;
	height:100px;
	background-color:white;
	padding: 10px;
	overflow: auto;
	}


</style>
</head>

<body >
	<a href="manager">管理员页面</a><br>
	<div id="userId" hidden="hidden"><%=request.getSession().getAttribute("userId") %></div>
	<hr>
		<div>
			<div><input type="text" id="subscribe"  placeholder="请输入需要订阅的内容"></div>
			<div><input type="text" id="date" placeholder="请输入订阅推送的时间：小时:分钟	eg：12:00"></div>
			<div><input type="button" value="订阅" onclick="subscribe()"></div>
		</div>
	聊天室
	<div id="chat">
		客服：hello.
	</div>
	<div id="recrod" >
	</div>
	<div>
		<input type="text" id="input" placeholder="请输入问题" />
		 <input
			id="tj" type="submit" onclick="chatServer()"  />
		 <input  type="submit" onclick="recordServer()" value="历史记录" />
	</div>
	<div id="record"></div>
	<div id="dy"></div>
</body>
</html>
