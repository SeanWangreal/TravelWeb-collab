<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>

<%
Company company = (Company) request.getAttribute("Company"); //Servlet.java(Concroller), 存入req的Company物件
if (company == null){
	String compString = (String) request.getSession().getAttribute("compId");
	if (compString == null ){
		response.sendRedirect(request.getContextPath() + "/ken/com_mem_signin.jsp");
		return;
	}
	Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId"));
	CompanyService companySvc = new CompanyService();
	company = companySvc.getOneCompany(compId);
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ken_css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ken_css/comp_mem.css">

<style>
.main-content-info>* {
	margin-top: 10px;
	margin-bottom: 10px;
}

#change_pass {
	margin-top: 15px;
	font-family: "粉圓";
	font-size: 16px;
	margin-left: 1%;
	margin-right: 3%;
	width: 100px;
}

#change_pass>a {
	color: white;
	/* border: 1px solid #FCC416; */
	background-color: #4D504F;
	padding: 5px 10px;
	border-radius: 15px;
}

.main-content-info>label {
	width: 100%;
	position: relative;
	color: black;
	/* border: 1px solid black; */
	background-color: #F3F3F3;
	padding: 8px 5px;
	border-radius: 10px;
}

.main-content-info>label>span {
	border: none;
	background-color: #F3F3F3;
	color: #000000;
	width: 70%;
	position: absolute;
	right: 0px;
	border-left: 2px black solid;
	padding-left: 15px;
}

.on {
	display: block;
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

.all {
	width: auto;
	height: auto;
	min-height: 100vh;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #F3F3F3;
	display: flex;
	flex-direction: column;
	align-items: stretch;
}

#detail {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 3;
	border: 1px solid black;
	width: 70%;
	height: 80%;
	background-color: white;
	/* display: none; */
}

.hotel-btn {
	border: none;
	/* border: #FCC416 1px solid; */
	background-color: #4D504F;
	border-radius: 15px;
	position: relative;
	color: white;
	padding: 5px 10px;
}
</style>
</head>

<body>
	<script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
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
			<button type="button" class="head_btn" id="">
				   <a class="profile "  href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp">
				    <i class="fa-solid fa-store"
				     style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>
				   </a>
			</button>
			<button type="button" class="head_btn">
				<a class="profile" href="${pageContext.request.contextPath}/ken/tripcom_mem.jsp"> <i class="fa-solid fa-user"
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
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<label class="">帳號&ensp;<span><%= company.getCompAccount()%></span></label>
				<br> <label class="">公司名稱&ensp;<span><%= company.getCompName()%></span></label>
				<br> <label class="">公司地址&ensp;<span><%= company.getCompAddress()%></span></label>
				<br> <label class="">負責人&ensp;<span><%= company.getPrincipalName()%></span></label>
				<br> <label class="">負責人電話&ensp;<span><%= company.getCompPhone()%></span></label>
				<br> <label class="">信箱&ensp;<span><%= company.getCompMail()%></span></label>
				<br> <label class="">廠商別&ensp;<span><%= company.getCompType()%></span></label>
				<br>
			</div>
			<FORM METHOD="post" ACTION="CompanyServlet" >
<!-- 				<div id="change_pass"> -->
<%-- 					<a href="${pageContext.request.contextPath}/CompanyServlet?action=getOneUpdate&compId=<%= company.getCompId()%>">修改密碼</a> --%>
<!-- 				</div> -->
				<input type="hidden" name="compId" value="<%= company.getCompId()%>">
				<input type="hidden" name="action" value="getOneUpdate">
				<div id="change_pass"> 
					<button type = "submit" >修改密碼</button>
 				</div> 
				
			</FORM>
			
			<div style="display: table-cell;" >
				<div style="border: 1px solid grey; height: 200px; width: 200px; position: relative; right: 0px; margin-top: 10px;">
					<img src="" alt="">
				</div>
			</div>
				
		</main>
	</div>
	<div id="detail" style="display: none;">123</div>
	<script src="${pageContext.request.contextPath}/static/ken_js/btn4com.js"></script>
</body>

</html>