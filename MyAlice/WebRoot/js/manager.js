function modifyStatus(id){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var status_element = document.getElementById(id+"_status");
	var status =status_element.value;
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert("修改完毕");
		}
	}
	xmlhttp.open("POST", "manager", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
//	xmlhttp.send("rid=" + Math.random +"&id=" + id+ "&status=" + status);
	xmlhttp.send("id=" + id+ "&status=" + status);
}