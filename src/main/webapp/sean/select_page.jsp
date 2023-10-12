<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Room</title>

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
   <tr><td><h3>Room</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Room</p>

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
  <li><a href='${pageContext.request.contextPath}/sean/hotel_room_all.jsp'>List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/sean/RoomServlet" >
        <b>(ONE)輸入業者編號 (如1):</b>
        <input type="text" name="compId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="roomSvc" scope="page" class="com.tha103.gogoyu.room.model.RoomServiceHibernate" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/sean/RoomServlet" >
       <b>(ALL)選擇業者編號:</b>
       <select size="1" name="compId">
         <c:forEach var="room" items="${roomSvc.all}"> 
          <option value="${room.compId}">${room.compId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getAllRoom">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/sean/RoomServlet" >
       <b>選擇房間名稱:</b>
       <select size="1" name="roomId">
         <c:forEach var="room" items="${roomSvc.all}" > 
          <option value="${room.roomId}">${room.roomName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增房型</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/sean/RoomServlet?action=add'>Add</a> a new Room.</li>
</ul>

</body>
</html>