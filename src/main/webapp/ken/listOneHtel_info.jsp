<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
	Hotel_info hotelInfo = (Hotel_info) request.getAttribute("Hotel_info");
%>
system.out.print("dddd");
<html>
<head>
<title>員工資料 - listOneConsumer.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneHtel_info.jsp</h3>
		 <h4><a href="Hotel_infoselect_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		
		<th>房型id</th>
		<th>餐廳</th>
		<th>客房服務</th>
		<th>24小時接待櫃檯</th>
		<th>SPA</th>
		<th>健身中心</th>
		<th>花園</th>
		<th>露臺</th>
		<th>禁菸客房</th>
		<th>免費無線網路</th>
		<th>暖氣</th>
		<th>海灘</th>
		<th>泳池</th>
		<th>電動車充電站</th>
		<th>停車場</th>
	</tr>
	<tr>
		<td><%= hotelInfo.getHotelInfoId()%></td>
			<td><%= hotelInfo.getRestaurant()%></td>
			<td><%= hotelInfo.getRoomService()%></td>
			<td><%= hotelInfo.getAlldayCounter()%></td>
			<td><%= hotelInfo.getSpa()%></td>
			<td><%= hotelInfo.getGym()%></td>
			<td><%= hotelInfo.getGarden()%></td>  
			<td><%= hotelInfo.getTerrace()%></td>
			<td><%= hotelInfo.getNoSmoking()%></td>
			<td><%= hotelInfo.getFreewifi()%></td>
			<td><%= hotelInfo.getHeater()%></td>
			<td><%= hotelInfo.getBeach()%></td>
			<td><%= hotelInfo.getPool()%></td>
			<td><%= hotelInfo.getChargingstation()%></td>
			<td><%= hotelInfo.getParking()%></td>
		
	</tr>
</table>

</body>
</html>