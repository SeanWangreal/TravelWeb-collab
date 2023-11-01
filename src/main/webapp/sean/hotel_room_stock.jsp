<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.room_photo.model.*"%>
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/sean_css/comp_product.css">
<style>
.left-down {
	padding: 5px;
	width: 265px;
	position: absolute;
	top: 270px;
	left: 60px;
	background-color: white;
	z-index: 1;
	box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

.left-down>* {
	margin-top: 10px;
	font-family: "粉圓";
}

.main-content-info {
	font-size: 0px;
}

.all-date {
	padding: 5px 0px 5px 5px;
	width: 13%;
	margin-right: 1.25%;
	margin-bottom: 1.3%;
	height: 100px;
	display: inline-block;
	background-color: white;
	box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
	width: 13%;
	display: inline-block;
	height: 100px;
}

div.all-date>* {
	font-size: 16px;
}

.searching {
	display: flex;
	width: 100%;
}

.searching>button {
	width: 25%;
	border-radius: 5px;
	border: none;
	color: white;
	background-color: #4D504F;
}

.search {
	width: 70%;
	margin-right: 5%;
	display: inline-block;
}

.stock {
	width: 50%;
	border: none;
	font-size: 16px;
	background-color: #F3F3F3;
}

.date {
	font-size: 16px;
}

.controllers {
	font-size: 0px;
}

.controller:nth-child(2n) {
	margin-right: 0%;
}

.controller {
	text-align: center; display : inline-block;
	font-size: 20px;
	margin-right: 10%;
	width: 45%;
	border: none;
	border-radius: 5px;
	color: white;
	background-color: #4D504F;
	display: inline-block;
}

.alert {
	font-size: 20px;
}

.nothing {
	display: none;
}
</style>
</head>
<body>
	<script
		src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
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
				<i class="fa-solid fa-store"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>

			</button>
			<button type="button" class="head_btn">
				<a class="profile"
					href="${pageContext.request.contextPath}/ken/com_mem.jsp">
					<i class="fa-solid fa-user icon"
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
				<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
					class="left_btn" style="color: #FCC416"> <i
					class="fa-solid fa-hotel" style="color: #000000;"></i> 我的房間
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/hotel_com_ord.jsp" class="left_btn" style="color: #000000"> <i
					class="fa-solid fa-file-invoice" style="color: black;"></i> 訂單資訊
				</a>
			</div>
			<div class="mem-data">
				<a href="" class="left_btn"> <i class="fa-regular fa-comment"
					style="color: #000000;"></i> 匿名評論
				</a>
			</div>
		</aside>
		<aside class="left-down">
			<form action="" class="searching">
				<input type="date" class="search">
				<button type="submit">查詢</button>
			</form>
			<div class="controllers">
				<button type="button" class="controller" id="lessDay">減少一天</button>
				<button type="button" class="controller" id="addDay">新增一天</button>
			</div>
			<div class="controllers">
				<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
					class="controller">取消</a>
				<button type="button" class="controller" id="change">更新</button>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<form
					action="${pageContext.request.contextPath}/sean/RoomStockServlet"
					method="post" id="dayForm">
					<input type="hidden" name="action" value="changeStock"> <input
						type="hidden" name="roomId" value="${roomId}">
					<button type="submit" id="go" style="display: none;"></button>
					<div class="alert_bg">
						<div class="alert">
							<div>
								確定更新嗎? <br>
								<button type="submit" class="other-btn yes">Yes</button>
								<button type="button" class="other-btn no">No</button>
							</div>
						</div>
					</div>
					<div id="ALL">
					<c:forEach var="stock" items="${list}">
						<div class="all-date">
							<label class="date">${stock.stockDate}</label> <input
								type="hidden" class="id" name="oldStock"
								value="${stock.roomStockId}"><br> <label
								class="week"></label><br> <label class="">庫存</label> <input
								class="stock" type="number" min="0" name="stockNum" value="${stock.stock}"><span
								class="">間</span>
						</div>
					</c:forEach>					
					</div>
				</form>
			</div>
		</main>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com.js"></script>
	<script>
		var day_list = ['日', '一', '二', '三', '四', '五', '六'];
		function addWeek(){
			let alldate = document.querySelectorAll(".all-date");
			for (var i=0; i<alldate.length; i++){
				let day = $(alldate[i]).find(".date").text();
				let now = new Date(day);
				let weekday = now.getDay();
				$(alldate[i]).find(".week").text("星期"+day_list[weekday]);
			}
		}

		$(function(){
			addWeek();
			$("#addDay").on("click",function(){
				let alldate = document.querySelectorAll(".all-date");
				if ($(".all-date").length != 0){
					let formatDay = $(".all-date").eq(-1).find(".date").text();
					let now = new Date(formatDay);
	 				now.setDate(now.getDate(formatDay)+1);
					let day = now.getDate();
					let weekday = now.getDay();
					let month = now.getMonth() +1;
					let year = now.getFullYear();
					now =year+'-'+ month+'-'+day;
					let newDay = 
						`<div class="all-date">
							<label class="date">`+now+`</label><br>
							<label class="">`+"星期"+day_list[weekday]+`</label><br>
							<input  type="hidden" name="newStockDate"  value=`
								+now+
								`>
							<label class="">庫存</label>
							<input class="stock" type="number" name="newStock" value="0" min="0"><span class="">間</span>
						 </div>`;
					$("#ALL").append(newDay);					
				} else{
					let now = new Date();
					let day = now.getDate();
					let weekday = now.getDay();
					let month = now.getMonth() +1;
					let year = now.getFullYear();
					now = year+'-'+ month+'-'+day;
					let newDay = 
						`<div class="all-date">
							<label class="date">`+now+`</label><br>
							<label class="">`+"星期"+day_list[weekday]+
							`</label><br><input type="hidden" name="newStockDate" value=`
							+now+
							`><label class="">庫存</label>
							<input class="stock" type="number" name="newStock" value="0"  min="0"><span>間</span>
						 </div>`;
					console.log(newDay);
					$("#ALL").append(newDay);		
				}
			})
			$("#lessDay").on("click",function(){
				if ($(".all-date").eq(-1).find(".stock").attr("name") === "newStock") {
					$(".all-date").eq(-1).remove();
				} else {
					$(".all-date").eq(-1).find(".id").attr("name","deleteStock");
					$(".all-date").eq(-1).find(".stock").attr("name","abandon");
					$(".all-date").eq(-1).find(".stock").removeClass("stock");
					$(".all-date").eq(-1).addClass("nothing").removeClass("all-date");					
				}
			})
			$("#change").on("click", function() {
				var pass = true;
				for  (var i =0 ; i<$("input.stock").length;i++){
					if ($("input.stock").eq(i).val()  ==  ""){
						pass = false;
						alert("請填入庫存");
						break;
					}
					
				}
				if (pass){
					$("body").css("overflow", "hidden");
					let alert_bg = $(".alert_bg").first();
					alert_bg.addClass("on");
					let alert = $(".alert").first();
					alert.addClass("on");
					$(".no").on("click", function() {
						$("body").css("overflow", "auto");
						alert_bg.removeClass("on");
						alert.removeClass("on");
				})
					
				}
			})
			
		});
	</script>

</body>
</html>