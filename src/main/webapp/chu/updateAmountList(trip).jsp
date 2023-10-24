<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/chu_css/bookingCheckedList(trip).css">

</head>
<!-- <h1 class="payment-title">交易明細</h1> -->
<body>
	<!-- 背景衝浪圖 -->
	<img class="fullscreen"
		src="${pageContext.request.contextPath}/chu/4621.png"
		alt="Full Screen Image">

	<div class="outside_div">
		<div class="inside_left_div">
			



				<div class="order-details" style = "margin-left:10px">
					<br> <i class="payment-title">訂單資訊[行程]</i> 
					<br>
					<br><br>
					<div class="order-item">
						<label for="orderId">訂單編號: </label>
					</div>
					<hr>
					<div class="order-item">
						<label for="cusId">會員編號:&nbsp&nbsp&nbsp&nbsp</label>
					</div>
					<hr>
					<div class="order-item">
						<label for="hotel-name">行程名稱:&nbsp&nbsp&nbsp&nbsp</label>
					</div>
					<hr>
					<div class="order-item">
						<label for="agency">數量:</label> <select size="1" name="amount">
							<c:forEach var="Scene" items="豪小五人行">
								<option value="豪小五人行">豪小五人行
							</c:forEach>
						</select> <input type="hidden" name="action" value="amount">
					</div>
					<hr>
					<div class="order-item">
						<label for="principalName">業者聯絡人:</label>
					</div>
					<hr>
					<div class="order-item">
						<label for="principalPhone">電話:</label>
					</div>
					<hr>
					<div class="order-item">
						<label for="checkInTime">行程開始時間: </label>
					</div>
					<hr>
					<div class="order-item">
						<label for="checkOutTime">行程結束時間:</label>
					</div>
					<hr>
					<div class="order-item">
						<label for="ordTime">下單時間:</label>
					</div>
					<hr>

					<div class="checkedBtn">
						<FORM METHOD="post" ACTION="/shopping_hotelServlet">
							<button type="submit">確認更新</button>
							<input type="hidden" name="action" value="hotelConfirmUpdate">
						</FORM>
						<FORM METHOD="post" ACTION="/shopping_hotelServlet">
							<button type="submit">取消更新</button>
							<input type="hidden" name="action" value="tripCancelUpdate">
						</FORM>
					</div>

				</div>

		


			<script>
				
			</script>
</body>

</html>