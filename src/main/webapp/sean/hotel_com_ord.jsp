<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../static/sean_css/comp_ord.css">
<style>
</style>
</head>

<body>
	<script src="../vendors/jquery/jquery-3.7.1.min.js"></script>
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
				<a href="" class="left_btn"> <i class="fa-regular fa-user"
					style="color: black;"></i> 會員資料
				</a>
			</div>
			<div class="mem-data">
				<a href="" class="left_btn" style="color: #FCC416"> <i
					class="fa-solid fa-file-invoice" style="color: black;"></i> 訂單資訊
				</a>
			</div>
		</aside>
	</nav>
	<%
	List<Room_ord> roomOrdList = (List<Room_ord>) request.getAttribute("roomOrdList");
	if (roomOrdList == null) {
		Room_ordServiceHibernate roomStockSvc = new Room_ordServiceHibernate();
		Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId"));
		roomOrdList = roomStockSvc.getRoomOrdByCompId(compId);
	}
	;
	request.setAttribute("roomOrdList", roomOrdList);
	%>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<div id="searching">
					<i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
					<input type="search" placeholder="輸入訂單編號" id="ord-search">
				</div>
				<c:forEach var="roomOrd" items="${roomOrdList}">
					<div class="ord">
						<div>
							<label class="ord-head">訂單編號:<span name="ord_id">${roomOrd.roomOrdId}</span></label>
<%-- 							<label class="ord-head">房間名稱:<span name="room_name">${roomOrd.roomName}</span></label> --%>
						</div>
						<div class="all-info">
							<div>
								<label for="" class="l_long ord-label">房型<br>
<%-- 								<span class="long" name="room_type">${roomOrd.roomName}</span></label> <label for="" --%>
									class="l_long ord-label">房數<br>
								<span class="long" name="room_num">${roomOrd.amount}</span></label> <label for=""
									class="l_long ord-label">總金額<br>
								<span class="long" name="total_money">${roomOrd.totalPrice}</span><span>元</span></label>
								<label for="" class="l_long ord-label">顧客名稱<br>
<%-- 								<span class="long" name="customer_name">${roomOrd.roomName}</span></label> <label for="" --%>
									class="l_long ord-label">入住日期<br>
								<span class="long" name="check_in">${roomOrd.checkInTime}</span></label> <label for=""
									class="l_long ord-label">退房日期<br>
								<span class="long" name="check_out">${roomOrd.checkOutTime}</span></label>
							</div>
							<div class="remark-block">
								<label for="" class="remark">備註:</label>
								<p class="remark-info" name="remark">${roomOrd.remark}
								</p>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</main>
	</div>
	<script src="../static/sean_js/btn4com.js"></script>
</body>
</html>