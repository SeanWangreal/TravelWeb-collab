<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>
<%@ page import="com.tha103.gogoyu.trip_photo.model.*"%>
<%@ page import="com.tha103.gogoyu.itinerary.model.*"%>
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
button.left_btn:hover {
	color: #ffbf1c;
}

.all-info {
	display: flex;
	flex-wrap: wrap;
	align-items: end;
}

.all-info>* {
	padding-right: 5px;
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

.product-opt {
	display: flex;
	flex-basis: 20%;
	flex-direction: column;
}

.product-opt>* {
	margin-top: 10px;
	margin-bottom: 10px;
}

#add_btn {
	border: none;
	border-radius: 5px;
	color: white;
	background-color: #4D504F;
}

.warning {
	color: red;
}

datalist {
	/*   position: absolute; */
	/*   background-color: white; */
	/*   border: 1px solid blue; */
	/*   border-radius: 0 0 5px 5px; */
	/*   border-top: none; */
	/*   font-family: sans-serif; */
	/*   width: 350px; */
	/*   padding: 5px; */
	/*   max-height: 10rem; */
	/*   overflow-y: auto */
	
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
				<a class="profile"
					href="${pageContext.request.contextPath}/sean/select_page.jsp">
					<i class="fa-solid fa-user"
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
				<a
					href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp"
					class="left_btn"> <i class="fa-solid fa-ticket"
					style="color: #000000;"></i> 我的行程
				</a>
			</div>
			<div class="mem-data">
				<a href="" class="left_btn"> <i class="fa-solid fa-file-invoice"
					style="color: black;"></i> 匿名評論
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<%
				Trip trip = (Trip) request.getAttribute("trip");
				Set<Trip_photo> tripPhoto = (Set<Trip_photo>) request.getAttribute("tripPhoto");
				%>
				<%!final BigDecimal commissionPercent = new BigDecimal("5");%>
				<%!BigDecimal profit = new BigDecimal("0");%>
				<%
				String profitS = "0";
				%>
				<%
				if (trip != null) {
					MathContext mx = new MathContext(34, RoundingMode.HALF_UP);
					profit = ((commissionPercent.divide(new BigDecimal("100")).add(new BigDecimal("-1"))).abs())
					.multiply(trip.getPrice()).round(mx).stripTrailingZeros();
					profitS = profit.toPlainString();
				}
				%>

				<form class="one-product" method="POST"
					action="${pageContext.request.contextPath}/sean/TripServlet"
					enctype="multipart/form-data">
					<h2 id="type">行程</h2>
					<hr>
					<div class="all-info">
						<div style="flex-basis: 15%;">
							<label for="name">行程名稱</label> <br> <input name="tripName"
								id="name" type="text" required="required"
								value="${trip.tripName}">
						</div>
						<div style="flex-basis: 15%">
							<label style="width: 90px;" for="bed-num">套票人數 <input
								type="number" id="bed-num" name="people" value="${trip.people}"
								min="1" style="width: 100%">
							</label>
						</div>
						<div style="flex-basis: 15%">
							<label style="width: 110px;" for="default-num">套票庫存 <input
								type="number" style="width: 100%" id="default-num" name="amount"
								value="${trip.amount}" min="1">
							</label>
						</div>
						<div style="flex-basis: 15%; flex-grow: 1;">
							<label for="">開始日期 <input type="date" style="width: 100%"
								name="startTime" value="${trip.startTime}" required="required">
							</label>
						</div>
						<div style="flex-basis: 15%; flex-grow: 1;">
							<label for="">結束日期 <input type="date"
								style="width: 100%;" name="endTime" value="${trip.endTime}"
								required="required">
							</label>
						</div>
					</div>
					<hr>
					<div>
						<h2>選擇地點</h2>
						<div class="room">
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Taipei_City"
									name="detail" value="taipeiCity"
									${(trip.taipeiCity == 1) ? "checked" : ""}> <label
									for="Taipei_City">台北市</label>
								</span> <span> <input class="where" type="checkbox" id="NewTaipei_City"
									name="detail" value="newtaipeiCity"
									${(trip.newtaipeiCity == 1) ? "checked" : ""}> <label
									for="NewTaipei_City">新北市</label>
								</span> <span> <input class="where" type="checkbox" id="Taoyuan_City"
									name="detail" value="taoyuanCity"
									${(trip.taoyuanCity == 1) ? "checked" : ""}> <label
									for="Taoyuan_City">桃園市</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Taichung_City"
									name="detail" value="taichungCity"
									${(trip.taichungCity == 1) ? "checked" : ""}> <label
									for="Taichung_City">台中市</label>
								</span> <span> <input class="where" type="checkbox" id="Tainan_City"
									name="detail" value="tainanCity"
									${(trip.tainanCity == 1) ? "checked" : ""}> <label
									for="Tainan_City">台南市</label>
								</span> <span> <input class="where" type="checkbox" id="Kaohsiung_City"
									name="detail" value="kaohsiungCity"
									${(trip.kaohsiungCity == 1) ? "checked" : ""}> <label
									for="Kaohsiung_City">高雄市</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Hsinchu_County"
									name="detail" value="hsinchuCounty"
									${(trip.hsinchuCounty == 1) ? "checked" : ""}> <label
									for="Hsinchu_County">新竹縣</label>
								</span> <span> <input class="where" type="checkbox" id="Miaoli_County"
									name="detail" value="miaoliCounty"
									${(trip.miaoliCounty == 1) ? "checked" : ""}> <label
									for="Miaoli_County">苗栗縣</label>
								</span> <span> <input class="where" type="checkbox" id="Changhua_County"
									name="detail" value="changhuaCounty"
									${(trip.changhuaCounty == 1) ? "checked" : ""}> <label
									for="Changhua_County">彰化縣</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Nantou_County"
									name="detail" value="nantouCounty"
									${(trip.nantouCounty == 1) ? "checked" : ""}> <label
									for="Nantou_County">南投縣</label>
								</span> <span> <input class="where" type="checkbox" id="Yunlin_County"
									name="detail" value="yunlinCounty"
									${(trip.yunlinCounty == 1) ? "checked" : ""}> <label
									for="Yunlin_County">雲林縣</label>
								</span> <span> <input class="where" type="checkbox" id="Chiayi_County"
									name="detail" value="chiayiCounty"
									${(trip.chiayiCounty == 1) ? "checked" : ""}> <label
									for="Chiayi_County">嘉義縣</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Pingtung_County"
									name="detail" value="pingtungCounty"
									${(trip.pingtungCounty == 1) ? "checked" : ""}> <label
									for="Pingtung_County">屏東縣</label>
								</span> <span> <input class="where" type="checkbox" id="Yilan_City"
									name="detail" value="yilanCity"
									${(trip.yilanCity == 1) ? "checked" : ""}> <label
									for="Yilan_City">宜蘭市</label>
								</span> <span> <input class="where" type="checkbox" id="Hualien_City"
									name="detail" value="hualienCity"
									${(trip.hualienCity == 1) ? "checked" : ""}> <label
									for="Hualien_City">花蓮市</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Taitung_County"
									name="detail" value="taitungCounty"
									${(trip.taitungCounty == 1) ? "checked" : ""}> <label
									for="Taitung_County">台東縣</label>
								</span> <span> <input class="where" type="checkbox" id="Kinmen_County"
									name="detail" value="kinmenCounty"
									${(trip.kinmenCounty == 1) ? "checked" : ""}> <label
									for="Kinmen_County">金門縣</label>
								</span> <span> <input class="where" type="checkbox" id="Lienchiang_County"
									name="detail" value="lienchiangCounty"
									${(trip.lienchiangCounty == 1) ? "checked" : ""}> <label
									for="Lienchiang_County">連江縣</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Keelung_City"
									name="detail" value="keelungCity"
									${(trip.keelungCity == 1) ? "checked" : ""}> <label
									for="Keelung_City">基隆市</label>
								</span> <span> <input class="where" type="checkbox" id="Hsinchu_City"
									name="detail" value="hsinchuCity"
									${(trip.hsinchuCity == 1) ? "checked" : ""}> <label
									for="Hsinchu_City">新竹市</label>
								</span> <span> <input class="where" type="checkbox" id="Chiayi_City"
									name="detail" value="chiayiCity"
									${(trip.chiayiCity == 1) ? "checked" : ""}> <label
									for="Chiayi_City">嘉義市</label>
								</span>
							</div>
							<div class="product-opt">
								<span> <input class="where" type="checkbox" id="Penghu_County"
									name="detail" value="penghuCounty"
									${(trip.penghuCounty == 1) ? "checked" : ""}> <label
									for="Penghu_County">澎湖縣</label>
								</span>
							</div>
						</div>
						<hr>
						<jsp:useBean id="sceneSvc" scope="page"
							class="com.tha103.gogoyu.scene.model.SceneServiceHibernate" />
						<datalist id="scene">
							<c:forEach var="scene" items="${sceneSvc.all}">
								<option class="data" data-value="${scene.sceneId}">${scene.sceneName}</option>
							</c:forEach>
						</datalist>
						<div id="first">
							<div class="all-info">
								<div style="flex-basis: 20%;">
									<label for="">景點</label><label class="warning"></label><br>
									<input type="hidden" class="sceneId" name="sceneId"
										value="${itineraryList[0].sceneId}"> <input
										list="scene" name="sceneName"
										value="${sceneSvc.findByPK(itineraryList[0].sceneId).sceneName}"
										type="text" class="select">
								</div>
								<div style="flex-basis: 20%;">
									<label for="">行程時間</label> <br> <input class="time"
										type="datetime-local" style="" name="beginTime" value="${itineraryList[0].beginTime}">
								</div>
								<div style="flex-basis: 20%;">
									<button type="button" id="add_btn">ADD TRIP</button>
								</div>
							</div>
							<c:forEach var="itinerary" items="${itineraryList}" begin="1">
								<div class="all-info">
									<div style="flex-basis: 20%;">
										<label for="">景點</label><label class="warning"></label><br>
										<input type="hidden" class="sceneId" name="sceneId"
											value="${itinerary.sceneId}"> <input list="scene"
											name="sceneName"
											value="${itinerary.sceneName}"
											type="text" class="select" name="scene">
									</div>
									<div style="flex-basis: 20%;">
										<label for="">行程時間</label> <br> <input class="time"
											type="datetime-local" style="width: 270px;" name="beginTime" value="${itinerary.beginTime}">
									</div>
									<div style="flex-basis: 20%;">
										<button type="button" class="delete other-btn">刪除</button>
									</div>
								</div>
							</c:forEach>
						</div>
						<hr>
						<h2>行程說明</h2>
						<textarea name="content" cols="30" rows="10"
							placeholder="請輸入行程簡介最少100字" style="width: 100%;"
							required="required">${trip.content}</textarea>
					</div>
					<br>
					<div>套票價格</div>
					<span>TWD</span><input name="price" id="money" type="text"
						value="${trip.price.stripTrailingZeros().toPlainString()}"
						required="required">
					<div>
						<span id="percent"><%=commissionPercent%></span><span>% 佣金</span>
						<br> <span>TWD </span><span id="profit"><%=profitS%></span><span>您的收益(四捨五入之結果)</span>
					</div>
					<hr>

					<c:if test="${trip == null}">
						<input type="hidden" name="action" value="addTrip">
					</c:if>
					<c:if test="${trip != null}">
						<input type="hidden" name="action" value="updateTrip">
					</c:if>

					<c:if test="${trip.mainPhoto == null}">
						<input type="hidden" name="tripId" value="${trip.tripId}">
						<h2>上傳行程活動照片</h2>
						<p>上傳1張代表貴行程的照片，將顯示在搜尋頁面</p>
						<input type="file" class="in" id="pic_file" name="mainPhoto"
							accept="image/*">
						<div class="drag"></div>
					</c:if>
					<c:if test="${trip.mainPhoto != null}">
						<input type="hidden" name="tripId" value="${trip.tripId}">
						<h2>上傳行程活動照片</h2>
						<p>替換照片</p>
						<input type="file" class="in" id="pic_file" name="mainPhoto"
							accept="image/*">
						<div class="drag">
							<img src="MainPhotoTripPrintServlet?tripId=${trip.tripId}"
								style="max-width: 100%">
						</div>
					</c:if>
					<c:if test="${tripPhoto != null}">
						<h2>上傳行程細節照片</h2>
						<p>更換行程細節照片</p>
						<input type="file" class="in" id="pic_files" name="photos"
							accept="image/*" multiple>
						<div class="multi-photo">
							<c:forEach var="pic" items="${tripPhoto}">
								<img class="imgs"
									src="TripPhotoPrintServlet?tripPhotoId=${pic.tripPhotoId}"
									style="width: 23%">
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${tripPhoto == null}">
						<h2>上傳行程細節照片</h2>
						<p>上傳至少3張行程細節照片</p>
						<input type="file" class="in" id="pic_files" name="photos"
							accept="image/*" multiple>
						<div class="multi-photo"></div>
					</c:if>
					<div class="btns">
						<a
							href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp"
							type="button" class="other-btn">取消</a>
						<button type="button" id="add" class="other-btn">${trip == null ? "新增":"更新"}</button>
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
	 $(document).ready(function(){
         var add_scene_btn = document.getElementById("add_btn");
         var first = document.getElementById("first");
         function binding(){
        	 var optionLength = $("#scene").find("option").length;
        	 var hiddenInput = $(this).closest("div").find(".sceneId");
        	 var warning = $(this).closest("div").find(".warning");
        	 var scene = $.trim($(this).val());
        	 $(this).val(scene);
        	 if (scene != "" ){
        	 var pass = inputSelect($(this).val(), optionLength, hiddenInput);
	        	 if (pass === false){
	        		 warning.text("  (請使用內建關鍵字)  ");
	        	 } else {
	        		 warning.text("");
	        	 }
        	 } else {
        		 warning.text("");
        	 }
         }
         $(".select").on("change", binding);
         function inputSelect(sceneName, optionLength, hiddenInput){
			for (var i =0; i< optionLength;i++){
				if (sceneName == $("#scene").find("option").eq(i).text()){
					hiddenInput.val($("#scene").find("option").eq(i).attr("data-value"));
					return true;
				}
			}
			return false;
         }
         function cancel(e) {
             e.target.parentNode.parentNode.remove();
         }
         function add_scene() {
             var html = `<div class="all-info">
                             <div style="flex-basis: 20%;">
                             <label for="">景點</label>
                             <label class="warning"></label>
                             <br>
                             <input type="hidden" class="sceneId" name="sceneId" value="">
							<input list="scene" name="sceneName" value="" type="text" class="select">
                             </div>
                             <div style="flex-basis: 20%;">
                             <label for="">行程時間</label>
                             <br>
                             <input type="datetime-local" class="time" style="width: 270px;" name="beginTime">
                             </div>
                             <div style="flex-basis: 20%;">
                                 <button type="button" class="delete other-btn" >刪除</button>
                             </div>
                         </div>
                         `
             first.insertAdjacentHTML("beforeEnd", html);
             var cancel_btn = document.querySelectorAll(".delete");
             console.log(cancel_btn)
             for (var i = 0; i < cancel_btn.length; i++) {
                 cancel_btn[i].addEventListener('click', cancel);
             }
             $(".select").on("change", binding);
         }
         add_scene_btn.addEventListener("click", add_scene);
         $("#add-data").on("click", function () {
             console.log("ho");
         })

     })
	</script>
</body>

</html>