<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>

</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h2>Registered Employees</h2>
    

<div>    
    <c:set var="pList" value="${requestScope.userList}"/>
	<table class="table table-striped">
    
    <tr>
    <th>User Name</th>
    <th>User Role</th>
    <th>User Email</th>
    <th>Action</th>
    </tr>

    
    	
    
    <c:forEach items="${pList}" var="user">
    <form:form action="${contextPath}/admin/userdelete" commandName="user"
		method="post">
    <tr> 
    <td> <c:out value="${user.username}"></c:out></td>
    <td> <c:out value="${user.role}"></c:out></td>
    <td><c:out  value="${user.email}"></c:out></td>
    <td><input type="submit" name="action" value="Delete" class="btn btn-info"/></td>
    </tr>
    
    <input type="hidden" name="userId" value="${user.personId}">
   
    </form:form>
    </c:forEach>
    </table>
</div>
    
   
    
    
</body>


</html>