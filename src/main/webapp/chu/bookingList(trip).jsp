<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);

Integer tripOrd = (Integer)request.getAttribute("tripOrdId");
Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();

System.out.println(TOSH.getTripOrdList(tripOrd));
pageContext.setAttribute("tripOrdList",TOSH.getTripOrdList(tripOrd));


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
    <img class="fullscreen" src="${pageContext.request.contextPath}/chu/4621.png" alt="Full Screen Image">
 
    <div class="outside_div">
        <div class="inside_left_div">
            <form action="#" method="get">
				<c:forEach var="trip"  items="${tripOrdList.keySet()}">
                <div class="order-details">
                    <br>
                    <i class="payment-title">訂單資訊[旅遊]</i>
                    <br>
                    <br>
                    <div class="order-item">
                        <label for="orderId">訂單編號:<i style="color: blue;margin-left:30px">${trip.tripOrdId}</i></label>
                    </div>
                    <hr>
                    
                    <div class="order-item">
                        <label for="tripName">行程名稱:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(0)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item" >
                     <label for="agency">套票人數:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(1)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="amount" >數量: <i style="color: blue;margin-left:30px">${trip.amount}</i></label>
                        	<i style="color: blue;margin-left:30px">
                        			<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/shopping_tripServlet" >
      										<select size="1" name="tripAmount">
       												<c:forEach begin="1" end="${tripOrdList.get(trip).get(2)}" var="i"> 
       										   				<option value="${i}">${i}
       											  	</c:forEach>  
       										</select>
									       <input type="hidden" name="action" value="countAmount">
									       <input type="hidden" name="tripOrdIdPk" value="${trip.tripOrdId}">								  
									       <input type="submit" value="送出">
     								</FORM>
                        	</i>
                        </label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="princitalName">業者聯絡人:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(4)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="princitalPhone">電話:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(5)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="startTime">行程開始時間:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(6)}</i></label>
                    </div>
                    <hr>
                    <div class="order-item">
                        <label for="endTime">行程結束時間:<i style="color: blue;margin-left:30px">${tripOrdList.get(trip).get(7)}</i></label>
                    </div>
                    <hr>
                   
     
                 
                    


                </div>

            </form>
        </div>

        <div class="inside_right_div">
        		
            
                <div class="order-item" style="width: 100%;">
<!--                     <div class="order_item_price"> -->
<!--                         <label class="price">價格:</label> -->
<%--     					 <label class="right_label2"><i>${tripOrdList.get(trip).get(8).intValue()}元</i></label>   --%>
<!--                     </div> -->
<!--                     <hr> -->
<!--                     <div class="order_item_price"> -->
<!--                         <label class="commission">稅額(10%):</label> -->
<%--                           <label class="right_label2"><i>${tripOrdList.get(trip).get(9).intValue()}元</i></label>   --%>
<!--                     </div> -->
<!--                     <hr> -->
<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
                    <div class="order_item_price">
                        <label class="totalPrice" style ="font-size :40px ">總價格:</label>
                       <label class="right_label2" style ="font-size :40px "><i>${tripOrdList.get(trip).get(8).intValue()}元</i></label>  
                    </div>
                
                </div>
			  

                
					<form action="${pageContext.request.contextPath}/shopping_tripServlet"  method="post">		
                    	 <button type="submit" class="payment-button"  style =" position : absolute ; left :10%">前往付款頁面</button>
                   		 <input type="hidden" name="action" value = "ConnectToECPAY">
						 <input type="hidden" name="tripOrdId" value = "${trip.tripOrdId}">
						 <input type="hidden" name="amount" value = "${trip.amount}">
						 <input type="hidden" name="profit" value = "${tripOrdList.get(trip).get(10).intValue()}">
						 <input type="hidden" name="commission" value = "${tripOrdList.get(trip).get(9).intValue()}">
						 <input type="hidden" name="totalPrice" value = "${tripOrdList.get(trip).get(8).intValue()}">
							<div class="order-item" style =" position : absolute ; top:80% ;right:5%">
		                        <label for="remark" >備註:</label>
		                    	<input type ="textarea"  placeholder="請輸入您要的內容"  style = "width:80%" name="remark"  required>
		                    </div>
					</form>
					<form action="${pageContext.request.contextPath}/shopping_tripServlet"  method="post"   >			
                     	 <button type="submit" class="Cancel-button" style =" position : absolute ; left :30%">取消本次交易</button>
                       	 <input type="hidden" name="action" value = "CancelTransaction">
    				</form>

           </c:forEach>

        </div>

        <script>
            
        </script>
</body>

</html>