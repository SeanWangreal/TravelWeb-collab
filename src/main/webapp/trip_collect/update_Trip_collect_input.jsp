<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.gogoyu.trip_collect.model.*"%>

<%//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
Trip_collect trip_collect = (Trip_collect) request.getAttribute("trip_collectVO");
%>
<%-- --<%= trip_photo==null %>--${trip_photo.trip_photo_id}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��{���ø�ƭק� - update_Trip_collect_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���u��ƭק� - update_Trip_collect_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="trip_collect.do" name="form1">
<table>
	<tr>
		<td>�|��ID:</td>
		<td><input type="TEXT" name="cus_id" value="<%=trip_collect.getCusId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>��{ID:</td>
		<td><input type="TEXT" name="trip_id" value="<%=trip_collect.getTripId()%>" size="45"/></td>
	</tr>
	<tr>
		<td>���îɶ�:<font color=red><b>*</b></font></td>
		<td><%=trip_collect.getCollectTime()%></td>
	</tr>

	<jsp:useBean id="tripCollectSvc" scope="page" class="com.tha103.gogoyu.trip_collect.model.Trip_collectService" />

	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="One_For_Update2" value="<%=trip_collect.getCusId()%>,<%=trip_collect.getTripId()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
</script>
</html>