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
        
        
        
       				<br>
                    <i class="payment-title" style = "margin-top : 100px;">訂單資訊[飯店]</i>
                  
                <div class="order-details" >
                     
                    <br>
                    <div class="order-item"  style = "margin-left : 10px;">
                        <label for="orderId">訂單編號:<i style="color: blue;margin-left:30px">${RoomOrd.roomOrdId}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="cusId">會員編號:<i style="color: blue;margin-left:30px">${RoomOrd.cusId}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="hotel-name">飯店名稱:<i style="color: blue;margin-left:30px">${RoomOrd.compName}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="ordTime">房型名稱:<i style="color: blue;margin-left:30px">${RoomOrd.roomName}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="agency">房型:<i style="color: blue;margin-left:30px">${RoomOrd.roomType}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="contact">房數:<i style="color: blue;margin-left:30px">${RoomOrd.amount}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="principalName">業者聯絡人:<i style="color: blue;margin-left:30px">${RoomOrd.principalName}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="principalPhone">電話:<i style="color: blue;margin-left:30px">${RoomOrd.principalPhone}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="checkInTime">入住時間:<i style="color: blue;margin-left:30px">${RoomOrd.startTime}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="checkOutTime">退房時間:<i style="color: blue;margin-left:30px">${RoomOrd.endTime}</i></label>
                    </div>
                   
                     
                    <hr>
<!--                     結帳後才會存入"備註" -->
                    <div class="order-item" style = "margin-left : 10px;">
                        <label for="remark">備註:</label>
                    	<input type ="textarea"  placeholder="請輸入您要的內容"  style = "width:80%">
                       <input type="hidden" name="remark" > 
                    </div>
                   

                </div>

            
        </div>

        <div class="inside_right_div">
        		
            
                <div class="order-item" style="width: 100%;">
                    <div class="order_item_price">
                        <label class="price">價格:</label>
                        <label class="right_label2"><i>${RoomOrd.profit}元</i></label>  
                    </div>
                    <hr>
                    <div class="order_item_price">
                        <label class="commission">稅額(10%):</label>
                        <label class="right_label2"><i>${RoomOrd.commission}元</i></label>  
                    </div>
                    <hr>
                    <div class="order_item_price">
                        <label class="totalPrice">總價格:</label>
                        <label class="right_label2"><i>${RoomOrd.totalPrice}元</i></label>  
                    </div>
                    <hr>
                </div>
			  
<!--                 <div class="pay_button" > -->
                
                 	<form action="${pageContext.request.contextPath}/shopping_hotelServlet"  method="post">			
                    	 <button type="submit" class="payment-button"  style ="margin-top:20px">前往付款頁面</button>
                   		 <input type="hidden" name="action" value = "ConnectToECPAY">
					</form>
					<form action="${pageContext.request.contextPath}/shopping_hotelServlet"  method="post"   >			
                     	 <button type="submit" class="Cancel-button" >取消本次交易</button>
                       	 <input type="hidden" name="action" value = "CancelTransaction">
    				</form>
<!--                 </div> -->
           

        </div>

        <script>
            
        </script>
</body>

</html>