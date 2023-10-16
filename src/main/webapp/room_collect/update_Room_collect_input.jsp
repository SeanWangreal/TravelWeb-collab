<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.gogoyu.room_collect.model.*"%>

<%//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
Room_collect room_collect = (Room_collect) request.getAttribute("room_collectVO");
%>
<%-- --<%= room_photo==null %>--${room_photo.room_photo_id}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程收藏資料修改 - update_Room_collect_input.jsp</title>

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
		 <h3>員工資料修改 - update_Room_collect_input.jsp</h3>
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

<FORM METHOD="post" ACTION="room_collect.do" name="form1">
<table>
	<tr>
		<td>會員ID:</td>
		<td><input type="TEXT" name="cus_id" value="<%=room_collect.getCusId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>行程ID:</td>
		<td><input type="TEXT" name="room_id" value="<%=room_collect.getRoomId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>收藏時間:<font color=red><b>*</b></font></td>
		<td><%=room_collect.getCollectTime()%></td>
	</tr>

	<jsp:useBean id="roomCollectSvc" scope="page" class="com.tha103.gogoyu.room_collect.model.Room_collectService" />

	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="One_For_Update2" value="<%=room_collect.getCusId()%>,<%=room_collect.getRoomId()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
</script>
</html>