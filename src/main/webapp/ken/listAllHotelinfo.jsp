<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
    List<Hotel_info> list = hotelInfoSvc.getAllHotel_info();
    //System.out.println(list.get(0).toString());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllHotelinfo.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllConsumer.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|��id</th>
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
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="hotelInfo" items="${list}" >
		
		<tr>
			<td>${hotelInfo.hotelInfoId}</td>
			<td>${hotelInfo.restaurant}</td>
			<td>${hotelInfo.roomService}</td>
			<td>${hotelInfo.alldayCounter}</td>
			<td>${hotelInfo.spa}</td>
			<td>${hotelInfo.gym}</td>
			<td>${hotelInfo.garden}</td>  
			<td>${hotelInfo.terrace}</td>
			<td>${hotelInfo.noSmoking}</td>
			<td>${hotelInfo.freewifi}</td>
			<td>${hotelInfo.heater}</td>
			<td>${hotelInfo.beach}</td>
			<td>${hotelInfo.pool}</td>
			<td>${hotelInfo.chargingstation}</td>
			<td>${hotelInfo.parking}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="hotelInfoId"  value="${hotelInfo.hotelInfoId}">
			     <input type="hidden" name="action"	value="getOneUpdate"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="hotelInfoId"  value="${hotelInfo.hotelInfoId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>