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
// response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
// response.setHeader("Pragma", "no-cache"); //HTTP 1.0
// response.setDateHeader("Expires", 0);

session.setAttribute("trip_id", 1);
session.setAttribute("cus_id", 1);
session.setAttribute("room_id", 1);

int cusId = (int) session.getAttribute("cus_id");
Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();


pageContext.setAttribute("room1", ROSH.getRoomOrdVo(1, cusId));
pageContext.setAttribute("room2", ROSH.getRoomOrdVo(2, cusId));
pageContext.setAttribute("room3", ROSH.getRoomOrdVo(3, cusId));
pageContext.setAttribute("room4", ROSH.getRoomOrdVo(4, cusId));
pageContext.setAttribute("room5", ROSH.getRoomOrdVo(5, cusId));
pageContext.setAttribute("trip1", TOSH.getTripOrdVo(1, cusId));
pageContext.setAttribute("trip2", TOSH.getTripOrdVo(2, cusId));
pageContext.setAttribute("trip3", TOSH.getTripOrdVo(3, cusId));
pageContext.setAttribute("trip4", TOSH.getTripOrdVo(4, cusId));
pageContext.setAttribute("trip5", TOSH.getTripOrdVo(5, cusId));
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



	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<div class="head">
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
<!-- 		<div class="selectColumn"> -->
<!-- 			<li style="list-style: none"> -->
<!-- 				<FORM METHOD="post" ACTION="emp.do"> -->
<!-- 					<b>請輸入欲查詢的訂單編號:</b> <input type="text" name="Scene"> <input -->
<!-- 						type="hidden" name="action" value="getOne_For_Display"> <input -->
<!-- 						type="submit" value="送出"> -->
<!-- 				</FORM> -->
<!-- 			</li> -->
<!-- 		</div> -->


		<div class="tab_list_block">
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





			<div class="tab plan1 -on" id="tab_plan1" >
<!-- 				<div class="no-items">暫無商品</div> -->
				<!-- ==============裡面的list=============== -->
				<c:forEach var="roomVo1" items="${room1.keySet()}">
				
	
					<div class="plan_tab_1 list">
							
						<div class="plan_tab_1_left">

							<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo1.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style = "margin-top:13px; width :210px  ">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room1.get(roomVo1).get(0)}</i>
									
								</div>
<!-- 					</form> -->
								<div class="comment_set"  >
									<div class="comment_message" style = "position:absolute;right: 100px;" >
										<a href="#"  > <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
									<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
										</a>
									</div>
								</div>
							</div>
							<span class="book_price" >豪華二人房</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo1.totalPrice}</i>
							</div>
							<div class="pay_btn" style = "margin-top:20px"> 

							
									<button class="b infos">詳細商品資訊</button>

								
							
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="hotelcheckOut">
										<input type="hidden" name="roomOrdId" value="${roomVo1.roomOrdId}">
										<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
									</form>
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="removeHotelOrder">
										<input type="hidden" name="roomOrdId" value="${roomVo1.roomOrdId}">
										<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

										<img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo1.tripId}">

									</div>
									<div class="plan_tab_1_right">
										<div class="right_side_first_row" >
											<div class="title_set" style = "margin-top:13px; width :210px  ">
												<span class="mark_for_type_hotel" >旅</span> <i
													id="named_of_title" >${trip1.get(tripVo1).get(0)} </i> 
												
											</div>

											<div class="comment_set" >
												<div class="comment_message">
													<a href="#"> <i class="fa-solid fa-message"></i>
													</a>
												</div>
												<div class="count_star">
													<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
													</a>
												</div>
											</div>
										</div>
										<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
										<div class="price_set">
											<i class="howmuch">${tripVo1.totalPrice}</i>
										</div>
										<div class="pay_btn" style = "margin-top:20px"> 
				                            <button class="b list">詳細商品資訊</button>


										
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="TripCheckOut">
												<input type="hidden" name="TripOrdId"  value="${tripVo1.tripOrdId}">
												<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
											</form>
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="removeTripOrder">
												<input type="hidden" name="TripOrdId"  value="${tripVo1.tripOrdId}">
												<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

						<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo2.roomId}">
						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style = "margin-top:13px; width :210px  ">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room2.get(roomVo2).get(0)}</i>
									
								</div>
<!-- 					</form> -->
								<div class="comment_set"  >
									<div class="comment_message" style = "position:absolute;right: 100px;" >
										<a href="#"  > <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
									<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
										</a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo2.totalPrice}</i>
							</div>
							<div class="pay_btn" style = "margin-top:20px"> 

							
									<button class="b infos">詳細商品資訊</button>

								
							
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="hotelcheckOut">
										<input type="hidden" name="roomOrdId" value="${roomVo2.roomOrdId}">
										<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
									</form>
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="removeHotelOrder">
										<input type="hidden" name="roomOrdId" value="${roomVo2.roomOrdId}">
										<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

									<img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo2.tripId}">

									</div>
									<div class="plan_tab_1_right">
										<div class="right_side_first_row" >
											<div class="title_set" style = "margin-top:13px; width :210px  ">
												<span class="mark_for_type_hotel" >旅</span> <i
													id="named_of_title" >${trip2.get(tripVo2).get(0)} </i> 
												
											</div>

											<div class="comment_set" >
												<div class="comment_message">
													<a href="#"> <i class="fa-solid fa-message"></i>
													</a>
												</div>
												<div class="count_star">
													<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
													</a>
												</div>
											</div>
										</div>
										<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
										<div class="price_set">
											<i class="howmuch">${tripVo2.totalPrice}</i>
										</div>
										<div class="pay_btn" style = "margin-top:20px"> 
				                            <button class="b list">詳細商品資訊</button>


										
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="TripCheckOut">
												<input type="hidden" name="TripOrdId"  value="${tripVo2.tripOrdId}">
												<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
											</form>
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="removeTripOrder">
												<input type="hidden" name="TripOrdId"  value="${tripVo2.tripOrdId}">
												<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

					<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo3.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style = "margin-top:13px; width :210px  ">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room3.get(roomVo3).get(0)}</i>
									
								</div>
<!-- 					</form> -->
								<div class="comment_set"  >
									<div class="comment_message" style = "position:absolute;right: 100px;" >
										<a href="#"  > <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
									<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
										</a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo3.totalPrice}</i>
							</div>
							<div class="pay_btn" style = "margin-top:20px"> 

							
									<button class="b infos">詳細商品資訊</button>

								
							
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="hotelcheckOut">
										<input type="hidden" name="roomOrdId" value="${roomVo3.roomOrdId}">
										<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
									</form>
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="removeHotelOrder">
										<input type="hidden" name="roomOrdId" value="${roomVo3.roomOrdId}">
										<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

								<img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo3.tripId}">

									</div>
									<div class="plan_tab_1_right">
										<div class="right_side_first_row" >
											<div class="title_set" style = "margin-top:13px; width :210px  ">
												<span class="mark_for_type_hotel" >旅</span> <i
													id="named_of_title" >${trip3.get(tripVo3).get(0)} </i> 
												
											</div>

											<div class="comment_set" >
												<div class="comment_message">
													<a href="#"> <i class="fa-solid fa-message"></i>
													</a>
												</div>
												<div class="count_star">
													<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
													</a>
												</div>
											</div>
										</div>
										<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
										<div class="price_set">
											<i class="howmuch">${tripVo3.totalPrice}</i>
										</div>
										<div class="pay_btn" style = "margin-top:20px"> 
				                            <button class="b list">詳細商品資訊</button>


										
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="TripCheckOut">
												<input type="hidden" name="TripOrdId"  value="${tripVo3.tripOrdId}">
												<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
											</form>
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="removeTripOrder">
												<input type="hidden" name="TripOrdId"  value="${tripVo3.tripOrdId}">
												<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

							<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo4.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style = "margin-top:13px; width :210px  ">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room4.get(roomVo4).get(0)}</i>
									
								</div>
<!-- 					</form> -->
								<div class="comment_set"  >
									<div class="comment_message" style = "position:absolute;right: 100px;" >
										<a href="#"  > <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
									<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
										</a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo4.totalPrice}</i>
							</div>
							<div class="pay_btn" style = "margin-top:20px"> 

							
									<button class="b infos">詳細商品資訊</button>

								
							
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="hotelcheckOut">
										<input type="hidden" name="roomOrdId" value="${roomVo4.roomOrdId}">
										<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
									</form>
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="removeHotelOrder">
										<input type="hidden" name="roomOrdId" value="${roomVo4.roomOrdId}">
										<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

								<img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo4.tripId}">

									</div>
									<div class="plan_tab_1_right">
										<div class="right_side_first_row" >
											<div class="title_set" style = "margin-top:13px; width :210px  ">
												<span class="mark_for_type_hotel" >旅</span> <i
													id="named_of_title" >${trip4.get(tripVo4).get(0)} </i> 
												
											</div>

											<div class="comment_set" >
												<div class="comment_message">
													<a href="#"> <i class="fa-solid fa-message"></i>
													</a>
												</div>
												<div class="count_star">
													<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
													</a>
												</div>
											</div>
										</div>
										<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
										<div class="price_set">
											<i class="howmuch">${tripVo4.totalPrice}</i>
										</div>
										<div class="pay_btn" style = "margin-top:20px"> 
				                            <button class="b list">詳細商品資訊</button>


										
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="TripCheckOut">
												<input type="hidden" name="TripOrdId"  value="${tripVo4.tripOrdId}">
												<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
											</form>
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="removeTripOrder">
												<input type="hidden" name="TripOrdId"  value="${tripVo4.tripOrdId}">
												<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
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

							<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${roomVo5.roomId}">

						</div>
						<div class="plan_tab_1_right">
							<div class="right_side_first_row">
								<div class="title_set" style = "margin-top:13px; width :210px  ">
									<span class="mark_for_type_hotel">飯</span> <i
										id="named_of_title">${room5.get(roomVo5).get(0)}</i>
									
								</div>
<!-- 					</form> -->
								<div class="comment_set"  >
									<div class="comment_message" style = "position:absolute;right: 100px;" >
										<a href="#"  > <i class="fa-solid fa-message"></i>
										</a>
									</div>
									<div class="count_star">
									<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
										</a>
									</div>
								</div>
							</div>
							<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
							<div class="price_set">
								<i class="howmuch">${roomVo5.totalPrice}</i>
							</div>
							<div class="pay_btn" style = "margin-top:20px"> 

							
									<button class="b infos">詳細商品資訊</button>

								
							
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="hotelcheckOut">
										<input type="hidden" name="roomOrdId" value="${roomVo5.roomOrdId}">
										<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
									</form>
									<form
										action="${pageContext.request.contextPath}/shopping_hotelServlet"
										method="post">
										<input type="hidden" name="action" value="removeHotelOrder">
										<input type="hidden" name="roomOrdId" value="${roomVo5.roomOrdId}">
										<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
									</form>
								
							</div>
						</div>


					</div>


				</c:forEach>

					


<!-- 				==============裡面的list=============== -->
									<c:forEach var="tripVo5" items="${trip5.keySet()}">
			
								<div class="plan_tab_1 list">

									<div class="plan_tab_1_left">

										<img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${tripVo5.tripId}">

									</div>
									<div class="plan_tab_1_right">
										<div class="right_side_first_row" >
											<div class="title_set" style = "margin-top:13px; width :210px  ">
												<span class="mark_for_type_hotel" >旅</span> <i
													id="named_of_title" >${trip5.get(tripVo5).get(0)} </i> 
												
											</div>

											<div class="comment_set" >
												<div class="comment_message">
													<a href="#"> <i class="fa-solid fa-message"></i>
													</a>
												</div>
												<div class="count_star">
													<a href="#"><i class="fa-solid fa-star" style =" width:85px;">8.7</i>
													</a>
												</div>
											</div>
										</div>
										<span class="book_price">價格(含稅)</span> <i class="howmuch_nt">TWD</i>
										<div class="price_set">
											<i class="howmuch">${tripVo5.totalPrice}</i>
										</div>
										<div class="pay_btn" style = "margin-top:20px"> 
				                            <button class="b list">詳細商品資訊</button>


										
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="TripCheckOut">
												<input type="hidden" name="TripOrdId"  value="${tripVo5.tripOrdId}">
												<button class="b pay" type="submit" style = "width:170px">前往付款作業</button>
											</form>
											<form action="${pageContext.request.contextPath}/shopping_tripServlet" method="post">
												<input type="hidden" name="action" value="removeTripOrder">
												<input type="hidden" name="TripOrdId"  value="${tripVo5.tripOrdId}">
												<button class="b remove" type="submit" style = "width:170px">移除此商品</button>
											</form>
										
									</div>
								</div>


							</div>

					</c:forEach>
					</div>
				<!--    =============================foreachForList(trip)======================================              -->

			</div>

			<!-- =======================main_content===================== -->



<script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/chu_js/shopping.js"></script>
	


		
</body>

</html>