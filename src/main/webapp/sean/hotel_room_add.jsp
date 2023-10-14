<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
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
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/sean_css/comp_product_add.css">
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
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

button.left_btn:hover {
	color: #ffbf1c;
}

.all-info {
	display: flex;
	flex-wrap: wrap;
}

.all-info>* {
	margin-right: 5px;
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

.room {
	display: flex;
}

.room-opt {
	display: flex;
	flex-basis: 20%;
	flex-direction: column;
}

.room-opt>* {
	margin-top: 10px;
	margin-bottom: 10px;
}

.btns {
	position: relative;
	left: 50%;
	transform: translate(-50%);
	text-align: center;
}

.btns>button {
	margin-left: 20px;
	margin-right: 20px;
}

.stop {
	overflow: hidden;
}

.on {
	display: block !important;
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
				<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
					class="left_btn" style="color: #FCC416"> <i class="fa-solid fa-hotel"
					style="color: #000000;"></i> 我的房間
				</a>
			</div>
			<div class="mem-data">
				<a href="" class="left_btn"> <i class="fa-regular fa-comment"
					style="color: #000000;"></i> 匿名評論
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<%Room room = (Room) request.getAttribute("room");%>
				<%! final BigDecimal commissionPercent = new BigDecimal("5");%>
				<%! BigDecimal profit = new BigDecimal("0");%>
				<%String profitS = "0";%>
				<%
				if (room != null) {
					MathContext mx = new MathContext(34, RoundingMode.HALF_UP);
					profit = ((commissionPercent.divide(new BigDecimal("100")).add(new BigDecimal("-1"))).abs())
					.multiply(room.getPrice()).round(mx).stripTrailingZeros();
					profitS = profit.toPlainString();
				}
				%>

				<form class="one-room" method="POST"
					action="${pageContext.request.contextPath}/sean/RoomServlet"
					enctype="multipart/form-data">
					<h2 id="type">客房</h2>
					<hr>
					<div class="all-info">
						<div style="flex-basis: 25%;">
							<label for="name">房間名稱</label> <br> <input name="roomName"
								id="name" type="text" required="required"
								value="${room.roomName}">
						</div>
						<div style="flex-basis: 20%;">
							<label for="room-type">選擇房型</label> <br> <select
								name="roomType" id="room-type" style="width: 90px;"
								required="required">
								<option value="1" ${(room.roomType == 1) ? "selected" : ""}>單人房</option>
								<option value="2" ${(room.roomType == 2) ? "selected" : ""}>雙人房</option>
								<option value="3" ${(room.roomType == 3) ? "selected" : ""}>三人房</option>
								<option value="4" ${(room.roomType == 4) ? "selected" : ""}>四人房</option>
							</select>
						</div>
						<div style="flex-basis: 20%;">
							<label for="bed-num">床位數</label> <br> <input name="bedNum"
								id="bed-num" type="number" style="width: 70px;"
								required="required" value="${room.beds}" min="1">
						</div>
						<div style="flex-basis: 15%;">
							<label for="default-num">預設每日房數</label> <br> <input
								name="default-num" id="defaultNum" type="text"
								style="width: 130px;" required="required" value="5" min="1">
						</div>
					</div>
					<hr>
					<div>
						<h2>客房設施</h2>
						<div class="room">
							<div class="room-opt">
								<span> <input type="checkbox" id="tissue" name="detail"
									value="tissue" ${(room.tissue== 1) ? "checked" : ""}> <label
									for="tissue">衛生紙</label>
								</span> <span> <input type="checkbox" id="freetoiletries"
									name="detail" value="freetoiletries"
									${(room.freetoiletries == 1) ? "checked" : ""}> <label
									for="freetoiletries">免費盥洗用品</label>
								</span> <span> <input type="checkbox" id="electric_kettle"
									name="detail" value="electric_kettle"
									${(room.electricKettle == 1) ? "checked" : ""}> <label
									for="electric_kettle">熱水壺</label>
								</span>
							</div>
							<div class="room-opt">
								<span> <input type="checkbox" id="shower" name="detail"
									value="shower" ${(room.shower == 1) ? "checked" : ""}>
									<label for="shower">淋浴間</label>
								</span> <span> <input type="checkbox" id="flushseat"
									name="detail" value="flushseat"
									${(room.flushseat == 1) ? "checked" : ""}> <label
									for="flushseat">沖洗座</label>
								</span>
							</div>
							<div class="room-opt">
								<span> <input type="checkbox" id="bathroom" name="detail"
									value="bathroom" ${(room.bathroom == 1) ? "checked" : ""}>
									<label for="bathroom">廁所</label>

								</span> <span> <input type="checkbox" id="slippers"
									name="detail" value="slippers"
									${(room.slippers == 1) ? "checked" : ""}> <label
									for="slippers">拖鞋</label>
								</span>
							</div>
							<div class="room-opt">
								<span> <input type="checkbox" id="dryer" name="detail"
									value="dryer" ${(room.dryer == 1) ? "checked" : ""}> <label
									for="dryer">吹風機</label>
								</span> <span> <input type="checkbox" id="bathrobe"
									name="detail" value="bathrobe"
									${(room.bathrobe == 1) ? "checked" : ""}> <label
									for="bathrobe">浴袍</label>
								</span>
							</div>
							<div class="room-opt">
								<span> <input type="checkbox" id="tub" name="detail"
									value="tub" ${(room.tub == 1) ? "checked" : ""}> <label
									for="tub">浴缸</label>
								</span> <span> <input type="checkbox" id="spatub" name="detail"
									value="spatub" ${(room.spatub == 1) ? "checked" : ""}>
									<label for="spatub">SPA浴缸</label>
								</span>
							</div>
						</div>
						<hr>
						<h2>客房介紹</h2>
						<textarea name="intro" id="" cols="30" rows="10"
							placeholder="請輸入客房簡介最少100字" style="width: 100%;"
							required="required">${room.intro}</textarea>
					</div>
					<br>
					<div>客房每晚價格</div>
					<span>TWD</span><input name="price" id="money" type="text"
						value="${room.price.stripTrailingZeros().toPlainString()}"
						required="required">
					<div>
						<span id="percent"><%=commissionPercent%></span><span>% 佣金</span>
						<br> <span>TWD </span><span id="profit"><%=profitS%></span><span>您的收益(四捨五入之結果)</span>
					</div>
					<hr>
					<c:if test="${room.mainPhoto == null}">
						<input type="hidden" name="action" value="addRoom">
						<input type="hidden" name="id" value="${room.roomId}">
						<h2>上傳飯店照片</h2>
						<p>上傳1張代表貴飯店的照片，將顯示在搜尋頁面</p>
						<input type="file" class="in" id="pic_file" name="mainPhoto"
							accept="image/*">
						<div class="drag"></div>

					</c:if>
					<c:if test="${room.mainPhoto != null}">
						<input type="hidden" name="action" value="updateRoom">
						<input type="hidden" name="id" value="${room.roomId}">
						<h2>上傳飯店照片</h2>
						<p>替換照片</p>
						<input type="file" class="in" id="pic_file" name="mainPhoto"
							accept="image/*">
						<div class="drag">
							<img
								src="RoomPhotoHibernateServlet?room_id=${room.roomId}"
								style="max-width: 100%">
						</div>
					</c:if>
					<div class="btns">
						<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
							type="button" class="other-btn">取消</a>
						<button type="button" id="add" class="other-btn">新增</button>
					</div>
					<button type="submit" id="add-data" style="display: none;"></button>
				</form>
			</div>
		</main>
		<div class="alert_bg">
			<div class="alert">
				<div>
					確定更新嗎? <br>
					<button type="button" id="yes" class="other-btn">Yes</button>
					<button type="button" id="no" class="other-btn">No</button>
				</div>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com4product.js"></script>
	<script type="text/javascript">
		
	</script>
</body>

</html>