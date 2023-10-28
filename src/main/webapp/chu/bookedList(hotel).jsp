<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>

<%
Map<Room_ord, List<String>> roomOrdMap = (Map<Room_ord, List<String>>) request.getAttribute("roomOrdMap");
Map<Trip_ord, List<String>> tripOrdMap = (Map<Trip_ord, List<String>>) request.getAttribute("tripOrdMap");

if (roomOrdMap == null && tripOrdMap  == null) {
	Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
	Integer cusId = (Integer) request.getSession().getAttribute("cusId"); //取得session的cusId
	Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
	if (cusId == null) { //假如沒有登入會員
		response.sendRedirect(request.getContextPath() + "/eric/signin.jsp");
		return;
	}
	roomOrdMap = ROSH.getRoomOrdByCusId(cusId);
	tripOrdMap = TOSH.getTripOrdByCusId(cusId);
}
;
request.setAttribute("roomOrdMap", roomOrdMap);
request.setAttribute("tripOrdMap", tripOrdMap);
%>





<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/chu_css/cus_ord.css">
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
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
</style>
</head>

<body>



	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<a class="logo" id="home" href="${pageContext.request.contextPath}/mhl/home.jsp">GO<i class="fa-solid fa-location-dot" style="color: #ffbf1c;"></i>GOYU</a>
		<div class="head">
			<button type="menu" class="head_btn" aria-label="規劃行程" id="shop">
                <i class="fa-solid fa-suitcase-rolling icon" style="color: black; font-size:30px;
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="msg">
                <i class="fa-regular fa-message icon" style="color: black; font-size:30px; 
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="info">
                <i class="fa-regular fa-bell  icon" style="color: black;font-size:30px; width: 30px;
                            background-color:transparent;"></i>
            </button>
            <button type="button" class="head_btn">
                <a class="profile" href="${pageContext.request.contextPath}/eric/personal_detail.jsp">
                    <i class="fa-solid fa-user" style="color: black; font-size:30px;
                                background-color:transparent;"></i>
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
				<a class="left_btn"> <i class="fa-solid fa-cart-shopping"
					style="color: black;"></i> 制定規劃
				</a>
			</div>
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
			<div class="mem-data">
				<a class="left_btn"> <i class="fa-solid fa-star"
					style="color: black;"></i> 我的蒐藏
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn"> <i class="fa-regular fa-calendar-days"
					style="color: black;"></i> 行事曆
				</a>
			</div>
		</aside>
	</nav>


	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<div id="searching">
					<i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
					<input type="search" placeholder="輸入訂單編號" id="ord-search">
				</div>
				
				 <c:if test="${not empty errorMessage}">
					    <div class="error-message" style = "color :red ; font-size:20px"><i>*${errorMessage}</i></div>
				</c:if>
				<c:forEach var="roomOrd" items="${roomOrdMap.keySet()}">
					<div class="ord">
						<div>
							<label class="ord-head">訂單編號:<span name="ord_id">${roomOrd.roomOrdId}</span></label>
							<label class="ord-head">飯店名稱:<span name="room_name">${roomOrdMap.get(roomOrd).get(3)}</span></label>
							<label class="ord-head">房間名稱:<span name="room_name">${roomOrdMap.get(roomOrd).get(0)}</span></label>
							<label class="ord-head">訂單狀態:<span name="room_name">${roomOrd.ordStatus == 1?"已成立":"已退款"}</span></label>
							<form action="${pageContext.request.contextPath}/Hotel_ordCommentServlet" method="post">
										<input type="hidden" name="action" value="goToComment">
										<input type="hidden" name="roomOrdId" value="${roomOrd.roomOrdId}">
										<input type="hidden" name="roomId" value="${roomOrd.roomId}">
										<button class="b remove" type="submit" style = "position :relative; left :90%;top:35%">評論去</button>
								</form>
						</div>
						<div class="all-info">
							<div>
							<label for="" class="l_long ord-label">顧客名稱<br> <span
									class="long" name="customer_name">${roomOrdMap.get(roomOrd).get(2)}</span></label>
								
								<label for="" class="l_long ord-label">房型<br> <span
									class="long" name="room_type">${roomOrdMap.get(roomOrd).get(1)}人房</span></label>
								<label for="" class="l_long ord-label">房數<br> <span
									class="long" name="room_num">${roomOrd.amount}</span></label> <label
									for="" class="l_long ord-label">總金額<br> <span
									class="long" name="total_money">${roomOrd.totalPrice.intValue()}</span><span>元</span></label>
								<label for="" class="l_long ord-label">入住日期<br> <span
									class="long" name="check_in">${roomOrd.checkInTime}</span></label> <label
									for="" class="l_long ord-label">退房日期<br> <span
									class="long" name="check_out">${roomOrd.checkOutTime}</span></label>
							</div>
							<div class="remark-block">
								<label for="" class="remark">備註:</label>
								<p class="remark-info" name="remark">${roomOrd.remark}</p>
							</div>
						</div>
					</div>

				</c:forEach>
				
				<c:forEach var="tripOrd" items="${tripOrdMap.keySet()}">
					<div class="ord">
						<div>
							<label class="ord-head">訂單編號:<span name="ord_id">${tripOrd.tripOrdId}</span></label>
							<label class="ord-head">行程名稱:<span name="trip_name">${tripOrdMap.get(tripOrd).get(0)}</span></label>
							<label class="ord-head">訂單狀態:<span name="trip_status">${tripOrd.ordStatus == 1?"已成立":"已退款"}</span></label>
								<form action="${pageContext.request.contextPath}/Trip_ordCommentServlet" method="post">
										<input type="hidden" name="action" value="goToComment">
										<input type="hidden" name="tripOrdId" value="${tripOrd.tripOrdId}">
										<input type="hidden" name="tripId" value="${tripOrd.tripId}">
										<button class="b remove" type="submit" style = "position :relative ; left :90%;top:35%">評論去</button>
								</form>
						</div>
						<div class="all-info">
							<div>
								<label for="" class="l_long ord-label">顧客名稱<br> <span
									class="long" name="customer_name">${tripOrdMap.get(tripOrd).get(1)}</span></label>
								<label for="" class="l_long ord-label">數量<br> <span
									class="long" name="amount">${tripOrd.amount}</span></label>
								<label for="" class="l_long ord-label">總人數<br> <span
									class="long" name="total_amount">${tripOrdMap.get(tripOrd).get(2)}人</span></label> <label
									for="" class="l_long ord-label">總金額<br> <span
									class="long" name="total_money">${tripOrd.totalPrice.intValue()}</span><span>元</span></label>
								<label for="" class="l_long ord-label">開始日期<br> <span
									class="long" name="staet_time">${tripOrd.startTime}</span></label> <label
									for="" class="l_long ord-label">結束日期<br> <span
									class="long" name="end_time">${tripOrd.endTime}</span></label>
							</div>
							<div class="remark-block">
								<label for="" class="remark">備註:</label>
								<p class="remark-info" name="remark">${tripOrd.remark}</p>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</main>
	</div>
	<script
		src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com.js"></script>
</body>
</html>