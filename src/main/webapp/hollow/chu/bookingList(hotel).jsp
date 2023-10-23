<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/chu_css/bookingList(hotel).css">

</head>
<!-- <h1 class="payment-title">交易明細</h1> -->
<body>
    <!-- 背景衝浪圖 -->
    <img class="fullscreen" src="./4621.png" alt="Full Screen Image">

    <div class="outside_div">
        <div class="inside_left_div">
            <form action="#" method="get">

                <div class="order-details">
                    <br>
                    <i class="payment-title">訂單資訊[飯店]</i>
                    
                    <br>
                    <div class="order-item" >
                        <label for="orderId">訂單編號:&nbsp&nbsp&nbsp&nbsp<label style="color: blue;">123</label></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="cusId">會員編號:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="hotel-name">飯店名稱:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="agency">房型:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="contact">房數:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="principalName">業者聯絡人:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="principalPhone">電話:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="checkInTime">入住時間:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="checkOutTime">退房時間:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                     <div class="order-item">
                        <label for="ordTime">下單時間:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="remark">備註:&nbsp&nbsp&nbsp&nbsp</label>
                    </div>
                   

                </div>

            </form>
        </div>

        <div class="inside_right_div">
            
                <div class="order-item" style="width: 100%;">
                    <div class="order_item_price">
                        <label class="price">價格:</label>
                        <label class="right_label2">280000<label>元</label></label>  
                    </div>
                    <hr>
                    <div class="order_item_price">
                        <label class="commission">稅額(10%):</label>
                        <label class="right_label2">28000<label>元</label></label>  
                    </div>
                    <hr>
                    <div class="order_item_price">
                        <label class="totalPrice">總價格:</label>
                        <label class="right_label2">308000<label>元</label></label>  
                    </div>
                    <hr>
                </div>

                <span class="pay_button">
                    
                    <button type="submit" class="payment-button">前往付款頁面</button>
                    <button type="submit" class="payment-button">取消本次交易</button>
    
                </span>
           
            
        </div>

        <script>
            
        </script>
</body>

</html>