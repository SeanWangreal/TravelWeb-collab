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


session.setAttribute("cus_id",1);
session.setAttribute("room_id",1);
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


		<!--    =============================foreachForList======================================              -->


		<div class="tab_contents">
		
		

		
			<div class="tab plan1 -on" id="tab_plan1">

	  <form  method = "post"  action="${pageContext.request.contextPath}/shopping_hotelServlet">
				<!-- ==============裡面的list=============== -->
				<!-- <div class="no-items n1">暫無商品</div> -->

				<div class="plan_tab_1 list">

					<div class="plan_tab_1_left">

						<img src="4621.png"> 
						
						
					</div>
					
					
					
					<div class="plan_tab_1_right">
						<div class="right_side_first_row">
							<div class="title_set">
								<span class="mark_for_type_hotel">飯</span>
								<input type = "hidden"  name ="compType" value ="飯">
								
								<i id="named_of_title">統神大戲院123</i> 
									<input type = "hidden"  name ="compName" value ="統神大戲院123">
									
								<div>
									規劃ID: <i style="color: darkorange;">1232142141</i>
									<input type = "hidden"  name ="planId" value ="1232142141">
								</div>
							</div>

							<div class="comment_set">
								<div class="comment_message">
									<a href="#"> <i class="fa-solid fa-message"></i>
									</a>
								</div>
								<div class="count_star">
									<a href="#"> <i class="fa-solid fa-star">8.7</i>
									<input type = "hidden"  name ="score" value ="8.7">
									</a>
								</div>
							</div>
						</div>
						<span class="book_price">價格(未含稅)</span> <i class="howmuch_nt">TWD</i>
						<div class="price_set">
							<i class="howmuch">87777</i>
							<input type = "hidden"  name ="price" value ="87777">
						</div>
						<div class="pay_btn">
                            <button class="b list">查看行程細況</button>
                            <button class="b infos">訂單資訊</button> 
                            
						<div class="pay_or_remove">
						
							
								<input type="hidden" name="actionForPay" value="pay">
									<select size="1" name="amount">
									 
 								         
														
													        <option value="1">
													      <input type="hidden" name="cart_id" value="1">


							       </select>
							       <form action="shopping_hotelServlet" method="post">
									<button  href="#" class="b pay"  style = "border: 1px solid blcak ; background-color: blue; color:black;">加入購物車</button>
									<input type = "hidden"  name ="action" value ="room_goShopping">
								</form>
							
							
							
							
							<form action="shopping_hotelServlet" method="post">
								<input type="hidden" name="action" value="remove">
								<button class="b remove" type="submit">移除訂單</button>
							</form>
						</div>
					</div>
				</div>
							
					
			</div>
			</form>
		
			<!--    =============================foreachForList======================================              -->







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

 <script>
 
 
 let a = ${successPutShopping};
 $("button.b pay").on("click",function(){
	 alert(a)
 })
 
 
 
 </script>

</body>

</html>