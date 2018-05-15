<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Details</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/TablePageStyling.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h2>View Your Orders</h2>
    

    
    <c:set var="pList" value="${orderDetails}"/>
	<table class="table table-striped">
    <thead>
    <tr>
    <th>Product Name</th>
    <th>Product Price</th>
    <th>Product Image</th>
    <th>Quantity</th>
    </tr>
    </thead>
    
    
    
    <c:forEach items="${pList}" var="orderDetail">
  	<tbody>
    <tr> 
	    <td> <c:out value="${orderDetail.product.productName}"></c:out></td>
     <td> <c:out value="${orderDetail.productPrice}"></c:out></td>
   	 <td><img class="img-rounded"  height="150" width="150" src="${orderDetail.product.filename}" /></td>
     <td> <c:out value="${orderDetail.productQty}"></c:out></td>
   	
     </tr>
    </tbody>
   	
    </c:forEach>
    </table>
    
    
   
    
    
</body>


</html>