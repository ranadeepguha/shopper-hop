<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/landing.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<h1>HELLO ADMIN</h1>

<div>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<a href="${contextPath}/admin/add" class="btn btn-info">Add Employee</a> <br />
<br>
<a href="${contextPath}/admin/delete/employee" class="btn btn-info">Delete Users</a> <br />

</div>
</body>
</html>