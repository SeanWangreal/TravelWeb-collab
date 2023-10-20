<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.gogoyu.trip_photo.model.*"%>

<%//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
Trip_photo trip_photo = (Trip_photo) request.getAttribute("trip_photo");
%>
<%-- --<%= trip_photo==null %>--${trip_photo.trip_photo_id}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_Trip_photo_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_Trip_photo_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="trip_photo.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>行程照片ID:<font color=red><b>*</b></font></td>
		<td><%=trip_photo.getTripPhotoId()%></td>
	</tr>
	<tr>
		<td>行程套票ID:</td>
		<td><input type="TEXT" name="trip_id" value="<%=trip_photo.getTripId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><input type="FILE" name="photo"  size="45"/></td>
	</tr>
	

	<jsp:useBean id="tripPhotoSvc" scope="page" class="com.tha103.gogoyu.trip_photo.model.Trip_photoServiceJDBC" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="trip_photo_id" value="<%=trip_photo.getTripPhotoId()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
</script>
</html>