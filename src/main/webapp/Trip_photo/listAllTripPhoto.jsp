<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.trip_photo.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
    List<Trip_photo> list = tripPhotoSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllTripPhoto.jsp</title>

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
  img{
	max-width: 50%;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllTripPhoto.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��{�Ӥ�ID</th>
		<th>��{�M��ID</th>
		<th>�Ӥ�</th>
		<th>�W�Ǯɶ�</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="Trip_photo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${Trip_photo.tripPhotoId}</td>
			<td>${Trip_photo.tripId}</td>
			<td><img src="${pageContext.request.contextPath}/pictureServlet?trip_photo_id=${Trip_photo.tripPhotoId}" ></td>
			<td>${Trip_photo.uploadTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Trip_photo/trip_photo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="trip_photo_id"  value="${Trip_photo.tripPhotoId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Trip_photo/trip_photo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_photo_id"  value="${Trip_photo.tripPhotoId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>