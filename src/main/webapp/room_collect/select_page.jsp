<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Room_collect: Home</title>

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
   <tr><td><h3>IBM Room_collect: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Room_collect: Home</p>

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
  <li><a href='listAllRoomCollect.jsp'>List</a> all RoomCollect.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="room_collect.do" >  <!--  P68���T���� web.xml -->
        <b>��J�|��ID:</b>
        <input type="text" name="cus_id">
        <br>
        <b>��J��{ID:</b>
        <input type="text" name="room_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="roomCollectSvc" scope="page" class="com.tha103.gogoyu.room_collect.model.Room_collectService" />
   
  <li>
     <FORM METHOD="post" ACTION="room_collect.do" >
       <b>��ܦ�{����:</b>
       <select size="1" name="room_collect">
         <c:forEach var="O1" items="${roomCollectSvc.all}" >  <%-- ${roomPhotoSvc.all} ���P�� <%= roomPhotoSvc.getAll() %> --%>
          <option value="${O1.cusId},${O1.roomId}">�|��ID=${O1.cusId}, ��{ID=${O1.roomId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="room_collect.do" >
       <b>��ܦ��îɶ�:</b>
       <select size="1" name="collect_time">
         <c:forEach var="O2" items="${roomCollectSvc.all}" > 
          <option value="${O2.cusId},${O2.roomId}">${O2.collectTime}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�s�W��{����:</h3>

<ul>
  <li><a href='addRoomCollect.jsp'>Add</a> a new room_collect.</li>
</ul>

</body>
</html>