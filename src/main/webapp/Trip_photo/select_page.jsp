<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Trip_photo: Home</title>

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
   <tr><td><h3>IBM trip_photo: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM trip_photo: Home</p>

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
  <li><a href='listAllTripPhoto.jsp'>List</a> all TripPhotos.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="trip_photo.do" >  <!--  P68���T���� web.xml -->
        <b>��J��{�Ӥ�ID:</b>
        <input type="text" name="trip_photo_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="tripPhotoSvc" scope="page" class="com.tha103.gogoyu.trip_photo.model.Trip_photoServiceJDBC" />
   
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>��ܦ�{�Ӥ�ID:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" >  <%-- ${tripPhotoSvc.all} ���P�� <%= tripPhotoSvc.getAll() %> --%>
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripPhotoId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>��ܦ�{�M��ID:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" >  <%-- ${tripPhotoSvc.all} ���P�� <%= tripPhotoSvc.getAll() %> --%>
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripPhotoId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>��ܷӤ�:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" > 
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>��ܤW�Ǯɶ�:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" > 
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.uploadTime}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�s�W��{�Ӥ�:</h3>

<ul>
  <li><a href='addTripPhoto.jsp'>Add</a> a new Trip_photo.</li>
</ul>

</body>
</html>