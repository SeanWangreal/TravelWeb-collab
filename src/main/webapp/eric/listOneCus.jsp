<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Consumer consumer = (Consumer) request.getAttribute("consumer"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�|����� - listOnecus.jsp</title>

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
  height:200px;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|����� - ListOnecus.jsp</h3>
				<h4>
					<a href="select_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�|��ID</th>
			<th>�m�W</th>
			<th>�b��</th>
			<th>�K�X</th>
			<th>�H�c</th>
			<th>�q��</th>
			<th>��}</th>
			<th>�ʧO</th>
			<th>�Ӥ�</th>

		</tr>
		<tr>
<%-- 			<td><%=consumer.getCusId() %></td> --%>
<%-- 			<td><%=consumer.getCusName() %></td> --%>
<%-- 			<td><%=consumer.getCusAccount() %></td> --%>
<%-- 			<td><%=consumer.getCusPassword() %></td> --%>
<%-- 			<td><%=consumer.getCusMail() %></td> --%>
<%-- 			<td><%=consumer.getCusPhone() %></td> --%>
<%-- 			<td><%=consumer.getCusAddress() %></td> --%>
<%-- 			<td><%=consumer.getCusSex() %></td> --%>
			<td>${consumer.cusId}</td>
			<td>${consumer.cusName}</td>
			<td>${consumer.cusAccount}</td>
			<td>${consumer.cusPassword}</td>
			<td>${consumer.cusMail}</td>
			<td>${consumer.cusPhone}</td> 
			<td>${consumer.cusAddress}</td>
			<td>${consumer.cusSex}</td>
			<td><img src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${consumer.cusId}"></td>
		</tr>
	</table>

</body>
</html>