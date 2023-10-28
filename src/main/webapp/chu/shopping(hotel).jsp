<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);

Integer cusId = (Integer) request.getSession().getAttribute("cusId");
System.out.println(cusId);
if (cusId != null) {

	Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
	Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();

	for (int i = 1; i <= 5; i++) {
		pageContext.setAttribute("room" + i, ROSH.getRoomOrdVo(i, cusId));
		pageContext.setAttribute("trip" + i, TOSH.getTripOrdVo(i, cusId));
	}
} else {
	response.sendRedirect(request.getContextPath() + "/eric/signin.jsp");
	return;
}
%>





<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/chu_css/shopping(trip).css">

</head>

<body>
	<script
			src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/static/chu_js/shopping.js"></script>
	

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



	<!-- =======================上面是aside===================== -->
	<!-- =======================下面是list===================== -->
				



	<div class="plan_tab">
						<c:if test="${not empty errorMessage}">
								<div class="error-message" style = "color :red ; font-size:25px ; top :15%; position : absolute ; left :50%"><i>*${errorMessage}</i></div>
						</c:if>
		<div class="tab_list_block">
<!-- 			<ul class="tab_list"> -->
<!-- 				<li><button data-target="plan1" class="tab -on">規劃一</button></li>  -->
<!-- 				<li><button data-target="plan2" class="tab ">規劃一</button></li>  -->
<!-- 				<li><button data-target="plan3" class="tab ">規劃一</button></li>  -->
<!-- 				<li><button data-target="plan4" class="tab ">規劃一</button></li>  -->
<!-- 				<li><button data-target="plan5" class="tab ">規劃一</button></li>  -->
		
		
<!-- 			</ul> -->
			<ul class="tab_list">
				<li><a href="#" data-target="plan1" class="tab -on">規劃一</a></li>
				<li><a href="#" data-target="plan2" class="tab ">規劃二</a></li>
				<li><a href="#" data-target="plan3" class="tab ">規劃三</a></li>
				<li><a href="#" data-target="plan4" class="tab ">規劃四</a></li>
				<li><a href="#" data-target="plan5" class="tab ">規劃五</a></li>
			</ul>
		</div>


		<!--    =============================foreachForList(hotel)======================================              -->


		<div class="tab_contents">





			<div class="tab plan1 -on" id="tab_plan1">
				<!-- 				<div class="no-items">暫無商品</div> -->
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo1" items="${room1.keySet()}">


					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo1.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room1.get(roomVo1).get(0)}</i>

								</div>
								<!-- 					</form> -->
								<div class="comment_set">
									<div class="comment_message"
										style="position: absolute; right: 100px;">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${room1.get(roomVo1).get(2)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price" style = "font-size :22px">${room1.get(roomVo1).get(1)}</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo1.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">

							<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeHotelCart">
									<input type = "hidden"  name ="roomOrdId" value ="${roomVo1.roomOrdId}">
								</form>
								</div>
								
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="hotelcheckOut">
									<input type="hidden" name="roomOrdId"
										value="${roomVo1.roomOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="removeHotelOrder">
									<input type="hidden" name="roomOrdId"
										value="${roomVo1.roomOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>


				</c:forEach>
				<!--    =============================foreachForList(hotel)======================================              -->
				<!--    =============================foreachForList(trip)======================================              -->



				<!-- 				==============裡面的list=============== -->
				<!-- 								<div class="no-items n1">暫無商品</div> -->
				<c:forEach var="tripVo1" items="${trip1.keySet()}">

					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo1.tripId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">旅</span> <i
										id="named_of_title" >${trip1.get(tripVo1).get(0)} </i>

								</div>

								<div class="comment_set">
									<div class="comment_message">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${trip1.get(tripVo1).get(1)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${tripVo1.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">
							
							
							
							<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeTripCart">
									<input type = "hidden"  name ="tripOrdId" value ="${tripVo1.tripOrdId}">
								</form>
								</div>
							
							


								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="TripCheckOut">
									<input type="hidden" name="TripOrdId"
										value="${tripVo1.tripOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="removeTripOrder">
									<input type="hidden" name="TripOrdId"
										value="${tripVo1.tripOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>

				</c:forEach>
			</div>
			<!--    =============================foreachForList(trip)======================================              -->







			<div class="tab plan2">
				<div class="no-items">暫無商品</div>
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo2" items="${room2.keySet()}">


					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo2.roomId}">
						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room2.get(roomVo2).get(0)}</i>

								</div>
								<!-- 					</form> -->
								<div class="comment_set">
									<div class="comment_message"
										style="position: absolute; right: 100px;">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${room2.get(roomVo2).get(2)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price" style = "font-size :25px">${room2.get(roomVo2).get(1)}</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo2.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">

						<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeHotelCart">
									<input type = "hidden"  name ="roomOrdId" value ="${roomVo2.roomOrdId}">
								</form>
								</div>

								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="hotelcheckOut">
									<input type="hidden" name="roomOrdId"
										value="${roomVo2.roomOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="removeHotelOrder">
									<input type="hidden" name="roomOrdId"
										value="${roomVo2.roomOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>


				</c:forEach>



				<!--    =============================foreachForList(trip)======================================              -->



				<!-- 				==============裡面的list=============== -->

				<c:forEach var="tripVo2" items="${trip2.keySet()}">

					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo2.tripId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">旅</span> <i
										id="named_of_title">${trip2.get(tripVo2).get(0)} </i>

								</div>

								<div class="comment_set">
									<div class="comment_message">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">8.7</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${tripVo2.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">
								<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeTripCart">
									<input type = "hidden"  name ="tripOrdId" value ="${tripVo2.tripOrdId}">
								</form>
								</div>


								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="TripCheckOut">
									<input type="hidden" name="TripOrdId"
										value="${tripVo2.tripOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="removeTripOrder">
									<input type="hidden" name="TripOrdId"
										value="${tripVo2.tripOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>

				</c:forEach>
			</div>
			<!--    =============================foreachForList(trip)======================================              -->





			<div class="tab plan3 ">
				<div class="no-items">暫無商品</div>
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo3" items="${room3.keySet()}">


					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo3.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room3.get(roomVo3).get(0)}</i>

								</div>
								<!-- 					</form> -->
								<div class="comment_set">
									<div class="comment_message"
										style="position: absolute; right: 100px;">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${room3.get(roomVo3).get(2)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price" style = "font-size :25px">${room3.get(roomVo3).get(1)}</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo3.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">

						<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeHotelCart">
									<input type = "hidden"  name ="roomOrdId" value ="${roomVo3.roomOrdId}">
								</form>
								</div>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="hotelcheckOut">
									<input type="hidden" name="roomOrdId"
										value="${roomVo3.roomOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="removeHotelOrder">
									<input type="hidden" name="roomOrdId"
										value="${roomVo3.roomOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>


				</c:forEach>


				<!--    =============================foreachForList(trip)======================================              -->



				<!-- 				==============裡面的list=============== -->

				<c:forEach var="tripVo3" items="${trip3.keySet()}">

					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo3.tripId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">旅</span> <i
										id="named_of_title">${trip3.get(tripVo3).get(0)} </i>

								</div>

								<div class="comment_set">
									<div class="comment_message">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">8.7</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${tripVo3.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">
								<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeTripCart">
									<input type = "hidden"  name ="tripOrdId" value ="${tripVo3.tripOrdId}">
								</form>
								</div>



								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="TripCheckOut">
									<input type="hidden" name="TripOrdId"
										value="${tripVo3.tripOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="removeTripOrder">
									<input type="hidden" name="TripOrdId"
										value="${tripVo3.tripOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>

				</c:forEach>
			</div>
			<!--    =============================foreachForList(trip)======================================              -->


			<div class="tab plan4">
				<div class="no-items">暫無商品</div>
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo4" items="${room4.keySet()}">


					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo4.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room4.get(roomVo4).get(0)}</i>

								</div>
								<!-- 					</form> -->
								<div class="comment_set">
									<div class="comment_message"
										style="position: absolute; right: 100px;">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${room4.get(roomVo4).get(2)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price" style = "font-size :25px">${room4.get(roomVo4).get(1)}</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo4.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">

							<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeHotelCart">
									<input type = "hidden"  name ="roomOrdId" value ="${roomVo4.roomOrdId}">
								</form>
								</div>

								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="hotelcheckOut">
									<input type="hidden" name="roomOrdId"
										value="${roomVo4.roomOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="removeHotelOrder">
									<input type="hidden" name="roomOrdId"
										value="${roomVo4.roomOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>


				</c:forEach>

				<!--    =============================foreachForList(trip)======================================              -->



				<!-- 				==============裡面的list=============== -->
				<c:forEach var="tripVo4" items="${trip4.keySet()}">

					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo4.tripId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">旅</span> <i
										id="named_of_title">${trip4.get(tripVo4).get(0)} </i>

								</div>

								<div class="comment_set">
									<div class="comment_message">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">8.7</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${tripVo4.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">
								<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeTripCart">
									<input type = "hidden"  name ="tripOrdId" value ="${tripVo4.tripOrdId}">
								</form>
								</div>


								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="TripCheckOut">
									<input type="hidden" name="TripOrdId"
										value="${tripVo4.tripOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="removeTripOrder">
									<input type="hidden" name="TripOrdId"
										value="${tripVo4.tripOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>

				</c:forEach>
			</div>
			<!--    =============================foreachForList(trip)======================================              -->

			<div class="tab plan5">
				<div class="no-items">暫無商品</div>
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo5" items="${room5.keySet()}">


					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo5.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room5.get(roomVo5).get(0)}</i>

								</div>
								<!-- 					</form> -->
								<div class="comment_set">
									<div class="comment_message"
										style="position: absolute; right: 100px;">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">${room5.get(roomVo5).get(2)}</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price" style = "font-size :25px">${room5.get(roomVo5).get(1)}</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo5.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">


								<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeHotelCart">
									<input type = "hidden"  name ="roomOrdId" value ="${roomVo5.roomOrdId}">
								</form>
								</div>



								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="hotelcheckOut">
									<input type="hidden" name="roomOrdId"
										value="${roomVo5.roomOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_hotelServlet"
									method="post">
									<input type="hidden" name="action" value="removeHotelOrder">
									<input type="hidden" name="roomOrdId"
										value="${roomVo5.roomOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>


				</c:forEach>




				<!-- 				==============裡面的list=============== -->
				<c:forEach var="tripVo5" items="${trip5.keySet()}">

					<div class="plan_tab_1 list">

						<div class="plan_tab_1_left">

							<img
								src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo5.tripId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style="margin-top: 13px; width: 210px">
									<span class="mark_for_type_hotel">旅</span> <i
										id="named_of_title">${trip5.get(tripVo5).get(0)} </i>

								</div>

								<div class="comment_set">
									<div class="comment_message">
										<a href="#"> <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
										<a href="#"><i class="fa-solid fa-star"
											style="width: 85px;">8.7</i> </a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${tripVo5.totalPrice.intValue()}</i>
							</div>
							<div class="pay_btn" style="margin-top: 20px">
								<div style ="display : flex">
								<button class="b infos">商品資訊</button>
								
								
								<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
									<select size="1" name="changeCartId" style ="position : absolute ; left:140px ; top:5px"> 
									        
									          <option value="1" >1
									          <option value="2" >2
									          <option value="3" >3
									          <option value="4" >4
									          <option value="5" >5
									      
									       </select>
										<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">換車</button>
									<input type = "hidden"  name ="action" value ="changeTripCart">
									<input type = "hidden"  name ="tripOrdId" value ="${tripVo5.tripOrdId}">
								</form>
								</div>



								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="TripCheckOut">
									<input type="hidden" name="TripOrdId"
										value="${tripVo5.tripOrdId}">
									<button class="b pay" type="submit" style="width: 170px">前往付款作業</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/shopping_tripServlet"
									method="post">
									<input type="hidden" name="action" value="removeTripOrder">
									<input type="hidden" name="TripOrdId"
										value="${tripVo5.tripOrdId}">
									<button class="b remove" type="submit" style="width: 170px">移除此商品</button>
								</form>

							</div>
						</div>


					</div>

				</c:forEach>
			</div>
			<!--    =============================foreachForList(trip)======================================              -->

		</div>

		<!-- =======================main_content===================== -->



		
</body>

</html>