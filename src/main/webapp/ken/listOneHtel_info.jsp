<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<% //��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
	Hotel_info hotelInfo = (Hotel_info) request.getAttribute("Hotel_info");
%>
system.out.print("dddd");
<html>
<head>
<title>���u��� - listOneConsumer.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneHtel_info.jsp</h3>
		 <h4><a href="Hotel_infoselect_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		
		<th>�Ы�id</th>
		<th>�\�U</th>
		<th>�ȩЪA��</th>
		<th>24�p�ɱ����d�i</th>
		<th>SPA</th>
		<th>��������</th>
		<th>���</th>
		<th>�S�O</th>
		<th>�T�ҫȩ�</th>
		<th>�K�O�L�u����</th>
		<th>�x��</th>
		<th>���y</th>
		<th>�a��</th>
		<th>�q�ʨ��R�q��</th>
		<th>������</th>
	</tr>
	<tr>
		<td><%= hotelInfo.getHotelInfoId()%></td>
			<td><%= hotelInfo.getRestaurant()%></td>
			<td><%= hotelInfo.getRoomService()%></td>
			<td><%= hotelInfo.getAlldayCounter()%></td>
			<td><%= hotelInfo.getSpa()%></td>
			<td><%= hotelInfo.getGym()%></td>
			<td><%= hotelInfo.getGarden()%></td>  
			<td><%= hotelInfo.getTerrace()%></td>
			<td><%= hotelInfo.getNoSmoking()%></td>
			<td><%= hotelInfo.getFreewifi()%></td>
			<td><%= hotelInfo.getHeater()%></td>
			<td><%= hotelInfo.getBeach()%></td>
			<td><%= hotelInfo.getPool()%></td>
			<td><%= hotelInfo.getChargingstation()%></td>
			<td><%= hotelInfo.getParking()%></td>
		
	</tr>
</table>

</body>
</html>