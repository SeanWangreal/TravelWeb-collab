<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>


<%-- <% --%>
<%-- <%Vector<BOOK> buylist = (Vector<BOOK>) session.getAttribute("shoppingcart");%> --%>
<%-- <%if (buylist != null && (buylist.size() > 0)) {%> --%>
<!-- %> -->


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
	<script src="../vendors/jquery/jquery-3.7.1.min.js"></script>
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
		<div class="selectColumn">
			<li style="list-style: none">
				<FORM METHOD="post" ACTION="emp.do">
					<b>請輸入欲查詢的訂單編號:</b> <input type="text" name="Scene"> 
						<input type="hidden" name="action" value="getOne_For_Display"> 
						<input type="submit" value="送出">
				</FORM>
			</li>
		</div>


		<div class="tab_list_block">
			<ul class="tab_list">
				<li><a href="#" data-target="plan1" class="tab -on">飯店一</a></li>
				<li><a href="#" data-target="plan2" class="tab ">飯店二</a></li>
				<li><a href="#" data-target="plan3" class="tab ">飯店三</a></li>
				<li><a href="#" data-target="plan4" class="tab ">飯店四</a></li>
				<li><a href="#" data-target="plan5" class="tab ">飯店五</a></li>
			</ul>
		</div>


		<!--    =============================foreachForList(hotel)======================================              -->


		<div class="tab_contents">
		
		

		
			<div class="tab plan1 -on" id="tab_plan1">
<form  method = "post"  action="${pageContext.request.contextPath}/shopping_hotelServlet">
				<!-- ==============裡面的list=============== -->

				<div class="plan_tab_1 list">

					<div class="plan_tab_1_left">

						<img src="4621.png"> 
<!-- 試試看用room_ord  找到fk通往有照片的pk -->
					</div>
					<div class="plan_tab_1_right">
						<div class="right_side_first_row">
							<div class="title_set">
								<span class="mark_for_type_hotel">飯</span> <i
									id="named_of_title">統神大戲院</i> 
								<div>
								訂單ID: <i style="color: darkorange;">1</i>
<!-- 									訂單ID: <i style="color: darkorange;">{ShoppingCartTrip.roomId}</i> -->
								</div>
							</div>

							<div class="comment_set">
								<div class="comment_message">
									<a href="#"> <i class="fa-solid fa-message"></i>
									</a>
								</div>
								<div class="count_star">
									<a href="#"> <i class="fa-solid fa-star">1</i>
									</a>
								</div>
							</div>
						</div>
						<span class="book_price">價格(未含稅)</span> <i class="howmuch_nt">TWD</i>
						<div class="price_set">
							<i class="howmuch">1</i>
						</div>
						<div class="pay_btn">
                            <button class="b list">查看行程細況</button>
                             
                         <div style = "display :flex">
                            <button class="b infos">訂單資訊</button> 
                           		<form action="/shopping_hotelServlet" method="post">
									    <input type="hidden" name="action" value = "update">
									    <button  class="b amount" type="submit">更新數量
									    </button>
								</form>
                        </div>
						<div class="pay_or_remove">
							<form action="${pageContext.request.contextPath}/shopping_hotelServlet" method="post">
								<input type="hidden" name="action" value="hotelcheckOut">
								<input type="hidden" name="roomOrdId"  value ="1">
								<button class="b pay" type="submit">前往付款</button>
							</form>
							<form action="${pageContext.request.contextPath}/shopping_hotelServlet"method="post">
								<input type="hidden" name="action" value="removeHotelOrder">
								<input type="hidden" name="roomOrdId"  value ="40">
								<button class="b remove" type="submit">移除訂單</button>
							</form>
						</div>
					</div>
				</div>
						
					
			</div>
			

		</form>
	<!--    =============================foreachForList(hotel)======================================              -->
	<!--    =============================foreachForList(trip)======================================              -->


		
		

		
<!-- 			<div class="tab plan1 -on" id="tab_plan1"> -->

				<!-- ==============裡面的list=============== -->
<!-- 				<div class="no-items n1">暫無商品</div> -->

<!-- 				<div class="plan_tab_1 list"> -->

<!-- 					<div class="plan_tab_1_left"> -->

<!-- 						<img src="4621.png">  -->
<!-- <!-- 試試看用room_ord  找到fk通往有照片的pk --> -->
<!-- 					</div> -->
<!-- 					<div class="plan_tab_1_right"> -->
<!-- 						<div class="right_side_first_row"> -->
<!-- 							<div class="title_set"> -->
<!-- 								<span class="mark_for_type_hotel">行</span> <i -->
<!-- 									id="named_of_title">統神大戲院</i>  -->
<!-- 								<div> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<div class="comment_set"> -->
<!-- 								<div class="comment_message"> -->
<!-- 									<a href="#"> <i class="fa-solid fa-message"></i> -->
<!-- 									</a> -->
<!-- 								</div> -->
<!-- 								<div class="count_star"> -->
<!-- 									<a href="#"> <i class="fa-solid fa-star">1</i> -->
<!-- 									</a> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<span class="book_price">價格(未含稅)</span> <i class="howmuch_nt">TWD</i> -->
<!-- 						<div class="price_set"> -->
<!-- 							<i class="howmuch">1</i> -->
<!-- 						</div> -->
<!-- 						<div class="pay_btn"> -->
<!--                             <button class="b list">查看行程細況</button> -->
                             
<!--                          <div style = "display :flex"> -->
<!--                             <button class="b infos">訂單資訊</button>  -->
<!--                            		<form action="/shopping_hotelServlet" method="post"> -->
<!-- 									    <input type="hidden" name="action" value = "update"> -->
<!-- 									    <button  class="b amount" type="submit">更新數量 -->
<!-- 									    </button> -->
<!-- 								</form> -->
<!--                         </div> -->
<!-- 						<div class="pay_or_remove"> -->
<!-- 							<form action="/shopping_hotelServlet" method="post"> -->
<!-- 								<input type="hidden" name="action" value="checkOut"> -->
<!-- 								<button class="b pay" type="submit">前往付款</button> -->
<!-- 							</form> -->
<!-- 							<form action="/shopping_hotelServlet" method="post"> -->
<!-- 								<input type="hidden" name="action" value="removeTripOrder"> -->

<!-- 								<button class="b remove" type="submit">移除訂單</button> -->
<!-- 							</form> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
						
					
<!-- 			</div> -->
			

		
			<!--    =============================foreachForList(trip)======================================              -->






		</div>
			<div class="tab plan2">
				<div class="no-items">暫無商品</div>

			</div>
			<div class="tab plan3 ">
				<div class="no-items">暫無商品</div>

			</div>
			<div class="tab plan4">
				<div class="no-items">暫無商品</div>

			</div>
			<div class="tab plan5">
				<div class="no-items">暫無商品</div>

			</div>
		</div>
	</div>
	
	<!-- =======================main_content===================== -->






	<script src="../static/chu_js/shopping.js"></script>





</body>

</html>