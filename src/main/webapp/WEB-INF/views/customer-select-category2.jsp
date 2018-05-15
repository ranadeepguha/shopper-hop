<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select a category</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/category.css"/>"/>
</head>
<body>
<h1>Select a category</h1>
<br>
<c:forEach var="line" items="${categoryList}">
<span>
<form:form action="${contextPath}/customer/productselect" commandName="category" method="post">
		
		<div>
		<img src="${line.filename}" />
		 <!--<c:out value="${line.categoryName}"/> -->
   		<input type="hidden" name="category" value="${line.categoryName}"/>
   		<input type="submit" class="btn btn-info" value="Select ${line.categoryName}" />
   		</div>
   	
</form:form>
</span>
</c:forEach>
</body>
</html>