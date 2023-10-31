<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>Consumer</title>

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
   <tr><td><h3>Consumer</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Consumer: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/eric/listAllCus.jsp'>List</a> all Cus.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/eric/ConsumerServlet" >
        <b>��J�|���s�� (�p7001):</b>
        <input type="text" name="cusId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="cusSvc" scope="page" class="com.tha103.gogoyu.consumer.model.ConsumerServiceHibernate" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/eric/ConsumerServlet" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="cusId">
         <c:forEach var="cus" items="${cusSvc.all}" > 
          <option value="${cus.cusId}">${cus.cusId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/eric/ConsumerServlet" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="cusName">
         <c:forEach var="cus" items="${cusSvc.all}" > 
          <option value="${cusVO.cusName}">${cusVO.cusName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�|���޲z</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/eric/signup_info.jsp'>Add</a> a new Cus.</li>
</ul>

</body>
</html>