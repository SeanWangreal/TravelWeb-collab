<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CompanyService companySvc = new CompanyService();
    List<Company> list = companySvc.getAllCompany();
    //System.out.println(list.get(0).toString());
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllConsumer.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllConsumer.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員id</th>
		<th>姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>信箱</th>
		<th>電話</th>
		<th>地址</th>
		<th>性別</th>
		<th>照片</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="company" items="${list}" >
		
		<tr>
			<td>${company.compId}</td>
<%-- 			<td>${company.compName}</td> --%>
<%-- 			<td>${company.compAccount}</td> --%>
<%-- 			<td>${company.compPassword}</td> --%>
<%-- 			<td>${company.compMail}</td> --%>
<%-- 			<td>${company.compPhone}</td>  --%>
<%-- 			<td>${company.compAddress}</td> --%>
<%-- 			<td>${company.compSex}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cusId"  value="${consumer.cusId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="cusId"  value="${consumer.cusId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>