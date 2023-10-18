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

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTripCollect.jsp'>List</a> all TripCollect.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="trip_collect.do" >  <!--  P68���T���� web.xml -->
        <b>��J�|��ID:</b>
        <input type="text" name="cus_id">
        <br>
        <b>��J��{ID:</b>
        <input type="text" name="trip_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="tripCollectSvc" scope="page" class="com.tha103.gogoyu.trip_collect.model.Trip_collectService" />
   
  <li>
     <FORM METHOD="post" ACTION="trip_collect.do" >
       <b>��ܦ�{����:</b>
       <select size="1" name="trip_collect">
         <c:forEach var="O1" items="${tripCollectSvc.all}" >  <%-- ${tripPhotoSvc.all} ���P�� <%= tripPhotoSvc.getAll() %> --%>
          <option value="${O1.cusId},${O1.tripId}">�|��ID=${O1.cusId}, ��{ID=${O1.tripId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_collect.do" >
       <b>��ܦ��îɶ�:</b>
       <select size="1" name="collect_time">
         <c:forEach var="O2" items="${tripCollectSvc.all}" > 
          <option value="${O2.cusId},${O2.tripId}">${O2.collectTime}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�s�W��{����:</h3>

<ul>
  <li><a href='addTripCollect.jsp'>Add</a> a new trip_collect.</li>
</ul>

</body>
</html>