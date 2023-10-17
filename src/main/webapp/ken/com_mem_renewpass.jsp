<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
Company company = (Company) request.getAttribute("Company");
System.out.println("2132145");
System.out.println(company);

%>

<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/ken_css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/ken_css/comp_mem.css">
<style>
.main-content {
	/* min-height: calc(100vh - 65px); */
	/* margin-top: 40px; */
	position: relative;
	width: 60%;
	left: 350px;
}

.main-content-info {
	position: relative;
	top: 10px;
	font-family: "粉圓";
	font-size: 16px;
	width: 400px;
}

.main-content-info>* {
	margin-top: 10px;
	margin-bottom: 10px;
}

.main-content-info>label {
	width: 45%;
	color: black;
}

.main-content-info>label>input {
	background-color: white;
	width: 70%;
	position: absolute;
	right: 0px;
}

@media ( min-height : 500px) and (max-height: 1300px) {
	.shop {
		height: 60vh;
	}
	.info {
		height: 60vh;
	}
}

@media ( min-height : 0px) and (max-height: 500px) {
	.shop {
		height: 60vh;
	}
	.info {
		height: 60vh;
	}
}

button.left_btn:hover {
	color: #ffbf1c;
}

.all {
	width: auto;
	/* min-height: calc(100vh - 55px); */
	min-height: calc(100vh - 65px);
	background-color: #F3F3F3;
	padding-top: 40px;
	padding-bottom: 20px;
	/* align-items: stretch; */
}

.alert {
	position: relative;
	top: 10vh;
	left: 50%;
	transform: translateX(-50%);
	background-color: white;
	width: 80vw;
	height: 80vh;
	z-index: 3;
	text-align: center;
	display: none;
}

.alert>* {
	position: relative;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.alert_bg {
	margin-top: 0px;
	width: 100vw;
	height: 100vh;
	position: fixed;
	top: 0px;
	z-index: 3;
	background-color: #000000a6;
	display: none;
}

.on {
	display: block;
}
</style>
</head>

<body>
	<script
		src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<div class="head">
			</button>
			<button type="menu" class="head_btn" id="msg">
				<i class="fa-regular fa-message icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="info">
				<i class="fa-regular fa-bell icon"
					style="color: black; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="">
				<i class="fa-solid fa-store icon"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>

			</button>
			<button type="button" class="head_btn">
				<a class="profile" href="#"> <i class="fa-solid fa-user"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
				</a>
			</button>
		</div>
		<aside class="msg all_side nothing" id="msg_side">
			msg<br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
			<br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
		</aside>
		<aside class="info all_side nothing" id="info_side">
			info<br>info<br>info<br>info<br>info<br>info
			<br>info<br>info<br>info<br>info<br>info
		</aside>
		<aside class="shop all_side nothing" id="shop_side">
			shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
		</aside>
		<div id="shell"></div>
		<aside class="left">
			<div class="mem-data">
				<a class="left_btn"> <i class="fa-regular fa-user"
					style="color: black;"></i> 會員資料
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn"> <i class="fa-solid fa-file-invoice"
					style="color: black;"></i> 訂單資訊
				</a>
			</div>
		</aside>
	</nav>

	F
		<div class="all">

			<main class="main-content">
				<label class="main-content-info">請輸入新密碼 <input type="text"
					name="compId" >
				</label>
					<br>
				<div>
					<!-- 					<a type="button" class="pass-btn">取消</a> -->
					<button type="button" class="pass-btn" id="add" >更新密碼</button>
					<button type="submit" id="add-data" style="display: none;"></button>
				</div>
			</main>
		</div>
	<FORM METHOD="post" ACTION="CompanyServlet">
		<div class="alert_bg">
			<div class="alert">
				<div>確定更新嗎? 
					<br>
					<!-- 				<button type="button" id="yes" class="other-btn">Yes</button> -->
					<!-- 				<button type="button" id="no" class="other-btn">No</button> -->
					<br> 
<%-- 					<a href="${pageContext.request.contextPath}/CompanyServlet?action=getPSForUpdate&compId=<%= company.getCompId()%>">送出修改</a> --%>
					<input type="hidden" name="action" value="getPSForUpdate">
					<input type="hidden" name="compId" value="<%=company.getCompId()%>">
					<input type="submit" value="送出修改">
				</div>
			</div>
		</div>
	</FORM>
	<script
		src="${pageContext.request.contextPath}/static/ken_js/btn4com.js"></script>
</body>

</html>
</html>