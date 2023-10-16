<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.adm_meb.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Adm_meb empVO = (Adm_meb) request.getAttribute("admVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂單資料 - listOneAdm_meb.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂單資料 - ListOneTrip_ord.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理員ID</th>
		<th>管理員名稱</th>
		<th>管理員帳號</th>
		<th>管理員密碼</th>
	</tr>
	<tr>
		<td><%=empVO.getAdmId()%></td>
		<td><%=empVO.getAdmName()%></td>
		<td><%=empVO.getAdmAccount()%></td>
		<td><%=empVO.getAdmPassword()%></td>
	</tr>
</table>

</body>
</html>