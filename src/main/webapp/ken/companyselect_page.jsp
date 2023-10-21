<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Company: Home</title>

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
   <tr><td><h3>IBM Consumer: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Consumer: Home</p>

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
  <li><a href='listAllConsumer.jsp'>List</a> all company.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="CompanyServlet" >
        <b>��J�|���s�� (�p7001):</b>
        <input type="text" name="cusId">
        <input type="hidden" name="action" value="getOne">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="companySvc" scope="page" class="com.tha103.gogoyu.company.model.CompanyService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/CompanyServlet" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="compId">
         <c:forEach var="company" items="${companySvc.allCompany}" > 
          <option value="${company.compId}">${company.compId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���m�W:</b> -->
<!--        <select size="1" name="name"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���b��:</b> -->
<!--        <select size="1" name="account"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusAccount} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���K�X:</b> -->
<!--        <select size="1" name="password"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusPassword} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���H�c:</b> -->
<!--        <select size="1" name="mail"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusMail} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���a�}:</b> -->
<!--        <select size="1" name="address"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusAddress} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���q��:</b> -->
<!--        <select size="1" name="phone"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusPhone} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܷ|���ʧO:</b> -->
<!--        <select size="1" name="sex"> -->
<%--          <c:forEach var="ConsumerVO" items="${consumerSvc.all}" >  --%>
<%--           <option value="${consumer.cusId}">${consumer.cusSex} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
  
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addcompany.jsp'>Add</a> a new Consumer.</li>
</ul>

</body>
</html>