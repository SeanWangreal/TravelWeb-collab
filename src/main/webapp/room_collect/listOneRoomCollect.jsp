<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.room_collect.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Room_collect room_collect = (Room_collect) request.getAttribute("room_collectVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneRoomCollect.jsp</title>

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
		 <h3>��{���ø�� - listOneRoomCollect.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|��ID</th>
		<th>��{ID</th>
		<th>���îɶ�</th>
	</tr>
	<tr>
		<td><%=room_collect.getCusId()%></td>
		<td><%=room_collect.getRoomId()%></td>
		<td><%=room_collect.getCollectTime()%></td>
	</tr>
</table>

</body>
</html>