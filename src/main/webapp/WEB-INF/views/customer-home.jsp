<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/landing.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Customer Home</title>
</head>

<body>
<h1>Welcome to the Store</h1>
<div>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<br>
<a href="${contextPath}/customer/categoryselect" class="btn btn-info" >Select a Category</a> <br />
<a href="${contextPath}/customer/viewprevious" class="btn btn-info">View Previous Orders </a> <br />
<a href="${contextPath}/customer/cart" class="btn btn-info">View Cart</a> <br />
<a href="${contextPath}/customer/wishlist" class="btn btn-info">View Wishlist</a> <br />
<a href="${contextPath}/customer/searchProducts" class="btn btn-info">Search For Products</a> <br />

</div>

</body>




</html>