<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Trip_collect: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM trip_collect: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM trip_collect: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTripCollect.jsp'>List</a> all TripCollect.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="trip_collect.do" >  <!--  P68明確對應 web.xml -->
        <b>輸入會員ID:</b>
        <input type="text" name="cus_id">
        <br>
        <b>輸入行程ID:</b>
        <input type="text" name="trip_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="tripCollectSvc" scope="page" class="com.tha103.gogoyu.trip_collect.model.Trip_collectService" />
   
  <li>
     <FORM METHOD="post" ACTION="trip_collect.do" >
       <b>選擇行程收藏:</b>
       <select size="1" name="trip_collect">
         <c:forEach var="O1" items="${tripCollectSvc.all}" >  <%-- ${tripPhotoSvc.all} 等同於 <%= tripPhotoSvc.getAll() %> --%>
          <option value="${O1.cusId},${O1.tripId}">會員ID=${O1.cusId}, 行程ID=${O1.tripId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_collect.do" >
       <b>選擇收藏時間:</b>
       <select size="1" name="collect_time">
         <c:forEach var="O2" items="${tripCollectSvc.all}" > 
          <option value="${O2.cusId},${O2.tripId}">${O2.collectTime}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增行程收藏:</h3>

<ul>
  <li><a href='addTripCollect.jsp'>Add</a> a new trip_collect.</li>
</ul>

</body>
</html>