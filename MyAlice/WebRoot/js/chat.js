/*获取请求对象：ajax浏览器兼容处理*/
var xmlhttp;
if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp = new XMLHttpRequest();
} else {// code for IE6, IE5
	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}

/*---------------------推荐-------------------------------------------------*/
xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		var temp = "<br>客服:你好哈！今天推荐：" + xmlhttp.responseText;
		document.getElementById("chat").innerHTML = temp;
		div.scrollTop = div.scrollHeight;
	}
}
xmlhttp.open("POST", "recommed", true);// recommend路径没有配置在web.xml中，配置在了对应的Servlet中
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send();

/*-------------------------input按钮监听回车键-----------------------------------------------*/
/* input按钮监听回车键 */
document.onkeydown = keyListener;

function keyListener(e) {
	e = e ? e : event;// 兼容FF
	if (e.keyCode == 13) {
		chatServer();
	}
}

/*-----------------------------异步提交聊天数据----------------------------------------------*/

/* 异步提交聊天数据 */
function chatServer() {
	var input = document.getElementById("input").value;
	if (input == "") {
		return;
	}
	var div = document.getElementById("chat");
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var temp = document.getElementById("chat").innerHTML + "<br>me:"
					+ input + "<br>客服:" + xmlhttp.responseText;
			document.getElementById("chat").innerHTML = temp;
			div.scrollTop = div.scrollHeight;
		}
	}
	xmlhttp.open("POST", "chat", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send("rid=" + Math.random + "&input=" + input);
	document.getElementById("input").value = "";
}

/*------------------------------异步获取聊天记录-------------------------------------------------*/

/* 异步获取聊天记录 */

function recordServer() {
	var div = document.getElementById("record");
	div.height = "200px";
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("record").innerHTML = xmlhttp.responseText;
		}
	}
	xmlhttp.open("POST", "record", true);
	xmlhttp.send();
}
/*-----------------------------websocket的js代码，一旦加载chat.jsp，那么建立websocket*/
var webSocket = new WebSocket('ws://localhost:8080/MyAlice/websocket');
webSocket.onerror = function(event) {
	alert("erro")
	onError(event)
};

webSocket.onopen = function(event) {
	onOpen(event)

};

webSocket.onmessage = function(event) {
	onMessage("event:" + event)
	if(event.data!=null&&event.data!=""){
		document.getElementById("dy").innerHTML=document.getElementById("dy").innerHTML+event.data;
	}
	
	
};

function onMessage(event) {
	 alert("onMessage:data:"+event.data)
}

function onOpen(event) {
	start();
}

function onError(event) {
	alert("erro:"+event.data);
}

function start() {
	webSocket.send(document.getElementById("userId").innerHTML);
	return false;
}
function subscribe() {
	var value = document.getElementById("subscribe").value;
	var date = document.getElementById("date").value;
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert(xmlhttp.responseText);
		}
	}
	xmlhttp.open("POST", "subscribe", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send("subscribe=" + value + "&date=" + date);
}