<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>
<%@ page import="com.tha103.gogoyu.adm_meb.model.*"%>

<%
    CompanyService cmpSvc = new CompanyService();
    List<Company> list = cmpSvc.getByCheckStatus();
    pageContext.setAttribute("list",list);
%>

<%-- <% --%>
<!--  	Adm_meb admin = (Adm_meb) request.getAttribute("admin"); //Servlet.java(Concroller), 存入req的Company物件 -->
<!--  	if (admin == null){ -->
<!--  		String compString = (String) request.getSession().getAttribute("compId"); -->
<!--  		if (compString == null ){ -->
<!--  			response.sendRedirect(request.getContextPath() + "/ken/com_mem_signin.jsp"); -->
<!--  			return; -->
<!--  		} -->
<!--  		Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId")); -->
<!--  		CompanyService companySvc = new CompanyService(); -->
<!--  		admin = companySvc.getOneCompany(compId); -->
<!--  		request.setAttribute("company", admin); -->
<!--  	} -->
<%-- %> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台首頁</title>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/hollow_css/backend_css.css">
</head>
<body onload="connect();" onunload="disconnect();">
    <br>
    <header>GoGoYu後台
    	<form method="post" action="${pageContext.request.contextPath}/hollow/AdmServlet">
    		<div style="margin:auto;display:flex;justify-content: center;">
	    		<h3>${admin.admName} 你好</h3>
	    		<input type="hidden" name="action" value="logout">
	    		<button type="submit" style="margin-left:10px" class="btn btn-outline-dark">登出</button>
    		</div>
    	</form>
        <table class="main_btn">
            <tr>
                <td>
                    <button type="button" class="main_btn_comp_audit" data-bs-toggle="button" autocomplete="off">業者審核</button>
                </td>
                <td>
                    <button type="button" class="main_btn_client_msg" data-bs-toggle="button" autocomplete="off">客戶訊息</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="main_btn_comp_search" data-bs-toggle="button" autocomplete="off">業者查詢</button>
                </td>
                <td>
                    <button type="button" class="main_btn_cust_search" data-bs-toggle="button" autocomplete="off">會員查詢</button>
                </td>
            </tr>
        </table>
    </header>
    
    <article class="art_comp_audit">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者審核</h1>
        <div class="task_list_parent">
            <ul class="list">
            	<c:forEach var="cmpVO" items="${list}">
					<li>
	                    <a class="link_title" href="#">
	                    	${cmpVO.compName}
	                    </a>
	                    <div class="inner_block">
	                        <table class="table table-striped">
	                            <tr>
	                                <th>業者ID</th>
	                                <th>公司名稱</th>
	                                <th>公司地址</th>
	                                <th>負責人</th>
	                            </tr>
	                            <tr>
	                                <td>${cmpVO.compId}</td>
	                                <td>${cmpVO.compName}</td>
	                                <td>${cmpVO.compAddress}</td>
	                                <td>${cmpVO.principalName}</td>
	                            </tr>
	                        </table>
	                        <table>
	                        	<tr>
	                        		<td>
	                        			 <form action="${pageContext.request.contextPath}/CompanyServlet">
						     				<input type="hidden" name="compId"  value="${cmpVO.compId}">
						     				<input type="hidden" name="chkStatus"  value=1>
						     				<input type="hidden" name="action"	value="updChkStat">
				                        	<button type="submit" class="btn_update btn btn-outline-success btn-lg">通過</button>
				                        </form>
	                        		</td>
	                        		<td>
	                        			<form class="" action="${pageContext.request.contextPath}/CompanyServlet">
						     				<input type="hidden" name="compId"  value="${cmpVO.compId}">
						     				<input type="hidden" name="chkStatus"  value=-1>
						     				<input type="hidden" name="action"	value="updChkStat">
				                        	<button type="submit" class="btn_update btn btn-outline-danger btn-lg">撤銷</button>
				                        </form>
	                        		</td>
	                        	</tr>
	                        </table>
	                    </div>                    
	                </li>
				</c:forEach>
			</ul>
		</div>
    </article>

    <article class="art_client_msg">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">客戶訊息</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
            <ul class="list">
					<li>
	                    <a class="link_title" href="#">testUser</a>
	                </li>
			</ul>
        </div>

		<div id="row"></div>
        <h3 id="statusOutput" class="statusOutput"></h3>
		<div id="messagesArea" class="panel message-area" ></div>
		<div class="panel input-area">
			<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
			<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		</div>
        
        <div class="task_list_parent">
            <ul class="task_list">
            </ul>
        </div>
    </article>

    <article class="art_comp_search">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者查詢</h1>
        
        <div class="task_add_block">
        	<form action="${pageContext.request.contextPath}/CompanyServlet">
	            <input type="text" class="task_name comp_search_value" placeholder="輸入查詢事項…">
	            <button type="button" class="task_add task_comp_search">查詢</button>
        	</form>
        </div>
        
        <div class="task_list_parent">
            <ul class="list list_company">
                <li>
                    <div class="">
                        <table class="table table-striped">
                        </table>
                    </div>                    
                </li>                
            </ul>
        </div>
    </article>

    <article class="art_cust_search">
        <h1 class="title1">會員查詢</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name cust_search_value" placeholder="輸入查詢事項…">
            <button type="button" class="task_add task_cust_search">查詢</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list list_consumer">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                        </table>      
                    </div>
                </li>
            </ul>
        </div>
    </article>

<!-- 
    <article class="art_add_scene">
        <h1 class="title1">景點新增</h1>
        
        <!-- <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>景點名稱</th>
                                <td><input type="text" class="text" value="太平島石油井"></td>
                            </tr>
                            <tr>
                                <th>開放時間</th>
                                <td><input type="text" class="text" value="全天"></td>
                            </tr>
                            <tr>
                                <th>門票價格</th>
                                <td><input type="text" class="text" value="100,000"></td>
                            </tr>
                            <tr>
                                <th>交通資訊</th>
                                <td><input type="text" class="text" value="飛機"></td>
                            </tr>
                            <tr>
                                <th>停車場</th>
                                <td><input type="text" class="text" value="停機坪"></td>
                            </tr>
                            <tr>
                                <th>地址</th>
                                <td><input type="text" class="text" value="太平島石油公園"></td>
                            </tr>
                            <tr>
                                <th>特色</th>
                                <td><input type="text" class="text" value="可以挖石油"></td>
                            </tr>
                            <tr>
                                <th>圖片</th>
                                <td><input type="file" class="text" value="圖片放這"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">預覽圖</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">新增</button>                    
                    </div>
                </li>
            </ul>
        </div>
    </article>
 -->
    
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/hollow_js/backend_js.js"></script>
    
    <script>
	var chatPoint = "/FriendWS/CS";//"FriendWS/${userName}"
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var chatPointURL = "ws://" + window.location.host + webCtx + chatPoint;

	//var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = 'CS';//'${userName}'
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(chatPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			//document.getElementById('connect').disabled = true;
			//document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i <= friends.length; i++) {
			if (friends[i] === self) { continue; }
			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
    </script>
</body>
</html>