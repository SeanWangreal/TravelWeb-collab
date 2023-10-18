<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
    List<Hotel_info> list = hotelInfoSvc.getAllHotel_info();
    //System.out.println(list.get(0).toString());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllHotelinfo.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllConsumer.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員id</th>
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
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="hotelInfo" items="${list}" >
		
		<tr>
			<td>${hotelInfo.hotelInfoId}</td>
			<td>${hotelInfo.restaurant}</td>
			<td>${hotelInfo.roomService}</td>
			<td>${hotelInfo.alldayCounter}</td>
			<td>${hotelInfo.spa}</td>
			<td>${hotelInfo.gym}</td>
			<td>${hotelInfo.garden}</td>  
			<td>${hotelInfo.terrace}</td>
			<td>${hotelInfo.noSmoking}</td>
			<td>${hotelInfo.freewifi}</td>
			<td>${hotelInfo.heater}</td>
			<td>${hotelInfo.beach}</td>
			<td>${hotelInfo.pool}</td>
			<td>${hotelInfo.chargingstation}</td>
			<td>${hotelInfo.parking}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="hotelInfoId"  value="${hotelInfo.hotelInfoId}">
			     <input type="hidden" name="action"	value="getOneUpdate"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="hotelInfoId"  value="${hotelInfo.hotelInfoId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>