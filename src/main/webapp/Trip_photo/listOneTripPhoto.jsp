<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.trip_photo.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Trip_photo trip_photo = (Trip_photo) request.getAttribute("trip_photo"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneTripPhoto.jsp</title>

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
  img{
	max-width: 50%;
  }
</style>

<style>
  table {
	width: 600px;
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
  	width: 200px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneTripPhoto.jsp</h3>
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
	<tr>
		<td><%=trip_photo.getTripPhotoId()%></td>
		<td><%=trip_photo.getTripId()%></td>
		<td><img src="${pageContext.request.contextPath}/pictureServlet?trip_photo_id=<%=trip_photo.getTripPhotoId()%>" ></td>
		<td><%=trip_photo.getUploadTime()%></td>

	</tr>
</table>

</body>
</html>