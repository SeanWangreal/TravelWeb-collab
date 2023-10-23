<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Adm: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/hollow/listAllAdm.jsp'>List</a> all Adm.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/hollow/AdmServlet" >
        <b>輸入訂單編號 (如7001):</b>
        <input type="text" name="admId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.tha103.gogoyu.adm_meb.model.Adm_mebService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/hollow/AdmServlet" >
       <b>選擇管理員編號:</b>
       <select size="1" name="admId">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.admId}">${empVO.admId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/hollow/AdmServlet" >
       <b>選擇管理員名稱:</b>
       <select size="1" name="admId">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.admId}">${empVO.admName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/hollow/addAdm.jsp'>Add</a> a new Adm.</li>
</ul>

</body>
</html>