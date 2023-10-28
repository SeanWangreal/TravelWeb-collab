<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<%@ page import="java.sql.Date"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);



Integer roomOrd = (Integer)request.getAttribute("roomOrdId");
Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
Date checkInTime = ROSH.getRoomOrd(roomOrd).getCheckInTime();
Date checkOutTime = ROSH.getRoomOrd(roomOrd).getCheckOutTime();

pageContext.setAttribute("roomOrdList",ROSH.getRoomOrdList(roomOrd , checkInTime , checkOutTime));

%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/chu_css/bookingList.css">
    <style>
        h2{
            font-size: 1px;
        }
    </style>
</head>
<!-- <h1 class="payment-title">交易明細</h1> -->
<body>
    <!-- 背景衝浪圖 -->
    <img class="fullscreen" src="${pageContext.request.contextPath}/chu/bed.jpg" alt="Full Screen Image">

    <div class="outside_div">
        <div class="inside_left_div">
        <c:forEach var="room"  items="${roomOrdList.keySet()}">
        	<div class="order-details" >
       				<br>
                    <i class="payment-title" style = "margin-top : 100px;">訂單資訊[飯店]</i>
                  
                
                     <br>
                    <br>
                    <div class="order-item"  style = "margin-left : 10px;">
                        <label for="orderId">訂單編號:<i style="color: blue;margin-left:30px">${room.roomOrdId}</i></label>
                    </div>
                    <hr>
                    
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="hotel-name">飯店名稱:<i style="color: blue;margin-left:30px">${roomOrdList.get(room).get(0)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="ordTime">房型名稱:<i style="color: blue;margin-left:30px">${roomOrdList.get(room).get(1)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="agency">房型:<i style="color: blue;margin-left:30px">${roomOrdList.get(room).get(2)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="amount" style ="margin-left:10px">房數: <i style="color: blue;margin-left:20px">${room.amount}</i></label>
                        	<i style="position : absolute ; left :20%;">
                        			<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/shopping_hotelServlet" >
      										<select size="1" name="roomAmount" >
       												<c:forEach begin="1" end="${roomOrdList.get(room).get(8)}" var="i"> 
       										   				<option value="${i}">${i}
       											  	</c:forEach>  
       										</select>
									       <input type="hidden" name="action" value="countAmount">
									       <input type="hidden" name="roomOrdIdPk" value="${room.roomOrdId}">								  
									       <input type="submit" value="送出">
     								</FORM>
                        	</i>
                        </label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="principalName">業者聯絡人:<i style="color: blue;margin-left:30px">${roomOrdList.get(room).get(3)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="principalPhone">電話:<i style="color: blue;margin-left:30px">${roomOrdList.get(room).get(4)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="checkInTime">入住時間:<i style="color: blue;margin-left:30px">${room.checkInTime}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="checkOutTime">退房時間:<i style="color: blue;margin-left:30px">${room.checkOutTime}</i></label>
                    </div>
                   
                     
                    <hr>

                </div>

            
        </div>

        <div class="inside_right_div">
        		
            
                <div class="order-item" style="width: 100%;">
<!--                     <div class="order_item_price"> -->
<!--                         <label class="price">價格:</label> -->
<%--                         <label class="right_label2"><i>${roomOrdList.get(room).get(5).intValue()}元</i></label>   --%>
<!--                     </div> -->
<!--                     <hr> -->
<!--                     <div class="order_item_price"> -->
<!--                         <label class="commission">稅額(10%):</label> -->
<%--                         <label class="right_label2"><i>${roomOrdList.get(room).get(6).intValue()}元</i></label>   --%>
<!--                     </div> -->
<!--                     <hr> -->
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
                    <div class="order_item_price"   >
                        <label class="totalPrice" style ="font-size :40px ">總價格:</label>
                        <label class="right_label2" style ="font-size :40px"><i>${roomOrdList.get(room).get(5).intValue()}元</i></label>  
                    </div>
                   
                </div>
			  

                
                 	<form action="${pageContext.request.contextPath}/shopping_hotelServlet"  method="post">			
                		 <button type="submit" class="payment-button"   style =" position : absolute ; left :10% ;right:5%">前往付款頁面</button>
                   		 <input type="hidden" name="action" value = "ConnectToECPAY">
                   		 <input type="hidden" name="roomOrdId" value = "${roomOrdId}">
                    	 <input type="hidden" name="amount" value = "${room.amount}">
						 <input type="hidden" name="profit" value = "${roomOrdList.get(room).get(7).intValue()}">
						 <input type="hidden" name="commission" value = "${roomOrdList.get(room).get(6).intValue()}">
						 <input type="hidden" name="totalPrice" value = "${roomOrdList.get(room).get(5).intValue()}">
							<div class="order-item" style =" position : absolute ; top:80% ;right:5%">
		                        <label for="remark" >備註:</label>
		                    	<input type ="textarea"  placeholder="請輸入您要的內容"  style = "width:80%" name="remark"  required>
		                    </div>
					</form>
					<form action="${pageContext.request.contextPath}/shopping_hotelServlet"  method="post"   >			
                     	 <button type="submit" class="Cancel-button" style =" position : absolute ; left :30% ;right:5%">取消本次交易</button>
                       	 <input type="hidden" name="action" value = "CancelTransaction">
    				</form>

           
				</c:forEach>
				
        </div>

        <script>
            
        </script>
</body>

</html>