<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
    List<Consumer> list = cusSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllCus.jsp</title>

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
  width: 200px;
  height:200px;
}
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllCus.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員ID</th>
		<th>姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>信箱</th>
		<th>電話</th>
		<th>住址</th>
		<th>性別</th>
		<th>照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="cus" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${cus.cusId}</td>
			<td>${cus.cusName}</td>
			<td>${cus.cusAccount}</td>
			<td>${cus.cusPassword}</td>
			<td>${cus.cusMail}</td>
			<td>${cus.cusPhone}</td> 
			<td>${cus.cusAddress}</td>
			<td>${cus.cusSex}</td>
			<td><img src="PictureServlet?cus_id=${cus.cusId}"  width="200px" height="200px"></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/eric/ConsumerServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cusId"  value="${cus.cusId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/eric/ConsumerServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="cusId"  value="${cus.cusId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>